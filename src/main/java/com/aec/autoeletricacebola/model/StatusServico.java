package com.aec.autoeletricacebola.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "TB_STATUS_SERVICO")
public class StatusServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    private Long id;

    private String status;

    private String dataHoraStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDataHoraStatus() {
        return dataHoraStatus;
    }

    public void setDataHoraStatus(String dataHoraStatus) {
        this.dataHoraStatus = dataHoraStatus;
    }
}
