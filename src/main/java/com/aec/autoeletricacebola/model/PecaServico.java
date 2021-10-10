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
@Table(name = "TB_PECA_SERVICO")
public class PecaServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_PECA_SERVICO")
    private Long idPecaServico;

    private int quantidadePecaServico;

    @ManyToOne
    @JoinColumn(name = "peca_estoque_id_peca_estoque")
    private PecaEstoque pecaEstoque;

    @ManyToOne
    @JoinColumn(name = "servico_id_servico")
    private Servico servico;

    public Long getIdPecaServico() {
        return idPecaServico;
    }

    public void setIdPecaServico(Long idPecaServico) {
        this.idPecaServico = idPecaServico;
    }

    public int getQuantidadePecaServico() {
        return quantidadePecaServico;
    }

    public void setQuantidadePecaServico(int quantidadePecaServico) {
        this.quantidadePecaServico = quantidadePecaServico;
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
