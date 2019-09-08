package br.gov.df.escolasocial.repository.dest;

import br.gov.df.escolasocial.domain.source.Producao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Transactional
@Repository
public class ProducaoDestRepository {

    private final Logger logger = LoggerFactory.getLogger(ProducaoDestRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("postgres")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void clear() {
        jdbcTemplate.update("delete from escola_social.producao where data_operacao >= '2019-01-01'");
    }

    public void create(Producao producao) {
        jdbcTemplate.update(
                "insert into escola_social.producao(sic_id,cod_etapa,data_operacao,matricula,nome,quantidade,tipo_produto,valor_etapa,valor) " +
                        "values (?,?,?,?,?,?,?,?,?)",
                producao.getSicId(),
                producao.getCodEtapa(),
                producao.getDataOperacao(),
                producao.getMatricula(),
                producao.getNome(),
                producao.getQuantidade(),
                producao.getTipoProduto(),
                producao.getValorEtapa(),
                producao.getValor()

        );
    }
}
