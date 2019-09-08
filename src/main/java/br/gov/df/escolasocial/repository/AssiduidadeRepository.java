package br.gov.df.escolasocial.repository;

import br.gov.df.escolasocial.domain.Assiduidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@Transactional
@Repository
public class AssiduidadeRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("access")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void create(Assiduidade assiduidade) {
        jdbcTemplate.update(
                "insert into tbAssiduidade(matricula, datar, horar, horar2, horar3, horar4, motivo, usuario, datai) " +
                        "values (?,?,?,?,?,?,?,?,?)",
                assiduidade.getMatricula(),
                assiduidade.getData(),
                assiduidade.getHoraEntradaManha(),
                assiduidade.getHoraSaidaManha(),
                assiduidade.getHoraEntradaTarde(),
                assiduidade.getHoraSaidaTarde(),
                assiduidade.getMotivo(),
                assiduidade.getUsuario(),
                assiduidade.getDataRegistro()

        );
    }

    class AssiduidadeRowMapper implements RowMapper<Assiduidade> {

        @Override
        public Assiduidade mapRow(ResultSet rs, int rowNum) throws SQLException {
            Assiduidade assiduidade = new Assiduidade();

            assiduidade.setCodigo(rs.getLong("cda"));
            assiduidade.setMatricula(rs.getLong("matricula"));
            assiduidade.setData(rs.getDate("datar"));
            assiduidade.setHoraEntradaManha(rs.getDate("horar"));
            assiduidade.setHoraSaidaManha(rs.getDate("horar2"));
            assiduidade.setHoraEntradaTarde(rs.getDate("horar3"));
            assiduidade.setHoraEntradaTarde(rs.getDate("horar4"));
            assiduidade.setMotivo(rs.getString("motivo"));
            assiduidade.setUsuario(rs.getString("usuario"));
            assiduidade.setDataRegistro(rs.getDate("datai"));

            return assiduidade;
        }
    }

    public boolean existByMatriculaAndDate(long matricula, Date date) {
        int count = jdbcTemplate.queryForObject(
                "select count(*) from tbAssiduidade where matricula = ? and datar = ?",
                new Object[] { matricula, date }, Integer.class
        );

        return count > 0;
    }
}
