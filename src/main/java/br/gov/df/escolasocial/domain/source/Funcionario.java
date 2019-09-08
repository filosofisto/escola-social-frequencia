package br.gov.df.escolasocial.domain.source;

import java.math.BigDecimal;
import java.util.Date;

public class Funcionario {

    private Long codCpf;
    private String cpf;
    private String nome;
    private String nomePai;
    private String nomeMae;
    private String sexo; // Texto Curto => FEMININO ou MASCULINO
    private String estadoCivil; // Texto Curto => Solteiro(a), Casado(a), etc
    private Date dataNascimento;
    private String cartEstrangeiro;
    private String pisPasep;
    private Date dataPisPasep;
    private String rg;
    private String rgOrgaoExpedidor;
    private String rgUfExpedidor;
    private Date rgDataExpedicao;
    private String tituloEleitor;
    private Integer zonaEleitoral;
    private Integer secaoEleitoral;
    private String municipioTituloEleitoral;
    private String ufTituloEleitoral;
    private String endereco;
    private String cidade;
    private String estado;
    private String bairro;
    private Integer cep;
    private String telefoneResidencial;
    private String telefoneCelular;
    private String email;
    private boolean possuiFilhos;
    private boolean pne;
    private String deficiencia; // QualDef
    private String nacionalidade;
    private String paisOrigem;
    private String naturalidade;
    private String ufNascimento;
    private String raca;
    private String cnh;
    private String ufCnh;
    private String categoriaCnh;
    private Date dataCnh;
    private Date validadeCnh;
    private String cartReservista;
    private String serieCartReservista;
    private String orgaoCarteiraReservista;
    private String categoriaCarteiraReservista;
    private Integer anoServicoMilitar;
    private Long certDispensaIncorporacao;
    private String unidadeMilitar;
    private String ufServicoMilitar;
    private String conjuge;
    private String localFoto;
    private String area;
    private String subArea;
    private String status;
    private Date dataDesligamento;
    private String unidadeFabrica;
    private Date dataInclusao;
    private Long numeroIdentificacaoSocial;
    private String usuarioRealizouCadastro;
    private Date dataRegistro;
    private boolean responsavelNucleoFamiliar;
    private String carteiraTrabalho;
    private Integer serieCartTrabalho;
    private BigDecimal renda;
    private boolean acl; // Define se o adolescente possui conflito com a lei.
    private boolean pcd; // Define se a pessoa concorreu à vaga de deficiente
    private boolean pi; // Define se a pessoa concorreu à vaga de idoso
    private boolean cg; // Define se a pessoa concorreu à vaga de concorrência geral
    private String turno; // MATUTINO, VESPERTINO, NOTURNO
    private Long codFamilia; // Define o código familiar no cadastro único.
    private Integer matricula;
    private Date horaRegistro;
    private Date limiteAtualizacaoCadastral;
    private Date dataInicioAtividades;

    public Long getCodCpf() {
        return codCpf;
    }

