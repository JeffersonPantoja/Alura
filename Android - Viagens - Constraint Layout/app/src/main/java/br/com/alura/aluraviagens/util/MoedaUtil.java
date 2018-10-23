package br.com.alura.aluraviagens.util;

import android.support.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

    @NonNull
    public static String formataParaBrasileiro(double valor) {
        NumberFormat formatadorBrasileiro = DecimalFormat.getCurrencyInstance(new Locale("pt","br"));
        return formatadorBrasileiro
                .format(valor)
                .replace("R$","R$ ");
    }
}
