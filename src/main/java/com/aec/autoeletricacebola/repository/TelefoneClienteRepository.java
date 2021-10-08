package com.aec.autoeletricacebola.repository;

import java.util.List;

import com.aec.autoeletricacebola.model.TelefoneCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TelefoneClienteRepository extends JpaRepository<TelefoneCliente, Long> {

    @Query(value = "SELECT * FROM TB_TELEFONE_CLIENTE WHERE cliente_id_cliente = ?1", nativeQuery = true)
    List<TelefoneCliente> findByUser(Long id);
}
