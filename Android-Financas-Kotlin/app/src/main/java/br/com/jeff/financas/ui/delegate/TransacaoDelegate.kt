package br.com.jeff.financas.ui.delegate

import br.com.jeff.financas.model.Transacao

interface TransacaoDelegate {
    fun delegate(transacao: Transacao)
}