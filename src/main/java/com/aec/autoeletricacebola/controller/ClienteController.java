package com.aec.autoeletricacebola.controller;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.model.Veiculo;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {

    @RequestMapping(value = "cliente/saveWithCarAndPhone", method = RequestMethod.POST)
    public String saveClientWithCarAndPhone(Cliente cliente, Veiculo veiculo, TelefoneCliente telefoneCliente, BindingResult result, RedirectAttributes attributes) {


        return "redirect:/menu";
    }
}
