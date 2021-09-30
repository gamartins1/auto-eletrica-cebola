package com.aec.autoeletricacebola.service.usuario;

import java.util.List;

import com.aec.autoeletricacebola.model.Usuario;

public interface UsuarioService {

    List <Usuario> findAll();
    Usuario findById(Long id);
    Usuario findByUser(String usuario);
    Usuario save(Usuario usuario);
}
