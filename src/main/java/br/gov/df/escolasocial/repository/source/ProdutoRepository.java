package br.gov.df.escolasocial.repository.source;

import br.gov.df.escolasocial.domain.source.Produto;
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
public class ProdutoRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProdutoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class ProdutoRowMapper implements RowMapper<Produto> {

        @Override
        public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
            Produto produto = new Produto();

            produto.setCodigo(rs.getLong("cd_produto"));
            produto.setProduto(rs.getString("Produto"));

            return produto;
        }
    }

    public List<Produto> list() {
        return jdbcTemplate.query("select cd_produto, Produto from TabProduto", new ProdutoRowMapper());
    }
}
