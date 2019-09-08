package br.gov.df.escolasocial.service;

import br.gov.df.escolasocial.repository.dest.QuadroDespesaDestRepository;
import br.gov.df.escolasocial.repository.source.QuadroDespesaRepository;
import br.gov.df.escolasocial.util.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;

import java.io.File;
import java.io.IOException;

@Service
public class QuadroDespesaService {

    private static final Logger logger = LoggerFactory.getLogger(QuadroDespesaService.class);

    private final TransactionTemplate transactionTemplate;

    private final QuadroDespesaRepository quadroDespesaRepository;

    private final QuadroDespesaDestRepository quadroDespesaDestRepository;

    @Autowired
    public QuadroDespesaService(TransactionTemplate transactionTemplate,
                                QuadroDespesaRepository quadroDespesaRepository,
                                QuadroDespesaDestRepository quadroDespesaDestRepository) {
        this.transactionTemplate = transactionTemplate;
        this.quadroDespesaRepository = quadroDespesaRepository;
        this.quadroDespesaDestRepository = quadroDespesaDestRepository;
    }

    public void load(String filename) throws IOException {
        if (!FileHelper.fileExistsInCurDir(filename)) {
            throw new RuntimeException("Arquivo " + filename + " nao existe");
        }

        // Austa o nome do arquivo para a pasta corrente do usuario
        final String filenameFull = FileHelper.curdir() + File.separator + filename;

        transactionTemplate.execute(status -> {
            quadroDespesaDestRepository.clear();

            try {
                quadroDespesaRepository.list(filenameFull).forEach(quadroDespesaDestRepository::save);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return null;
        });
    }
}
