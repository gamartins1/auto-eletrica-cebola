package com.aec.autoeletricacebola.service.mecanico;

import java.util.List;

import com.aec.autoeletricacebola.model.Mecanico;

public interface MecanicoService {
    List <Mecanico> findAll();
    List <Mecanico> findByName(String nome);
    Mecanico findById(Long id);
    Mecanico save(Mecanico mecanico);
}
