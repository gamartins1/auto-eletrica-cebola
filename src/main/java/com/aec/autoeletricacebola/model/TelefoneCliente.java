package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_TELEFONE_CLIENTE")
public class TelefoneCliente {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_TEL_CLIENTE")
    private Long idTelefoneCliente;

    private String numeroTelefoneCliente;

    private boolean ativo;

    private String observacoesTelefoneCliente;

    @ManyToOne
    @JoinColumn(name = "cliente_id_cliente")
    private Cliente cliente;

    public Long getIdTelefoneCliente() {
        return idTelefoneCliente;
    }

    public void setIdTelefoneCliente(Long idTelefoneCliente) {
        this.idTelefoneCliente = idTelefoneCliente;
    }

    public String getNumeroTelefoneCliente() {
        return numeroTelefoneCliente;
    }

    public void setNumeroTelefoneCliente(String numeroTelefoneCliente) {
        this.numeroTelefoneCliente = numeroTelefoneCliente;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getObservacoesTelefoneCliente() {
        return observacoesTelefoneCliente;
    }

    public void setObservacoesTelefoneCliente(String observacoesTelefoneCliente) {
        this.observacoesTelefoneCliente = observacoesTelefoneCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
