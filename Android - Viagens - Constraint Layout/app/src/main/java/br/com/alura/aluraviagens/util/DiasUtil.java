package br.com.alura.aluraviagens.util;

public class DiasUtil {

    public static final String SINGULAR = " dia";
    public static final String PLURAL = "dias";

    public static String formataEmTexto(int quantidadeDias) {
        String diasEmTexto;
        if(quantidadeDias > 1)
            diasEmTexto = quantidadeDias + " " + PLURAL;
        else
            diasEmTexto = quantidadeDias + SINGULAR;
        return diasEmTexto;
    }
}
