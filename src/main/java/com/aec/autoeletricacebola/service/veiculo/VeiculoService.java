package com.aec.autoeletricacebola.service.veiculo;

import java.util.List;

import com.aec.autoeletricacebola.model.Veiculo;

public interface VeiculoService {
    List <Veiculo> findAll();
    List <Veiculo> findByModelo(String modelo);
    Veiculo findById(Long id);
    Veiculo findByPlaca(String placa);
    Veiculo save(Veiculo veiculo);
}
