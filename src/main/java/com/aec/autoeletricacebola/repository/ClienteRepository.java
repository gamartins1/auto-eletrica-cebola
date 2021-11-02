package com.aec.autoeletricacebola.repository;

import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    @Query(value = "SELECT * FROM tb_cliente WHERE nome_cliente LIKE %?1%", nativeQuery = true)
    List <Cliente> findByNome(String nome);

    @Query(value = "SELECT tb_cliente.* FROM tb_cliente INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_cliente.id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?2% AND tb_cliente.nome_cliente LIKE %?1%", nativeQuery = true)
    List<Cliente> findByTelefoneAndName(String nome, String telefone);
}