    public void setCodCpf(Long codCpf) {
        this.codCpf = codCpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getCep() {
        return cep;
    }

    public void setCep(Integer cep) {
        this.cep = cep;
    }

    public boolean isPossuiFilhos() {
        return possuiFilhos;
    }

    public void setPossuiFilhos(boolean possuiFilhos) {
        this.possuiFilhos = possuiFilhos;
    }

    public boolean isPne() {
        return pne;
    }

    public void setPne(boolean pne) {
        this.pne = pne;
    }

    public String getDeficiencia() {
        return deficiencia;
    }

    public void setDeficiencia(String deficiencia) {
        this.deficiencia = deficiencia;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public String getUfNascimento() {
        return ufNascimento;
    }

    public void setUfNascimento(String ufNascimento) {
        this.ufNascimento = ufNascimento;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSubArea() {
        return subArea;
    }

    public void setSubArea(String subArea) {
        this.subArea = subArea;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataDesligamento() {
        return dataDesligamento;
    }

    public void setDataDesligamento(Date dataDesligamento) {
        this.dataDesligamento = dataDesligamento;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public void setDataRegistro(Date dataRegistro) {
        this.dataRegistro = dataRegistro;
    }

    public boolean isResponsavelNucleoFamiliar() {
        return responsavelNucleoFamiliar;
    }

    public void setResponsavelNucleoFamiliar(boolean responsavelNucleoFamiliar) {
        this.responsavelNucleoFamiliar = responsavelNucleoFamiliar;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

    public boolean isAcl() {
        return acl;
    }

    public void setAcl(boolean acl) {
        this.acl = acl;
    }

    public boolean isPcd() {
        return pcd;
    }

    public void setPcd(boolean pcd) {
        this.pcd = pcd;
    }

    public boolean isPi() {
        return pi;
    }

    public void setPi(boolean pi) {
        this.pi = pi;
    }

    public boolean isCg() {
        return cg;
    }

    public void setCg(boolean cg) {
        this.cg = cg;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getCartEstrangeiro() {
        return cartEstrangeiro;
    }

    public void setCartEstrangeiro(String cartEstrangeiro) {
        this.cartEstrangeiro = cartEstrangeiro;
    }

    public String getPisPasep() {
        return pisPasep;
    }

    public void setPisPasep(String pisPasep) {
        this.pisPasep = pisPasep;
    }

    public Date getDataPisPasep() {
        return dataPisPasep;
    }

    public void setDataPisPasep(Date dataPisPasep) {
        this.dataPisPasep = dataPisPasep;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRgOrgaoExpedidor() {
        return rgOrgaoExpedidor;
    }

    public void setRgOrgaoExpedidor(String rgOrgaoExpedidor) {
        this.rgOrgaoExpedidor = rgOrgaoExpedidor;
    }

    public String getRgUfExpedidor() {
        return rgUfExpedidor;
    }

    public void setRgUfExpedidor(String rgUfExpedidor) {
        this.rgUfExpedidor = rgUfExpedidor;
    }

    public Date getRgDataExpedicao() {
        return rgDataExpedicao;
    }

    public void setRgDataExpedicao(Date rgDataExpedicao) {
        this.rgDataExpedicao = rgDataExpedicao;
    }

    public String getTituloEleitor() {
        return tituloEleitor;
    }

    public void setTituloEleitor(String tituloEleitor) {
        this.tituloEleitor = tituloEleitor;
    }

    public Integer getZonaEleitoral() {
        return zonaEleitoral;
    }

    public void setZonaEleitoral(Integer zonaEleitoral) {
        this.zonaEleitoral = zonaEleitoral;
    }

    public Integer getSecaoEleitoral() {
        return secaoEleitoral;
    }

    public void setSecaoEleitoral(Integer secaoEleitoral) {
        this.secaoEleitoral = secaoEleitoral;
    }

    public String getMunicipioTituloEleitoral() {
        return municipioTituloEleitoral;
    }

    public void setMunicipioTituloEleitoral(String municipioTituloEleitoral) {
        this.municipioTituloEleitoral = municipioTituloEleitoral;
    }

    public String getUfTituloEleitoral() {
        return ufTituloEleitoral;
    }

    public void setUfTituloEleitoral(String ufTituloEleitoral) {
        this.ufTituloEleitoral = ufTituloEleitoral;
    }

    public String getTelefoneResidencial() {
        return telefoneResidencial;
    }

    public void setTelefoneResidencial(String telefoneResidencial) {
        this.telefoneResidencial = telefoneResidencial;
    }

    public String getTelefoneCelular() {
        return telefoneCelular;
    }

    public void setTelefoneCelular(String telefoneCelular) {
        this.telefoneCelular = telefoneCelular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getUfCnh() {
        return ufCnh;
    }

    public void setUfCnh(String ufCnh) {
        this.ufCnh = ufCnh;
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public void setCategoriaCnh(String categoriaCnh) {
        this.categoriaCnh = categoriaCnh;
    }

    public Date getDataCnh() {
        return dataCnh;
    }

    public void setDataCnh(Date dataCnh) {
        this.dataCnh = dataCnh;
    }

    public Date getValidadeCnh() {
        return validadeCnh;
    }

    public void setValidadeCnh(Date validadeCnh) {
        this.validadeCnh = validadeCnh;
    }

    public String getCartReservista() {
        return cartReservista;
    }

    public void setCartReservista(String cartReservista) {
        this.cartReservista = cartReservista;
    }

    public String getSerieCartReservista() {
        return serieCartReservista;
    }

    public void setSerieCartReservista(String serieCartReservista) {
        this.serieCartReservista = serieCartReservista;
    }

    public String getOrgaoCarteiraReservista() {
        return orgaoCarteiraReservista;
    }

    public void setOrgaoCarteiraReservista(String orgaoCarteiraReservista) {
        this.orgaoCarteiraReservista = orgaoCarteiraReservista;
    }

    public String getCategoriaCarteiraReservista() {
        return categoriaCarteiraReservista;
    }

    public void setCategoriaCarteiraReservista(String categoriaCarteiraReservista) {
        this.categoriaCarteiraReservista = categoriaCarteiraReservista;
    }

    public Integer getAnoServicoMilitar() {
        return anoServicoMilitar;
    }

    public void setAnoServicoMilitar(Integer anoServicoMilitar) {
        this.anoServicoMilitar = anoServicoMilitar;
    }

    public Long getCertDispensaIncorporacao() {
        return certDispensaIncorporacao;
    }

    public void setCertDispensaIncorporacao(Long certDispensaIncorporacao) {
        this.certDispensaIncorporacao = certDispensaIncorporacao;
    }

    public String getUnidadeMilitar() {
        return unidadeMilitar;
    }

    public void setUnidadeMilitar(String unidadeMilitar) {
        this.unidadeMilitar = unidadeMilitar;
    }

    public String getUfServicoMilitar() {
        return ufServicoMilitar;
    }

    public void setUfServicoMilitar(String ufServicoMilitar) {
        this.ufServicoMilitar = ufServicoMilitar;
    }

    public String getConjuge() {
        return conjuge;
    }

    public void setConjuge(String conjuge) {
        this.conjuge = conjuge;
    }

    public String getLocalFoto() {
        return localFoto;
    }

    public void setLocalFoto(String localFoto) {
        this.localFoto = localFoto;
    }

    public String getUnidadeFabrica() {
        return unidadeFabrica;
    }

    public void setUnidadeFabrica(String unidadeFabrica) {
        this.unidadeFabrica = unidadeFabrica;
    }

    public Long getNumeroIdentificacaoSocial() {
        return numeroIdentificacaoSocial;
    }

    public void setNumeroIdentificacaoSocial(Long numeroIdentificacaoSocial) {
        this.numeroIdentificacaoSocial = numeroIdentificacaoSocial;
    }

    public String getUsuarioRealizouCadastro() {
        return usuarioRealizouCadastro;
    }

    public void setUsuarioRealizouCadastro(String usuarioRealizouCadastro) {
        this.usuarioRealizouCadastro = usuarioRealizouCadastro;
    }

    public String getCarteiraTrabalho() {
        return carteiraTrabalho;
    }

    public void setCarteiraTrabalho(String carteiraTrabalho) {
        this.carteiraTrabalho = carteiraTrabalho;
    }

    public Integer getSerieCartTrabalho() {
        return serieCartTrabalho;
    }

    public void setSerieCartTrabalho(Integer serieCartTrabalho) {
        this.serieCartTrabalho = serieCartTrabalho;
    }

    public Long getCodFamilia() {
        return codFamilia;
    }

    public void setCodFamilia(Long codFamilia) {
        this.codFamilia = codFamilia;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Date getHoraRegistro() {
        return horaRegistro;
    }

    public void setHoraRegistro(Date horaRegistro) {
        this.horaRegistro = horaRegistro;
    }

    public Date getLimiteAtualizacaoCadastral() {
        return limiteAtualizacaoCadastral;
    }

    public void setLimiteAtualizacaoCadastral(Date limiteAtualizacaoCadastral) {
        this.limiteAtualizacaoCadastral = limiteAtualizacaoCadastral;
    }

    public Date getDataInicioAtividades() {
        return dataInicioAtividades;
    }

    public void setDataInicioAtividades(Date dataInicioAtividades) {
        this.dataInicioAtividades = dataInicioAtividades;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", sexo='" + sexo + '\'' +
                ", estadoCivil='" + estadoCivil + '\'' +
                '}';
    }

    public void print() {
        System.out.println(this);
    }
}
