package com.aec.autoeletricacebola.repository;

import com.aec.autoeletricacebola.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoClienteRepository extends JpaRepository <EnderecoCliente, Long> {
}
