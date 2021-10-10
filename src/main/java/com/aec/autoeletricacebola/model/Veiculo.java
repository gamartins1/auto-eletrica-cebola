package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_VEICULO")
public class Veiculo {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_VEICULO")
    private Long idVeiculo;

    private String modeloVeiculo;

    private String placaVeiculo;

    private String observacoesVeiculo;

    private boolean ativo;

    @ManyToOne
    private Cliente cliente;

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getObservacoesVeiculo() {
        return observacoesVeiculo;
    }

    public void setObservacoesVeiculo(String observacoesVeiculo) {
        this.observacoesVeiculo = observacoesVeiculo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
