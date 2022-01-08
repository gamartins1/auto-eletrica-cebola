package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_FORMAT;
import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.*;
import static com.aec.autoeletricacebola.utils.StatusServicoConstants.ABERTO;
import static com.aec.autoeletricacebola.utils.StatusServicoConstants.FECHADO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.DescricaoServicoRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import com.aec.autoeletricacebola.service.mecanico.MecanicoService;
import com.aec.autoeletricacebola.service.servico.ServicoService;
import com.aec.autoeletricacebola.utils.ServicoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private DescricaoServicoRepository descricaoServicoRepository;

    @Autowired
    private MecanicoService mecanicoService;

    @Autowired
    private ServicoUtils servicoUtils;

    @GetMapping("/cadastrarServico")
    @RequestMapping(value = "/cadastrarServico", method = RequestMethod.GET)
    public ModelAndView redirectConsultaClientesForm() {
        ModelAndView modelAndView = new ModelAndView("cadastrarServico");
        List <Cliente> clientes = this.clienteRepository.findAll();
        List <Veiculo> veiculos = this.veiculoRepository.findAll();

        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("veiculos", veiculos);

        return modelAndView;
    }

    @GetMapping("/finalizarServico")
    @RequestMapping(value = "/finalizarServico/{idServico}", method = RequestMethod.GET)
    public ModelAndView redirectFinalizarServicoForm(@PathVariable("idServico") Long id) {
        ModelAndView modelAndView = new ModelAndView("finalizarServico");
        Servico servico = servicoService.findById(id);
        List<Mecanico> mecanicos = mecanicoService.findAll();

        modelAndView.addObject("servico", servico);
        modelAndView.addObject("mecanicos", mecanicos);

        return modelAndView;
    }

    @RequestMapping(value = "servico/cadastrarNovoServico", method = RequestMethod.POST)
    public @ResponseBody String cadastrarNovoServico(@RequestBody Map descricaoServicos, BindingResult result, RedirectAttributes attributes) {

        if(descricaoServicos.get(ID_CLIENTE) == null || descricaoServicos.get(ID_VEICULO) == null) {
            return "";
        }

        Long idCliente = Long.parseLong((String) descricaoServicos.get(ID_CLIENTE));
        Long idVeiculo = Long.parseLong((String) descricaoServicos.get(ID_VEICULO));

        Cliente cliente = clienteRepository.findById(idCliente).get();
        Veiculo veiculo = veiculoRepository.findById(idVeiculo).get();

        Servico servico = new Servico(cliente, veiculo, LocalDateTime.now().format(APPLICATION_DATE_FORMAT), ABERTO);

        servico = servicoService.save(servico);

        servico.setDescricaoServico(this.servicoUtils.criarDescricoesServico(servico, (List <String>) descricaoServicos.get(DESCRICAO_SERVICOS)));
        servico = servicoService.save(servico);

        System.out.println("Servico salvo com sucesso. ID: " + servico.getIdServico());
        return "Servico salvo com sucesso";
    }

    @RequestMapping(value = "finalizarServico/servico/{idServico}/finalizarUmServico", method = RequestMethod.POST)
    public @ResponseBody String finalizarServico(@RequestBody Map atributosServico, BindingResult result, RedirectAttributes attributes, @PathVariable String idServico) {

        if(idServico == null) {
            return "";
        }

        Long idDoServico = Long.parseLong((String) atributosServico.get(ID_SERVICO));

        Servico servico = servicoService.findById(idDoServico);

        double valorFinal = Double.parseDouble(((String) atributosServico.get(VALOR_FINAL_SERVICO)).replace(",", "."));

        if(valorFinal < 0) {
            return EMPTY;
        }

        servico.setDescricaoServico(this.servicoUtils.criarDescricoesServico(servico, (List <String>) atributosServico.get(DESCRICAO_SERVICOS)));
        servico.setMaoDeObraServico(this.servicoUtils.criarMaosDeObraServico(servico, (List<String>) atributosServico.get(MAOS_DE_OBRA_SERVICO)));
        servico.setPecasServico(this.servicoUtils.criarPecasServico(servico, (List<String>) atributosServico.get(PECAS_SERVICO)));
        servico.setValorFinalServico(valorFinal);
        servico.setEncerramentoServico(LocalDateTime.now().format(APPLICATION_DATE_FORMAT));
        servico.setStatusAtualServico(FECHADO);

        servico = this.servicoService.save(servico);
        System.out.println("Serviço salvo e finalizado. Id: " + servico.getIdServico());

        return "Servico finalizado com sucesso";
    }

}