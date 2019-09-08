package br.gov.df.escolasocial.service;

import br.gov.df.escolasocial.domain.source.Producao;
import br.gov.df.escolasocial.repository.dest.ProducaoDestRepository;
import br.gov.df.escolasocial.repository.source.ProducaoRepository;
import br.gov.df.escolasocial.util.YearMonth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
public class ProducaoService {

    private static final Logger logger = LoggerFactory.getLogger(ProducaoService.class);

    private final TransactionTemplate transactionTemplate;

    private final ProducaoRepository producaoRepository;

    private final ProducaoDestRepository producaoDestRepository;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private Map<YearMonth, Integer> mapMatriculadosAnoMes;

    @Autowired
    public ProducaoService(PlatformTransactionManager platformTransactionManager,
                           ProducaoRepository producaoRepository,
                           ProducaoDestRepository producaoDestRepository) {
        this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
        this.producaoRepository = producaoRepository;
        this.producaoDestRepository = producaoDestRepository;
    }

    public void sync() {
        dataImport();
    }

    private void dataImport() {
        logger.info("Sincronizando Dados de Producao...");

        logger.info("Limpando registros ...");
        producaoDestRepository.clear();
        logger.info("Limpeza concluida com sucesso");

        logger.info("Importanto Dados de Producao...");
        List<Producao> producaoList = producaoRepository.list();
        producaoList.forEach(producao -> producaoDestRepository.create(producao));
        logger.info("Importacao realizada com sucesso ...");
    }
}
