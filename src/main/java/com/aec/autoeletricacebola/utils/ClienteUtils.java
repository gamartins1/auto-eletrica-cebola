package com.aec.autoeletricacebola.utils;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_FORMAT;

import java.time.LocalDateTime;

import com.aec.autoeletricacebola.model.Cliente;

public class ClienteUtils {

    /**
     * Seta no cliente os dados que não vem do front-end
     * @param cliente
     * @return cliente
     */
    public static Cliente initializeClient(Cliente cliente) {
        cliente.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_FORMAT));

        return cliente;
    }
}
