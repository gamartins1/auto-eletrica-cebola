package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Where;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_CLIENTE")
public class Cliente {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_CLIENTE")
    private Long idCliente;

    private String nomeCliente;

    private String dataCadastroCliente;

    @OneToMany(targetEntity = TelefoneCliente.class)
    @Where(clause = "ativo = 1")
    private List <TelefoneCliente> telefoneCliente;

    @OneToOne
    private EnderecoCliente enderecoCliente;

    @OneToMany(targetEntity = Veiculo.class)
    @Where(clause = "ativo = 1")
    private List <Veiculo> veiculosCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDataCadastroCliente() {
        return dataCadastroCliente;
    }

    public void setDataCadastroCliente(String dataCadastroCliente) {
        this.dataCadastroCliente = dataCadastroCliente;
    }

    public List <TelefoneCliente> getTelefoneCliente() {
        return telefoneCliente;
    }

    public void setTelefoneCliente(List <TelefoneCliente> telefoneCliente) {
        this.telefoneCliente = telefoneCliente;
    }

    public void addTelefoneCliente(TelefoneCliente telefoneCliente) {
        if(this.telefoneCliente == null) {
            this.telefoneCliente = new ArrayList <>();
        }
        this.telefoneCliente.add(telefoneCliente);
    }

    public EnderecoCliente getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public List <Veiculo> getVeiculosCliente() {
        return veiculosCliente;
    }

    public void setVeiculosCliente(List <Veiculo> veiculosCliente) {
        this.veiculosCliente = veiculosCliente;
    }

    public void addVeiculoCliente(Veiculo veiculo) {
        if(this.veiculosCliente == null) {
            this.veiculosCliente = new ArrayList <>();
        }
        this.veiculosCliente.add(veiculo);
    }
}
