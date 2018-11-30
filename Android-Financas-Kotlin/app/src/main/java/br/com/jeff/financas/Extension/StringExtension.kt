package br.com.jeff.financas.Extension

import java.text.SimpleDateFormat
import java.util.*


fun String.converteParaCalendar(): Calendar {
    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
    val dataConvertida: Date = formatoBrasileiro.parse(this)
    val data = Calendar.getInstance()
    data.time = dataConvertida
    return data
}