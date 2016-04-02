package com.example.maxi.siem.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateUtils {

    public static final String HH_MM = "hh:mm";
    private static DateFormat dateFormat;

    public CalculateUtils() {
    }

    public static String getMaxTiempo(String saldoStr, String costoStr) {
        double saldo = Double.valueOf(saldoStr);
        double costo = Double.valueOf(costoStr);

        double r = saldo / costo;

        return Double.toString(r);
    }

    public static String getConsumoHoras(String entrada, String salida) {
        dateFormat = new SimpleDateFormat(HH_MM);
        Date dEntrada = null;
        Date dSalida = null;
        Calendar primero = Calendar.getInstance();
        Calendar segundo = Calendar.getInstance();

        try {
            dEntrada = dateFormat.parse(entrada);
            dSalida = dateFormat.parse(salida);
        } catch (ParseException e) {
            dEntrada = Calendar.getInstance().getTime();
            dSalida = Calendar.getInstance().getTime();
        }

        primero.setTime(dEntrada);
        segundo.setTime(dSalida);

        int segHora = segundo.get(Calendar.HOUR);
        int priHora = primero.get(Calendar.HOUR);

        int segMin = segundo.get(Calendar.MINUTE);
        int priMin = primero.get(Calendar.MINUTE);

        int hora, min;
        String tiempoFinal = "00:00";

        try {
            primero.setTime(dateFormat.parse(entrada));
            segundo.setTime(dateFormat.parse(salida));

            hora = segHora - priHora;

            if (segMin < priMin) {
                hora --;
                min = 60 + segMin - priMin;
            } else {
                min = segMin - priMin;
            }

            tiempoFinal = hora + ":" + min;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return tiempoFinal;
    }

    public static String getConsumo(String sHora, String sCosto) {
        String horaRedondeada = redondeo(sHora);
        String replace = horaRedondeada.replace(":", ".");
        double costo = Double.parseDouble(sCosto);
        double hora = Double.parseDouble(replace);

        return Double.toString(costo * hora);
    }

    public static String getSaldo(String sSaldo, String Sconsumo) {
        double saldo = Double.parseDouble(sSaldo);
        double consumo = Double.parseDouble(Sconsumo);

        return Double.toString(saldo - consumo);
    }

    private static String redondeo(String hora) {
        Date parse = Calendar.getInstance().getTime();
        try {
            parse = dateFormat.parse(hora);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(parse);

        if(c.get(Calendar.MINUTE) > 30) {
            int incremento = c.get(Calendar.HOUR) + 1;
            c.set(Calendar.HOUR, incremento);
        }
        c.set(Calendar.MINUTE, 0);

        if(c.get(Calendar.HOUR) < 1 && c.get(Calendar.MINUTE) > 30) {
            c.set(Calendar.HOUR, 1);
        }

        return dateFormat.format(c.getTime());
    }
}
