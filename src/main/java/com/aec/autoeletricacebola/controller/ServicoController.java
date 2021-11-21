package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_FORMAT;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.DESCRICAO_SERVICOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_VEICULO;
import static com.aec.autoeletricacebola.utils.StatusServicoConstants.ABERTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.DescricaoServico;
import com.aec.autoeletricacebola.model.EnderecoCliente;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.DescricaoServicoRepository;
import com.aec.autoeletricacebola.repository.ServicoRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
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
public class ServicoController {

    @Autowired
    private ServicoRepository servicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private DescricaoServicoRepository descricaoServicoRepository;

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

        servico = servicoRepository.save(servico);

        List<DescricaoServico> descricoes = new ArrayList <>();

        for(String descricao : (List <String>) descricaoServicos.get(DESCRICAO_SERVICOS)) {
            descricoes.add(new DescricaoServico(descricao, servico));
        }
        descricoes = this.descricaoServicoRepository.saveAll(descricoes);
        servico.setDescricaoServico(descricoes);
        servico = servicoRepository.save(servico);

        System.out.println("Servico salvo com sucesso. ID: " + servico.getIdServico());
        return "Servico salvo com sucesso";
    }

}