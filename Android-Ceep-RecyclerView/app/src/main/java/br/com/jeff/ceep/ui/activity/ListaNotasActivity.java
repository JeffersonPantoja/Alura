package br.com.jeff.ceep.ui.activity;

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


        RecyclerView listaNotas = findViewById(R.id.recyclerviewlist);

        NotaDAO notaDAO = new NotaDAO();
        notaDAO.insere(new Nota("Primeira nota", "Descrição da primeira nota"));

        ListaNotasAdapterRecylerView listaNotasAdapter = new ListaNotasAdapterRecylerView(this, notaDAO.todos());
        listaNotas.setAdapter(listaNotasAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        listaNotas.setLayoutManager(linearLayoutManager);

    }
}
