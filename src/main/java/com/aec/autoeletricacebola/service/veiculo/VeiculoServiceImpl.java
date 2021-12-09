package com.aec.autoeletricacebola.service.veiculo;

import java.util.List;

import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public List <Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    @Override
    public List <Veiculo> findByModelo(String modelo) {
        return null;
    }

    @Override
    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).get();
    }

    @Override
    public Veiculo findByPlaca(String placa) {
        return null;
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
}
