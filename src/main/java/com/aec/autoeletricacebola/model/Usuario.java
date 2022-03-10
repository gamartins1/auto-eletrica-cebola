package com.aec.autoeletricacebola.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity//Anotação para identificar uma tabela
@Table(name = "TB_USUARIO")
public class Usuario {

    @Id//Indica chave primária no banco
    @GeneratedValue(strategy = GenerationType.AUTO)//Valor auto-gerado
    @Column(name = "ID_USER")
    private Long idUsuario;

    private String nomeUsuario;

    private boolean ativo;

    private String userUsuario;

    private String senhaUsuario;

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getUserUsuario() {
        return userUsuario;
    }

    public void setUserUsuario(String userUsuario) {
        this.userUsuario = userUsuario;
    }

    public String getSenhaUsuario() {
        return senhaUsuario;
    }

    public void setSenhaUsuario(String senhaUsuario) {
        this.senhaUsuario = senhaUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Usuario) {
            Usuario usuario = (Usuario) obj;
            return usuario.getUserUsuario().equals(this.userUsuario) && usuario.getNomeUsuario().equals(this.nomeUsuario) && usuario.getSenhaUsuario().equals(this.senhaUsuario);
        }
        return super.equals(obj);
    }
}
