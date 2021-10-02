package com.aec.autoeletricacebola.service.mecanico;

import java.util.List;

import com.aec.autoeletricacebola.model.Mecanico;

public interface MecanicoService {
    List <Mecanico> findAll();
    Mecanico findById(Long id);
    Mecanico findByName(String nome);
    Mecanico save(Mecanico mecanico);
}
