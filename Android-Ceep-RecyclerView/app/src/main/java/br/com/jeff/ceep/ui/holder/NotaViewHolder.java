package br.com.jeff.ceep.ui.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import br.com.jeff.ceep.R;
import br.com.jeff.ceep.model.Nota;

public class NotaViewHolder extends RecyclerView.ViewHolder {

    private final TextView titulo;
    private final TextView descricao;

    public NotaViewHolder(@NonNull View itemView) {
        super(itemView);
        titulo = itemView.findViewById(R.id.item_nota_titulo);
        descricao = itemView.findViewById(R.id.item_nota_descricao);

    }

    public void preencheCampos(Nota nota){
        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());
    }
}
