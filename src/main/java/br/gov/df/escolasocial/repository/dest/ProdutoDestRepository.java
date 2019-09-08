package br.gov.df.escolasocial.repository.dest;

import br.gov.df.escolasocial.domain.dest.ProdutoDest;
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
public class ProdutoDestRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProdutoDestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    class ProdutoTargetRowMapper implements RowMapper<ProdutoDest> {

        @Override
        public ProdutoDest mapRow(ResultSet rs, int rowNum) throws SQLException {
            ProdutoDest produto = new ProdutoDest();

            produto.setCodigo(rs.getLong("id_produto"));
            produto.setProduto(rs.getString("descricao"));

            return produto;
        }
    }

    public List<ProdutoDest> list() {
        return jdbcTemplate.query("select codigo, desricao from Produtos", new ProdutoTargetRowMapper());
    }

    public ProdutoDest find(Long idProduto) {
        return jdbcTemplate.queryForObject(
                "select * from Produtos where id_produto = :idProduto",
                new ProdutoTargetRowMapper()
        );
    }
}
