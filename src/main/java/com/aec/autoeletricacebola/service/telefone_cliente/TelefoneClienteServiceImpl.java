package com.aec.autoeletricacebola.service.telefone_cliente;

import java.util.List;

import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.repository.TelefoneClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelefoneClienteServiceImpl implements TelefoneClienteService {

    @Autowired
    private TelefoneClienteRepository telefoneClienteRepository;

    @Override
    public List <TelefoneCliente> findAll() {
        return telefoneClienteRepository.findAll();
    }

    @Override
    public TelefoneCliente findById(Long id) {
        return telefoneClienteRepository.findById(id).get();
    }

    @Override
    public List <TelefoneCliente> findByUser(Long id) {
        return telefoneClienteRepository.findByUser(id);
    }

    @Override
    public TelefoneCliente save(TelefoneCliente telCliente) {
        return telefoneClienteRepository.save(telCliente);
    }
}
