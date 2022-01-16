package com.aec.autoeletricacebola.utils;

import java.time.format.DateTimeFormatter;

public class CebolaAutoEletricaConstants {

    public static final String DEFAULT_DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";

    public static final DateTimeFormatter APPLICATION_DATE_TIME_FORMAT = DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN);

    public static final String EMPTY = "";
}
