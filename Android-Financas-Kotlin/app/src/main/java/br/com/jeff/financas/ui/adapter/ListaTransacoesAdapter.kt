package br.com.jeff.financas.ui.adapter

import android.content.Context
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Tipo.RECEITA
import br.com.jeff.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    private val context : Context,
    private val transacoes : List<Transacao>) : BaseAdapter() {

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent,false)
        val transacao : Transacao = transacoes[posicao]


        adicionaValor(view, transacao)
        adicionaIcone(view, transacao)
        adicionaCategoria(view, transacao)
        adicionaData(view, transacao)

        return view
    }

    private fun adicionaValor(view: View, transacao: Transacao) {
        val cor : Int = corPor(transacao.tipo)
        view.transacao_valor.setTextColor(cor)
        view.transacao_valor.text = transacao.valor.formataParaBrasileiro()
    }

    private fun corPor(tipo : Tipo) : Int {
        if (tipo == RECEITA) {
            return ContextCompat.getColor(context, R.color.receita)
        }
        return ContextCompat.getColor(context, R.color.despesa)

    }

    private fun adicionaIcone(view : View, transacao: Transacao){
        val icone = iconePor(transacao.tipo)
        view.transacao_icone.setBackgroundResource(icone)
    }

    private fun iconePor(tipo: Tipo): Int {
        if (tipo == RECEITA) {
            return R.drawable.icone_transacao_item_receita
        }
        return R.drawable.icone_transacao_item_despesa
    }

    private fun adicionaCategoria(view: View, transacao: Transacao) {
        view.transacao_categoria.text = transacao.categoria
    }

    private fun adicionaData(view: View, transacao: Transacao) {
        view.transacao_data.text = transacao.data.formataParaBrasileiro()
    }


    override fun getItem(posicao: Int): Transacao {
        return transacoes[posicao]
    }

    override fun getItemId(posicao: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return transacoes.size
    }
}