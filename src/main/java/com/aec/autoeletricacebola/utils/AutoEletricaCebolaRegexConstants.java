package com.aec.autoeletricacebola.utils;

public class AutoEletricaCebolaRegexConstants {

    public static final String MECANICO_ATTRIBUTES_MAO_DE_OBRA_SPLITTER = "Realizado por: ";

    public static final String ESPACO = "\\ ";

    public static final String HIFEN_EM_ESPACOS = ESPACO + "\\-" + ESPACO;

    public static final String PONTO = "\\.";

    public static final String PONTO_ESPACO_FIM = PONTO + ESPACO;

    public static final String UNICO = ESPACO + "\\(único\\)." + ESPACO;

    public static final String CIFRAO = "R\\$";

    public static final String GARANTIA_DE = "Garantia de";

    public static final String QUANTIDADE = "Quantidade" + ESPACO;
}
