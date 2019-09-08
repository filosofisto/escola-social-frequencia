package br.gov.df.escolasocial.repository.source;

import br.gov.df.escolasocial.domain.QuadroDespesa;
import br.gov.df.escolasocial.util.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class QuadroDespesaRepository {

    private static final Logger logger = LoggerFactory.getLogger(QuadroDespesaRepository.class);

    private static final String[] MESES = {
            "janeiro", "fevereiro", "março", "abril", "maio", "junho",
            "julho", "agosto", "setembro", "outubro", "novembro", "dezembro"
    };

    private static final Stream<String> MESES_STREAM = Arrays.stream(MESES);

    public List<QuadroDespesa> list(String filename) throws IOException {
        List<QuadroDespesa> list = new ArrayList<>();

        FileInputStream inputStream = new FileInputStream(new File(filename));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowsIt = sheet.iterator();

        while (rowsIt.hasNext()) {
            Row row = rowsIt.next();

            String month = firstCellIsProcessable(row);
            if (month != null) {
                loadMonth(month, rowsIt, list);
            }
        }

        //workbook.close();
        inputStream.close();

        return list;
    }

    private void loadMonth(String month, Iterator<Row> rowsIt, List<QuadroDespesa> list) {
        QuadroDespesa quadroDespesa;
        Row row = rowsIt.next();
        while (rowsIt.hasNext()) {
            String semana = row.getCell(1).getStringCellValue();
            if (finishedMonth(semana)) {
                break;
            }

            quadroDespesa = new QuadroDespesa();

            quadroDespesa.setAno(DateUtil.actualYear());
            quadroDespesa.setMes(monthAsInt(month));
            quadroDespesa.setSemana(Integer.parseInt(semana.substring(0,1)));

            quadroDespesa.setLei(new BigDecimal(row.getCell(2).getNumericCellValue()));
            quadroDespesa.setAlteracaoLei(new BigDecimal(row.getCell(3).getNumericCellValue()));
            quadroDespesa.setPercentualLei((float) row.getCell(4).getNumericCellValue());
            quadroDespesa.setContigenciado(new BigDecimal(row.getCell(5).getNumericCellValue()));
            quadroDespesa.setPercentualContingenciado((float) row.getCell(6).getNumericCellValue());
            quadroDespesa.setCota(new BigDecimal(row.getCell(7).getNumericCellValue()));
            quadroDespesa.setPercentualCota((float) row.getCell(8).getNumericCellValue());
            quadroDespesa.setBloqueado(new BigDecimal(row.getCell(9).getNumericCellValue()));
            quadroDespesa.setDespesaAutorizada(new BigDecimal(row.getCell(10).getNumericCellValue()));
            quadroDespesa.setEmpenhado(new BigDecimal(row.getCell(11).getNumericCellValue()));
            quadroDespesa.setDisponivel(new BigDecimal(row.getCell(12).getNumericCellValue()));
            quadroDespesa.setPercentualDisponivel((float) row.getCell(13).getNumericCellValue());
            quadroDespesa.setLiquidado(new BigDecimal(row.getCell(14).getNumericCellValue()));
            quadroDespesa.setPercentualLiquidado((float) row.getCell(15).getNumericCellValue());

            logger.info(quadroDespesa.toString());

            list.add(quadroDespesa);

            row = rowsIt.next();
        }
    }

    private int monthAsInt(String month) {
        switch (month.toLowerCase()) {
            case "janeiro": return 1;
            case "fevereiro": return 2;
            case "março": return 3;
            case "abril": return 4;
            case "maio": return 5;
            case "junho": return 6;
            case "julho": return 7;
            case "agosto": return 8;
            case "setembro": return 9;
            case "outubro": return 10;
            case "novembro": return 11;
            case "dezembro": return 12;
            default:
                throw new RuntimeException("Mês inválido");
        }
    }

    private boolean finishedMonth(String semana) {
        return semana.equals("Subtotal");
    }

    private String firstCellIsProcessable(Row row) {
        String value = row.getCell(0).getStringCellValue().toLowerCase();

        for (String mes: MESES) {
            if (value.equalsIgnoreCase(mes)) {
                return mes;
            }
        }

        return null;
    }

}
