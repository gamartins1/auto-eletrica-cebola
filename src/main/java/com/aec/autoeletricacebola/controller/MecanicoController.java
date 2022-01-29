package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.NOME_MECANICO;

import java.util.Map;

import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.service.mecanico.MecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MecanicoController {

    @Autowired
    private MecanicoService mecanicoService;

    @GetMapping("/cadastrarMecanico")
    @RequestMapping(value = "/cadastrarMecanico", method = RequestMethod.GET)
    public String redirectCadastrarMecanicoForm() {

        return "cadastrarMecanico";
    }

    @RequestMapping(value = "mecanico/cadastrarNovoMecanico", method = RequestMethod.POST)
    public @ResponseBody
    String cadastrarNovoMecanico(@RequestBody Map itensMecanico, BindingResult result, RedirectAttributes attributes) {

        String nomeMecanico = (String) itensMecanico.get(NOME_MECANICO);

        Mecanico mecanico = new Mecanico(nomeMecanico, true);

        mecanico = this.mecanicoService.save(mecanico);

        System.out.println("Mecânico salvo com sucesso. ID: " + mecanico.getIdMecanico());
        return "Mecânico salvo com sucesso";
    }


}
