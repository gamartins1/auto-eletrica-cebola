package com.aec.autoeletricacebola.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_PECA_SERVICO")
public class PecaServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    private Long id;

    private int quantidade;

    @ManyToOne
    private PecaEstoque pecaEstoque;

    @ManyToOne
    private Servico servico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public PecaEstoque getPecaEstoque() {
        return pecaEstoque;
    }

    public void setPecaEstoque(PecaEstoque pecaEstoque) {
        this.pecaEstoque = pecaEstoque;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
