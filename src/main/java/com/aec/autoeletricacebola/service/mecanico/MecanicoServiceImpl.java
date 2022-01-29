package com.aec.autoeletricacebola.service.mecanico;

import java.util.List;

import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.repository.MecanicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MecanicoServiceImpl implements MecanicoService {

    @Autowired
    private MecanicoRepository mecanicoRepository;

    @Override
    public List <Mecanico> findAll() {
        return mecanicoRepository.findAll();
    }

    @Override
    public Mecanico findById(Long id) {
        return mecanicoRepository.findById(id).get();
    }

    @Override
    public List <Mecanico> findByName(String nome) {
        return this.mecanicoRepository.findByName(nome);
    }

    @Override
    public Mecanico save(Mecanico mecanico) {
        return mecanicoRepository.save(mecanico);
    }
}
