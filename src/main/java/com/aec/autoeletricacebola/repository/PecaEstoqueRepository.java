package com.aec.autoeletricacebola.repository;

import java.util.List;

import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.model.PecaEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PecaEstoqueRepository extends JpaRepository<PecaEstoque, Long> {

    @Override
    @Query(value = "SELECT * FROM tb_peca_estoque WHERE ativo = 1", nativeQuery = true)
    List <PecaEstoque> findAll();

    @Query(value = "SELECT * FROM tb_peca_estoque", nativeQuery = true)
    List <PecaEstoque> findAllIncludesInactives();

    @Query(value = "SELECT * FROM tb_peca_estoque WHERE nome_peca_estoque LIKE %?1%", nativeQuery = true)
    List <PecaEstoque> findByName(String nome);
}
