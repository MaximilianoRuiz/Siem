package com.example.maxi.siem.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FormatUtils {

    public static final String HH_MM = "hh:mm";
    public static final String TWO_DECIMALS = "##.##";
    private static DateFormat dateFormat;
    private static DecimalFormat decimalFormat;

    public FormatUtils() {
    }

    public static String getLocalTime() {
        dateFormat = new SimpleDateFormat(HH_MM);
        String horaEntrada = dateFormat.format(Calendar.getInstance().getTime());

        return horaEntrada;
    }

    public static String getDoubleFormat (String value) {
        decimalFormat = new DecimalFormat(TWO_DECIMALS);

        return decimalFormat.format(Double.parseDouble(value));
    }

    public static String setDecimalFormat(int value) {
        if (value < 10) {
            return  "0" + value;
        } else {
            return Integer.toString(value);
        }
    }
}
