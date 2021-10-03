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

import java.util.List;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_SERVICO")
public class Servico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    private Long id;

    private String status;

    private String abertura;

    private String encerramento;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valor;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Veiculo veiculo;

    @OneToMany(targetEntity = StatusServico.class)
    private List stausServico;

    @OneToMany(targetEntity = DescricaoServico.class)
    private List descricaoServico;

    @OneToMany(targetEntity = PecaServico.class)
    private List pecasServico;

    @OneToMany(targetEntity = MaoDeObraServico.class)
    private List maoDeObraServico;

    @OneToOne
    private NotaServico notaServico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getEncerramento() {
        return encerramento;
    }

    public void setEncerramento(String encerramento) {
        this.encerramento = encerramento;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

    public List getStausServico() {
        return stausServico;
    }

    public void setStausServico(List stausServico) {
        this.stausServico = stausServico;
    }

    public List getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(List descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public List getPecasServico() {
        return pecasServico;
    }

    public void setPecasServico(List pecasServico) {
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
}
