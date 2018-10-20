package br.com.alura.aluraviagens.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

public class ResumoPacoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_pacote);

        Pacote pacoteSaoPaulo = new Pacote("São Paulo", "sao_paulo_sp",
                2, new BigDecimal("243.99"));


        bidinPacote(pacoteSaoPaulo);


    }

    private void bidinPacote(Pacote pacoteSaoPaulo) {
        bidinImagemPacote(pacoteSaoPaulo);

        TextView nomeLocal = findViewById(R.id.resumo_pacote_local);
        nomeLocal.setText(pacoteSaoPaulo.getLocal());

        TextView quantidadeDias = findViewById(R.id.resumo_pacote_quantidade_dias);
        quantidadeDias.setText(DiasUtil.formataEmTexto(pacoteSaoPaulo.getDias()));

        TextView preco = findViewById(R.id.resumo_pacote_preco);
        preco.setText(MoedaUtil.formataParaBrasileiro(pacoteSaoPaulo.getPreco()));
    }

    private void bidinImagemPacote(Pacote pacoteSaoPaulo) {
        ImageView imagemPacote = findViewById(R.id.resumo_pacote_imagem);
        imagemPacote.setImageDrawable(ResourceUtil.devolveDrawable(this,pacoteSaoPaulo.getImagem()));
    }

}
