package com.aec.autoeletricacebola.service.peca_estoque;

import java.util.List;

import com.aec.autoeletricacebola.model.PecaEstoque;

public interface PecaEstoqueService {

    List <PecaEstoque> findAll();
    List <PecaEstoque> findAllIncludesInactives();
    List <PecaEstoque> findByName(String nome);
    PecaEstoque findById(Long id);
    PecaEstoque findOneByName(String nome);
    PecaEstoque save(PecaEstoque pecaEstoque);
}
