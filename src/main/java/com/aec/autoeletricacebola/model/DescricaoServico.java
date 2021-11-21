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
    private Long idDescricaoServico;

    private String descricaoDoServico;

    @ManyToOne
    @JoinColumn(name = "servico_id")
    private Servico servico;

    public Long getIdDescricaoServico() {
        return idDescricaoServico;
    }

    public void setIdDescricaoServico(Long idDescricaoServico) {
        this.idDescricaoServico = idDescricaoServico;
    }

    public String getDescricaoDoServico() {
        return descricaoDoServico;
    }

    public void setDescricaoDoServico(String descricaoDoServico) {
        this.descricaoDoServico = descricaoDoServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public DescricaoServico() {
    }

    public DescricaoServico(String descricaoDoServico, Servico servico) {
        this.descricaoDoServico = descricaoDoServico;
        this.servico = servico;
    }
}
