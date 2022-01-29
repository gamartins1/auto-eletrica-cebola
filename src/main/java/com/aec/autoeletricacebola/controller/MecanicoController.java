package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.MECANICOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.MECANICOS_LISTA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.NOME_MECANICO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.QUANTIDADE_MECANICOS;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.service.mecanico.MecanicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("/consultarMecanicos")
    @RequestMapping(value = "/consultarMecanicos", method = RequestMethod.GET)
    public ModelAndView redirectConsultarMecanicosForm() {
        ModelAndView modelAndView = new ModelAndView("consultarMecanicos");
        List <Mecanico> mecanicos = this.mecanicoService.findAll();
        List <String> mecanicosNomes = mecanicos.stream().map(Mecanico::getNomeMecanico).collect(Collectors.toList());

        modelAndView.addObject("mecanicosNomes", mecanicosNomes);
        modelAndView.addObject("mecanicos", mecanicos);

        return modelAndView;
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

    @GetMapping("/newConsultaMecanicosPadrao")
    public String redirectConsultaMecanicosPadrao(Model m) {

        List<Mecanico> mecanicos = this.mecanicoService.findAll();
        List <String> mecanicosNomes = mecanicos.stream().map(Mecanico::getNomeMecanico).collect(Collectors.toList());

        m.addAttribute("mecanicos", mecanicos);
        m.addAttribute("quantidadeMecanicos", mecanicos.size());
        m.addAttribute("mecanicosNomes", mecanicosNomes);
        m.addAttribute("mecanicosLista", mecanicos);
        return "modal/lista_consulta_mecanicos :: mecanicosLista";
    }

    @RequestMapping(value = "mecanico/reSearchMecanicoUsingParams", method = RequestMethod.POST)
    public String reSearchMecanicoUsingParams(String nomeMecanico, Model m) {
        List <Mecanico> mecanicos;

        if(nomeMecanico.equals(EMPTY)) {
            mecanicos = this.mecanicoService.findAll();
        }
        else {
            mecanicos = this.mecanicoService.findByName(nomeMecanico);
        }

        m.addAttribute(MECANICOS, mecanicos);
        m.addAttribute(QUANTIDADE_MECANICOS, mecanicos.size());

        m.addAttribute(MECANICOS_LISTA, mecanicos);
        return "modal/lista_consulta_mecanicos :: mecanicosLista";
    }

}
