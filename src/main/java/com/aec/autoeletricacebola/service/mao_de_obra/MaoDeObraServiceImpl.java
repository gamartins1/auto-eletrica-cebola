package com.aec.autoeletricacebola.service.mao_de_obra;

import java.util.List;

import com.aec.autoeletricacebola.model.MaoDeObraServico;
import com.aec.autoeletricacebola.repository.MaoDeObraServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaoDeObraServiceImpl implements MaoDeObraService {

    @Autowired
    private MaoDeObraServicoRepository maoDeObraServicoRepository;

    @Override
    public List <MaoDeObraServico> findAll() {
        return maoDeObraServicoRepository.findAll();
    }

    @Override
    public MaoDeObraServico findById(Long id) {
        return maoDeObraServicoRepository.findById(id).get();
    }

    @Override
    public MaoDeObraServico save(MaoDeObraServico maoDeObraServico) {
        return maoDeObraServicoRepository.save(maoDeObraServico);
    }
}
