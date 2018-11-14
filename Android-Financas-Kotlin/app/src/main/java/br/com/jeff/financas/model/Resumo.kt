package br.com.jeff.financas.model

import java.math.BigDecimal

class Resumo(val transacoes : List<Transacao>){

    fun receita(): BigDecimal{
        var totalReceita = BigDecimal.ZERO
        for (transcao in transacoes) {
            if (transcao.tipo == Tipo.RECEITA){
                totalReceita = totalReceita.plus(transcao.valor)
            }
        }

        return totalReceita
    }

    fun despesa(): BigDecimal{
        var totalDespesa = BigDecimal.ZERO
        for (transcao in transacoes) {
            if (transcao.tipo == Tipo.DESPESA){
                totalDespesa = totalDespesa.plus(transcao.valor)
            }
        }
        return totalDespesa
    }

    fun total(): BigDecimal{
        return receita().subtract(despesa())
    }


}