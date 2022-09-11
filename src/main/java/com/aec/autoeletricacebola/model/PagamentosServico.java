package com.aec.autoeletricacebola.model;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_TIME_FORMAT;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.time.LocalDateTime;
import java.util.Date;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_PAGAMENTOS_SERVICO")
public class PagamentosServico {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_PAGAMENTO_SERVICO")
    private Long idPagamentoServico;

    private Date dataPagamentoServico;

    //Apenas para obter o dado de forma mais fácil, mantenho o atributo acima para rastreabilidade com o objeto de data
    private String dataRecebimentoPagamentoServico;

    @Column(columnDefinition="Decimal(10,2) default '000.00'")
    private double valorPagamentoServico;

    @ManyToOne
    @JoinColumn(name = "servico_id_servico")
    private Servico servico;


    public PagamentosServico(double valorPagamentoServico, Servico servico) {
        this.valorPagamentoServico = valorPagamentoServico;
        this.servico = servico;
        this.dataPagamentoServico = new Date(System.currentTimeMillis());
        this.dataRecebimentoPagamentoServico = LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT);
    }

    public Long getIdPagamentoServico() {
        return idPagamentoServico;
    }

    public void setIdPagamentoServico(Long idPagamentoServico) {
        this.idPagamentoServico = idPagamentoServico;
    }

    public Date getDataPagamentoServico() {
        return dataPagamentoServico;
    }

    public void setDataPagamentoServico(Date dataPagamentoServico) {
        this.dataPagamentoServico = dataPagamentoServico;
    }

    public double getValorPagamentoServico() {
        return valorPagamentoServico;
    }

    public void setValorPagamentoServico(double valorPagamentoServico) {
        this.valorPagamentoServico = valorPagamentoServico;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public String getDataRecebimentoPagamentoServico() {
        return dataRecebimentoPagamentoServico;
    }

    public void setDataRecebimentoPagamentoServico(String dataRecebimentoPagamentoServico) {
        this.dataRecebimentoPagamentoServico = dataRecebimentoPagamentoServico;
    }


}
