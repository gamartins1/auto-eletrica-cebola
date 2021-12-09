package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.MODELO_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.OBSERVACOES_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.PLACA_VEICULO;

import java.util.List;
import java.util.Map;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.service.cliente.ClienteService;
import com.aec.autoeletricacebola.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/cadastrarVeiculo")
    @RequestMapping(value = "/cadastrarVeiculo", method = RequestMethod.GET)
    public ModelAndView redirectConsultaClientesForm() {
        ModelAndView modelAndView = new ModelAndView("cadastrarVeiculo");
        List <Cliente> clientes = this.clienteService.findAll();

        modelAndView.addObject("clientes", clientes);

        return modelAndView;
    }

    @RequestMapping(value = "veiculo/cadastrarNovoVeiculo", method = RequestMethod.POST)
    public @ResponseBody String cadastrarNovoServico(@RequestBody Map requestVeiculo, BindingResult result, RedirectAttributes attributes) {
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

}
