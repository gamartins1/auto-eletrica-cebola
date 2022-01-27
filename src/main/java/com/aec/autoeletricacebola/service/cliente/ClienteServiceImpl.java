package com.aec.autoeletricacebola.service.cliente;

import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List <Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findById(Long id) {
        return clienteRepository.findById(id).get();
    }

    @Override
    public List<Cliente> findByName(String nome) {
        return clienteRepository.findByNome(nome);
    }

    @Override
    public Cliente save(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public List <Cliente> saveAll(List<Cliente> clientes) {
        return this.clienteRepository.saveAll(clientes);
    }
}
