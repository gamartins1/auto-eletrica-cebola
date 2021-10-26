package com.aec.autoeletricacebola.service.endereco_cliente;

import java.util.List;

import com.aec.autoeletricacebola.model.EnderecoCliente;

public interface EnderecoClienteService {

    List <EnderecoCliente> findAll();
    List<EnderecoCliente> findByCep(String cep);
    EnderecoCliente findById(Long id);
    EnderecoCliente save(EnderecoCliente enderecoCliente);
}
