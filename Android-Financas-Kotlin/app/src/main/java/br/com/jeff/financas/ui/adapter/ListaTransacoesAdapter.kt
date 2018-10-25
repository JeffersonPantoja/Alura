package br.com.jeff.financas.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Transacao
import kotlinx.android.synthetic.main.transacao_item.view.*

class ListaTransacoesAdapter(
    val context : Context,
    val transacoes : List<Transacao>) : BaseAdapter() {

    override fun getView(posicao: Int, view: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.transacao_item, parent,false)
        val transacao : Transacao = transacoes[posicao]

        view.transacao_valor.text = transacao.valor.toString()
        view.transacao_categoria.text = transacao.categoria
        view.transacao_data.text =  transacao.data.formataParaBrasileiro()

        return view
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