package br.com.jeff.financas.ui

import android.view.View
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.model.Resumo
import br.com.jeff.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*

class ResumoView(private val view: View,transacoes: List<Transacao>){


    val resumo: Resumo = Resumo(transacoes)

    fun adicionaReceita() {
        val receita = resumo.receita()
        view.resumo_card_receita.text = receita.formataParaBrasileiro()
    }

    fun aidicionaDespesa() {
        var despesa = resumo.despesa()
        view.resumo_card_despesa.text = despesa.formataParaBrasileiro()
    }

    fun adicionaTotal(){
        val total = resumo.total()
        view.resumo_card_total.text = total.formataParaBrasileiro()
    }

}