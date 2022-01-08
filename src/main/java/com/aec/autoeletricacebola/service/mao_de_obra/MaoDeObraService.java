package com.aec.autoeletricacebola.service.mao_de_obra;

import java.util.List;

import com.aec.autoeletricacebola.model.MaoDeObraServico;
import com.aec.autoeletricacebola.model.Mecanico;

public interface MaoDeObraService {
    List <MaoDeObraServico> findAll();
    MaoDeObraServico findById(Long id);
    MaoDeObraServico save(MaoDeObraServico maoDeObraServico);
}
