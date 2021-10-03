package com.aec.autoeletricacebola.controller;

import java.util.List;

import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.service.servico.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MenuController {

    @Autowired
    private ServicoService servicoService;

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    public ModelAndView getMenuForm() {
        ModelAndView modelAndView = new ModelAndView("menu");
        List<Servico> servicos = getOpenedServices();

        modelAndView.addObject("servicos", servicos);
        modelAndView.addObject("quantidadeServicos", servicos.size());

        return modelAndView;
    }

    public List <Servico> getOpenedServices() {
        return servicoService.findByServicesUnfinished();
    }
}
