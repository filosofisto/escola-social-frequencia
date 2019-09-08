package br.gov.df.escolasocial.repository.dest;

import br.gov.df.escolasocial.domain.dest.Absenteismo;
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
public class AbsenteismoRepository {

    private final Logger logger = LoggerFactory.getLogger(AbsenteismoRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("postgres")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void clear() {
        jdbcTemplate.update("delete from escola_social.absenteismo");
    }

    class AbsenteismoRowMapper implements RowMapper<Absenteismo> {

        @Override
        public Absenteismo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Absenteismo absenteismo = new Absenteismo();

            absenteismo.setId(rs.getLong("id"));
            absenteismo.setData(rs.getDate("data"));
            absenteismo.setMatriculados(rs.getInt("matriculados"));
            absenteismo.setPresentes(rs.getInt("presentes"));
            absenteismo.setAbsenteismo(rs.getInt("absenteismo"));
            absenteismo.setPercentual(rs.getDouble("percentual"));

            return absenteismo;
        }
    }

    public void create(Absenteismo absenteismo) {
        jdbcTemplate.update(
                "insert into escola_social.absenteismo (data,matriculados,presentes,absenteismo,percentual) " +
                        "values (?,?,?,?,?)",
                absenteismo.getData(),
                absenteismo.getMatriculados(),
                absenteismo.getPresentes(),
                absenteismo.getAbsenteismo(),
                absenteismo.getPercentual()
        );
    }
}
