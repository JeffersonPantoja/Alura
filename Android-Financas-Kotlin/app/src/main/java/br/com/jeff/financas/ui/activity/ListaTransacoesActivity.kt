package br.com.jeff.financas.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import br.com.jeff.financas.R
import kotlinx.android.synthetic.main.activity_lista_transacoes.*

class ListaTransacoesActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes = listOf("Teste 01", "Teste 02")
        val arrayadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, transacoes)

        lista_transacoes_listview.adapter = arrayadapter
    }
}