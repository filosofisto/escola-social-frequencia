package br.gov.df.escolasocial.service;

import br.gov.df.escolasocial.domain.source.FolhaPagamento;
import br.gov.df.escolasocial.domain.source.FolhaPagamentoResumo;
import br.gov.df.escolasocial.repository.dest.FolhaPagamentoDestRepository;
import br.gov.df.escolasocial.repository.source.FolhaPagamentoRepository;
import br.gov.df.escolasocial.util.DateUtil;
import br.gov.df.escolasocial.util.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;

@Service
public class FolhaPagamentoService {

    private static final Logger logger = LoggerFactory.getLogger(FolhaPagamentoService.class);

    private final TransactionTemplate transactionTemplate;

    private final FolhaPagamentoRepository folhaPagamentoRepository;

    private final FolhaPagamentoDestRepository folhaPagamentoDestRepository;

    @Autowired
    public FolhaPagamentoService(TransactionTemplate transactionTemplate,
                                 FolhaPagamentoRepository folhaPagamentoRepository,
                                 FolhaPagamentoDestRepository folhaPagamentoDestRepository) {
        this.transactionTemplate = transactionTemplate;
        this.folhaPagamentoRepository = folhaPagamentoRepository;
        this.folhaPagamentoDestRepository = folhaPagamentoDestRepository;
    }

    public void sync() {
        dataImport();
        processResume();
    }

    // TODO: Os registros de matriculas q nao estao na folha q esta sendo calculada
    //       nao deve ser calculado diferenca, ou seja, diferenca eh ZERO
    private void processResume() {
        logger.info("Calculando resumo da Folha de Pagamento...");
        int ano = DateUtil.actualYear();
        YearMonth yearMonth;

        for (int i = 1; i <= 12; i++) {
            yearMonth = new YearMonth(ano, i);

            // ----- Mes Atual -----
            FolhaPagamentoResumo folha1MesAtual = folhaPagamentoDestRepository.queryMesAno(
                    yearMonth.getYear(), yearMonth.getMonth(), 1
            );

            // ----- Mes Anterior -----
            YearMonth yearMonthBefore = yearMonth.lessOneMonth();

            FolhaPagamentoResumo folha2MesAnterior = folhaPagamentoDestRepository.queryMesAno(
                    yearMonthBefore.getYear(), yearMonthBefore.getMonth(), 2
            );
            FolhaPagamentoResumo folha1MesAnterior = folhaPagamentoDestRepository.queryMesAno(
                    yearMonthBefore.getYear(), yearMonthBefore.getMonth(), 1
            );

            // ----- Save record -----
            createFolhaPagamentoResumo(yearMonth, folha1MesAtual, folha2MesAnterior, folha1MesAnterior);
        }

        logger.info("Calculo do resumo realizado com sucesso");
    }

    private void createFolhaPagamentoResumo(YearMonth yearMonth,
                                            FolhaPagamentoResumo folha1MesAtual,
                                            FolhaPagamentoResumo folha2MesAnterior,
                                            FolhaPagamentoResumo folha1MesAnterior) {
        FolhaPagamentoResumo folhaPagamentoResumo = new FolhaPagamentoResumo();
        folhaPagamentoResumo.setAno(yearMonth.getYear());
        folhaPagamentoResumo.setMes(yearMonth.getMonth());
        folhaPagamentoResumo.setAuxilioTransporte(
                folha1MesAtual.getAuxilioTransporte() + (folha2MesAnterior.getAuxilioTransporte() - folha1MesAnterior.getAuxilioTransporte())
        );
        folhaPagamentoResumo.setAuxilioAproveitamentoIndividual(
                folha1MesAtual.getAuxilioAproveitamentoIndividual() + (folha2MesAnterior.getAuxilioAproveitamentoIndividual() - folha1MesAnterior.getAuxilioAproveitamentoIndividual())
        );
        folhaPagamentoResumo.setIncentivoAssiduidade(
                folha1MesAtual.getIncentivoAssiduidade() + (folha2MesAnterior.getIncentivoAssiduidade() - folha1MesAnterior.getIncentivoAssiduidade())
        );
        folhaPagamentoResumo.setAuxilioAlimentacao(
                folha1MesAtual.getAuxilioAlimentacao() + (folha2MesAnterior.getAuxilioAlimentacao() - folha1MesAnterior.getAuxilioAlimentacao())
        );

        if (folhaPagamentoResumo.getAuxilioTransporte() < 0) {
            folhaPagamentoResumo.setAuxilioTransporte(0);
        }
        if (folhaPagamentoResumo.getAuxilioAproveitamentoIndividual() < 0) {
            folhaPagamentoResumo.setAuxilioAproveitamentoIndividual(0);
        }
        if (folhaPagamentoResumo.getIncentivoAssiduidade() < 0) {
            folhaPagamentoResumo.setIncentivoAssiduidade(0);
        }
        if (folhaPagamentoResumo.getAuxilioAlimentacao() < 0) {
            folhaPagamentoResumo.setAuxilioAlimentacao(0);
        }

        folhaPagamentoDestRepository.create(folhaPagamentoResumo);

        logger.info("Resumo Folha de Pagamento para {}/{}", yearMonth.getMonth(), yearMonth.getYear());
    }

    private void dataImport() {
        logger.info("Sincronizando Dados de Folha de Pagamento...");

        logger.info("Limpando registros ...");
        folhaPagamentoDestRepository.clear();
        folhaPagamentoDestRepository.clearResumo();
        logger.info("Limpeza concluida com sucesso");

        logger.info("Importanto Dados de Folha de Pagamento...");
        List<FolhaPagamento> folhaPagamentoList = folhaPagamentoRepository.list();
        folhaPagamentoList.forEach(folhaPagamento -> folhaPagamentoDestRepository.create(folhaPagamento));
        logger.info("Importacao realizada com sucesso ...");
    }
}
