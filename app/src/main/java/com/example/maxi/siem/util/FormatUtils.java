package com.example.maxi.siem.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatUtils {

    public static final String HH_MM = "hh:mm";
    private static DateFormat dateFormat;

    public FormatUtils() {
    }

    public static String getLocalTime() {
        dateFormat = new SimpleDateFormat(HH_MM);
        String horaEntrada = dateFormat.format(Calendar.getInstance().getTime());

        return horaEntrada;
    }
}
