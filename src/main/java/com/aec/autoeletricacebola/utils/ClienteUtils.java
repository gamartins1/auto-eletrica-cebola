package com.aec.autoeletricacebola.utils;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_TIME_FORMAT;
import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.DescricaoServico;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.service.cliente.ClienteService;
import com.aec.autoeletricacebola.service.telefone_cliente.TelefoneClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteUtils {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private TelefoneClienteService telefoneClienteService;

    private static final DecimalFormat df = new DecimalFormat("0.0");
    /**
     * Seta no cliente os dados que não vem do front-end
     * @param cliente
     * @return cliente
     */
    public static Cliente initializeClient(Cliente cliente) {
        cliente.setDataCadastroCliente(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));

        return cliente;
    }

    public List <TelefoneCliente> criarTelefonesCliente(Cliente cliente, List<String> telefones) {
        List<TelefoneCliente> telefonesCliente = new ArrayList <>();

        for(String telefone : telefones) {
            TelefoneCliente telefoneCliente = new TelefoneCliente();
            telefoneCliente.setNumeroTelefoneCliente(telefone);
            telefoneCliente.setCliente(cliente);
            telefoneCliente.setAtivo(true);
            telefoneCliente.setObservacoesTelefoneCliente(EMPTY);

            final TelefoneCliente finalTelefoneCliente = telefoneCliente;//Para usar no stream precisa ser final
            if(cliente.getTelefoneCliente().stream().noneMatch(tel -> tel.getNumeroTelefoneCliente().equals(finalTelefoneCliente.getNumeroTelefoneCliente()))) {
                telefoneCliente = this.telefoneClienteService.save(telefoneCliente);
                telefonesCliente.add(telefoneCliente);
            }
            else {
                final TelefoneCliente finalTelefoneCliente1 = telefoneCliente;//Para usar no stream precisa ser final
                telefonesCliente.add(cliente.getTelefoneCliente().stream().filter(t -> t.getNumeroTelefoneCliente().equals(finalTelefoneCliente1.getNumeroTelefoneCliente())).findFirst().get());
            }

        }

        return telefonesCliente;
    }

    public static String calcClientRate(List<Servico> services) {
        String clientRate = "5.0";

        if(services != null && services.size() > 0) {
            double sumRates = services.stream().mapToDouble(servico -> (servico.getNotaServico().getNotaServico())).sum();
            clientRate = df.format(sumRates / services.size());
        }
        return clientRate;
    }
}
