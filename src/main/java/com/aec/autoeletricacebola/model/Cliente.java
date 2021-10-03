package com.aec.autoeletricacebola.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    private Long id;

    private String nome;

    private String dataCadastro;

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
}
