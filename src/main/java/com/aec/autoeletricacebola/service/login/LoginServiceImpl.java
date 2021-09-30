package com.aec.autoeletricacebola.service.login;

import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario findUserLogin(Usuario usuario) {

        return usuarioRepository.findUserLogin(usuario.getUser(), usuario.getSenha());
    }
}
