package com.aec.autoeletricacebola.service.servico;

import java.util.List;

import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoServiceImpl implements ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    @Override
    public List <Servico> findAll() {
        return servicoRepository.findAll();
    }

    @Override
    public List <Servico> findByDateInterval(String dateStart, String dateEnd) {
        return null;
    }

    @Override
    public List <Servico> findByServicesUnfinished() {
        return null;
    }

    @Override
    public Servico findById(Long id) {
        return servicoRepository.findById(id).get();
    }

    @Override
    public Servico save(Servico servico) {
        return servicoRepository.save(servico);
    }
}
