package br.gov.df.escolasocial.domain.dest;

public class ProdutoDest {

    private Long codigo;
    private String produto;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "codigo=" + codigo +
                ", produto='" + produto + '\'' +
                '}';
    }

    public void print() {
        System.out.println(this);
    }
}
