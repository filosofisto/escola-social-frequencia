package br.gov.df.escolasocial.domain.dest;

import java.util.Date;

public class AssiduidadeDest {

    private Long id;
    private Long idSic;
    private Long matricula;
    private Date data;
    private Integer presenca;
    private Integer falta;
    private String motivo;
    private boolean recessoOuFeriado;

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

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getPresenca() {
        return presenca;
    }

    public void setPresenca(Integer presenca) {
        this.presenca = presenca;
    }

    public Integer getFalta() {
        return falta;
    }

    public void setFalta(Integer falta) {
        this.falta = falta;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public boolean isRecessoOuFeriado() {
        return recessoOuFeriado;
    }

    public void setRecessoOuFeriado(boolean recessoOuFeriado) {
        this.recessoOuFeriado = recessoOuFeriado;
    }
}
