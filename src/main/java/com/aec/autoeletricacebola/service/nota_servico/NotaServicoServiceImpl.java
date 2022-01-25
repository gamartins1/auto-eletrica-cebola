package com.aec.autoeletricacebola.service.nota_servico;

import com.aec.autoeletricacebola.model.NotaServico;
import com.aec.autoeletricacebola.repository.NotaServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotaServicoServiceImpl implements NotaServicoService {

    @Autowired
    private NotaServicoRepository notaServicoRepository;

    @Override
    public NotaServico findById(Long id) {
        return null;
    }

    @Override
    public NotaServico findByIdServico(Long idServico) {
        return null;
    }

    @Override
    public NotaServico save(NotaServico notaServico) {
        return this.notaServicoRepository.save(notaServico);
    }
}
