package br.gov.df.escolasocial.repository.dest;

import br.gov.df.escolasocial.domain.source.FolhaPagamento;
import br.gov.df.escolasocial.domain.source.FolhaPagamentoResumo;
import br.gov.df.escolasocial.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Transactional
@Repository
public class FolhaPagamentoDestRepository {

    private final Logger logger = LoggerFactory.getLogger(FolhaPagamentoDestRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("postgres")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void clear() {
        jdbcTemplate.update("delete from escola_social.folha_pagamento where ano >= '" + (DateUtil.actualYear()-1) + "'");
    }

    public void clearResumo() {
        jdbcTemplate.update("delete from escola_social.folha_pagamento_resumo where ano = '" + DateUtil.actualYear() + "'");
    }

    public void create(FolhaPagamento folhaPagamento) {
        jdbcTemplate.update(
                "insert into escola_social.folha_pagamento(" +
                        "id_sic,matricula,auxilio_aproveitamento_individual,percentual_incentivo_assiduidade," +
                        "incentivo_assiduidade,presencas_alimentacao,auxilio_alimentacao,custo_diario," +
                        "presencas_transporte,auxilio_transporte,mes,ano,numero_folha,dias_uteis) " +
                        "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                folhaPagamento.getCdfp(),
                folhaPagamento.getMatricula(),
                folhaPagamento.getAai(),
                folhaPagamento.getPia(),
                folhaPagamento.getIa(),
                folhaPagamento.getPa(),
                folhaPagamento.getAa(),
                folhaPagamento.getCd(),
                folhaPagamento.getPt(),
                folhaPagamento.getAt(),
                mesToInt(folhaPagamento.getMes()),
                Integer.parseInt(folhaPagamento.getAno()),
                folhaPagamento.getnFolha(),
                folhaPagamento.getDiasu()
        );
    }

    public void create(FolhaPagamentoResumo folhaPagamentoResumo) {
        jdbcTemplate.update(
                "insert into escola_social.folha_pagamento_resumo(" +
                        "auxilio_aproveitamento_individual,incentivo_assiduidade,auxilio_alimentacao," +
                        "auxilio_transporte,mes,ano) " +
                        "values (?,?,?,?,?,?)",
                folhaPagamentoResumo.getAuxilioAproveitamentoIndividual(),
                folhaPagamentoResumo.getIncentivoAssiduidade(),
                folhaPagamentoResumo.getAuxilioAlimentacao(),
                folhaPagamentoResumo.getAuxilioTransporte(),
                folhaPagamentoResumo.getMes(),
                folhaPagamentoResumo.getAno()
        );
    }

    class FolhaPagamentoResumoRowMapper implements RowMapper<FolhaPagamentoResumo> {

        @Override
        public FolhaPagamentoResumo mapRow(ResultSet rs, int rowNum) throws SQLException {
            FolhaPagamentoResumo folhaPagamentoResumo = new FolhaPagamentoResumo();

            folhaPagamentoResumo.setAuxilioAlimentacao(rs.getDouble("AUXILIO_ALIMENTACAO"));
            folhaPagamentoResumo.setIncentivoAssiduidade(rs.getDouble("INCENTIVO_ASSIDUIDADE"));
            folhaPagamentoResumo.setAuxilioAproveitamentoIndividual(rs.getDouble("AUXILIO_APROVEITAMENTO_INDIVIDUAL"));
            folhaPagamentoResumo.setAuxilioTransporte(rs.getDouble("AUXILIO_TRANSPORTE"));

            return folhaPagamentoResumo;
        }
    }

    public FolhaPagamentoResumo queryMesAno(int ano, int mes, int folha) {
        return jdbcTemplate.queryForObject(
                "select  sum(f.auxilio_alimentacao)                      AUXILIO_ALIMENTACAO\n" +
                        "       ,sum(f.incentivo_assiduidade)                INCENTIVO_ASSIDUIDADE\n" +
                        "       ,sum(f.auxilio_aproveitamento_individual)    AUXILIO_APROVEITAMENTO_INDIVIDUAL\n" +
                        "       ,sum(f.auxilio_transporte)                   AUXILIO_TRANSPORTE\n" +
                        "from   escola_social.folha_pagamento f\n" +
                        "where  ano=" + ano + " and mes=" + mes + " and numero_folha=" + folha,
                new FolhaPagamentoResumoRowMapper()
        );
    }

    private int mesToInt(String mes) {
        String mesUp = mes.toUpperCase();

        if ("JANEIRO".equals(mesUp)) {
            return 1;
        } else if ("FEVEREIRO".equals(mesUp)) {
            return 2;
        } else if ("MARCO".equals(mesUp) || "MARÇO".equals(mesUp)) {
            return 3;
        } else if ("ABRIL".equals(mesUp)) {
            return 4;
        } else if ("MAIO".equals(mesUp)) {
            return 5;
        } else if ("JUNHO".equals(mesUp)) {
            return 6;
        } else if ("JULHO".equals(mesUp)) {
            return 7;
        } else if ("AGOSTO".equals(mesUp)) {
            return 8;
        } else if ("SETEMBRO".equals(mesUp)) {
            return 9;
        } else if ("OUTUBRO".equals(mesUp)) {
            return 10;
        } else if ("NOVEMBRO".equals(mesUp)) {
            return 11;
        } else if ("DEZEMBRO".equals(mesUp)) {
            return 12;
        }

        return 0;
    }
}
