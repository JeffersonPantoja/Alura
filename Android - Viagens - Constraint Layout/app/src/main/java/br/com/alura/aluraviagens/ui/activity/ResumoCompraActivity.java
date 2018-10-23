package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.util.DataUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

import static br.com.alura.aluraviagens.ui.activity.Constantes.EXTRA_PACOTE;
import static br.com.alura.aluraviagens.ui.activity.ResumoPacoteActivity.PACOTE_ERRO;

public class ResumoCompraActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Resumo Compra";
    private Pacote pacote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo_compra);

        setTitle(TITULO_APPBAR);
        carregaPAcote();
    }

    private void carregaPAcote() {
        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_PACOTE)){
            pacote = intent.getExtras().getParcelable(EXTRA_PACOTE);
            mostraLocal(pacote);
            mostraData(pacote);
            mostraImagem(pacote);
            mostraPreco(pacote);
        }else {
            Toast.makeText(this, PACOTE_ERRO,Toast.LENGTH_SHORT).show();
        }
    }

    private void mostraLocal(Pacote pacoteSaoPaulo) {
        TextView local = findViewById(R.id.resumo_compra_pacote_local);
        local.setText(pacoteSaoPaulo.getLocal());
    }

    private void mostraData(Pacote pacoteSaoPaulo) {
        TextView data = findViewById(R.id.resumo_compra_pacote_data);
        data.setText(DataUtil.periodoEmTextto(pacoteSaoPaulo.getDias()));
    }

    private void mostraImagem(Pacote pacoteSaoPaulo) {
        ImageView imagemPacote = findViewById(R.id.resumo_compra_pacote_imagem);
        imagemPacote.setImageDrawable(ResourceUtil.devolveDrawable(this, pacoteSaoPaulo.getImagem()));
    }

    private void mostraPreco(Pacote pacoteSaoPaulo) {
        TextView precoPacote = findViewById(R.id.resumo_compra_pacote_preco);
        precoPacote.setText(MoedaUtil.formataParaBrasileiro(pacoteSaoPaulo.getPreco()));
    }
}
