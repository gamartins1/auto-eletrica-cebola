package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_DESCRICAO_SERVICO")
public class DescricaoServico {
    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_DESC_SERVICO")
    private Long id;

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
