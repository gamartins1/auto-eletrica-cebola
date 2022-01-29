package com.aec.autoeletricacebola.service.peca_estoque;

import java.util.List;

import com.aec.autoeletricacebola.model.PecaEstoque;
import com.aec.autoeletricacebola.repository.PecaEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PecaEstoqueServiceImpl implements PecaEstoqueService {

    @Autowired
    private PecaEstoqueRepository pecaEstoqueRepository;

    @Override
    public List <PecaEstoque> findAll() {
        return this.pecaEstoqueRepository.findAll();
    }

    @Override
    public List <PecaEstoque> findAllIncludesInactives() {
        return this.pecaEstoqueRepository.findAllIncludesInactives();
    }

    @Override
    public List <PecaEstoque> findByName(String nome) {
        return null;
    }

    @Override
    public PecaEstoque findById(Long id) {
        return this.pecaEstoqueRepository.findById(id).get();
    }

    @Override
    public PecaEstoque save(PecaEstoque pecaEstoque) {
        return this.pecaEstoqueRepository.save(pecaEstoque);
    }
}
