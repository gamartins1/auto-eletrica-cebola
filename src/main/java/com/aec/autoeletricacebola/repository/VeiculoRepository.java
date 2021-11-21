package com.aec.autoeletricacebola.repository;

import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {

    @Query(value = "SELECT * FROM tb_veiculo WHERE cliente_id_cliente = ?1", nativeQuery = true)
    List <Veiculo> findByClienteId(Long id);
}
