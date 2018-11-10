package br.com.jeff.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import br.com.jeff.ceep.R;
import br.com.jeff.ceep.dao.NotaDAO;
import br.com.jeff.ceep.model.Nota;
import br.com.jeff.ceep.ui.adapter.ListaNotasAdapterRecylerView;

import static br.com.jeff.ceep.ui.activity.Constantes.CHAVE_NOTA;
import static br.com.jeff.ceep.ui.activity.Constantes.CODIGO_REQUISICAO_INSERI_NOTA;
import static br.com.jeff.ceep.ui.activity.Constantes.RESULTADO_NOTA_CRIADA;

public class ListaNotasActivity extends AppCompatActivity {


    private ListaNotasAdapterRecylerView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);

        List<Nota> listaDeNotas = pegaTodasAsNotas();
        configuraRecyclerView(listaDeNotas);

        configuraBotaoInseriNota();

    }

    private void configuraBotaoInseriNota() {
        TextView insereNota = findViewById(R.id.lista_notas_insere_nota);
        insereNota.setOnClickListener(vaiParaFormularioNota());
    }

    @NonNull
    private View.OnClickListener vaiParaFormularioNota() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vaiParaFormularioNota =
                        new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivityForResult(vaiParaFormularioNota, CODIGO_REQUISICAO_INSERI_NOTA);
            }
        };
    }

    private List<Nota> pegaTodasAsNotas() {
        NotaDAO notaDAO = new NotaDAO();
        return notaDAO.todos();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (ehResultadoComNota(requestCode, resultCode, data)) {
            Nota notaRecebida = (Nota) data.getExtras().getSerializable(CHAVE_NOTA);
            adiciona(notaRecebida);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void adiciona(Nota nota) {
        new NotaDAO().insere(nota);
        adapter.adiciona(nota);
    }

    private boolean ehResultadoComNota(int requestCode, int resultCode, @Nullable Intent data) {
        return ehRequisicaoInseriNota(requestCode)
                && ehResultadoNotaCriada(resultCode)
                && data.hasExtra(CHAVE_NOTA);
    }

    private boolean ehResultadoNotaCriada(int resultCode) {
        return resultCode == RESULTADO_NOTA_CRIADA;
    }

    private boolean ehRequisicaoInseriNota(int requestCode) {
        return requestCode == CODIGO_REQUISICAO_INSERI_NOTA;
    }

    private void configuraRecyclerView(List<Nota> notas) {
        RecyclerView recyclerview = findViewById(R.id.recyclerviewlist);
        configuraAdapter(recyclerview, notas);
//        configuraLayoutManager(recyclerview);
    }

    private void configuraAdapter(RecyclerView recyclerview, List<Nota> notas) {
        adapter = new ListaNotasAdapterRecylerView(this, notas);
        recyclerview.setAdapter(adapter);
    }

//    private void configuraLayoutManager(RecyclerView recyclerview) {
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
//        recyclerview.setLayoutManager(linearLayoutManager);
//    }
}
