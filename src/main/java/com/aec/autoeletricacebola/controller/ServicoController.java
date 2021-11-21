package com.aec.autoeletricacebola.controller;

import java.util.List;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.ServicoRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @GetMapping("/cadastrarServico")
    @RequestMapping(value = "/cadastrarServico", method = RequestMethod.GET)
    public ModelAndView redirectConsultaClientesForm() {
        ModelAndView modelAndView = new ModelAndView("cadastrarServico");
        List <Cliente> clientes = this.clienteRepository.findAll();
        List<Veiculo> veiculos = this.veiculoRepository.findAll();

        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("veiculos", veiculos);

        return modelAndView;
    }
}
