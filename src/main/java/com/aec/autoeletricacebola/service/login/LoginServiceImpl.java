package com.aec.autoeletricacebola.service.login;

import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.repository.UsuarioRepository;
import com.aec.autoeletricacebola.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UsuarioService usuarioService;

    @Override
    public Usuario findUserLogin(Usuario usuario) {

        return usuarioService.findUserLogin(usuario.getUserUsuario(), usuario.getSenhaUsuario());
    }
}
