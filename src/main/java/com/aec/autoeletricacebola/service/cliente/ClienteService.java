package com.aec.autoeletricacebola.service.cliente;

import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;

public interface ClienteService {

    List <Cliente> findAll();
    Cliente findById(Long id);
    List<Cliente> findByName(String nome);
    Cliente save(Cliente cliente);
    List<Cliente> saveAll(List<Cliente> clientes);
}
