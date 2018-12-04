package br.com.jeff.financas.ui.dialog

import android.content.Context
import android.view.ViewGroup
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.delegate.TransacaoDelegate

class AlteraTransacaoDialog(
    viewGroup: ViewGroup,
    private val context: Context
) : FormularioTransacaoDialog(viewGroup, context) {
    override val botaoPositivo: String
        get() = "Alterar"

    fun chama(transacao: Transacao, transacaoDelegate: TransacaoDelegate) {

        val tipo = transacao.tipo
        super.chama(tipo, transacaoDelegate)

        inicializaCampos(transacao)
    }

    private fun inicializaCampos(transacao: Transacao) {
        inicializaValor(transacao)
        inicializaData(transacao)
        inicializaCategoria(transacao)
    }

    private fun inicializaValor(transacao: Transacao) {
        campoValor.setText(transacao.valor.toString())
    }

    private fun inicializaData(transacao: Transacao) {
        campoData.setText(transacao.data.formataParaBrasileiro())
    }

    private fun inicializaCategoria(transacao: Transacao) {
        val tipo = transacao.tipo
        val categoriasRetornadas = context.resources.getStringArray(categoriaPor(tipo))
        val posicao = categoriasRetornadas.indexOf(transacao.categoria)
        campoCategoria.setSelection(posicao)
    }

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.altera_receita
        }
        return R.string.altera_despesa
    }
}