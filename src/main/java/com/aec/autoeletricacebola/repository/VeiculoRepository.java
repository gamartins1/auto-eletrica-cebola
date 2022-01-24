package com.aec.autoeletricacebola.repository;

import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VeiculoRepository extends JpaRepository <Veiculo, Long> {

    @Query(value = "SELECT * FROM tb_veiculo WHERE cliente_id_cliente = ?1", nativeQuery = true)
    List <Veiculo> findByClienteId(Long id);

    @Query(value = "SELECT tb_veiculo.* FROM tb_veiculo WHERE tb_veiculo.modelo_veiculo LIKE %?1%", nativeQuery = true)
    List<Veiculo> findByModelo(String modelo);

    @Query(value = "SELECT tb_veiculo.* FROM tb_veiculo WHERE tb_veiculo.placa_veiculo LIKE %?1%", nativeQuery = true)
    List<Veiculo> findByPlaca(String placa);

    @Query(value = "SELECT tb_veiculo.* FROM tb_veiculo INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_veiculo.cliente_id_cliente WHERE tb_cliente.nome_cliente LIKE %?1%", nativeQuery = true)
    List <Veiculo> findByClientName(String nome);

    @Query(value = "SELECT tb_veiculo.* FROM tb_veiculo INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_veiculo.cliente_id_cliente WHERE tb_cliente.nome_cliente LIKE %?1% AND tb_veiculo.modelo_veiculo LIKE %?2%", nativeQuery = true)
    List <Veiculo> findByClientNameAndModel(String nome, String modelo);

}
