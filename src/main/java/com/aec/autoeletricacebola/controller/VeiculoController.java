package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.BAIRRO_ENDERECO_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.CEP_ENDERECO_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.CIDADE_ENDERECO_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.MODELO_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.NOME_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.NUMERO_ENDERECO_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.OBSERVACOES_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.PLACA_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.QUANTIDADE_VEICULOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.RUA_ENDERECO_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.TELEFONES_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.VEICULOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.VEICULOS_LISTA;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.EnderecoCliente;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.service.cliente.ClienteService;
import com.aec.autoeletricacebola.service.servico.ServicoService;
import com.aec.autoeletricacebola.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ServicoService servicoService;

    @GetMapping("/cadastrarVeiculo")
    @RequestMapping(value = "/cadastrarVeiculo", method = RequestMethod.GET)
    public ModelAndView redirectCadastraVeiculoForm() {
        ModelAndView modelAndView = new ModelAndView("cadastrarVeiculo");
        List <Cliente> clientes = this.clienteService.findAll();

        modelAndView.addObject("clientes", clientes);

        return modelAndView;
    }

    @GetMapping("/consultarVeiculos")
    @RequestMapping(value = "/consultarVeiculos", method = RequestMethod.GET)
    public ModelAndView redirectConsultaVeiculosForm() {
        ModelAndView modelAndView = new ModelAndView("consultarVeiculos");

        return modelAndView;
    }

    @GetMapping("/newConsultaVeiculosPadrao")
    public String redirectConsultaVeiculosPadrao(Model m) {
        List <Veiculo> veiculos = this.veiculoService.findAll();

        List <String> clientesNomes = this.clienteService.findAll().stream().map(Cliente::getNomeCliente).collect(Collectors.toList());
        List <String> placas = this.veiculoService.findAll().stream().map(Veiculo::getPlacaVeiculo).collect(Collectors.toList());
        List<String> modelos = this.veiculoService.findAll().stream().map(Veiculo::getModeloVeiculo).collect(Collectors.toList());

        m.addAttribute("placas", placas);
        m.addAttribute("clientesNomes", clientesNomes);
        m.addAttribute("modelos", modelos);
        m.addAttribute("veiculos", veiculos);
        m.addAttribute("quantidadeVeiculos", veiculos.size());

        m.addAttribute("veiculosLista", veiculos);
        return "modal/lista_consulta_veiculos :: veiculosLista";
    }

    @RequestMapping(value = "veiculo/reSearchCarsUsingParams", method = RequestMethod.POST)
    public String reSearchCarsUsingParams(String nomeCliente, String placaVeiculo, String modeloVeiculo, Model m) {
        List <Veiculo> veiculos;

        //Não tem nenhum parâmetro, buscará todos os veículos
        if(placaVeiculo.equals(EMPTY) && nomeCliente.equals(EMPTY) && modeloVeiculo.equals(EMPTY)) {
            veiculos = this.veiculoService.findAll();
        }
        //Será buscado por placa, por ser dado único não precisa de modelo/cliente
        else if(!placaVeiculo.equals(EMPTY)) {
            veiculos = this.veiculoService.findByPlacas(placaVeiculo);
        }
        else if(!nomeCliente.equals(EMPTY)) {
            //Tem nome e modelo, buscará pelos dois parâmetros
            if(!modeloVeiculo.equals(EMPTY)) {
                veiculos = this.veiculoService.findByClientNameAndModel(nomeCliente, modeloVeiculo);
            }
            //Buscará somente pelo nome do cliente
            else {
                veiculos = this.veiculoService.findByClientName(nomeCliente);
            }
        }
        else {
            //Buscará somente pelo modelo
            veiculos = this.veiculoService.findByModelo(modeloVeiculo);
        }

        m.addAttribute(VEICULOS, veiculos);
        m.addAttribute(QUANTIDADE_VEICULOS, veiculos.size());

        m.addAttribute(VEICULOS_LISTA, veiculos);
        return "modal/lista_consulta_veiculos :: veiculosLista";
    }

    @RequestMapping(value = "veiculo/cadastrarNovoVeiculo", method = RequestMethod.POST)
    public @ResponseBody String cadastrarNovoVeiculo(@RequestBody Map requestVeiculo, BindingResult result, RedirectAttributes attributes) {
        Long idCliente = Long.parseLong((String) requestVeiculo.get(ID_CLIENTE));

        Cliente cliente = clienteService.findById(idCliente);

        if(cliente == null) {
            return "";
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setPlacaVeiculo((String) requestVeiculo.get(PLACA_VEICULO));
        veiculo.setModeloVeiculo((String) requestVeiculo.get(MODELO_VEICULO));
        veiculo.setObservacoesVeiculo((String) requestVeiculo.get(OBSERVACOES_VEICULO));

        veiculo.setAtivo(true);
        veiculo.setCliente(cliente);

        veiculo = veiculoService.save(veiculo);

        cliente.addVeiculoCliente(veiculo);
        cliente = clienteService.save(cliente);

        System.out.println("Veículo " + veiculo.getModeloVeiculo() + " salvo. Cliente: " + cliente.getNomeCliente());
        return "Veículo salvo com sucesso";
    }

    @GetMapping("/editarVeiculo")
    @RequestMapping(value = "/editarVeiculo/{idVeiculo}", method = RequestMethod.GET)
    public ModelAndView redirectEditarVeiculoForm(@PathVariable("idVeiculo") Long id) {
        ModelAndView modelAndView = new ModelAndView("editarVeiculo");
        Veiculo veiculo = this.veiculoService.findById(id);

        modelAndView.addObject("veiculo", veiculo);

        return modelAndView;
    }

    @RequestMapping(value = "editarVeiculo/veiculo/{idVeiculo}/editarUmVeiculo", method = RequestMethod.POST)
    public @ResponseBody
    String editarServico(@RequestBody Map atributosVeiculo, BindingResult result, RedirectAttributes attributes, @PathVariable String idVeiculo) {

        if(idVeiculo == null) {
            return "";
        }

        Long idDoVeiculo = Long.parseLong((String) atributosVeiculo.get(ID_VEICULO));

        Veiculo veiculo = this.veiculoService.findById(idDoVeiculo);
        veiculo.setObservacoesVeiculo((String) atributosVeiculo.get(OBSERVACOES_VEICULO));
        veiculo.setModeloVeiculo((String) atributosVeiculo.get(MODELO_VEICULO));

        veiculo = this.veiculoService.save(veiculo);

        System.out.println("Veículo salvo. Id: " + veiculo.getIdVeiculo());

        return "Veículo editado com sucesso";
    }

}
