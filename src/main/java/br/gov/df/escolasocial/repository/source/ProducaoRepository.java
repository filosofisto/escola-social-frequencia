package br.gov.df.escolasocial.repository.source;

import br.gov.df.escolasocial.domain.source.Producao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class ProducaoRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("access")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    class ProducaoRowMapper implements RowMapper<Producao> {

        @Override
        public Producao mapRow(ResultSet rs, int rowNum) throws SQLException {
            Producao producao = new Producao();

            producao.setSicId(rs.getLong("cd_op"));
            producao.setCodEtapa(rs.getInt("cd_etapa"));
            producao.setDataOperacao(rs.getDate("dataop"));
            producao.setMatricula(rs.getInt("matricula"));
            producao.setNome(rs.getString("nome"));
            producao.setQuantidade(rs.getInt("Quantidadeq"));
            producao.setTipoProduto(rs.getString("tipop"));
            producao.setValorEtapa(rs.getDouble("valoretapa"));
            producao.setValor(rs.getDouble("valorq"));

            return producao;
        }
    }

    public List<Producao> list() {
        return jdbcTemplate.query(
                "select * from TabOP where dataop >= '2019-01-01' order by dataop",
                new ProducaoRowMapper()
        );
    }

}


