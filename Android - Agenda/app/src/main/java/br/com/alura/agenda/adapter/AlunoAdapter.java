package br.com.alura.agenda.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.modelo.Aluno;

/**
 * Created by jefferson on 22/05/18.
 */

public class AlunoAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final Context context;

    public AlunoAdapter(List<Aluno> alunos, Context context) {
        this.alunos = alunos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.alunos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.alunos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return this.alunos.get(i).getId();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        Aluno aluno = alunos.get(i);
        LayoutInflater inflater = LayoutInflater.from(this.context);
        View view = convertView;
        if (view == null){
            view = inflater.inflate(R.layout.item_lista_alunos, parent, false);
        }

        TextView campoNnome = (TextView) view.findViewById(R.id.item_nome);
        TextView campoTelefone = (TextView) view.findViewById(R.id.item_telefone);
        TextView campoEndereco = (TextView) view.findViewById(R.id.item_endereco);
        TextView campoSite = (TextView) view.findViewById(R.id.item_site);
        campoNnome.setText(aluno.getNome());
        campoTelefone.setText(aluno.getTelefone());
        if(campoEndereco != null && campoSite != null){
            campoEndereco.setText(aluno.getEndereco());
            campoSite.setText(aluno.getSite());
        }

        ImageView campoFoto = view.findViewById(R.id.item_foto);
        String caminhoFoto = aluno.getCaminhoFoto();
        if(caminhoFoto != null){
            Bitmap resultBitmap = BitmapFactory.decodeFile(caminhoFoto);
            Bitmap bitmapReduzido = resultBitmap.createScaledBitmap(resultBitmap, 300, 300, true);
            campoFoto.setImageBitmap(bitmapReduzido);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        return view;
    }
}
