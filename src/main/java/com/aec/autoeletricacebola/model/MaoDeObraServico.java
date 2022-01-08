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
    private Long idMaoDeObraServico;

    @Lob//Indica texto longo no banco
    private String descricaoMaoDeObraServico;

    private String tempoGarantiaMaoDeObraServico;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorMaoDeObraServico;

    @ManyToOne
    @JoinColumn(name = "servico_id_servico")
    private Servico servico;

    @ManyToOne
    @JoinColumn(name = "mecanico_id_mecanico")
    private Mecanico mecanico;

    public Long getIdMaoDeObraServico() {
        return idMaoDeObraServico;
    }

    public void setIdMaoDeObraServico(Long idMaoDeObraServico) {
        this.idMaoDeObraServico = idMaoDeObraServico;
    }

    public String getDescricaoMaoDeObraServico() {
        return descricaoMaoDeObraServico;
    }

    public void setDescricaoMaoDeObraServico(String descricaoMaoDeObraServico) {
        this.descricaoMaoDeObraServico = descricaoMaoDeObraServico;
    }

    public String getTempoGarantiaMaoDeObraServico() {
        return tempoGarantiaMaoDeObraServico;
    }

    public void setTempoGarantiaMaoDeObraServico(String tempoGarantiaMaoDeObraServico) {
        this.tempoGarantiaMaoDeObraServico = tempoGarantiaMaoDeObraServico;
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

    public double getValorMaoDeObraServico() {
        return valorMaoDeObraServico;
    }

    public void setValorMaoDeObraServico(double valorMaoDeObraServico) {
        this.valorMaoDeObraServico = valorMaoDeObraServico;
    }
}
