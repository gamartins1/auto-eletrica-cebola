package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Where;
import org.hibernate.annotations.WhereJoinTable;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_CLIENTE")
    private Long id;

    private String nome;

    private String dataCadastro;

    @OneToMany(targetEntity = TelefoneCliente.class)
    @Where(clause = "ativo = 1")
    private List <TelefoneCliente> telefoneCliente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List <TelefoneCliente> getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(List <TelefoneCliente> telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public void addTelefoneCliente(TelefoneCliente telefoneCliente) {
        if(this.telefoneCliente == null) {
            this.telefoneCliente = new ArrayList <>();
        }
        this.telefoneCliente.add(telefoneCliente);
    }
}
