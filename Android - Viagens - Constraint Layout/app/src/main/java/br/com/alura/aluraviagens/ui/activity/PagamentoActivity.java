package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.MoedaUtil;

import static br.com.alura.aluraviagens.ui.activity.Constantes.EXTRA_PACOTE;
import static br.com.alura.aluraviagens.ui.activity.ResumoPacoteActivity.PACOTE_ERRO;

public class PagamentoActivity extends AppCompatActivity {

    private static final String TITULO_APPBAR = "Pagamento";
    private Pacote pacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagamento);

        setTitle(TITULO_APPBAR);

        Intent intent = getIntent();
        carregaPacote(intent);
    }

    private void carregaPacote(Intent intent) {
        if(intent.hasExtra(EXTRA_PACOTE)){
            pacote = intent.getExtras().getParcelable(EXTRA_PACOTE);
            mostraPreco(pacote);
            configuraBotaoFinalizaCompra();
        }else {
            Toast.makeText(this, PACOTE_ERRO,Toast.LENGTH_SHORT).show();
        }
    }

    private void mostraPreco(Pacote pacoteSaoPaulo) {
        TextView precoPacote = findViewById(R.id.pagamento_pacote_preco);
        precoPacote.setText(MoedaUtil.formataParaBrasileiro(pacoteSaoPaulo.getPreco()));
    }

    private void configuraBotaoFinalizaCompra() {
        Button botaoFinalizaCompra = findViewById(R.id.pagamento_finaliza_compra);
        botaoFinalizaCompra.setOnClickListener(finalizaCompra());
    }

    @NonNull
    private View.OnClickListener finalizaCompra() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PagamentoActivity.this, ResumoCompraActivity.class);
                intent.putExtra(EXTRA_PACOTE, pacote);
                startActivity(intent);
            }
        };
    }
}
