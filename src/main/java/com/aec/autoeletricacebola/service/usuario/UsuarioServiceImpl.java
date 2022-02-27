package com.aec.autoeletricacebola.service.usuario;

import java.util.List;

import com.aec.autoeletricacebola.model.Usuario;
import com.aec.autoeletricacebola.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List <Usuario> findAll() {
        return this.usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return this.usuarioRepository.findById(id).get();
    }

    @Override
    public Usuario findByUser(String usuario) {
        return this.usuarioRepository.findByUser(usuario);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUserLogin(String usuario, String senha) {
        return this.usuarioRepository.findUserLogin(usuario, senha);
    }
}
