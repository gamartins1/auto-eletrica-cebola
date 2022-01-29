package com.aec.autoeletricacebola.repository;

import java.util.List;

import com.aec.autoeletricacebola.model.Mecanico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MecanicoRepository extends JpaRepository <Mecanico, Long> {

    @Query(value = "SELECT * FROM tb_mecanico WHERE nome_mecanico LIKE %?1%", nativeQuery = true)
    List <Mecanico> findByName(String nome);
}
