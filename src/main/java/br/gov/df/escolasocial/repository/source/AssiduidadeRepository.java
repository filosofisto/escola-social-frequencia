package br.gov.df.escolasocial.repository.source;

import br.gov.df.escolasocial.domain.source.Assiduidade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class AssiduidadeRepository {

    private JdbcTemplate jdbcTemplate;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    @Qualifier("access")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
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

    public List<Assiduidade> list() {
        return jdbcTemplate.query(
                "select * from tbAssiduidade where matricula is not null and datar >= '2019-01-01' order by datar",
                new AssiduidadeRowMapper()
        );
    }

    public List<Date> datasRegistros() {
        return jdbcTemplate.query(
                "SELECT a.datar \n" +
                        "FROM tbAssiduidade a\n" +
                        "where a.datar between #2019-01-01# and Date()\n" +
                        "group by a.datar\n" +
                        "order by a.datar;",
                (rs, rowNum) -> rs.getDate(1)
        );
    }

    public Integer matriculados(Date dataInicial, Date dataFinal) {
        String sDataInicial = dateFormat.format(dataInicial);
        String sDataFinal = dateFormat.format(dataFinal);

        return jdbcTemplate.query(
            "SELECT count(*)\n" +
                    "FROM (SELECT a.matricula\n" +
                    "FROM tbAssiduidade AS a INNER JOIN TabFuncionarios AS f ON a.matricula = f.matricula \n" +
                    "WHERE (a.matricula Is Not Null) AND (a.datar Is Not Null) " +
                    "       AND (a.datar between #" + sDataInicial + "# and #" + sDataFinal + "#)\n" +
                    "       AND (f.datadesliga IS NULL OR f.datadesliga > #" + sDataFinal + "#)" +
                    "       AND (f.status <> 'INATIVO(A)')" +
                    "group by a.matricula\n" +
                    ")  AS R;\n",
                (rs, rowNum) -> rs.getInt(1)
        ).get(0);
    }

    public Integer presentes(Date date) {
        String sData = dateFormat.format(date);

        return jdbcTemplate.query(
                "SELECT count(*)\n" +
                        "FROM (SELECT a.matricula\n" +
                        "FROM tbAssiduidade a INNER JOIN TabFuncionarios AS f ON a.matricula = f.matricula \n" +
                        "where a.datai = #" + dateFormat.format(date) + "# \n" +
                        "       AND (f.datadesliga IS NULL OR f.datadesliga > #" + sData + "#) " +
                        "       AND (a.motivo = '-----------------------------------') " +
                        "       AND (f.status <> 'INATIVO(A)')" +
                        "group by a.matricula\n" +
                        ") AS R",
                (rs, rowNum) -> rs.getInt(1)
        ).get(0);
    }

    public Integer ausentesJustificados(Date date) {
        String sData = dateFormat.format(date);

        return jdbcTemplate.query(
                "SELECT count(*)\n" +
                        "FROM (SELECT a.matricula\n" +
                        "FROM tbAssiduidade a INNER JOIN TabFuncionarios AS f ON a.matricula = f.matricula \n" +
                        "where a.datai = #" + dateFormat.format(date) + "# \n" +
                        "       AND (f.datadesliga IS NULL OR f.datadesliga > #" + sData + "#) " +
                        "       AND (a.motivo <> '-----------------------------------') " +
                        "       AND (a.motivo <> 'GREVE DE TRANSPORTE') " +
                        "       AND (a.motivo <> 'RECESSO OU FERIADO') " +
                        "       AND (f.status <> 'INATIVO(A)')" +
                        "group by a.matricula\n" +
                        ") AS R",
                (rs, rowNum) -> rs.getInt(1)
        ).get(0);
    }
}
