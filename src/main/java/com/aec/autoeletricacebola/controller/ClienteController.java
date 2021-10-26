package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.ClienteUtils.initializeClient;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.EnderecoCliente;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.EnderecoClienteRepository;
import com.aec.autoeletricacebola.repository.TelefoneClienteRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TelefoneClienteRepository telefoneClienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private EnderecoClienteRepository enderecoClienteRepository;

    @RequestMapping(value = "cliente/saveWithCarAndPhone", method = RequestMethod.POST)
    public String saveClientWithCarAndPhone(Cliente cliente, Veiculo veiculo, EnderecoCliente enderecoCliente, TelefoneCliente telefoneCliente, BindingResult result, RedirectAttributes attributes) {

        cliente = clienteRepository.save(initializeClient(cliente));

        telefoneCliente.setCliente(cliente);
        telefoneCliente = this.telefoneClienteRepository.save(telefoneCliente);

        cliente.addTelefoneCliente(telefoneCliente);

        enderecoCliente.setCliente(cliente);
        enderecoCliente = this.enderecoClienteRepository.save(enderecoCliente);
        cliente.setEnderecoCliente(enderecoCliente);

        if(veiculo != null && !veiculo.getModeloVeiculo().isEmpty()) {
            veiculo.setCliente(cliente);
            veiculo = this.veiculoRepository.save(veiculo);
            System.out.println("Veiculo do cliente salvo: " + veiculo.getModeloVeiculo());
        }
        //Salva mais uma vez pra manter os relacionamentos do JPA
        cliente = this.clienteRepository.save(cliente);

        System.out.println("Cliente salvo com seus atributos: " + cliente.getNomeCliente());

        return "redirect:/menu";
    }
}
