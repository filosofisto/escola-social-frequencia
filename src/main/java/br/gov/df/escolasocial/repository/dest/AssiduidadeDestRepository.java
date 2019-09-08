package br.gov.df.escolasocial.repository.dest;

import br.gov.df.escolasocial.domain.dest.AssiduidadeDest;
import br.gov.df.escolasocial.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Transactional
@Repository
public class AssiduidadeDestRepository {

    private final Logger logger = LoggerFactory.getLogger(AssiduidadeDestRepository.class);

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("postgres")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void deleteNoSICRecords() {
        jdbcTemplate.update("delete from escola_social.assiduidade where id_sic is null");
    }

    class AssiduidadeTargetRowMapper implements RowMapper<AssiduidadeDest> {

        @Override
        public AssiduidadeDest mapRow(ResultSet rs, int rowNum) throws SQLException {
            AssiduidadeDest assiduidade = new AssiduidadeDest();

            assiduidade.setId(rs.getLong("id_assiduidade"));
            assiduidade.setIdSic(rs.getLong("id_sic"));
            assiduidade.setMatricula(rs.getLong("matricula"));
            assiduidade.setData(rs.getTimestamp("data"));
            assiduidade.setPresenca(rs.getInt("presenca"));
            assiduidade.setFalta(rs.getInt("falta"));
            assiduidade.setMotivo(rs.getString("motivo"));
            assiduidade.setRecessoOuFeriado(rs.getBoolean("recesso_feriado"));

            return assiduidade;
        }
    }

    public List<AssiduidadeDest> list() {
        return jdbcTemplate.query("select * from escola_social.assiduidade order by matricula, data", new AssiduidadeTargetRowMapper());
    }

    public List<Date> listRecessOrHoliday() {
        return jdbcTemplate.query(
                "select data from escola_social.assiduidade where recesso_feriado group by data order by data",
                new RowMapper<Date>() {
                    @Override
                    public Date mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getDate("data");
                    }
                }
        );
    }

    public List<DateUtil.MonthYear> listMonthYear() {
        return jdbcTemplate.query(
                "select  extract(MONTH from a.data)  MES,\n" +
                    "        extract(YEAR from a.data)   ANO\n" +
                    "from    escola_social.assiduidade a\n" +
                    "where   a.data BETWEEN '2019-01-01' AND current_date \n" +
                    "group by extract(MONTH from a.data), extract(YEAR from a.data)\n" +
                    "order by ANO, MES",
                new RowMapper<DateUtil.MonthYear>() {
                    @Override
                    public DateUtil.MonthYear mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new DateUtil.MonthYear(rs.getInt("MES"), rs.getInt("ANO"), true);
                    }
                }
        );
    }

    public List<Long> listMatriculas() {
        return jdbcTemplate.query(
                "select  a.matricula MATRICULA\n" +
                    "from    escola_social.assiduidade a\n" +
                    "where   a.data BETWEEN '2019-01-01' AND current_date \n" +
                    "group by a.matricula\n" +
                    "order by a.matricula",
                new RowMapper<Long>() {
                    @Override
                    public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return rs.getLong("MATRICULA");
                    }
                }
        );
    }

    public Long getMatricula(Date data, Long matricula) {
        try {
            return jdbcTemplate.queryForObject(
                    "select  a.id_assiduidade\n" +
                            "from    escola_social.assiduidade a\n" +
                            "where   a.data = ? and a.matricula = ?",
                    new RowMapper<Long>() {
                        @Override
                        public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getLong("id_assiduidade");
                        }
                    },
                    data,
                    matricula);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public AssiduidadeDest find(Long id) {
        return jdbcTemplate.queryForObject(
                "select * from escola_social.assiduidade where id_assiduidade = ?",
                new AssiduidadeTargetRowMapper(),
                new Object[] {id}
        );
    }

    public AssiduidadeDest findByIdSic(Long idSic) {
        try {
            return jdbcTemplate.queryForObject(
                    "select * from escola_social.assiduidade where id_sic = ?",
                    new AssiduidadeTargetRowMapper(),
                    idSic);
        } catch (EmptyResultDataAccessException e) {
            logger.warn("Registro nao encontrado: idSic => {}", idSic);
            return null;
        }
    }

    public boolean existByIdSic(Long idSic) {
        return findByIdSic(idSic) != null;
    }

    public void create(AssiduidadeDest assiduidadeDest) {
        jdbcTemplate.update(
                "insert into escola_social.assiduidade (" +
                        "id_sic,matricula,data,presenca,falta,motivo,recesso_feriado) values (?,?,?,?,?,?,?)",
                    assiduidadeDest.getIdSic(),
                    assiduidadeDest.getMatricula(),
                    assiduidadeDest.getData(),
                    assiduidadeDest.getPresenca(),
                    assiduidadeDest.getFalta(),
                    assiduidadeDest.getMotivo(),
                    assiduidadeDest.isRecessoOuFeriado()
                );
    }

    public void update(AssiduidadeDest assiduidadeDest) {
        jdbcTemplate.update(
                "update escola_social.assiduidade set " +
                        "matricula=?," +
                        "data=?," +
                        "presenca=?," +
                        "falta=?," +
                        "motivo=?" +
                        "recesso_feriado=?," +
                        "where id_sic=?",
                assiduidadeDest.getMatricula(),
                assiduidadeDest.getData(),
                assiduidadeDest.getPresenca(),
                assiduidadeDest.getFalta(),
                assiduidadeDest.getMotivo(),
                assiduidadeDest.isRecessoOuFeriado(),
                assiduidadeDest.getIdSic()
        );
    }
}
