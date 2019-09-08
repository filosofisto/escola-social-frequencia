package br.gov.df.escolasocial.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class QuadroDespesa {

    // id                       integer NOT NULL DEFAULT nextval('escola_social.quadro_despesa_id_seq'::regclass)
    private Long id;
    // ano                      integer not null,
    private Integer ano;
    //mes                      integer not null,
    private Integer mes;
    //semana                   integer not null,
    private Integer semana;
    //lei                      double precision,
    private BigDecimal lei;
    //alteracao_lei            double precision,
    private BigDecimal alteracaoLei;
    //percentual_lei           decimal(5, 2),
    private Float percentualLei;
    //contigenciado            double precision,
    private BigDecimal contigenciado;
    //percentual_contigenciado double precision,
    private Float percentualContingenciado;
    //cota                     double precision,
    private BigDecimal cota;
    //percentual_cota          decimal(5, 2),
    private Float percentualCota;
    //bloqueado                double precision,
    private BigDecimal bloqueado;
    //despesa_autorizada       double precision,
    private BigDecimal despesaAutorizada;
    //empenhado                double precision,
    private BigDecimal empenhado;
    //disponivel               double precision,
    private BigDecimal disponivel;
    //percentual_disponivel    decimal(5, 2),
    private Float percentualDisponivel;
    // liquidado               double precision,
    private BigDecimal liquidado;
    // percentual_liquidado    decimal(5, 2)
    private Float percentualLiquidado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getSemana() {
        return semana;
    }

    public void setSemana(Integer semana) {
        this.semana = semana;
    }

    public BigDecimal getLei() {
        return lei;
    }

    public void setLei(BigDecimal lei) {
        this.lei = lei;
    }

    public BigDecimal getAlteracaoLei() {
        return alteracaoLei;
    }

    public void setAlteracaoLei(BigDecimal alteracaoLei) {
        this.alteracaoLei = alteracaoLei;
    }

    public Float getPercentualLei() {
        return percentualLei;
    }

    public void setPercentualLei(Float percentualLei) {
        this.percentualLei = percentualLei;
    }

    public BigDecimal getContigenciado() {
        return contigenciado;
    }

    public void setContigenciado(BigDecimal contigenciado) {
        this.contigenciado = contigenciado;
    }

    public Float getPercentualContingenciado() {
        return percentualContingenciado;
    }

    public void setPercentualContingenciado(Float percentualContingenciado) {
        this.percentualContingenciado = percentualContingenciado;
    }

    public BigDecimal getCota() {
        return cota;
    }

    public void setCota(BigDecimal cota) {
        this.cota = cota;
    }

    public Float getPercentualCota() {
        return percentualCota;
    }

    public void setPercentualCota(Float percentualCota) {
        this.percentualCota = percentualCota;
    }

    public BigDecimal getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(BigDecimal bloqueado) {
        this.bloqueado = bloqueado;
    }

    public BigDecimal getDespesaAutorizada() {
        return despesaAutorizada;
    }

    public void setDespesaAutorizada(BigDecimal despesaAutorizada) {
        this.despesaAutorizada = despesaAutorizada;
    }

    public BigDecimal getEmpenhado() {
        return empenhado;
    }

    public void setEmpenhado(BigDecimal empenhado) {
        this.empenhado = empenhado;
    }

    public BigDecimal getDisponivel() {
        return disponivel;
    }

    public void setDisponivel(BigDecimal disponivel) {
        this.disponivel = disponivel;
    }

    public Float getPercentualDisponivel() {
        return percentualDisponivel;
    }

    public void setPercentualDisponivel(Float percentualDisponivel) {
        this.percentualDisponivel = percentualDisponivel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuadroDespesa that = (QuadroDespesa) o;
        return ano.equals(that.ano) &&
                mes.equals(that.mes) &&
                semana.equals(that.semana);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ano, mes, semana);
    }

    @Override
    public String toString() {
        return "QuadroDespesa{" +
                "id=" + id +
                ", ano=" + ano +
                ", mes=" + mes +
                ", semana=" + semana +
                ", lei=" + lei +
                ", alteracaoLei=" + alteracaoLei +
                ", percentualLei=" + percentualLei +
                ", contigenciado=" + contigenciado +
                ", percentualContingenciado=" + percentualContingenciado +
                ", cota=" + cota +
                ", percentualCota=" + percentualCota +
                ", bloqueado=" + bloqueado +
                ", despesaAutorizada=" + despesaAutorizada +
                ", empenhado=" + empenhado +
                ", disponivel=" + disponivel +
                ", percentualDisponivel=" + percentualDisponivel +
                ", liquidado=" + liquidado +
                ", percentualLiquidado=" + percentualLiquidado +
                '}';
    }

    public BigDecimal getLiquidado() {
        return liquidado;
    }

    public void setLiquidado(BigDecimal liquidado) {
        this.liquidado = liquidado;
    }

    public Float getPercentualLiquidado() {
        return percentualLiquidado;
    }

    public void setPercentualLiquidado(Float percentualLiquidado) {
        this.percentualLiquidado = percentualLiquidado;
    }
}
