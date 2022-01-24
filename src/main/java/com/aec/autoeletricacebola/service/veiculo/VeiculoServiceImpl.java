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
        return this.veiculoRepository.findByModelo(modelo);
    }

    @Override
    public Veiculo findById(Long id) {
        return veiculoRepository.findById(id).get();
    }

    @Override
    public List<Veiculo> findByPlacas(String placa) {
        return this.veiculoRepository.findByPlaca(placa);
    }

    @Override
    public List <Veiculo> findByClientName(String nome) {
        return this.veiculoRepository.findByClientName(nome);
    }

    @Override
    public List <Veiculo> findByClientNameAndModel(String nome, String modelo) {
        return this.veiculoRepository.findByClientNameAndModel(nome, modelo);
    }

    @Override
    public Veiculo save(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }
}
