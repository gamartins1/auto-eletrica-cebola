package com.aec.autoeletricacebola.service.pagamento_servico;

import java.util.List;

import com.aec.autoeletricacebola.model.PagamentosServico;
import com.aec.autoeletricacebola.repository.PagamentoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagamentoServicoServiceImpl implements PagamentoServicoService {

    @Autowired
    private PagamentoServicoRepository pagamentoServicoRepository;

    @Override
    public List <PagamentosServico> findAll() {
        return this.pagamentoServicoRepository.findAll();
    }

    @Override
    public List <PagamentosServico> saveAll(List <PagamentosServico> pecasServico) {
        return this.pagamentoServicoRepository.saveAll(pecasServico);
    }

    @Override
    public PagamentosServico findById(Long id) {
        return this.pagamentoServicoRepository.findById(id).get();
    }

    @Override
    public PagamentosServico save(PagamentosServico pecaServico) {
        return this.pagamentoServicoRepository.save(pecaServico);
    }

}
