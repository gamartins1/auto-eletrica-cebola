package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_MAO_DE_OBRA_SERVICO")
public class MaoDeObraServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_MAO_OBRA_SERVICO")
    private Long id;

    @Lob//Indica texto longo no banco
    private String descricao;

    private String tempoGarantia;

    @ManyToOne
    @JoinColumn(name = "servico_id_servico")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "mecanico_id_mecanico")
    private Mecanico mecanico;

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

    public String getTempoGarantia() {
        return tempoGarantia;
    }

    public void setTempoGarantia(String tempoGarantia) {
        this.tempoGarantia = tempoGarantia;
    }

    public Mecanico getMecanico() {
        return mecanico;
    }

    public void setMecanico(Mecanico mecanico) {
        this.mecanico = mecanico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
