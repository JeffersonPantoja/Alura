package br.com.jeff.financas.ui.activity

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.ResumoView
import br.com.jeff.financas.ui.adapter.ListaTransacoesAdapter
import kotlinx.android.synthetic.main.activity_lista_transacoes.*
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.*

class ListaTransacoesActivity : AppCompatActivity() {

    private val transacoes : MutableList = MutableList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_transacoes)

        val transacoes: List<Transacao> = listaDeExemplo()
        configuraAdapter(transacoes)
        configuraResumo(transacoes)

        lista_transacoes_adiciona_receita.setOnClickListener {

            val view : View = window.decorView
            val viewCriada = LayoutInflater.from(this).inflate(R.layout.form_transacao, view as ViewGroup, false)

            val hoje = Calendar.getInstance()
            viewCriada.form_transacao_data.setText(hoje.formataParaBrasileiro())

            val ano = 2018
            val mes = 11
            val dia = 20

            viewCriada.form_transacao_data.setOnClickListener {
                DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, ano, mes, dia ->
                    val dataSelecionada = Calendar.getInstance()
                    dataSelecionada.set(ano, mes, dia)
                    viewCriada.form_transacao_data.setText(dataSelecionada.formataParaBrasileiro())
                }, ano, mes, dia)
                    .show()
            }

            val adapter = ArrayAdapter.createFromResource(this,
                R.array.categorias_de_receita,
                android.R.layout.simple_spinner_dropdown_item)

            viewCriada.form_transacao_categoria.adapter = adapter

            AlertDialog.Builder(this)
                .setTitle("Alert dialog")
                .setView(viewCriada)
                .setPositiveButton("Adicionar") { dialogInterface, i ->
                    val valorEmTexto = viewCriada.form_transacao_valor.text.toString()
                    val dataEmTexto = viewCriada.form_transacao_data.text.toString()
                    val categoriaEmTexto = viewCriada.form_transacao_categoria.selectedItem.toString()

                    val valor = BigDecimal(valorEmTexto)

                    val formatoBrasileiro = SimpleDateFormat("dd/MM/yyyy")
                    val dataConvertida: Date = formatoBrasileiro.parse(dataEmTexto)
                    val data = Calendar.getInstance()
                    data.time = dataConvertida

                    val transacaoCriada = Transacao(valor, categoriaEmTexto, Tipo.RECEITA, data)

                    Toast.makeText(this, "${transacaoCriada.valor} - " +
                    "${transacaoCriada.categoria} - " +
                            "${transacaoCriada.data.formataParaBrasileiro()} - " +
                            "${transacaoCriada.tipo}", Toast.LENGTH_LONG).show()

                }
                .setNegativeButton("Cancelar",null)
                .show()
        }
    }

    private fun configuraResumo(transacoes: List<Transacao>) {
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