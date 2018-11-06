package br.com.jeff.ceep.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import br.com.jeff.ceep.R;
import br.com.jeff.ceep.dao.NotaDAO;
import br.com.jeff.ceep.model.Nota;
import br.com.jeff.ceep.ui.adapter.ListaNotasAdapter;
import br.com.jeff.ceep.ui.adapter.ListaNotasAdapterRecylerView;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        NotaDAO notaDAO = notasDeExemplo();
        configuraRecyclerView(notaDAO);

    }

    @NonNull
    private NotaDAO notasDeExemplo() {
        NotaDAO notaDAO = new NotaDAO();
        notaDAO.insere(new Nota("Primeira nota", "Descrição da primeira nota"));
        return notaDAO;
    }

    private void configuraRecyclerView(NotaDAO notaDAO) {
        RecyclerView listaNotas = findViewById(R.id.recyclerviewlist);
        configuraAdapter(notaDAO, listaNotas);
//        configuraLayoutManager(listaNotas);
    }

    private void configuraAdapter(NotaDAO notaDAO, RecyclerView listaNotas) {
        listaNotas.setAdapter(new ListaNotasAdapterRecylerView(this, notaDAO.todos()));
    }

//    private void configuraLayoutManager(RecyclerView listaNotas) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        listaNotas.setLayoutManager(linearLayoutManager);
//    }
}
