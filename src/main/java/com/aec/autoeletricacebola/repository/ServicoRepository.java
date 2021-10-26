package com.aec.autoeletricacebola.repository;

import static com.aec.autoeletricacebola.utils.StatusServicoConstants.ABERTO;

import java.util.List;

import com.aec.autoeletricacebola.model.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServicoRepository extends JpaRepository <Servico, Long> {

    @Query(value = "SELECT * FROM TB_SERVICO WHERE status_atual_servico = '" + ABERTO + "'", nativeQuery = true)
    List<Servico> findByServicesUnfinished();
}
