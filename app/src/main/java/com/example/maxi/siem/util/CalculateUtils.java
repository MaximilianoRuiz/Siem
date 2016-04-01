package com.example.maxi.siem.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

        String hora = "00:00";

        try {
            long tiempoEntrada = dateFormat.parse(entrada).getTime();
            long tiempoSalida = dateFormat.parse(salida).getTime();

            Date d = new Date(tiempoEntrada - tiempoSalida);

            hora = dateFormat.format(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return hora;
    }

    public static String getConsumo(String sHora, String sCosto) {
        String replace = sHora.replace(":", ".");
        double costo = Double.parseDouble(sCosto);
        double hora = Double.parseDouble(replace);

        return Double.toString(costo * hora);
    }

    public static String getSaldo(String sSaldo, String Sconsumo) {
        double saldo = Double.parseDouble(sSaldo);
        double consumo = Double.parseDouble(Sconsumo);

        return Double.toString(saldo - consumo);
    }
}
