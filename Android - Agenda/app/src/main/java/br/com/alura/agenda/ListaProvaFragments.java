package br.com.alura.agenda;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.alura.agenda.dao.Prova;

public class ListaProvaFragments extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        List<String> topicosDePortugues = Arrays.asList("Objeto direto e indireto", "Pronome");
        Prova provaDePortugues = new Prova("Português", "01/07/2018", topicosDePortugues);

        List<String> topicosdeMatematica = Arrays.asList("Equação Afim", "Equação do segundo grau");
        Prova provaDeMatematica = new Prova("Matemática", "02/07/2018", topicosdeMatematica);

        List<Prova> provas = Arrays.asList(provaDePortugues, provaDeMatematica);

        ArrayAdapter<Prova> adapter = new ArrayAdapter<Prova>(getContext(), android.R.layout.simple_list_item_1, provas);

        ListView provasListView = (ListView) view.findViewById(R.id.provas_lista);
        provasListView.setAdapter(adapter);
        provasListView.setOnItemClickListener(setProvasListView());

        return view;
    }

    @NonNull
    private AdapterView.OnItemClickListener setProvasListView() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProvasActivity provasActivity = (ProvasActivity) getActivity();


                provasActivity.selecionaProva((Prova) parent.getItemAtPosition(position));
            }
        };
    }
}
