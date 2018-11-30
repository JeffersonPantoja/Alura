package br.com.jeff.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.ResumoView
import br.com.jeff.financas.ui.adapter.ListaTransacoesAdapter
import br.com.jeff.financas.ui.delegate.TransacaoDelegate
import br.com.jeff.financas.ui.dialog.AdicionaTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes: MutableList<Transacao> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        configuraAdapter()
        configuraResumo()
        configuraFab()
    }

    private fun configuraFab() {
        lista_transacoes_adiciona_receita
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.RECEITA)
            }

        lista_transacoes_adiciona_despesa
            .setOnClickListener {
                chamaDialogDeAdicao(Tipo.DESPESA)
            }
    }

    private fun chamaDialogDeAdicao(tipo: Tipo) {
        AdicionaTransacaoDialog(window.decorView as ViewGroup, this)
            .chama(tipo, object : TransacaoDelegate {
                override fun delegate(transacao: Transacao) {
                    atualizaTransacoes(transacao)
                    lista_transacoes_adiciona_menu.close(true)
                }
            })
    }

    private fun atualizaTransacoes(transacao: Transacao) {
        transacoes.add(transacao)
        configuraAdapter()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, window.decorView, transacoes)
        resumoView.atualiza()
    }

    private fun configuraAdapter() {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(this, transacoes)
    }

}