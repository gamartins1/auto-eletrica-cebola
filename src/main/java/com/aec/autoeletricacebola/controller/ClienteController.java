package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;
import static com.aec.autoeletricacebola.utils.ClienteUtils.initializeClient;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.CLIENTES_LISTA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.QUANTIDADE_CLIENTES;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.VEICULOS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("/consultaClientes")
    @RequestMapping(value = "/consultaClientes", method = RequestMethod.GET)
    public ModelAndView redirectConsultaClientesForm() {
        ModelAndView modelAndView = new ModelAndView("consultaClientes");
        List <Cliente> clientes = this.clienteRepository.findAll();

        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("quantidadeClientes", clientes.size());
        return modelAndView;
    }

    @GetMapping("/newConsultaCliente")
    public String redirectCliente(Model m) {
        List <Cliente> clientes = this.clienteRepository.findAll();

        m.addAttribute("clientes", clientes);
        m.addAttribute("quantidadeClientes", clientes.size());

        m.addAttribute("clientesLista", clientes);
        return "modal/lista_consulta_clientes :: clientesLista";
    }

    @RequestMapping(value = "cliente/reSearchClientUsingParams", method = RequestMethod.POST)
    public String reSearchClientUsingParams(String telefoneCliente, String nomeCliente, Model m) {
        List <Cliente> clientes;

        //Não tem parâmetro no nome nem no telefone, retorna tudo
        if(telefoneCliente.equals(EMPTY) && nomeCliente.equals(EMPTY)) {
            clientes = this.clienteRepository.findAll();
        }
        else {
            //Tem nome e telefone como parâmetros, o retorno deve ser um cliente com esse nome e telefone
            if(!telefoneCliente.equals(EMPTY) && !nomeCliente.equals(EMPTY)) {
                clientes = this.clienteRepository.findByTelefoneAndName(nomeCliente, telefoneCliente);
            }
            //Só tem o telefone como parâmetro, retornará os clientes com esse telefone
            else if(!telefoneCliente.equals(EMPTY)) {
                HashMap<Long, Cliente> map = new HashMap <>();

                List<TelefoneCliente> telefoneClientes = this.telefoneClienteRepository.findByTelefone(telefoneCliente);

                for(TelefoneCliente telefoneCliente1 : telefoneClientes) {
                    map.put(telefoneCliente1.getCliente().getIdCliente(), telefoneCliente1.getCliente());
                }
                clientes = new ArrayList <>(map.values());
            }
            //Só tem o nome como parâmetro, retornará os clientes com esse nome
            else {
                clientes = this.clienteRepository.findByNome(nomeCliente);
            }
        }
        
        m.addAttribute(CLIENTE, clientes);
        m.addAttribute(QUANTIDADE_CLIENTES, clientes.size());

        m.addAttribute(CLIENTES_LISTA, clientes);
        return "modal/lista_consulta_clientes :: clientesLista";
    }

    @RequestMapping(value = "cliente/getVeiculosCliente", method = RequestMethod.POST)
    public String searchVeiculosCliente(String idCliente, Model m) {

        Cliente cliente = this.clienteRepository.findById(Long.parseLong(idCliente)).get();

        m.addAttribute(VEICULOS, cliente.getVeiculosCliente());
        return "modal/opcoes_veiculos_cliente :: opcoesVeiculoCliente";
    }
}
