package br.com.jeff.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import java.math.BigDecimal
import java.util.*

class ListaTransacoesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf(Transacao(BigDecimal(500), "Eletrônicos", Calendar.getInstance()),
            Transacao(BigDecimal(1000), "Esporte", Calendar.getInstance())
        )

        lista_transacoes_listview.adapter = ListaTransacoesAdapter(this,transacoes)
    }
}