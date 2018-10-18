package br.com.alura.agenda;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.adapter.AlunoAdapter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class ListaAlunosActivity extends AppCompatActivity {

    private ListView listaAlunos;
    private Button novoAluno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        int i = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);
        if(i != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String[] {Manifest.permission.RECEIVE_SMS}, 321);
        }

        listaAlunos = (ListView) findViewById(R.id.lista_alunos);
        novoAluno = (Button) findViewById(R.id.novo_aluno);

        listaAlunos.setOnItemClickListener(editarAluno());
        novoAluno.setOnClickListener(adicionarAluno());

        registerForContextMenu(listaAlunos);
    }



    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @NonNull
    private View.OnClickListener adicionarAluno() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentVaiProFormulario);
            }
        };
    }

    @NonNull
    private AdapterView.OnItemClickListener editarAluno() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(position);
                Intent intentVaiProFormulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentVaiProFormulario.putExtra("aluno", aluno);
                startActivity(intentVaiProFormulario);
            }
        };
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) listaAlunos.getItemAtPosition(info.position);

        //Opção ligar para o aluno
        MenuItem itemLigar = menu.add("Ligar");
        itemLigar.setOnMenuItemClickListener(menuLigarAluno(aluno));

        //Opção de envio de SMS para o aluno
        this.setOpcaoMenu(menu, "Enviar SMS", Uri.parse("sms:" + aluno.getTelefone()));
        //Opção de visualizar endereço no google mpas
        this.setOpcaoMenu(menu, "Ver Endereço", Uri.parse("geo:0,0?q="+aluno.getEndereco()));
        //Opção de site do aluno
        String siteAluno = aluno.getSite().startsWith("http://") ? aluno.getSite() : "http://" + aluno.getSite();
        this.setOpcaoMenu(menu, "Visitar Site", Uri.parse(siteAluno));
        //opção para deletar aluno
        MenuItem itemDeletar = menu.add("Deletar");
        itemDeletar.setOnMenuItemClickListener(menuDeletarAluno(aluno));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_enviar_notas:
                EnviarDadosServidor task =  new EnviarDadosServidor(this);
                task.execute();
                break;
            case R.id.menu_baixar_provas:
                Intent irParaProvas = new Intent(this, ProvasActivity.class);
                startActivity(irParaProvas);
                break;
            case R.id.menu_mapa:
                Intent irParaMapa =  new Intent(this, MapaActivity.class);
                startActivity(irParaMapa);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private MenuItem.OnMenuItemClickListener menuLigarAluno(final Aluno aluno) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this, new String [] {Manifest.permission.CALL_PHONE},123);
                }else{
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("call:" + aluno.getTelefone()));
                    startActivity(intentLigar);
                }

                return false;
            }
        };
    }

    @NonNull
    private MenuItem.OnMenuItemClickListener menuDeletarAluno(final Aluno aluno) {
        return new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                return deletarAluno(aluno);
            }
        };
    }

    private boolean deletarAluno(Aluno aluno) {
        Toast.makeText(ListaAlunosActivity.this, "Deletar o aluno " + aluno.getNome(), Toast.LENGTH_SHORT).show();
        AlunoDAO dao = new AlunoDAO(ListaAlunosActivity.this);
        dao.deleta(aluno);
        dao.close();
        carregaLista();
        return false;
    }

    private void setOpcaoMenu(ContextMenu menu, String nomeMenu, Uri parse) {
        MenuItem itemMenu = menu.add(nomeMenu);
        Intent intentMenu = new Intent(Intent.ACTION_VIEW);
        intentMenu.setData(parse);
        itemMenu.setIntent(intentMenu);
    }

    private void carregaLista() {
        AlunoDAO dao = new AlunoDAO(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();
        AlunoAdapter adapter = new AlunoAdapter(alunos, this);
        listaAlunos.setAdapter(adapter);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 123){
            //faz algo
        }
    }
}
