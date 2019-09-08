package br.gov.df.escolasocial.service;

import br.gov.df.escolasocial.domain.dest.Absenteismo;
import br.gov.df.escolasocial.domain.dest.AssiduidadeDest;
import br.gov.df.escolasocial.domain.source.Assiduidade;
import br.gov.df.escolasocial.repository.dest.AbsenteismoRepository;
import br.gov.df.escolasocial.repository.dest.AssiduidadeDestRepository;
import br.gov.df.escolasocial.repository.source.AssiduidadeRepository;
import br.gov.df.escolasocial.util.DateUtil;
import br.gov.df.escolasocial.util.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AssiduidadeService {

    private static final Logger logger = LoggerFactory.getLogger(AssiduidadeService.class);

    private final TransactionTemplate transactionTemplate;

    private final AssiduidadeRepository assiduidadeRepository;

    private final AssiduidadeDestRepository assiduidadeDestRepository;

    private final AbsenteismoRepository absenteismoRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Map<YearMonth, Integer> mapMatriculadosAnoMes;

    @Autowired
    public AssiduidadeService(PlatformTransactionManager platformTransactionManager,
                              AssiduidadeRepository assiduidadeRepository,
                              AssiduidadeDestRepository assiduidadeDestRepository,
                              AbsenteismoRepository absenteismoRepository) {
        this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
        this.assiduidadeRepository = assiduidadeRepository;
        this.assiduidadeDestRepository = assiduidadeDestRepository;
        this.absenteismoRepository = absenteismoRepository;
        this.mapMatriculadosAnoMes = new HashMap<>();
    }

    public void sync() {
        dataImport();
        //dataImportTransaction();
        //completeFrequencyDataTransaction();
    }

    private void completeFrequencyDataTransaction() {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                deleteAllArtificialData();
                completeFrequencyData();

                return null;
            }
        });
    }

    private void deleteAllArtificialData() {
        assiduidadeDestRepository.deleteNoSICRecords();
    }

    private void completeFrequencyData() {
        logger.info("Obtendo registros de frequencia...");
        List<AssiduidadeDest> assiduidadeDestList = assiduidadeDestRepository.list();
        if (assiduidadeDestList.isEmpty()) {
            logger.warn("Registros de frequencia nao encontrado!");
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Cria um map com todos os dias de recesso ou feriado de acordo com o banco de dados
        logger.info("Obtendo datas de recesso ou feriado...");
        List<Date> listRecessOrHoliday = assiduidadeDestRepository.listRecessOrHoliday();
        final Map<String, Date> mapReccessOrHoliday = new HashMap<>();
        listRecessOrHoliday.forEach(date -> mapReccessOrHoliday.put(dateFormat.format(date), date));

        // Meses e Anos com registros
        logger.info("Obtendo informacoes de mes/ano...");
        List<DateUtil.MonthYear> monthYearList = assiduidadeDestRepository.listMonthYear();

        // Matriculas com registro no periodo
        logger.info("Obtendo matriculas registradas no sistema...");
        List<Long> matriculaList = assiduidadeDestRepository.listMatriculas();

        final Stat stat = new Stat(listRecessOrHoliday.size(), monthYearList.size(), matriculaList.size());

        Date yesterday = DateUtil.yesterday();

        monthYearList.forEach(monthYear ->
                monthYear.getDatesOfMonth()
                        .stream()
                        .filter(data -> data.before(yesterday) || DateUtil.equalsDate(data, yesterday))
                        .forEach(data -> {
                            logger.info("Processando data {}", dateFormat.format(data));

                            matriculaList.forEach(matricula -> {
                                if (naoExisteRegistroParaMatriculaNaData(matricula, data)) {
                                    logger.warn("Nao foi encontrado registro para a matricula {} em {}",
                                            matricula, dateFormat.format(data));

                                    boolean recessOrHoliday = isRecessOrHoliday(dateFormat, data, mapReccessOrHoliday);

                                    inserirRegistroAssiduidade(recessOrHoliday, data, matricula);

                                    stat.inc();
                                }
                            });
                        })
        );

        logger.info("Processo de complemento de registros de faltas finalizado com sucesso");
        logger.info("Estatisticas: ");
        logger.info("\tQuantidade de Recessos e Feriados:   {}", stat.getQuantityReccessOrHoliday());
        logger.info("\tQuantidade de Mes/Ano:               {}", stat.getQuantityMonthYear());
        logger.info("\tQuantidade de Matriculas:            {}", stat.getQuantityMatriculas());
        logger.info("\tQuantidade de Registros Artificiais: {}", stat.getRecords());
    }

    private void inserirRegistroAssiduidade(boolean recessOrHoliday, Date data, Long matricula) {
        AssiduidadeDest assiduidadeDest = new AssiduidadeDest();
        assiduidadeDest.setMatricula(matricula);
        assiduidadeDest.setData(data);
        assiduidadeDest.setRecessoOuFeriado(recessOrHoliday);
        assiduidadeDest.setIdSic(null);
        assiduidadeDest.setMotivo(
                recessOrHoliday ? "RECESSO OU FERIADO" : "-----------------------------------"
        );
        assiduidadeDest.setFalta(recessOrHoliday ? 0 : 1);
        assiduidadeDest.setPresenca(0);

        if (recessOrHoliday) {
            logger.info("Inserindo registro de recesso ou feriado");
        } else {
            logger.info("Inserindo registro de falta");
        }

        assiduidadeDestRepository.create(assiduidadeDest);
    }

    private boolean isRecessOrHoliday(DateFormat dateFormat, Date data, Map<String, Date> mapReccessOrHoliday) {
        return mapReccessOrHoliday.containsKey(dateFormat.format(data));
    }

    private boolean naoExisteRegistroParaMatriculaNaData(Long matricula, Date data) {
        return assiduidadeDestRepository.getMatricula(data, matricula) == null;
    }

    private void dataImportTransaction() {
        transactionTemplate.execute(new TransactionCallback<Object>() {
            @Override
            public Object doInTransaction(TransactionStatus status) {
                dataImport();

                return null;
            }
        });
    }

    private void dataImport() {
        logger.info("Sincronizando Dados...");

        logger.info("Limpando registros do ultimo calculo...");
        absenteismoRepository.clear();
        logger.info("Limpeza concluida com sucesso");

        logger.info("Consultando tabela tabAssiduidades...");
        List<Date> datasList = assiduidadeRepository.datasRegistros();
        logger.info("Total de datas: {}", datasList.size());

        logger.info("Calculando dados de Assiduidade, Frequencia e Percentual");
        datasList.forEach(data -> syncAbsenteismo(data));
        logger.info("Calculo realizado com sucesso");

        /*assiduidadeList.forEach(assiduidade -> syncAssiduidade(assiduidade));
        logger.info("Sincronizacao realizada com sucesso");*/
    }


    private void syncAbsenteismo(Date data) {
        int presentes = assiduidadeRepository.presentes(data);
        if (presentes == 0) {
            logger.warn("Nao existem registros de presenca para {}", dateFormat.format(data));
            return;
        }

        int ausentesJustificados = assiduidadeRepository.ausentesJustificados(data);

        int matriculados = getMatriculados(data);

        Absenteismo absenteismo = new Absenteismo();
        absenteismo.setData(data);
        absenteismo.setMatriculados(matriculados);
        absenteismo.setPresentes(presentes);
        absenteismo.setAbsenteismo(matriculados-presentes-ausentesJustificados);
        absenteismo.setPercentual(
                (absenteismo.getAbsenteismo().doubleValue()/matriculados)
                *100
        );

        absenteismoRepository.create(absenteismo);
        logger.info(
                "Absenteismo em {} foi de {}",
                dateFormat.format(data),
                absenteismo.getAbsenteismo()
        );
    }

    private Integer getMatriculados(Date data) {
        YearMonth yearMonth = DateUtil.yearAndMonthFrom(data);

        if (mapMatriculadosAnoMes.containsKey(yearMonth)) {
            return mapMatriculadosAnoMes.get(yearMonth);
        } else {
            int matriculados = assiduidadeRepository.matriculados(
                    DateUtil.firstDayFrom(yearMonth),
                    DateUtil.lastDayFrom(yearMonth)
            );
            mapMatriculadosAnoMes.put(yearMonth, matriculados);

            logger.info("Total de Matriculados Ativos em {}/{} foram {}",
                    yearMonth.getMonth(), yearMonth.getYear(), matriculados
            );

            return matriculados;
        }
    }

    /*private void syncAssiduidade(Assiduidade assiduidade) {
        AssiduidadeDest assiduidadeTarget = createAssiduidadeTarget(assiduidade);
        if (!assiduidadeTargetRepository.existByIdSic(assiduidade.getCodigo())) {
            assiduidadeTargetRepository.create(assiduidadeTarget);
        }
    }*/

    /*private AssiduidadeDest createAssiduidadeTarget(Assiduidade assiduidade) {
        AssiduidadeDest assiduidadeTarget = new AssiduidadeDest();

        assiduidadeTarget.setIdSic(assiduidade.getCodigo());
        assiduidadeTarget.setMatricula(assiduidade.getMatricula());
        assiduidadeTarget.setData(assiduidade.getData());
        assiduidadeTarget.setMotivo(assiduidade.getMotivo());

        if (assiduidade.podeCalcularPresenca()) {
            if (assiduidade.isPresenca()) {
                assiduidadeTarget.setPresenca(1);
                assiduidadeTarget.setFalta(0);
            } else {
                assiduidadeTarget.setPresenca(0);
                assiduidadeTarget.setFalta(1);
            }
        } else {
            assiduidadeTarget.setPresenca(0);

            if (assiduidade.isFalta()) {
                assiduidadeTarget.setFalta(1);
            } else {
                assiduidadeTarget.setFalta(0);
            }
        }

        assiduidadeTarget.setRecessoOuFeriado(assiduidade.isRecessoOuFeriado());

        return assiduidadeTarget;
    }*/

    private List<Assiduidade> listAssiduidades() {
        return assiduidadeRepository.list();
    }
}
