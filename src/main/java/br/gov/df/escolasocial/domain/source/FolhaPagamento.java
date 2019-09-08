package br.gov.df.escolasocial.domain.source;

import java.math.BigDecimal;

public class FolhaPagamento {

    Long cdfp;
    Integer matricula;
    BigDecimal aai;
    Integer pia;
    BigDecimal ia;
    Integer pa;
    BigDecimal aa;
    BigDecimal cd;
    Integer pt;
    BigDecimal at;
    String mes;
    String ano;
    Long nFolha;
    Integer diasu;

    public FolhaPagamento() {
    }

    public FolhaPagamento(Long cdfp, Integer matricula, BigDecimal aai, Integer pia, BigDecimal ia, Integer pa,
                          BigDecimal aa, BigDecimal cd, Integer pt, BigDecimal at, String mes, String ano, Long nFolha,
                          Integer diasu) {
        this.cdfp = cdfp;
        this.matricula = matricula;
        this.aai = aai;
        this.pia = pia;
        this.ia = ia;
        this.pa = pa;
        this.aa = aa;
        this.cd = cd;
        this.pt = pt;
        this.at = at;
        this.mes = mes;
        this.ano = ano;
        this.nFolha = nFolha;
        this.diasu = diasu;
    }

    public Long getCdfp() {
        return cdfp;
    }

    public void setCdfp(Long cdfp) {
        this.cdfp = cdfp;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public BigDecimal getAai() {
        return aai;
    }

    public void setAai(BigDecimal aai) {
        this.aai = aai;
    }

    public Integer getPia() {
        return pia;
    }

    public void setPia(Integer pia) {
        this.pia = pia;
    }

    public BigDecimal getIa() {
        return ia;
    }

    public void setIa(BigDecimal ia) {
        this.ia = ia;
    }

    public Integer getPa() {
        return pa;
    }

    public void setPa(Integer pa) {
        this.pa = pa;
    }

    public BigDecimal getAa() {
        return aa;
    }

    public void setAa(BigDecimal aa) {
        this.aa = aa;
    }

    public BigDecimal getCd() {
        return cd;
    }

    public void setCd(BigDecimal cd) {
        this.cd = cd;
    }

    public Integer getPt() {
        return pt;
    }

    public void setPt(Integer pt) {
        this.pt = pt;
    }

    public BigDecimal getAt() {
        return at;
    }

    public void setAt(BigDecimal at) {
        this.at = at;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public Long getnFolha() {
        return nFolha;
    }

    public void setnFolha(Long nFolha) {
        this.nFolha = nFolha;
    }

    public Integer getDiasu() {
        return diasu;
    }

    public void setDiasu(Integer diasu) {
        this.diasu = diasu;
    }
}
