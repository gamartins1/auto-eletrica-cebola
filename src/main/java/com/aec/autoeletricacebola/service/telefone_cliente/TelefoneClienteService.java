package com.aec.autoeletricacebola.service.telefone_cliente;

import java.util.List;

import com.aec.autoeletricacebola.model.TelefoneCliente;

public interface TelefoneClienteService {

    List <TelefoneCliente> findAll();
    TelefoneCliente findById(Long id);
    List<TelefoneCliente> findByUser(Long id);
    TelefoneCliente save(TelefoneCliente telCliente);
}
