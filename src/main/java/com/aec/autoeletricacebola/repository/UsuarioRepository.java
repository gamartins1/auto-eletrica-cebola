package com.aec.autoeletricacebola.repository;

import com.aec.autoeletricacebola.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT * FROM tb_usuario WHERE user_usuario = ?1 AND senha_usuario = ?2", nativeQuery = true)
    Usuario findUserLogin(String usuario, String senha);

}
