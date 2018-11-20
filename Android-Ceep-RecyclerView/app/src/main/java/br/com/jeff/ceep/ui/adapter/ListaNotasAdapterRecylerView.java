package br.com.jeff.ceep.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.com.jeff.ceep.R;
import br.com.jeff.ceep.model.Nota;
import br.com.jeff.ceep.ui.holder.NotaViewHolder;
import br.com.jeff.ceep.ui.listener.OnNotaClickListener;

public class ListaNotasAdapterRecylerView extends RecyclerView.Adapter<NotaViewHolder> {
    private Context context;
    private List<Nota> notas;
    private int count = 0;
    private OnNotaClickListener onNotaClickListener;

    public ListaNotasAdapterRecylerView(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    public void setOnNotaClickListener(OnNotaClickListener onNotaClickListener) {
        this.onNotaClickListener = onNotaClickListener;
    }

    @NonNull
    @Override
    public NotaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nota,viewGroup, false);
        count++;
        Log.i("adapter", "view holder bind: " + count);
        return new NotaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotaViewHolder viewHolder, int posicao) {
        Nota nota = notas.get(posicao);
        viewHolder.preencheCampos(nota);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNotaClickListener.onNotaClick();
            }
        });

    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public void adiciona(Nota nota) {
        notas.add(nota);
        notifyDataSetChanged();
    }
}
