package br.gov.df.escolasocial.domain.source;

public class FolhaPagamentoResumo {

    private int mes;
    private int ano;
    private double auxilioAlimentacao;
    private double incentivoAssiduidade;
    private double auxilioAproveitamentoIndividual;
    private double auxilioTransporte;

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getAuxilioAlimentacao() {
        return auxilioAlimentacao;
    }

    public void setAuxilioAlimentacao(double auxilioAlimentacao) {
        this.auxilioAlimentacao = auxilioAlimentacao;
    }

    public double getIncentivoAssiduidade() {
        return incentivoAssiduidade;
    }

    public void setIncentivoAssiduidade(double incentivoAssiduidade) {
        this.incentivoAssiduidade = incentivoAssiduidade;
    }

    public double getAuxilioAproveitamentoIndividual() {
        return auxilioAproveitamentoIndividual;
    }

    public void setAuxilioAproveitamentoIndividual(double auxilioAproveitamentoIndividual) {
        this.auxilioAproveitamentoIndividual = auxilioAproveitamentoIndividual;
    }

    public double getAuxilioTransporte() {
        return auxilioTransporte;
    }

    public void setAuxilioTransporte(double auxilioTransporte) {
        this.auxilioTransporte = auxilioTransporte;
    }
}
