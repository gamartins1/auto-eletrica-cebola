package com.aec.autoeletricacebola.repository;

import static com.aec.autoeletricacebola.utils.StatusServicoConstants.ABERTO;

import java.sql.Date;
import java.util.List;

import com.aec.autoeletricacebola.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicoRepository extends JpaRepository <Servico, Long> {

    @Query(value = "SELECT * FROM TB_SERVICO WHERE status_atual_servico = '" + ABERTO + "'", nativeQuery = true)
    List<Servico> findByServicesUnfinished();

    @Query(value = "SELECT * FROM TB_SERVICO WHERE cliente_id_cliente = ?1", nativeQuery = true)
    List<Servico> findServicesByClientId(Long id);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_veiculo ON tb_servico.veiculo_id_veiculo = tb_veiculo.id_veiculo WHERE tb_veiculo.placa_veiculo LIKE %?1%", nativeQuery = true)
    List<Servico> findByCarPlate(String plate);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_veiculo ON tb_servico.veiculo_id_veiculo = tb_veiculo.id_veiculo WHERE tb_veiculo.placa_veiculo LIKE %?1% AND data_abertura_servico >= ?2", nativeQuery = true)
    List<Servico> findByCarPlateWhereDateFrom(String plate, Date dateStart);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_veiculo ON tb_servico.veiculo_id_veiculo = tb_veiculo.id_veiculo WHERE tb_veiculo.placa_veiculo LIKE %?1% AND data_encerramento_servico <= ?2", nativeQuery = true)
    List<Servico> findByCarPlateWhereDateUntil(String plate, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_veiculo ON tb_servico.veiculo_id_veiculo = tb_veiculo.id_veiculo WHERE tb_veiculo.placa_veiculo LIKE %?1% AND (data_abertura_servico between ?2 AND ?3)", nativeQuery = true)
    List<Servico> findByCarPlateWhereDateInterval(String plate, Date dateStart, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?2% AND tb_cliente.nome_cliente LIKE %?1%", nativeQuery = true)
    List<Servico> findByNameAndPhone(String name, String phone);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?2% AND tb_cliente.nome_cliente LIKE %?1% AND tb_servico.data_abertura_servico >= ?3", nativeQuery = true)
    List<Servico> findByNameAndPhoneWhereDateFrom(String name, String phone, Date dateStart);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?2% AND tb_cliente.nome_cliente LIKE %?1% AND tb_servico.data_encerramento_servico <= ?3", nativeQuery = true)
    List<Servico> findByNameAndPhoneWhereDateUntil(String name, String phone, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?2% AND tb_cliente.nome_cliente LIKE %?1% AND (data_abertura_servico between ?3 AND ?4)", nativeQuery = true)
    List<Servico> findByNameAndPhoneWhereDateInterval(String name, String phone, Date dateStart, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?1% AND tb_telefone_cliente.ativo = 1", nativeQuery = true)
    List<Servico> findByPhone(String phone);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?1% AND tb_telefone_cliente.ativo = 1 AND tb_servico.data_abertura_servico >= ?2", nativeQuery = true)
    List<Servico> findByPhoneWhereDateFrom(String phone, Date dateStart);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?1% AND tb_telefone_cliente.ativo = 1 AND tb_servico.data_encerramento_servico <= ?2", nativeQuery = true)
    List<Servico> findByPhoneWhereDateUntil(String phone, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_telefone_cliente ON tb_telefone_cliente.cliente_id_cliente = tb_servico.cliente_id_cliente WHERE tb_telefone_cliente.numero_telefone_cliente LIKE %?1% AND tb_telefone_cliente.ativo = 1 AND (data_abertura_servico between ?2 AND ?3)", nativeQuery = true)
    List<Servico> findByPhoneWhereDateInterval(String phone, Date dateStart, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_cliente.nome_cliente LIKE %?1%", nativeQuery = true)
    List<Servico> findByName(String name);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_cliente.nome_cliente LIKE %?1% AND tb_servico.data_abertura_servico >= ?2", nativeQuery = true)
    List<Servico> findByNameWhereDateFrom(String name, Date dateStart);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_cliente.nome_cliente LIKE %?1% AND tb_servico.data_encerramento_servico <= ?2", nativeQuery = true)
    List<Servico> findByNameWhereDateUntil(String name, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico INNER JOIN tb_cliente ON tb_cliente.id_cliente = tb_servico.cliente_id_cliente WHERE tb_cliente.nome_cliente LIKE %?1% AND (data_abertura_servico between ?2 AND ?3)", nativeQuery = true)
    List<Servico> findByNameWhereDateInterval(String name, Date dateStart, Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico WHERE tb_servico.data_abertura_servico >= ?1", nativeQuery = true)
    List<Servico> findByDateFrom(Date dateStart);

    @Query(value = "SELECT tb_servico.* FROM tb_servico WHERE tb_servico.data_encerramento_servico <= ?1", nativeQuery = true)
    List<Servico> findByDateUntil(Date dateEnd);

    @Query(value = "SELECT tb_servico.* FROM tb_servico WHERE tb_servico.data_abertura_servico BETWEEN ?1 AND ?2", nativeQuery = true)
    List <Servico> findByDateInterval(Date dateStart, Date dateEnd);
}
