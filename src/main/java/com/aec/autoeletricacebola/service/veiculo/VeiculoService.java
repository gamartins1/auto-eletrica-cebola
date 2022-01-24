package com.aec.autoeletricacebola.service.veiculo;

import java.util.List;

import com.aec.autoeletricacebola.model.Veiculo;

public interface VeiculoService {
    List <Veiculo> findAll();
    List <Veiculo> findByModelo(String modelo);
    List <Veiculo> findByPlacas(String placa);
    List <Veiculo> findByClientName(String nome);
    List <Veiculo> findByClientNameAndModel(String nome, String modelo);
    Veiculo findById(Long id);
    Veiculo save(Veiculo veiculo);
}
