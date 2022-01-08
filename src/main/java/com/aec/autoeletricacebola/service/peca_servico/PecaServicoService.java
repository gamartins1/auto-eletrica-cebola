package com.aec.autoeletricacebola.service.peca_servico;

import java.util.List;

import com.aec.autoeletricacebola.model.PecaServico;

public interface PecaServicoService {

    List <PecaServico> findAll();
    List<PecaServico> saveAll(List<PecaServico> pecasServico);
    PecaServico findById(Long id);
    PecaServico save(PecaServico pecaServico);

}
