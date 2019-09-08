package br.gov.df.escolasocial.repository.source;

import br.gov.df.escolasocial.domain.source.FolhaPagamento;
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
import java.util.List;

@Transactional
@Repository
public class FolhaPagamentoRepository {

    private static final Logger logger = LoggerFactory.getLogger(FolhaPagamentoRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("access")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    class FolhaPagamentoRowMapper implements RowMapper<FolhaPagamento> {

        @Override
        public FolhaPagamento mapRow(ResultSet rs, int rowNum) throws SQLException {
            FolhaPagamento folhaPagamento = new FolhaPagamento();

            folhaPagamento.setCdfp(rs.getLong("cdfp"));
            folhaPagamento.setMatricula(rs.getInt("matricula"));
            folhaPagamento.setAai(rs.getBigDecimal("aai"));
            folhaPagamento.setPia(rs.getInt("pia"));
            folhaPagamento.setIa(rs.getBigDecimal("ia"));
            folhaPagamento.setPa(rs.getInt("pa"));
            folhaPagamento.setAa(rs.getBigDecimal("aa"));
            folhaPagamento.setCd(rs.getBigDecimal("cd"));
            folhaPagamento.setPt(rs.getInt("pt"));
            folhaPagamento.setAt(rs.getBigDecimal("at"));
            folhaPagamento.setMes(rs.getString("mes"));
            folhaPagamento.setAno(rs.getString("ano"));
            folhaPagamento.setnFolha(rs.getLong("nfolha"));
            folhaPagamento.setDiasu(rs.getInt("diasu"));

            return folhaPagamento;
        }
    }

    public List<FolhaPagamento> list() {
        return jdbcTemplate.query(
                "select * from tbfolhap where ano >= " + (DateUtil.actualYear()-1),
                new FolhaPagamentoRowMapper()
        );
    }
}
