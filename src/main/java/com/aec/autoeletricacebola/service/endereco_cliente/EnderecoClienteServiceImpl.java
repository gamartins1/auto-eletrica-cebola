package com.aec.autoeletricacebola.service.endereco_cliente;

import java.util.List;

import com.aec.autoeletricacebola.model.EnderecoCliente;
import com.aec.autoeletricacebola.repository.EnderecoClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnderecoClienteServiceImpl implements EnderecoClienteService {

    @Autowired
    private EnderecoClienteRepository repository;

    @Override
    public List <EnderecoCliente> findAll() {
        return this.repository.findAll();
    }

    @Override
    public List <EnderecoCliente> findByCep(String cep) {
        return null;
    }

    @Override
    public EnderecoCliente findById(Long id) {
        return this.repository.findById(id).get();
    }

    @Override
    public EnderecoCliente save(EnderecoCliente enderecoCliente) {
        return this.repository.save(enderecoCliente);
    }
}
