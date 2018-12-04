package br.com.jeff.financas.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import br.com.jeff.financas.Extension.converteParaCalendar
import br.com.jeff.financas.Extension.formataParaBrasileiro
import br.com.jeff.financas.R
import br.com.jeff.financas.model.Tipo
import br.com.jeff.financas.model.Transacao
import br.com.jeff.financas.ui.delegate.TransacaoDelegate
import kotlinx.android.synthetic.main.form_transacao.view.*
import java.math.BigDecimal
import java.util.*

abstract class FormularioTransacaoDialog(private val viewGroup: ViewGroup, private val context: Context) {

    private val viewCriada = criarLayout()
    protected val campoData = viewCriada.form_transacao_data
    protected val campoValor = viewCriada.form_transacao_valor
    protected val campoCategoria = viewCriada.form_transacao_categoria
    abstract val botaoPositivo: String

    private fun criarLayout() = LayoutInflater.from(context)
        .inflate(R.layout.form_transacao, viewGroup, false)

    fun chama(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {
        configuraCampoData()
        configuraCampoCategoria(tipo)
        configuraFormulario(tipo, transacaoDelegate)
    }

    private fun configuraCampoData() {
        val hoje = Calendar.getInstance()
        campoData.setText(hoje.formataParaBrasileiro())

        val ano = hoje.get(Calendar.YEAR)
        val mes = hoje.get(Calendar.MONTH)
        val dia = hoje.get(Calendar.DAY_OF_MONTH)

        campoData.setOnClickListener {
            DatePickerDialog(context, { _, ano, mes, dia ->
                val dataSelecionada = Calendar.getInstance()
                dataSelecionada.set(ano, mes, dia)
                campoData.setText(dataSelecionada.formataParaBrasileiro())
            }, ano, mes, dia).show()
        }
    }


    private fun configuraCampoCategoria(tipo: Tipo) {

        val categorias = categoriaPor(tipo)
        val adapter = ArrayAdapter.createFromResource(
            context,
            categorias,
            android.R.layout.simple_spinner_dropdown_item
        )
        campoCategoria.adapter = adapter
    }

    private fun configuraFormulario(tipo: Tipo, transacaoDelegate: TransacaoDelegate) {

        val titulo = tituloPor(tipo)

        AlertDialog.Builder(context)
            .setTitle(titulo)
            .setView(viewCriada)
            .setPositiveButton(botaoPositivo) { _, _ ->
                val valorEmTexto = campoValor.text.toString()
                val dataEmTexto = campoData.text.toString()
                val categoriaEmTexto = campoCategoria.selectedItem.toString()

                val valor = converteCampoValor(valorEmTexto)
                val data = dataEmTexto.converteParaCalendar()
                val transacaoCriada = Transacao(valor, categoriaEmTexto, tipo, data)
                transacaoDelegate.delegate(transacaoCriada)
            }
            .setNegativeButton("Cancelar", null)
            .show()
    }

    private fun converteCampoValor(valorEmTexto: String): BigDecimal {
        val valor = try {
            BigDecimal(valorEmTexto)
        } catch (exception: Exception) {
            Toast.makeText(context, "Erro de conversão", Toast.LENGTH_SHORT).show()
            BigDecimal.ZERO
        }
        return valor
    }

    protected fun categoriaPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.array.categorias_de_receita
        }
        return R.array.categorias_de_despesa
    }

    abstract fun tituloPor(tipo: Tipo): Int
}