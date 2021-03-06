package br.com.jeff.financas.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.View
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Resumo
import br.com.jeff.financas.model.Transacao
import kotlinx.android.synthetic.main.resumo_card.view.*
import java.math.BigDecimal

class ResumoView(
    context: Context,
    private val view: View,
    transacoes: List<Transacao>
) {

    private val resumo: Resumo = Resumo(transacoes)
    private val corReceita = ContextCompat.getColor(context, R.color.receita)
    private val corDespesa = ContextCompat.getColor(context, R.color.despesa)

    fun atualiza() {
        adicionaReceita()
        adicionaDespesa()
        adicionaTotal()
    }

    private fun adicionaReceita() {
        val receita = resumo.receita()

//        view?.let { //essa técnica é mais comum, mas não é a ideal
//            with(it.resumo_card_receita) {
//                text = receita.formataParaBrasileiro()
//                setTextColor(corReceita)
//            }
//        }

//        view?.resumo_card_receita?.text = receita.formataParaBrasileiro() é uma forma de tratar o null safely
//        view?.resumo_card_receita?.setTextColor(corReceita)

        with(view.resumo_card_receita) {
            text = receita.formataParaBrasileiro()
            setTextColor(corReceita)
        }
    }

    private fun adicionaDespesa() {
        var despesa = resumo.despesa
//        view?.let {
//            with(it.resumo_card_despesa) {
//                text = despesa.formataParaBrasileiro()
//                setTextColor(corDespesa)
//            }
//        }

        with(view.resumo_card_despesa) {
            text = despesa.formataParaBrasileiro()
            setTextColor(corDespesa)
        }
    }

    private fun adicionaTotal() {
        val total = resumo.total()
        val cor = corPor(total)
//        view?.let{
//            with(it.resumo_card_total) {
//                text = total.formataParaBrasileiro()
//                setTextColor(cor)
//            }
//        }

        with(view.resumo_card_total) {
            text = total.formataParaBrasileiro()
            setTextColor(cor)
        }
    }

    private fun corPor(total: BigDecimal): Int {
        if (total >= BigDecimal.ZERO) {
            return corReceita
        }
        return corDespesa
    }

}