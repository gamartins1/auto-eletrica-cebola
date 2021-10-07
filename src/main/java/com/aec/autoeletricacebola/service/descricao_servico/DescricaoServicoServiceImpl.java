package com.aec.autoeletricacebola.service.descricao_servico;

import java.util.ArrayList;
import java.util.List;

import com.aec.autoeletricacebola.model.DescricaoServico;
import com.aec.autoeletricacebola.repository.DescricaoServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DescricaoServicoServiceImpl implements DescricaoServicoService {

    @Autowired
    private DescricaoServicoRepository descricaoServicoRepository;

    @Override
    public List <DescricaoServico> findAll() {
        return descricaoServicoRepository.findAll();
    }

    @Override
    public DescricaoServico findById(Long id) {
        return descricaoServicoRepository.findById(id).get();
    }

    @Override
    public DescricaoServico save(DescricaoServico descServico) {
        return descricaoServicoRepository.save(descServico);
    }

    @Override
    public List <DescricaoServico> saveAll(List <DescricaoServico> descricoesServico) {
        return descricaoServicoRepository.saveAll(descricoesServico);
    }
}
