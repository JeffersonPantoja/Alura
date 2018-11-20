package br.com.jeff.financas.model

import java.math.BigDecimal

class Resumo(val transacoes : List<Transacao>){

    //função tipificada
    fun receita(): BigDecimal = somaPor(Tipo.RECEITA)

    //property
    val despesa get() = somaPor(Tipo.DESPESA)

    fun total() = receita().subtract(despesa)

    private fun somaPor(tipo : Tipo): BigDecimal {
        val somaTransacoesPeloTipo = transacoes
            .filter { transacao -> transacao.tipo == tipo }
            .sumByDouble { it.valor.toDouble() } // it no lugar da ->
        return BigDecimal(somaTransacoesPeloTipo)
    }

}