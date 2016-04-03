package com.example.maxi.siem.util;

import java.text.DateFormat;

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

    public static String getConsumoHoras(String sEntrada, String sSalida) {
        int hora, min;

        int indexEntrada = sEntrada.indexOf(":");
        int indexSalida = sSalida.indexOf(":");

        String horaEntrada = sEntrada.substring(0, indexEntrada);
        String horaSalida = sSalida.substring(0, indexSalida);

        String minEntrada = sEntrada.substring(indexEntrada + 1);
        String minSalida = sSalida.substring(indexSalida + 1);

        int iHoraEntrada = Integer.parseInt(horaEntrada);
        int iHoraSalida = Integer.parseInt(horaSalida);

        int iMinEntrada = Integer.parseInt(minEntrada);
        int iMinSalida = Integer.parseInt(minSalida);

        if(iHoraSalida < iHoraEntrada) {
            hora = 12 + iHoraSalida - iHoraEntrada;
        } else {
            hora = iHoraSalida - iHoraEntrada;
        }

        if(iMinSalida < iMinEntrada) {
            min = 60 + iMinSalida - iMinEntrada;
            hora --;
        } else {
            min = iMinSalida - iMinEntrada;
        }

        return FormatUtils.setDecimalFormat(hora) + ":" + FormatUtils.setDecimalFormat(min);
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

    private static String redondeo(String sHora) {
        int index = sHora.indexOf(":");
        String hora = sHora.substring(0, index);
        String min = sHora.substring(index + 1);

        int iHora = Integer.parseInt(hora);
        int iMin = Integer.parseInt(min);

        if(iMin > 30) {
            iHora ++;
        }
        iMin = 0;

        if(iHora < 1 && iMin > 30) {
            iHora = 1;
        }

        return iHora + ":" + iMin;
    }


}
