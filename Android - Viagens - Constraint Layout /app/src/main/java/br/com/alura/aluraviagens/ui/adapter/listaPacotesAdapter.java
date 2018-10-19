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
import br.com.alura.aluraviagens.util.DiasUtil;
import br.com.alura.aluraviagens.util.MoedaUtil;
import br.com.alura.aluraviagens.util.ResourceUtil;

public class listaPacotesAdapter extends BaseAdapter {

    private final Context context;
    private final List<Pacote> pacotes;

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

        bidinDeCidade(view, pacote);
        bidinDeDias(view, pacote);
        bidinDePreco(view, pacote);
        bidinDeImagem(view, pacote);

        return view;
    }

    private void bidinDeCidade(View view, Pacote pacote) {
        TextView nomeCidade = view.findViewById(R.id.item_pacote_nome_cidade);
        nomeCidade.setText(pacote.getLocal());
    }

    private void bidinDePreco(View view, Pacote pacote) {
        TextView preco = view.findViewById(R.id.item_pacote_preco);
        String moedaBrasileira = MoedaUtil.formataParaBrasileiro(pacote.getPreco());
        preco.setText(moedaBrasileira);
    }

    private void bidinDeImagem(View view, Pacote pacote) {
        ImageView imagem = view.findViewById(R.id.item_pacote_imagem);
        Drawable drawable = ResourceUtil.devolveDrawable(context,pacote.getImagem());
        imagem.setImageDrawable(drawable);
    }

    private void bidinDeDias(View view, Pacote pacote) {
        TextView numeroDias = view.findViewById(R.id.item_pacote_numero_dias);
        String diasEmTexto = DiasUtil.formataEmTexto(pacote.getDias());
        numeroDias.setText(diasEmTexto);
    }


}
