package br.com.jeff.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.ResumoView
import br.com.jeff.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = listaDeExemplo()
        configuraAdapter(transacoes)


        val resumoView = ResumoView(this, window.decorView, transacoes)
        resumoView.atualiza()
    }

    private fun configuraAdapter(transacoes: List<Transacao>) {
        lista_transacoes_listview.adapter = ListaTransacoesAdapter(this, transacoes)
    }

    private fun listaDeExemplo(): List<Transacao> {
        val transacoes = listOf(
            Transacao(
                valor = BigDecimal(500),
                tipo = Tipo.DESPESA,
                data = Calendar.getInstance()
            ),

            Transacao(
                valor = BigDecimal(1000),
                categoria = "Categoria esporte que vai ficar grandão",
                tipo = Tipo.RECEITA
            )
        )
        return transacoes
    }
}