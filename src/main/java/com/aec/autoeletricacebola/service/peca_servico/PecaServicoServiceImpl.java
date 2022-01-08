package com.aec.autoeletricacebola.service.peca_servico;

import java.util.List;

import com.aec.autoeletricacebola.model.PecaServico;
import com.aec.autoeletricacebola.repository.PecaServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PecaServicoServiceImpl implements PecaServicoService {

    @Autowired
    private PecaServicoRepository pecaServicoRepository;

    @Override
    public List <PecaServico> findAll() {
        return pecaServicoRepository.findAll();
    }

    @Override
    public PecaServico findById(Long id) {
        return pecaServicoRepository.findById(id).get();
    }

    @Override
    public PecaServico save(PecaServico pecaServico) {
        return pecaServicoRepository.save(pecaServico);
    }

    @Override
    public List <PecaServico> saveAll(List <PecaServico> pecasServico) {
        return pecaServicoRepository.saveAll(pecasServico);
    }
}
