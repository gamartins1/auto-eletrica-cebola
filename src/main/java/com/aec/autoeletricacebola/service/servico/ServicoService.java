package com.aec.autoeletricacebola.service.servico;

import java.util.List;

import com.aec.autoeletricacebola.model.Servico;

public interface ServicoService {

    List <Servico> findAll();
    List <Servico> findByDateInterval(String dateStart, String dateEnd);
    List <Servico> findByServicesUnfinished();
    Servico findById(Long id);
    Servico save(Servico servico);
}
