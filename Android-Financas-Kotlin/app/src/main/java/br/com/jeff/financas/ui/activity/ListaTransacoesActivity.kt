package br.com.jeff.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.AdapterView
import br.com.jeff.financas.R
import br.com.jeff.financas.dao.TransacaoDao
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.ResumoView
import br.com.jeff.financas.ui.adapter.ListaTransacoesAdapter
import br.com.jeff.financas.ui.delegate.TransacaoDelegate
import br.com.jeff.financas.ui.dialog.AdicionaTransacaoDialog
import br.com.jeff.financas.ui.dialog.AlteraTransacaoDialog
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val dao = TransacaoDao()
    private val transacoes = dao.transacoes

//    private lateinit var viewDaActivity: View

    private val viewDaActivity by lazy {
        window.decorView as ViewGroup
    }

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
        AdicionaTransacaoDialog(viewDaActivity, this)
            .chama(tipo) {transacaoCriada ->
                adiciona(transacaoCriada)
                lista_transacoes_adiciona_menu.close(true)
            }
    }


    private fun adiciona(transacao: Transacao) {
        dao.adiciona(transacao)
        atualizaTransacoes()
    }

    private fun atualizaTransacoes() {
        configuraAdapter()
        configuraResumo()
    }

    private fun configuraResumo() {
        val resumoView = ResumoView(this, viewDaActivity, transacoes)
        resumoView.atualiza()
    }

    private fun configuraAdapter() {
        with(lista_transacoes_listview){
            adapter = ListaTransacoesAdapter(this@ListaTransacoesActivity, transacoes)
            setOnItemClickListener { _, _, posicao, _ ->
                val transacao = transacoes[posicao]
                chamaDialogDeAlteracao(transacao, posicao)
            }
            setOnCreateContextMenuListener { menu, _, _ ->
                menu.add(Menu.NONE,1,Menu.NONE,"Remover")
            }
        }
    }


    override fun onContextItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        if(id == 1){
            val adapterContextMenuInfo = item?.menuInfo as AdapterView.AdapterContextMenuInfo
            val posicao = adapterContextMenuInfo.position
            removeTransacaoPor(posicao)
        }
        return super.onContextItemSelected(item)
    }

    private fun removeTransacaoPor(posicao: Int) {
        dao.remove(posicao)
        atualizaTransacoes()
    }

    private fun chamaDialogDeAlteracao(transacao: Transacao, posicao: Int) {
        AlteraTransacaoDialog(viewDaActivity, this)
            .chama(transacao) {transacaoAlterada ->
                altera(transacaoAlterada, posicao)
                lista_transacoes_adiciona_menu.close(true)
            }
    }

    private fun altera(transacao: Transacao, posicao: Int) {
        dao.altera(transacao,posicao)
        atualizaTransacoes()
    }

}