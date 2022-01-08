package com.aec.autoeletricacebola.utils;

import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.HIFEN_EM_ESPACOS;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.MECANICO_ATTRIBUTES_MAO_DE_OBRA_SPLITTER;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaStringSplitUtils.quantidadePecasServico;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaStringSplitUtils.tempoGarantiaItemServico;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaStringSplitUtils.valorItemServico;

import java.util.ArrayList;
import java.util.List;

import com.aec.autoeletricacebola.model.DescricaoServico;
import com.aec.autoeletricacebola.model.MaoDeObraServico;
import com.aec.autoeletricacebola.model.Mecanico;
import com.aec.autoeletricacebola.model.PecaServico;
import com.aec.autoeletricacebola.model.Servico;
import com.aec.autoeletricacebola.service.descricao_servico.DescricaoServicoService;
import com.aec.autoeletricacebola.service.mao_de_obra.MaoDeObraService;
import com.aec.autoeletricacebola.service.mecanico.MecanicoService;
import com.aec.autoeletricacebola.service.peca_servico.PecaServicoService;
import com.aec.autoeletricacebola.service.servico.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicoUtils {

    @Autowired
    private ServicoService servicoService;

    @Autowired
    private MecanicoService mecanicoService;

    @Autowired
    private MaoDeObraService maoDeObraService;

    @Autowired
    private DescricaoServicoService descricaoServicoService;

    @Autowired
    private PecaServicoService pecaServicoService;

    public List <MaoDeObraServico> criarMaosDeObraServico(Servico servico, List<String> maosDeObra) {
        List<MaoDeObraServico> maosDeObraServico = new ArrayList <>();

        for(String maoDeObra : maosDeObra) {
            MaoDeObraServico maoDeObraServico = new MaoDeObraServico();

            String descricaoMaoDeObra = maoDeObra.split(HIFEN_EM_ESPACOS)[0];

            String mecanicoAttrs = maoDeObra.split(MECANICO_ATTRIBUTES_MAO_DE_OBRA_SPLITTER)[1];
            Long mecanicoId = Long.parseLong(mecanicoAttrs.split(HIFEN_EM_ESPACOS)[0]);
            Mecanico mecanico = this.mecanicoService.findById(mecanicoId);

            maoDeObraServico.setTempoGarantiaMaoDeObraServico(tempoGarantiaItemServico(maoDeObra));
            maoDeObraServico.setValorMaoDeObraServico(valorItemServico(maoDeObra));
            maoDeObraServico.setDescricaoMaoDeObraServico(descricaoMaoDeObra);
            maoDeObraServico.setMecanico(mecanico);
            maoDeObraServico.setServico(servico);

            maoDeObraServico = maoDeObraService.save(maoDeObraServico);

            maosDeObraServico.add(maoDeObraServico);
        }

        return maosDeObraServico;
    }

    public List<DescricaoServico> criarDescricoesServico(Servico servico, List<String> descricoesTexto) {
        List<DescricaoServico> descricoes = new ArrayList <>();

        for(String descricao : descricoesTexto) {
            descricoes.add(new DescricaoServico(descricao, servico));
        }
        descricoes = this.descricaoServicoService.saveAll(descricoes);

        return descricoes;
    }

    public List<PecaServico> criarPecasServico(Servico servico, List<String> pecasServico) {
        List<PecaServico> pecas = new ArrayList <>();

        for(String peca : pecasServico) {
            PecaServico pecaServico = new PecaServico();

            String descricaoPeca = peca.split(HIFEN_EM_ESPACOS)[0];

            pecaServico.setNomePecaEstoque(descricaoPeca);
            pecaServico.setValorPeca(valorItemServico(peca));
            pecaServico.setTempoGarantiaPecaServico(tempoGarantiaItemServico(peca));
            pecaServico.setQuantidadePecaServico(quantidadePecasServico(peca));
            pecaServico.setServico(servico);

            pecas.add(pecaServico);
        }

        pecas = this.pecaServicoService.saveAll(pecas);
        return pecas;
    }
}
