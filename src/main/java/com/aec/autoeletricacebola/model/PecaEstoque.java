package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_PECA_ESTOQUE")
public class PecaEstoque {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_PECA_ESTOQUE")
    private Long idPecaEstoque;

    private String nomePecaEstoque;

    private int quantidadePecaEstoque;

    private String tempoGarantiaPecaEstoque;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorCompraPecaEstoque;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorVendaPecaEstoque;

    private boolean ativo;

    public Long getIdPecaEstoque() {
        return idPecaEstoque;
    }

    public void setIdPecaEstoque(Long idPecaEstoque) {
        this.idPecaEstoque = idPecaEstoque;
    }

    public String getNomePecaEstoque() {
        return nomePecaEstoque;
    }

    public void setNomePecaEstoque(String nomePecaEstoque) {
        this.nomePecaEstoque = nomePecaEstoque;
    }

    public int getQuantidadePecaEstoque() {
        return quantidadePecaEstoque;
    }

    public void setQuantidadePecaEstoque(int quantidadePecaEstoque) {
        this.quantidadePecaEstoque = quantidadePecaEstoque;
    }

    public void addPecaEstoque(int quantidadePecaEstoque) {
        this.quantidadePecaEstoque += quantidadePecaEstoque;
    }

    public String getTempoGarantiaPecaEstoque() {
        return tempoGarantiaPecaEstoque;
    }

    public void setTempoGarantiaPecaEstoque(String tempoGarantiaPecaEstoque) {
        this.tempoGarantiaPecaEstoque = tempoGarantiaPecaEstoque;
    }

    public double getValorCompraPecaEstoque() {
        return valorCompraPecaEstoque;
    }

    public void setValorCompraPecaEstoque(double valorCompraPecaEstoque) {
        this.valorCompraPecaEstoque = valorCompraPecaEstoque;
    }

    public double getValorVendaPecaEstoque() {
        return valorVendaPecaEstoque;
    }

    public void setValorVendaPecaEstoque(double valorVendaPecaEstoque) {
        this.valorVendaPecaEstoque = valorVendaPecaEstoque;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getNomeAndValor() {
        return this.nomePecaEstoque + " - " + this.valorVendaPecaEstoque;
    }
}
