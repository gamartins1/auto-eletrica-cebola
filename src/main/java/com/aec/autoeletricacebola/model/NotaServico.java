package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_NOTA_SERVICO")
public class NotaServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_NOTA_SERVICO")
    private Long idNotaServico;

    @Column(columnDefinition="Decimal(10,2) default '00.00'")
    private double notaServico;

    @OneToOne
    private Servico servico;

    public Long getIdNotaServico() {
        return idNotaServico;
    }

    public void setIdNotaServico(Long idNotaServico) {
        this.idNotaServico = idNotaServico;
    }

    public double getNotaServico() {
        return notaServico;
    }

    public void setNotaServico(double notaServico) {
        this.notaServico = notaServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public NotaServico(double notaServico, Servico servico) {
        this.notaServico = notaServico;
        this.servico = servico;
    }

    public NotaServico() {

    }
}
