package com.example.maxi.siem.util;

public class CalculateUtils {

    public CalculateUtils() {
    }

    public static String getMaxTime(String saldoStr, String costoStr) {
        double saldo = Double.valueOf(saldoStr);
        double costo = Double.valueOf(costoStr);

        double r = saldo / costo;

        return Double.toString(r) + " hs";
    }
}
