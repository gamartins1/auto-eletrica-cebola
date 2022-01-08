package com.aec.autoeletricacebola.utils;

import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.CIFRAO;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.GARANTIA_DE;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.PONTO_ESPACO_FIM;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.QUANTIDADE;
import static com.aec.autoeletricacebola.utils.AutoEletricaCebolaRegexConstants.UNICO;
import static java.lang.Double.parseDouble;

public class AutoEletricaCebolaStringSplitUtils {

    public static double valorItemServico(String item) {
        return parseDouble(item.split(UNICO)[0].split(CIFRAO)[1]);
    }

    public static String tempoGarantiaItemServico(String item) {
        return item.split(UNICO)[1].split(PONTO_ESPACO_FIM)[0].split(GARANTIA_DE)[1];
    }

    public static int quantidadePecasServico(String peca) {
        return Integer.parseInt(peca.split(PONTO_ESPACO_FIM)[2].split(QUANTIDADE)[1]);
    }
}
