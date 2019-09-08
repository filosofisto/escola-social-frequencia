package br.gov.df.escolasocial.domain.dest;

import java.util.Date;

public class Absenteismo {

    private Long id;
    private Date data;
    private Integer matriculados;
    private Integer presentes;
    private Integer absenteismo;
    private Double percentual;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getMatriculados() {
        return matriculados;
    }

    public void setMatriculados(Integer matriculados) {
        this.matriculados = matriculados;
    }

    public Integer getPresentes() {
        return presentes;
    }

    public void setPresentes(Integer presentes) {
        this.presentes = presentes;
    }

    public Integer getAbsenteismo() {
        return absenteismo;
    }

    public void setAbsenteismo(Integer absenteismo) {
        this.absenteismo = absenteismo;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }
}
