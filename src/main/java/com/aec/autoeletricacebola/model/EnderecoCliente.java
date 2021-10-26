package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_ENDERECO_CLIENTE")
public class EnderecoCliente {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_END_CLIENTE")
    private Long idEnderecoCliente;

    private String ruaEnderecoCliente;

    private String numeroEnderecoCliente;

    private String cepEnderecoCliente;

    private String bairroEnderecoCliente;

    private String cidadeEnderecoCliente;

    @OneToOne
    private Cliente cliente;

    public Long getIdEnderecoCliente() {
        return idEnderecoCliente;
    }

    public void setIdEnderecoCliente(Long idEnderecoCliente) {
        this.idEnderecoCliente = idEnderecoCliente;
    }

    public String getRuaEnderecoCliente() {
        return ruaEnderecoCliente;
    }

    public void setRuaEnderecoCliente(String ruaEnderecoCliente) {
        this.ruaEnderecoCliente = ruaEnderecoCliente;
    }

    public String getNumeroEnderecoCliente() {
        return numeroEnderecoCliente;
    }

    public void setNumeroEnderecoCliente(String numeroEnderecoCliente) {
        this.numeroEnderecoCliente = numeroEnderecoCliente;
    }

    public String getCepEnderecoCliente() {
        return cepEnderecoCliente;
    }

    public void setCepEnderecoCliente(String cepEnderecoCliente) {
        this.cepEnderecoCliente = cepEnderecoCliente;
    }

    public String getBairroEnderecoCliente() {
        return bairroEnderecoCliente;
    }

    public void setBairroEnderecoCliente(String bairroEnderecoCliente) {
        this.bairroEnderecoCliente = bairroEnderecoCliente;
    }

    public String getCidadeEnderecoCliente() {
        return cidadeEnderecoCliente;
    }

    public void setCidadeEnderecoCliente(String cidadeEnderecoCliente) {
        this.cidadeEnderecoCliente = cidadeEnderecoCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
