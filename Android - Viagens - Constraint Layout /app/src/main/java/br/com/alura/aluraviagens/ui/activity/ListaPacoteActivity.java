package br.com.alura.aluraviagens.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.dao.PacoteDao;
import br.com.alura.aluraviagens.model.Pacote;
import br.com.alura.aluraviagens.ui.adapter.listaPacotesAdapter;

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
        ListView listaPacotes = findViewById(R.id.lista_pacote_pacotes);
        List<Pacote> pacotes = new PacoteDao().lista();
        listaPacotes.setAdapter(new listaPacotesAdapter(this, pacotes));
    }
}
