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
    private Long id;

    private String nome;

    private int quantidade;

    private String tempoGarantia;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorCompra;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorVenda;

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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getTempoGarantia() {
        return tempoGarantia;
    }

    public void setTempoGarantia(String tempoGarantia) {
        this.tempoGarantia = tempoGarantia;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(double valorVenda) {
        this.valorVenda = valorVenda;
    }
}
