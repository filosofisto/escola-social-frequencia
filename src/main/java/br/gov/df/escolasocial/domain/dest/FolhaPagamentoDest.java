package br.gov.df.escolasocial.domain.dest;

import java.math.BigDecimal;

public class FolhaPagamentoDest {

    private Long id;
    private Long idSic;
    private Integer matricula;
    private BigDecimal auxilioAproveitamentoIndividual;
    private Integer percentualIncentivoAssiduidade;
    private BigDecimal incentivoAssiduidade;
    private Integer presencasAlimentacao;
    private BigDecimal auxilioAlimentacao;
    private BigDecimal custoDiario;
    private Integer presencasTransporte;
    private BigDecimal auxilioTransporte;
    private Integer mes;
    private Integer ano;
    private Integer numeroFolha;
    private Integer diasUteis;

    public FolhaPagamentoDest() {
    }

    public FolhaPagamentoDest(Long id, Long idSic, Integer matricula, BigDecimal auxilioAproveitamentoIndividual,
                              Integer percentualIncentivoAssiduidade, BigDecimal incentivoAssiduidade,
                              Integer presencasAlimentacao, BigDecimal auxilioAlimentacao, BigDecimal custoDiario,
                              Integer presencasTransporte, BigDecimal auxilioTransporte, Integer mes, Integer ano,
                              Integer numeroFolha, Integer diasUteis) {
        this.id = id;
        this.idSic = idSic;
        this.matricula = matricula;
        this.auxilioAproveitamentoIndividual = auxilioAproveitamentoIndividual;
        this.percentualIncentivoAssiduidade = percentualIncentivoAssiduidade;
        this.incentivoAssiduidade = incentivoAssiduidade;
        this.presencasAlimentacao = presencasAlimentacao;
        this.auxilioAlimentacao = auxilioAlimentacao;
        this.custoDiario = custoDiario;
        this.presencasTransporte = presencasTransporte;
        this.auxilioTransporte = auxilioTransporte;
        this.mes = mes;
        this.ano = ano;
        this.numeroFolha = numeroFolha;
        this.diasUteis = diasUteis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdSic() {
        return idSic;
    }

    public void setIdSic(Long idSic) {
        this.idSic = idSic;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getAuxilioAproveitamentoIndividual() {
        return auxilioAproveitamentoIndividual;
    }

    public void setAuxilioAproveitamentoIndividual(BigDecimal auxilioAproveitamentoIndividual) {
        this.auxilioAproveitamentoIndividual = auxilioAproveitamentoIndividual;
    }

    public Integer getPercentualIncentivoAssiduidade() {
        return percentualIncentivoAssiduidade;
    }

    public void setPercentualIncentivoAssiduidade(Integer percentualIncentivoAssiduidade) {
        this.percentualIncentivoAssiduidade = percentualIncentivoAssiduidade;
    }

    public BigDecimal getIncentivoAssiduidade() {
        return incentivoAssiduidade;
    }

    public void setIncentivoAssiduidade(BigDecimal incentivoAssiduidade) {
        this.incentivoAssiduidade = incentivoAssiduidade;
    }

    public Integer getPresencasAlimentacao() {
        return presencasAlimentacao;
    }

    public void setPresencasAlimentacao(Integer presencasAlimentacao) {
        this.presencasAlimentacao = presencasAlimentacao;
    }

    public BigDecimal getAuxilioAlimentacao() {
        return auxilioAlimentacao;
    }

    public void setAuxilioAlimentacao(BigDecimal auxilioAlimentacao) {
        this.auxilioAlimentacao = auxilioAlimentacao;
    }

    public BigDecimal getCustoDiario() {
        return custoDiario;
    }

    public void setCustoDiario(BigDecimal custoDiario) {
        this.custoDiario = custoDiario;
    }

    public Integer getPresencasTransporte() {
        return presencasTransporte;
    }

    public void setPresencasTransporte(Integer presencasTransporte) {
        this.presencasTransporte = presencasTransporte;
    }

    public BigDecimal getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(BigDecimal auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getNumeroFolha() {
        return numeroFolha;
    }

    public void setNumeroFolha(Integer numeroFolha) {
        this.numeroFolha = numeroFolha;
    }

    public Integer getDiasUteis() {
        return diasUteis;
    }

    public void setDiasUteis(Integer diasUteis) {
        this.diasUteis = diasUteis;
    }
}
