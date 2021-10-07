package com.aec.autoeletricacebola.service.descricao_servico;

import java.util.List;

import com.aec.autoeletricacebola.model.DescricaoServico;

public interface DescricaoServicoService {

    List<DescricaoServico> findAll();
    DescricaoServico findById(Long id);
    DescricaoServico save(DescricaoServico descServico);
    List<DescricaoServico> saveAll(List<DescricaoServico> descricoesServico);
}
