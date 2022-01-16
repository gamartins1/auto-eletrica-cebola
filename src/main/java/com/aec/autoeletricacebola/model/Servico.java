package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.sql.Date;
import java.util.List;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_SERVICO")
public class Servico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_SERVICO")
    private Long idServico;

    private String statusAtualServico;

    private String aberturaServico;

    private String encerramentoServico;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorFinalServico;

    private Date dataAberturaServico;

    private Date dataEncerramentoServico;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    @OneToMany(targetEntity = StatusServico.class)
    private List<StatusServico> stausServico;

    @OneToMany(targetEntity = DescricaoServico.class)
    private List<DescricaoServico> descricaoServico;

    @OneToMany(targetEntity = PecaServico.class)
    private List<PecaServico> pecasServico;

    @OneToMany(targetEntity = MaoDeObraServico.class)
    private List maoDeObraServico;

    @OneToOne
    private NotaServico notaServico;

    public Long getIdServico() {
        return idServico;
    }

    public void setIdServico(Long idServico) {
        this.idServico = idServico;
    }

    public String getStatusAtualServico() {
        return statusAtualServico;
    }

    public void setStatusAtualServico(String statusAtualServico) {
        this.statusAtualServico = statusAtualServico;
    }

    public String getAberturaServico() {
        return aberturaServico;
    }

    public void setAberturaServico(String aberturaServico) {
        this.aberturaServico = aberturaServico;
    }

    public String getEncerramentoServico() {
        return encerramentoServico;
    }

    public void setEncerramentoServico(String encerramentoServico) {
        this.encerramentoServico = encerramentoServico;
    }

    public double getValorFinalServico() {
        return valorFinalServico;
    }

    public void setValorFinalServico(double valorFinalServico) {
        this.valorFinalServico = valorFinalServico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public List<StatusServico> getStausServico() {
        return stausServico;
    }

    public void setStausServico(List<StatusServico> stausServico) {
        this.stausServico = stausServico;
    }

    public List<DescricaoServico> getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(List<DescricaoServico> descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public List<PecaServico> getPecasServico() {
        return pecasServico;
    }

    public void setPecasServico(List<PecaServico> pecasServico) {
        this.pecasServico = pecasServico;
    }

    public List getMaoDeObraServico() {
        return maoDeObraServico;
    }

    public void setMaoDeObraServico(List maoDeObraServico) {
        this.maoDeObraServico = maoDeObraServico;
    }

    public NotaServico getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(NotaServico notaServico) {
        this.notaServico = notaServico;
    }

    public Date getDataAberturaServico() {
        return dataAberturaServico;
    }

    public void setDataAberturaServico(Date dataAberturaServico) {
        this.dataAberturaServico = dataAberturaServico;
    }

    public Date getDataEncerramentoServico() {
        return dataEncerramentoServico;
    }

    public void setDataEncerramentoServico(Date dataEncerramentoServico) {
        this.dataEncerramentoServico = dataEncerramentoServico;
    }

    public Servico(Cliente cliente, Veiculo veiculo, String aberturaServico, String statusAtualServico, Date dataAberturaServico) {
        this.aberturaServico = aberturaServico;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.statusAtualServico = statusAtualServico;
        this.dataAberturaServico = dataAberturaServico;
    }

    public Servico() {

    }
}
