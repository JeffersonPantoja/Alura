package br.com.jeff.financas.ui.dialog

import android.app.DatePickerDialog
import android.content.Context
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
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

class AdicionaTransacaoDialog(
    viewGroup: ViewGroup,
    context: Context
) : FormularioTransacaoDialog(viewGroup,context) {

    override val botaoPositivo: String
        get() = "Adicionar"

    override fun tituloPor(tipo: Tipo): Int {
        if (tipo == Tipo.RECEITA) {
            return R.string.adiciona_receita
        }
        return R.string.adiciona_despesa
    }
}