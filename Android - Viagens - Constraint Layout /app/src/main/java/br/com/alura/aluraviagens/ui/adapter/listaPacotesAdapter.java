package br.com.alura.aluraviagens.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.alura.aluraviagens.R;
import br.com.alura.aluraviagens.model.Pacote;

public class listaPacotesAdapter extends BaseAdapter {

    private Context context;
    private List<Pacote> pacotes;

    public listaPacotesAdapter(Context context, List<Pacote> Pacotes) {
        this.context = context;
        this.pacotes = Pacotes;
    }

    @Override
    public int getCount() {
        return pacotes.size();
    }

    @Override
    public Object getItem(int position) {
        return pacotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pacote, parent, false);

        Pacote pacote = pacotes.get(position);

        TextView nomeCidade = view.findViewById(R.id.item_pacote_nome_cidade);
        nomeCidade.setText(pacote.getLocal());

        TextView numeroDias = view.findViewById(R.id.item_pacote_numero_dias);
        numeroDias.setText(pacote.getDias() + " Dias");

        TextView preco = view.findViewById(R.id.item_pacote_preco);
        preco.setText(pacote.getPreco().toString());

        Resources resources = context.getResources();
        int idDrawble = resources.getIdentifier(pacote.getImagem(),"drawable",context.getPackageName());
        Drawable drawable = resources.getDrawable(idDrawble);
        ImageView imagem = view.findViewById(R.id.item_pacote_imagem);
        imagem.setImageDrawable(drawable);


        return view;
    }
}
