package br.gov.df.escolasocial.service;

import br.gov.df.escolasocial.domain.Assiduidade;
import br.gov.df.escolasocial.exception.InvalidFrequencyData;
import br.gov.df.escolasocial.repository.AssiduidadeRepository;
import br.gov.df.escolasocial.util.FileHelper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AssiduidadeService {

    private static final Logger logger = LoggerFactory.getLogger(AssiduidadeService.class);
    public static final int START_DATE_COLUMN = 3;
    public static final int DATE_ROW = 7;
    public static final int START_ROW_DATA = 8;

    private final TransactionTemplate transactionTemplate;

    private final AssiduidadeRepository assiduidadeRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    @Autowired
    public AssiduidadeService(PlatformTransactionManager platformTransactionManager,
                              AssiduidadeRepository assiduidadeRepository) {
        this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
        this.assiduidadeRepository = assiduidadeRepository;
    }

    public void importSheet(String filename) throws IOException, InvalidFrequencyData {
        if (!FileHelper.fileExists(filename)) {
            throw new FileNotFoundException(filename);
        }

        FileInputStream inputStream = new FileInputStream(new File(filename));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(3);

        logger.info("Obtendo informações de datas");
        DateCellPositionContainer dateCellPositionContainer = getDatePositions(sheet);
        logger.info("Datas a serem processadas: {}", dateCellPositionContainer);

        logger.info("Processando informações de frequencia");
        List<Assiduidade> assiduidadeList = createAssiduidadeList(sheet, dateCellPositionContainer);
        logger.info("Quantidade de registros de frequencia calculados: {}", assiduidadeList.size());

        inputStream.close();

        logger.info("Atualizando banco de dados");
        dataImportTransaction(assiduidadeList);
        logger.info("Banco de dados atualizado com sucesso");
    }

    private List<Assiduidade> createAssiduidadeList(XSSFSheet sheet, DateCellPositionContainer dateCellPositionContainer)
            throws InvalidFrequencyData {
        long matricula;
        List<Assiduidade> assiduidadeList = new ArrayList<>();
        Date today = new Date();

        for (int i = START_ROW_DATA; i < sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0);
            if (!isValidCell(cell)) {
                break;
            }

            matricula = new Double(sheet.getRow(i).getCell(1).getNumericCellValue()).intValue();
            if (isMatriculaValida(matricula)) {
                logger.info("Processando matricula {}", matricula);

                for (DateCellPosition dateCellPosition: dateCellPositionContainer.list()) {
                    int manha = getCellNumberOrString(row.getCell(dateCellPosition.getColumn()-1));
                    int tarde = getCellNumberOrString(row.getCell(dateCellPosition.getColumn()-1));

                    if (!isValidCellFrequency(manha, tarde)) {
                        throw new InvalidFrequencyData(i, dateCellPosition);
                    }

                    Assiduidade assiduidade = createAssiduidade(matricula, today, dateCellPosition, manha, tarde);
                    assiduidadeList.add(assiduidade);
                }
            }
        }

        return assiduidadeList;
    }

    private int getCellNumberOrString(Cell cell) {
        try {
            return new Double(cell.getNumericCellValue()).intValue();
        } catch (Exception e) {
            return Integer.parseInt(cell.getStringCellValue());
        }
    }

    private Assiduidade createAssiduidade(long matricula, Date today, DateCellPosition dateCellPosition, int manha, int tarde) {
        Assiduidade assiduidade = new Assiduidade();
        assiduidade.setData(dateCellPosition.getDate());
        assiduidade.setDataRegistro(today);
        assiduidade.setMatricula(matricula);
        assiduidade.setUsuario("system");
        assiduidade.setMotivo(
                manha == 0 || tarde == 0
                        ? "-----------------------------------"
                        : manha == 2 || tarde == 2
                            ? "OUTROS"
                            : ""
        );

        return assiduidade;
    }

    private boolean isValidCellFrequency(int manha, int tarde) {
        return (manha == 0 || manha == 1 || manha == 2)
                && (tarde == 0 || tarde == 1 || tarde == 2);
    }

    private DateCellPositionContainer getDatePositions(XSSFSheet sheet) {
        DateCellPositionContainer dateCellPositionContainer = new DateCellPositionContainer();
        int col = START_DATE_COLUMN;
        Row rowDates = sheet.getRow(DATE_ROW);
        for(;;) {
            Cell cellDate = rowDates.getCell(col++);
            if (isDate(cellDate)) {
                dateCellPositionContainer.add(cellDate.getDateCellValue(), col);
            } else if (isFinishedDateColumn(cellDate)) {
                break;
            }
        }
        return dateCellPositionContainer;
    }

    private boolean isFinishedDateColumn(Cell cellDate) {
        return "Total de faltas".equalsIgnoreCase(cellDate.getStringCellValue())
                || "faltas justificadas".equalsIgnoreCase(cellDate.getStringCellValue())
                || "comparecimentos".equalsIgnoreCase(cellDate.getStringCellValue())
                || "Informações para preenchimento:".equalsIgnoreCase(cellDate.getStringCellValue());
    }

    private boolean isDate(Cell cellDate) {
        try {
            return cellDate.getDateCellValue() != null;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isMatriculaValida(long matricula) {
        return matricula > 0;
    }

    private boolean isValidCell(Cell cell) {
        return cell != null;
    }

    private void dataImportTransaction(List<Assiduidade> assiduidadeList) {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                dataImport(assiduidadeList);

                return null;
            }
        });
    }

    private void dataImport(List<Assiduidade> assiduidadeList) {
        assiduidadeList.forEach(assiduidade -> {
            boolean exists = assiduidadeRepository.existByMatriculaAndDate(
                    assiduidade.getMatricula(), assiduidade.getData()
            );
            if (exists) {
                logger.warn("Ja existe registro referente a matricula {} e data {}",
                        assiduidade.getMatricula(), dateFormat.format(assiduidade.getData())
                );
            } else {
                assiduidadeRepository.create(assiduidade);
                logger.info("Registro incluido para a matricula {} e data {}",
                        assiduidade.getMatricula(), dateFormat.format(assiduidade.getData()));
            }
        });
    }
}
