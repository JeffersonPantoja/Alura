package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do Pacote";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);

        setTitle(TITULO_APPBAR);

        Pacote pacoteSaoPaulo = new Pacote("São Paulo", "sao_paulo_sp",
                2, new BigDecimal("243.99"));

        bidinPacote(pacoteSaoPaulo);
    }

    private void bidinPacote(Pacote pacoteSaoPaulo) {
        bidinImagemPacote(pacoteSaoPaulo);
        bidinLocal(pacoteSaoPaulo);
        bidinQuantidadeDias(pacoteSaoPaulo);
        bidingPreco(pacoteSaoPaulo);
        bidingData(pacoteSaoPaulo);
        configuraBotao();
    }

    private void configuraBotao() {
        Button pagamentoBotao = findViewById(R.id.resumo_pacote_realizar_pagamento);
        pagamentoBotao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoPacoteActivity.this, PagamentoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bidingData(Pacote pacoteSaoPaulo) {
        TextView data = findViewById(R.id.resumo_pacote_data);
        String dataFormatadaviagem = DataUtil.periodoEmTextto(pacoteSaoPaulo.getDias());
        data.setText(dataFormatadaviagem);
    }

    private void bidingPreco(Pacote pacoteSaoPaulo) {
        TextView preco = findViewById(R.id.resumo_pacote_preco);
        preco.setText(MoedaUtil.formataParaBrasileiro(pacoteSaoPaulo.getPreco()));
    }

    private void bidinQuantidadeDias(Pacote pacoteSaoPaulo) {
        TextView quantidadeDias = findViewById(R.id.resumo_pacote_quantidade_dias);
        quantidadeDias.setText(DiasUtil.formataEmTexto(pacoteSaoPaulo.getDias()));
    }

    private void bidinLocal(Pacote pacoteSaoPaulo) {
        TextView nomeLocal = findViewById(R.id.resumo_pacote_local);
        nomeLocal.setText(pacoteSaoPaulo.getLocal());
    }

    private void bidinImagemPacote(Pacote pacoteSaoPaulo) {
        ImageView imagemPacote = findViewById(R.id.resumo_pacote_imagem);
        imagemPacote.setImageDrawable(ResourceUtil.devolveDrawable(this,pacoteSaoPaulo.getImagem()));
    }

}
