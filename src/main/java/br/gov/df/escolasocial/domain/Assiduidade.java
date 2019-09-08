package br.gov.df.escolasocial.domain;

import java.util.Date;

public class Assiduidade {

    private Long codigo; // cda
    private Long matricula;
    private Date data;
    private Date horaEntradaManha;
    private Date horaSaidaManha;
    private Date horaEntradaTarde;
    private Date horaSaidaTarde;
    private String motivo;
    private String usuario;
    private Date dataRegistro;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
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

    public Date getHoraEntradaManha() {
        return horaEntradaManha;
    }

    public void setHoraEntradaManha(Date horaEntradaManha) {
        this.horaEntradaManha = horaEntradaManha;
    }

    public Date getHoraSaidaManha() {
        return horaSaidaManha;
    }

    public void setHoraSaidaManha(Date horaSaidaManha) {
        this.horaSaidaManha = horaSaidaManha;
    }

    public Date getHoraEntradaTarde() {
        return horaEntradaTarde;
    }

    public void setHoraEntradaTarde(Date horaEntradaTarde) {
        this.horaEntradaTarde = horaEntradaTarde;
    }

    public Date getHoraSaidaTarde() {
        return horaSaidaTarde;
    }

    public void setHoraSaidaTarde(Date horaSaidaTarde) {
        this.horaSaidaTarde = horaSaidaTarde;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    @Override
    public String toString() {
        return "Assiduidade{" +
                "codigo=" + codigo +
                ", matricula=" + matricula +
                ", data=" + data +
                ", horaEntradaManha=" + horaEntradaManha +
                ", horaSaidaManha=" + horaSaidaManha +
                ", horaEntradaTarde=" + horaEntradaTarde +
                ", horaSaidaTarde=" + horaSaidaTarde +
                ", motivo='" + motivo + '\'' +
                ", usuario='" + usuario + '\'' +
                ", dataRegistro=" + dataRegistro +
                '}';
    }

    public void print() {
        System.out.println(this);
    }
}
