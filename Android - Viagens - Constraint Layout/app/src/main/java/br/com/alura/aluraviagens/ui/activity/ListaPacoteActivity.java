package br.com.alura.aluraviagens.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDao;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.ui.adapter.listaPacotesAdapter;

import static br.com.alura.aluraviagens.ui.activity.Constantes.EXTRA_PACOTE;

public class ListaPacoteActivity extends AppCompatActivity {

    private String TITULO = "Pacotes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_pacote);
        setTitle(TITULO);
        configuraLista();
    }

    private void configuraLista() {
        final ListView listaPacotes = findViewById(R.id.lista_pacote_pacotes);
        final List<Pacote> pacotes = new PacoteDao().lista();
        listaPacotes.setAdapter(new listaPacotesAdapter(this, pacotes));
        listaPacotes.setOnItemClickListener(vaiParaResumo(pacotes));
    }

    @NonNull
    private AdapterView.OnItemClickListener vaiParaResumo(final List<Pacote> pacotes) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListaPacoteActivity.this, ResumoPacoteActivity.class);
                Pacote pacote = pacotes.get(position);
                intent.putExtra(EXTRA_PACOTE, pacote);
                startActivity(intent);
            }
        };
    }
}
