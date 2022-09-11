package com.aec.autoeletricacebola.service.pagamento_servico;

import java.util.List;

import com.aec.autoeletricacebola.model.PagamentosServico;

public interface PagamentoServicoService {

    List <PagamentosServico> findAll();
    List<PagamentosServico> saveAll(List<PagamentosServico> pecasServico);
    PagamentosServico findById(Long id);
    PagamentosServico save(PagamentosServico pecaServico);
}
