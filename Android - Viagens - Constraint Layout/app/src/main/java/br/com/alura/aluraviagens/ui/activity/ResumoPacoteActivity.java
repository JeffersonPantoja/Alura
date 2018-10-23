package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import static br.com.alura.aluraviagens.ui.activity.Constantes.EXTRA_PACOTE;

public class ResumoPacoteActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo do Pacote";
    public static final String PACOTE_ERRO = "Pacote não encontrado";
    private Pacote pacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);

        setTitle(TITULO_APPBAR);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_PACOTE)){
            pacote = intent.getExtras().getParcelable(EXTRA_PACOTE);
            bidinPacote(pacote);
        }else {
            Toast.makeText(this, PACOTE_ERRO,Toast.LENGTH_SHORT).show();
        }

    }

    private void bidinPacote(Pacote pacoteSaoPaulo) {
        mostraImagem(pacoteSaoPaulo);
        mostraLocal(pacoteSaoPaulo);
        mostraQuantidadeDias(pacoteSaoPaulo);
        mostraPreco(pacoteSaoPaulo);
        mostraData(pacoteSaoPaulo);
        configuraBotao();
    }

    private void configuraBotao() {
        Button pagamentoBotao = findViewById(R.id.resumo_pacote_realizar_pagamento);
        pagamentoBotao.setOnClickListener(vaiParaPAgamento());
    }

    @NonNull
    private View.OnClickListener vaiParaPAgamento() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResumoPacoteActivity.this, PagamentoActivity.class);
                intent.putExtra(EXTRA_PACOTE, pacote);
                startActivity(intent);
            }
        };
    }

    private void mostraData(Pacote pacoteSaoPaulo) {
        TextView data = findViewById(R.id.resumo_compra_pacote_data);
        String dataFormatadaviagem = DataUtil.periodoEmTextto(pacoteSaoPaulo.getDias());
        data.setText(dataFormatadaviagem);
    }

    private void mostraPreco(Pacote pacoteSaoPaulo) {
        TextView preco = findViewById(R.id.resumo_compra_pacote_preco);
        preco.setText(MoedaUtil.formataParaBrasileiro(pacoteSaoPaulo.getPreco()));
    }

    private void mostraQuantidadeDias(Pacote pacoteSaoPaulo) {
        TextView quantidadeDias = findViewById(R.id.resumo_pacote_quantidade_dias);
        quantidadeDias.setText(DiasUtil.formataEmTexto(pacoteSaoPaulo.getDias()));
    }

    private void mostraLocal(Pacote pacoteSaoPaulo) {
        TextView nomeLocal = findViewById(R.id.resumo_pacote_local);
        nomeLocal.setText(pacoteSaoPaulo.getLocal());
    }

    private void mostraImagem(Pacote pacoteSaoPaulo) {
        ImageView imagemPacote = findViewById(R.id.resumo_compra_pacote_imagem);
        imagemPacote.setImageDrawable(ResourceUtil.devolveDrawable(this,pacoteSaoPaulo.getImagem()));
    }

}
