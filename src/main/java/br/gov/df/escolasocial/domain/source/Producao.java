package br.gov.df.escolasocial.domain.source;

import java.util.Date;

public class Producao {

    private Long id;
    private Long sicId;
    private Integer codEtapa;
    private String tipoProduto;
    private Integer matricula;
    private String nome;
    private Integer quantidade;
    private Date dataOperacao;
    private Double valorEtapa;
    private Double valor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSicId() {
        return sicId;
    }

    public void setSicId(Long sicId) {
        this.sicId = sicId;
    }

    public Integer getCodEtapa() {
        return codEtapa;
    }

    public void setCodEtapa(Integer codEtapa) {
        this.codEtapa = codEtapa;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataOperacao() {
        return dataOperacao;
    }

    public void setDataOperacao(Date dataOperacao) {
        this.dataOperacao = dataOperacao;
    }

    public Double getValorEtapa() {
        return valorEtapa;
    }

    public void setValorEtapa(Double valorEtapa) {
        this.valorEtapa = valorEtapa;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
