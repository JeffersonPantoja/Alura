package br.com.jeff.financas.Extension

import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Locale

fun BigDecimal.formataParaBrasileiro() : String {
    val formatadorBrasileiro = DecimalFormat.getCurrencyInstance(Locale("pt", "br"))
    return formatadorBrasileiro.format(this)
}