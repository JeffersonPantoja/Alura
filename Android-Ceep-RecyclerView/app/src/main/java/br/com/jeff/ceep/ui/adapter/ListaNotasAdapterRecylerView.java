package br.com.jeff.ceep.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.jeff.ceep.R;
import br.com.jeff.ceep.model.Nota;
import br.com.jeff.ceep.ui.holder.NotaViewHolder;

public class ListaNotasAdapterRecylerView extends RecyclerView.Adapter {
    private Context context;
    private List<Nota> notas;

    public ListaNotasAdapterRecylerView(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nota,viewGroup, false);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int posicao) {
        Nota nota = notas.get(posicao);
        TextView titulo = viewHolder.itemView.findViewById(R.id.item_nota_titulo);
        titulo.setText(nota.getTitulo());
        TextView descricao = viewHolder.itemView.findViewById(R.id.item_nota_descricao);
        descricao.setText(nota.getDescricao());
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }
}
