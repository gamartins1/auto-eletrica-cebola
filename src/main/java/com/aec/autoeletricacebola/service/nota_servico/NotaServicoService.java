package com.aec.autoeletricacebola.service.nota_servico;

import com.aec.autoeletricacebola.model.NotaServico;

public interface NotaServicoService {

    NotaServico findById(Long id);
    NotaServico findByIdServico(Long idServico);
    NotaServico save(NotaServico notaServico);
}
