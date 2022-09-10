package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.CebolaAutoEletricaConstants.EMPTY;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aec.autoeletricacebola.model.PecaEstoque;
import com.aec.autoeletricacebola.service.peca_estoque.PecaEstoqueService;
import com.aec.autoeletricacebola.service.peca_servico.PecaServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

@Controller
public class PecaEstoqueController {

    @Autowired
    private PecaEstoqueService pecaEstoqueService;

    @Autowired
    private PecaServicoService pecaServicoService;

    @GetMapping("/cadastrarPeca")
    @RequestMapping(value = "/cadastrarPeca", method = RequestMethod.GET)
    public String redirectCadastrarPecaForm() {

        return "cadastrarPeca";
    }

    @RequestMapping(value = "peca/cadastrarNovaPeca", method = RequestMethod.POST)
    public @ResponseBody
    String cadastrarNovaPeca(@RequestBody Map itensPeca, BindingResult result, RedirectAttributes attributes) {

        PecaEstoque pecaEstoque = new PecaEstoque();
        pecaEstoque.setAtivo(true);
        pecaEstoque.setNomePecaEstoque((String) itensPeca.get(DESCRICAO_PECA));
        pecaEstoque.setQuantidadePecaEstoque(Integer.parseInt((String) itensPeca.get(QUANTIDADE_PECA)));
        pecaEstoque.setTempoGarantiaPecaEstoque((String) itensPeca.get(TEMPO_GARANTIA));
        pecaEstoque.setValorCompraPecaEstoque(Double.parseDouble(((String) itensPeca.get(PRECO_COMPRA)).replace(",", ".")));
        pecaEstoque.setValorVendaPecaEstoque(Double.parseDouble(((String) itensPeca.get(PRECO_VENDA)).replace(",", ".")));

        pecaEstoque = this.pecaEstoqueService.save(pecaEstoque);

        System.out.println("Peça salva com sucesso. ID: " + pecaEstoque.getIdPecaEstoque());
        return "Peça salva com sucesso";
    }

    @GetMapping("/consultarPecas")
    @RequestMapping(value = "/consultarPecas", method = RequestMethod.GET)
    public ModelAndView redirectConsultarPecasForm() {
        ModelAndView modelAndView = new ModelAndView("consultarPecas");
        List <PecaEstoque> pecas = this.pecaEstoqueService.findAllIncludesInactives();
        List <String> pecasNomes = pecas.stream().map(PecaEstoque::getNomePecaEstoque).collect(Collectors.toList());

        modelAndView.addObject("pecas", pecas);
        modelAndView.addObject("pecasNomes", pecasNomes);
        return modelAndView;
    }

    @GetMapping("/newConsultaPecasPadrao")
    public String redirectConsultaPecasPadrao(Model m) {
        List <PecaEstoque> pecas = this.pecaEstoqueService.findAllIncludesInactives();

        m.addAttribute(PECAS, pecas);

        m.addAttribute(QUANTIDADE_PECA, pecas.size());

        m.addAttribute(PECAS_LISTA, pecas);
        return "modal/lista_consulta_pecas :: pecasLista";
    }

    @RequestMapping(value = "peca/reSearchPecaUsingParams", method = RequestMethod.POST)
    public String reSearchPecaUsingParams(String nomePeca, Model m) {
        List <PecaEstoque> pecasEstoque;

        //Não tem nenhum parâmetro, buscará todas as peças
        if(nomePeca.equals(EMPTY)) {
            pecasEstoque = this.pecaEstoqueService.findAllIncludesInactives();
        }
        else {
            //Buscará somente pelo nome da peça
            pecasEstoque = this.pecaEstoqueService.findByName(nomePeca);
        }

        m.addAttribute(PECAS, pecasEstoque);
        m.addAttribute(QUANTIDADE_PECA, pecasEstoque.size());

        m.addAttribute(PECAS_LISTA, pecasEstoque);
        return "modal/lista_consulta_pecas :: pecasLista";
    }

    @GetMapping("/editarPeca")
    @RequestMapping(value = "/editarPeca/{idPeca}", method = RequestMethod.GET)
    public ModelAndView redirectEditarPecaForm(@PathVariable("idPeca") Long id) {
        ModelAndView modelAndView = new ModelAndView("editarPeca");
        PecaEstoque pecaEstoque = this.pecaEstoqueService.findById(id);

        modelAndView.addObject(PECA, pecaEstoque);

        return modelAndView;
    }

    @RequestMapping(value = "editarPeca/peca/{idPeca}/editarUmaPeca", method = RequestMethod.POST)
    public @ResponseBody
    String editarPeca(@RequestBody Map atributosPeca, BindingResult result, RedirectAttributes attributes, @PathVariable String idPeca) {

        if(idPeca == null) {
            return "";
        }

        Long idDaPeca = Long.parseLong((String) atributosPeca.get(ID_PECA));

        PecaEstoque pecaEstoque = this.pecaEstoqueService.findById(idDaPeca);
        pecaEstoque.setAtivo(true);
        pecaEstoque.setNomePecaEstoque((String) atributosPeca.get(DESCRICAO_PECA));
        pecaEstoque.setQuantidadePecaEstoque(Integer.parseInt((String) atributosPeca.get(QUANTIDADE_PECA)));
        pecaEstoque.setTempoGarantiaPecaEstoque((String) atributosPeca.get(TEMPO_GARANTIA));
        pecaEstoque.setValorCompraPecaEstoque(Double.parseDouble(((String) atributosPeca.get(PRECO_COMPRA)).replace(",", ".")));
        pecaEstoque.setValorVendaPecaEstoque(Double.parseDouble(((String) atributosPeca.get(PRECO_VENDA)).replace(",", ".")));

        pecaEstoque = this.pecaEstoqueService.save(pecaEstoque);
        System.out.println("Peça alteradca com sucesso. ID: " + pecaEstoque.getIdPecaEstoque());

        return "Peça editada com sucesso";
    }

    @RequestMapping(value = "peca/obterQuantidadeEstoquePeca", method = RequestMethod.POST)
    public @ResponseBody
    String obterQuantidadeEstoquePeca(@RequestBody Map atributosPeca) {

        if(StringUtils.isEmpty((String)atributosPeca.get(ID_PECA))) {
            return EMPTY;
        }

        Long idPeca = Long.parseLong((String) atributosPeca.get(ID_PECA));

        PecaEstoque pecaEstoque = this.pecaEstoqueService.findById(idPeca);

        if(pecaEstoque != null) {
            return String.valueOf(pecaEstoque.getQuantidadePecaEstoque());
        }

        return EMPTY;
    }

    @RequestMapping(value = "peca/obterQuantidadeEstoquePecas", method = RequestMethod.POST)
    public @ResponseBody
    List<PecaEstoque> obterQuantidadeEstoquePecas(@RequestBody Map atributosPeca) {

        if(ObjectUtils.isEmpty(atributosPeca.get(ID_PECAS))) {
            return null;
        }

        List<Long> longs = ((List<Integer>)atributosPeca.get(ID_PECAS)).stream().mapToLong(Integer::longValue).boxed().collect(Collectors.toList());
        List<PecaEstoque> pecasEstoque = this.pecaEstoqueService.findAllById(longs);


//        List<PecaEstoque> pecasEstoque = pecasServico.stream().map(PecaServico::getPecaEstoque).filter(Objects::nonNull).collect(Collectors.toList());

        return pecasEstoque;
    }
}
