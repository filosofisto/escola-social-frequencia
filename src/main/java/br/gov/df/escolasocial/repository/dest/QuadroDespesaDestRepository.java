package br.gov.df.escolasocial.repository.dest;

import br.gov.df.escolasocial.domain.QuadroDespesa;
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
public class QuadroDespesaDestRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("postgres")
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    class QuadroDespesaRowMapper implements RowMapper<QuadroDespesa> {

        @Override
        public QuadroDespesa mapRow(ResultSet rs, int rowNum) throws SQLException {
            QuadroDespesa quadroDespesa = new QuadroDespesa();

            quadroDespesa.setId(rs.getLong("id"));
            quadroDespesa.setAno(rs.getInt("ano"));
            quadroDespesa.setMes(rs.getInt("mes"));
            quadroDespesa.setSemana(rs.getInt("semana"));
            quadroDespesa.setLei(rs.getBigDecimal("lei"));
            quadroDespesa.setAlteracaoLei(rs.getBigDecimal("alteracao_lei"));
            quadroDespesa.setPercentualLei(rs.getFloat("percentual_lei"));
            quadroDespesa.setContigenciado(rs.getBigDecimal("contigenciado"));
            quadroDespesa.setPercentualContingenciado(rs.getFloat("percentual_contigenciado"));
            quadroDespesa.setCota(rs.getBigDecimal("cota"));
            quadroDespesa.setPercentualCota(rs.getFloat("percentual_cota"));
            quadroDespesa.setBloqueado(rs.getBigDecimal("bloqueado"));
            quadroDespesa.setDespesaAutorizada(rs.getBigDecimal("despesa_autorizada"));
            quadroDespesa.setEmpenhado(rs.getBigDecimal("empenhado"));
            quadroDespesa.setDisponivel(rs.getBigDecimal("disponivel"));
            quadroDespesa.setPercentualDisponivel(rs.getFloat("percentual_disponivel"));
            quadroDespesa.setLiquidado(rs.getBigDecimal("liquidado"));
            quadroDespesa.setPercentualLiquidado(rs.getFloat("percentual_liquidado"));

            return quadroDespesa;
        }
    }

    public List<QuadroDespesa> list() {
        return jdbcTemplate.query(
                "select * from escola_social.quadro_despesa where ano = extract(year from current_date) order by ano, mes, semana",
                new QuadroDespesaRowMapper()
        );
    }

    public void clear() {
        jdbcTemplate.update("delete from escola_social.quadro_despesa a where a.ano = extract(year from current_date)");
    }

    public void save(QuadroDespesa quadroDespesa) {
        if (quadroDespesa.getId() == null) {
            create(quadroDespesa);
        } else {
            update(quadroDespesa);
        }
    }

    public void create(QuadroDespesa quadroDespesa) {
        jdbcTemplate.update(
                "insert into escola_social.quadro_despesa (" +
                        "ano,mes,semana,lei,alteracao_lei,percentual_lei,contigenciado,percentual_contigenciado," +
                        "cota,percentual_cota,bloqueado,despesa_autorizada,empenhado,disponivel,percentual_disponivel," +
                        "liquidado,percentual_liquidado) " +
                        "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                quadroDespesa.getAno(),
                quadroDespesa.getMes(),
                quadroDespesa.getSemana(),
                quadroDespesa.getLei(),
                quadroDespesa.getAlteracaoLei(),
                quadroDespesa.getPercentualLei(),
                quadroDespesa.getContigenciado(),
                quadroDespesa.getPercentualContingenciado(),
                quadroDespesa.getCota(),
                quadroDespesa.getPercentualCota(),
                quadroDespesa.getBloqueado(),
                quadroDespesa.getDespesaAutorizada(),
                quadroDespesa.getEmpenhado(),
                quadroDespesa.getDisponivel(),
                quadroDespesa.getPercentualDisponivel(),
                quadroDespesa.getLiquidado(),
                quadroDespesa.getPercentualLiquidado()
        );
    }

    public void update(QuadroDespesa quadroDespesa) {
        jdbcTemplate.update(
                "update escola_social.quadro_despesa set " +
                        "ano=?,mes=?,semana=?,lei=?,alteracao_lei=?,percentual_lei=?,contigenciado=?,percentual_contigenciado=?," +
                        "cota=?,percentual_cota=?,bloqueado=?,despesa_autorizada=?,empenhado=?,disponivel=?,percentual_disponivel=?," +
                        "liquidado=?,percentual_liquidado=? " +
                        "where id=?",
                quadroDespesa.getAno(),
                quadroDespesa.getMes(),
                quadroDespesa.getSemana(),
                quadroDespesa.getLei(),
                quadroDespesa.getAlteracaoLei(),
                quadroDespesa.getPercentualLei(),
                quadroDespesa.getContigenciado(),
                quadroDespesa.getPercentualContingenciado(),
                quadroDespesa.getCota(),
                quadroDespesa.getPercentualCota(),
                quadroDespesa.getBloqueado(),
                quadroDespesa.getDespesaAutorizada(),
                quadroDespesa.getEmpenhado(),
                quadroDespesa.getDisponivel(),
                quadroDespesa.getPercentualDisponivel(),
                quadroDespesa.getLiquidado(),
                quadroDespesa.getPercentualLiquidado(),
                quadroDespesa.getId()
        );
    }
}
