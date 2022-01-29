package com.aec.autoeletricacebola.controller;

import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.DESCRICAO_PECA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.PRECO_COMPRA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.PRECO_VENDA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.QUANTIDADE_PECA;
import static com.aec.autoeletricacebola.utils.ModelAttributeKeys.TEMPO_GARANTIA;

import java.util.Map;

import com.aec.autoeletricacebola.model.PecaEstoque;
import com.aec.autoeletricacebola.service.peca_estoque.PecaEstoqueService;
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
public class PecaEstoqueController {

    @Autowired
    private PecaEstoqueService pecaEstoqueService;

    @GetMapping("/cadastrarPeca")
    @RequestMapping(value = "/cadastrarPeca", method = RequestMethod.GET)
    public String redirectCadastrarMecanicoForm() {

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

}
