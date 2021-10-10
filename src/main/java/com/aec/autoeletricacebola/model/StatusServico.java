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
@Table(name = "TB_STATUS_SERVICO")
public class StatusServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_STATUS_SERVICO")
    private Long idStatusServico;

    private String statusServico;

    private String dataHoraStatusServico;

    @ManyToOne
    @JoinColumn(name = "servico_id_servico")
    private Servico servico;

    public Long getIdStatusServico() {
        return idStatusServico;
    }

    public void setIdStatusServico(Long idStatusServico) {
        this.idStatusServico = idStatusServico;
    }

    public String getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(String statusServico) {
        this.statusServico = statusServico;
    }

    public String getDataHoraStatusServico() {
        return dataHoraStatusServico;
    }

    public void setDataHoraStatusServico(String dataHoraStatusServico) {
        this.dataHoraStatusServico = dataHoraStatusServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
}
