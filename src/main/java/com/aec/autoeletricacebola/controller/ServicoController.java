package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.APPLICATION_DATE_TIME_FORMAT;
import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.DESCRICAO_SERVICOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_CLIENTE;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_SERVICO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.ID_VEICULO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.MAOS_DE_OBRA_SERVICO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.NOTA_SERVICO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.PECAS_SERVICO;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.QUANTIDADE_SERVICOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.SERVICOS;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.SERVICOS_LISTA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.VALOR_FINAL_SERVICO;
import static com.aec.autoeletricacebola.utils.StatusServicoConstants.ABERTO;
import static com.aec.autoeletricacebola.utils.StatusServicoConstants.FECHADO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aec.autoeletricacebola.model.Cliente;
import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.model.NotaServico;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.model.TelefoneCliente;
import com.aec.autoeletricacebola.model.Veiculo;
import com.aec.autoeletricacebola.repository.ClienteRepository;
import com.aec.autoeletricacebola.repository.DescricaoServicoRepository;
import com.aec.autoeletricacebola.repository.VeiculoRepository;
import com.aec.autoeletricacebola.service.mecanico.MecanicoService;
import com.aec.autoeletricacebola.service.nota_servico.NotaServicoService;
import com.aec.autoeletricacebola.service.servico.ServicoService;
import com.aec.autoeletricacebola.service.telefone_cliente.TelefoneClienteService;
import com.aec.autoeletricacebola.utils.ServicoUtils;
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

    @Autowired
    private TelefoneClienteService telefoneClienteService;

    @Autowired
    private NotaServicoService notaServicoService;

    @GetMapping("/cadastrarServico")
    @RequestMapping(value = "/cadastrarServico", method = RequestMethod.GET)
    public ModelAndView redirectCadastrarServicoForm() {
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

        Servico servico = new Servico(cliente, veiculo, LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT), ABERTO, new Date(System.currentTimeMillis()));

        servico = servicoService.save(servico);

        servico.setDescricaoServico(this.servicoUtils.criarDescricoesServico(servico, (List <String>) descricaoServicos.get(DESCRICAO_SERVICOS)));
        servico = servicoService.save(servico);

        System.out.println("Servico salvo com sucesso. ID: " + servico.getIdServico());
        return "Servico salvo com sucesso";
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

        double notaFinal = Double.parseDouble(((String) atributosServico.get(NOTA_SERVICO)));
        NotaServico notaServico = this.notaServicoService.save(new NotaServico(notaFinal, servico));

        servico.setNotaServico(notaServico);
        servico.setDescricaoServico(this.servicoUtils.criarDescricoesServico(servico, (List <String>) atributosServico.get(DESCRICAO_SERVICOS)));
        servico.setMaoDeObraServico(this.servicoUtils.criarMaosDeObraServico(servico, (List<String>) atributosServico.get(MAOS_DE_OBRA_SERVICO)));
        servico.setPecasServico(this.servicoUtils.criarPecasServico(servico, (List<String>) atributosServico.get(PECAS_SERVICO)));
        servico.setValorFinalServico(valorFinal);
        servico.setEncerramentoServico(LocalDateTime.now().format(APPLICATION_DATE_TIME_FORMAT));
        servico.setDataEncerramentoServico(new Date(System.currentTimeMillis()));
        servico.setStatusAtualServico(FECHADO);

        servico = this.servicoService.save(servico);
        System.out.println("Serviço salvo e finalizado. Id: " + servico.getIdServico());

        return "Servico finalizado com sucesso";
    }

    @GetMapping("/editarServico")
    @RequestMapping(value = "/editarServico/{idServico}", method = RequestMethod.GET)
    public ModelAndView redirectEditarServicoForm(@PathVariable("idServico") Long id) {
        ModelAndView modelAndView = new ModelAndView("editarServico");
        Servico servico = servicoService.findById(id);
        List<Mecanico> mecanicos = mecanicoService.findAll();

        modelAndView.addObject("servico", servico);
        modelAndView.addObject("mecanicos", mecanicos);

        return modelAndView;
    }

    @RequestMapping(value = "editarServico/servico/{idServico}/editarUmServico", method = RequestMethod.POST)
    public @ResponseBody String editarServico(@RequestBody Map atributosServico, BindingResult result, RedirectAttributes attributes, @PathVariable String idServico) {

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
        servico.setStatusAtualServico(ABERTO);

        servico = this.servicoService.save(servico);
        System.out.println("Serviço salvo. Id: " + servico.getIdServico());

        return "Servico editado com sucesso";
    }

    @GetMapping("/consultarServico")
    @RequestMapping(value = "/consultarServico/{idServico}", method = RequestMethod.GET)
    public ModelAndView redirectConsultarServicoForm(@PathVariable("idServico") Long id) {
        ModelAndView modelAndView = new ModelAndView("consultarServico");
        Servico servico = servicoService.findById(id);
        List<Mecanico> mecanicos = mecanicoService.findAll();

        modelAndView.addObject("servico", servico);
        modelAndView.addObject("mecanicos", mecanicos);

        return modelAndView;
    }

    @GetMapping("/consultarServicos")
    @RequestMapping(value = "/consultarServicos", method = RequestMethod.GET)
    public ModelAndView redirectConsultarServicosForm() {
        ModelAndView modelAndView = new ModelAndView("consultarServicos");
        List<Servico> servicos = servicoService.findAll();

        modelAndView.addObject("servico", servicos);

        return modelAndView;
    }

    @GetMapping("/newConsultaServicosPadrao")
    public String redirectConsultaServicosPadrao(Model m) {
        List <Servico> servicos = this.servicoService.findAll();

        List <String> clientesNomes = this.clienteRepository.findAll().stream().map(Cliente::getNomeCliente).collect(Collectors.toList());
        List <String> placas = this.veiculoRepository.findAll().stream().map(Veiculo::getPlacaVeiculo).collect(Collectors.toList());
        List <String> telefones = this.telefoneClienteService.findAll().stream().map(TelefoneCliente::getNumeroTelefoneCliente).collect(Collectors.toList());

        m.addAttribute("telefones", telefones);
        m.addAttribute("placas", placas);
        m.addAttribute("clientesNomes", clientesNomes);
        m.addAttribute("servicos", servicos);
        m.addAttribute("quantidadeServicos", servicos.size());

        m.addAttribute("servicosLista", servicos);
        return "modal/lista_consulta_servicos :: servicosLista";
    }

    @RequestMapping(value = "servico/reSearchServicesUsingParams", method = RequestMethod.POST)
    public String reSearchServicesUsingParams(String telefoneCliente, String nomeCliente, String placaVeiculo, String dataAte, String dataDe, Model m) {
        List <Servico> servicos;

        //Não tem parâmetro no nome nem no telefone, retorna tudo
        if(telefoneCliente.equals(EMPTY) && nomeCliente.equals(EMPTY) && placaVeiculo.equals(EMPTY) && dataDe.equals(EMPTY) && dataAte.equals(EMPTY)) {
            servicos = this.servicoService.findAll();
        }
        else {
            //O parâmetro de placa só pode ser combinado com parâmetros de data
            if(!placaVeiculo.equals(EMPTY)) {
                //Não tem parâmetro de data, buscará todos os serviços realizados pelo veículo
                if(dataAte.equals(EMPTY) && dataDe.equals(EMPTY)) {
                    servicos = this.servicoService.findByCarPlate(placaVeiculo);
                }
                else {
                    //Só tem a data inicial do filtro, buscará os serviços do veículo realizados desde aquela data
                    if(dataAte.equals(EMPTY)) {
                        servicos = this.servicoService.findByCarPlateWhereDateFrom(placaVeiculo, Date.valueOf(dataDe));
                    }
                    //Só tem a data final do filtro, buscará os serviços do veículo realizados até aquela data
                    else if(dataDe.equals(EMPTY)) {
                        servicos = this.servicoService.findByCarPlateWhereDateUntil(placaVeiculo, Date.valueOf(dataAte));
                    }
                    //Possui um intervalo de data pra buscar
                    else {
                        servicos = this.servicoService.findByCarPlateWhereDateInterval(placaVeiculo, Date.valueOf(dataDe), Date.valueOf(dataAte));
                    }
                }
            }
            //Tem nome e telefone como parâmetros, o retorno deve ser um serviço com esse nome e telefone
            else if(!telefoneCliente.equals(EMPTY) && !nomeCliente.equals(EMPTY)) {
                //Não tem parâmetro de data, buscará todos os serviços realizados para aquele nome/telefone
                if(dataAte.equals(EMPTY) && dataDe.equals(EMPTY)) {
                    servicos = this.servicoService.findByNameAndPhone(nomeCliente, telefoneCliente);
                }
                else {
                    //Só tem a data inicial do filtro, buscará os serviços do veículo realizados desde aquela data para aquele nome/telefone
                    if(dataAte.equals(EMPTY)) {
                        servicos = this.servicoService.findByNameAndPhoneWhereDateFrom(nomeCliente, telefoneCliente, Date.valueOf(dataDe));
                    }
                    //Só tem a data final do filtro, buscará os serviços do veículo realizados até aquela data para aquele nome/telefone
                    else if(dataDe.equals(EMPTY)) {
                        servicos = this.servicoService.findByNameAndPhoneWhereDateUntil(nomeCliente, telefoneCliente, Date.valueOf(dataAte));
                    }
                    //Busca com intervalo de data
                    else {
                        servicos = this.servicoService.findByNameAndPhoneWhereDateInterval(nomeCliente, telefoneCliente, Date.valueOf(dataDe), Date.valueOf(dataAte));
                    }
                }
            }
            //Só tem o telefone como parâmetro, retornará os clientes com esse telefone
            else if(!telefoneCliente.equals(EMPTY)) {
                //Não tem parâmetro de data, buscará todos os serviços realizados para aquele telefone
                if(dataAte.equals(EMPTY) && dataDe.equals(EMPTY)) {
                    servicos = this.servicoService.findByPhone(telefoneCliente);
                }
                else {
                    //Só tem a data inicial do filtro, buscará os serviços do veículo realizados desde aquela data para aquele telefone
                    if(dataAte.equals(EMPTY)) {
                        servicos = this.servicoService.findByPhoneWhereDateFrom(telefoneCliente, Date.valueOf(dataDe));
                    }
                    //Só tem a data final do filtro, buscará os serviços do veículo realizados até aquela data para aquele telefone
                    else if(dataDe.equals(EMPTY)) {
                        servicos = this.servicoService.findByPhoneWhereDateUntil(telefoneCliente, Date.valueOf(dataAte));
                    }
                    //Busca com intervalo de data
                    else {
                        servicos = this.servicoService.findByPhoneWhereDateInterval(telefoneCliente, Date.valueOf(dataDe), Date.valueOf(dataAte));
                    }
                }
            }
            //Só tem o nome como parâmetro, retornará os clientes com esse nome
            else if(!nomeCliente.equals(EMPTY)) {
                if (dataAte.equals(EMPTY) && dataDe.equals(EMPTY)) {
                    servicos = this.servicoService.findByName(nomeCliente);
                }
                else {
                    //Só tem a data inicial do filtro, buscará os serviços do veículo realizados desde aquela data para aquele nome
                    if (dataAte.equals(EMPTY)) {
                        servicos = this.servicoService.findByNameWhereDateFrom(nomeCliente, Date.valueOf(dataDe));
                    }
                    //Só tem a data final do filtro, buscará os serviços do veículo realizados até aquela data para aquele nome
                    else if(dataDe.equals(EMPTY)) {
                        servicos = this.servicoService.findByNameWhereDateUntil(nomeCliente, Date.valueOf(dataAte));
                    }
                    //Busca com intervalo de data
                    else {
                        servicos = this.servicoService.findByNameWhereDateInterval(nomeCliente, Date.valueOf(dataDe), Date.valueOf(dataAte));
                    }
                }
            }
            else if(!dataDe.equals(EMPTY) && dataAte.equals(EMPTY)) {
                //Só tem data de inicio de busca, trará todos os serviços desde essa data
                servicos = this.servicoService.findByDateFrom(Date.valueOf(dataDe));
            }
            else if(dataDe.equals(EMPTY) && !dataAte.equals(EMPTY)){
                //Só tem data final de busca, trará todos os serviços até essa data
                servicos = this.servicoService.findByDateUntil(Date.valueOf(dataAte));
            }
            else {
                //Possui data de inicio e fim de filtro de busca, trará todos os serviços realizados dentro desse intervalo
                servicos = this.servicoService.findByDateInterval(Date.valueOf(dataDe), Date.valueOf(dataAte));
            }
        }

        m.addAttribute(SERVICOS, servicos);
        m.addAttribute(QUANTIDADE_SERVICOS, servicos.size());

        m.addAttribute(SERVICOS_LISTA, servicos);
        return "modal/lista_consulta_servicos :: servicosLista";
    }

}