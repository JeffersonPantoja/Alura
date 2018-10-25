package br.com.jeff.financas.Extension

import java.text.SimpleDateFormat
import java.util.*


fun Calendar.formataParaBrasileiro(): String? {
    val fomatatoBrasileiro = "dd/MM/yyyy"
    val formatador = SimpleDateFormat(fomatatoBrasileiro)
    val dataFormatada = formatador.format(this.time)
    return dataFormatada
}