package br.gov.df.escolasocial.repository.source;

import br.gov.df.escolasocial.domain.source.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Transactional
@Repository
public class FuncionarioRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public FuncionarioRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class FuncionarioRowMapper implements RowMapper<Funcionario> {

        @Override
        public Funcionario mapRow(ResultSet rs, int rowNum) throws SQLException {
            Funcionario funcionario = new Funcionario();

            funcionario.setCodCpf(rs.getLong("CodCPF"));
            funcionario.setCpf(rs.getString("CPF"));
            funcionario.setNome(rs.getString("Nome"));
            funcionario.setSexo(rs.getString("Sexo"));
            funcionario.setEstadoCivil(rs.getString("EstadoCivil"));
            funcionario.setDataNascimento(rs.getDate("DataNascimento"));
            funcionario.setEndereco(rs.getString("Endereco"));
            funcionario.setCidade(rs.getString("Cidade"));
            funcionario.setEstado(rs.getString("Estado"));
            funcionario.setBairro(rs.getString("Bairro"));
            funcionario.setCep(rs.getInt("CEP"));
            funcionario.setPossuiFilhos(rs.getBoolean("Filhos"));
            funcionario.setPne(rs.getBoolean("PNE"));
            funcionario.setDeficiencia(rs.getString("QualDef"));
            funcionario.setNacionalidade(rs.getString("Nac"));


            return funcionario;
        }
    }

    public List<Funcionario> list() {
        return jdbcTemplate.query("select * from TabFuncionarios", new FuncionarioRowMapper());
    }

}
