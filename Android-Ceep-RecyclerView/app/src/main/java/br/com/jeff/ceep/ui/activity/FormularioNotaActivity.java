package br.com.jeff.ceep.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import br.com.jeff.ceep.R;
import br.com.jeff.ceep.model.Nota;

import static br.com.jeff.ceep.ui.activity.Constantes.CHAVE_NOTA;
import static br.com.jeff.ceep.ui.activity.Constantes.RESULTADO_NOTA_CRIADA;

public class FormularioNotaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_nota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(ehMenuSalvaNota(item.getItemId())){
            Nota nota = criaNota();
            retornaNotaCriada(nota);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean ehMenuSalvaNota(int idMenu) {
        return idMenu == R.id.menu_formulario_salva_nota;
    }

    @NonNull
    private Nota criaNota() {
        EditText titulo = findViewById(R.id.formulario_nota_titulo);
        EditText descricao = findViewById(R.id.formulario_nota_descricao);
        return new Nota(titulo.getText().toString(), descricao.getText().toString());
    }

    private void retornaNotaCriada(Nota nota) {
        Intent intent = new Intent();
        intent.putExtra(CHAVE_NOTA, nota);
        setResult(RESULTADO_NOTA_CRIADA, intent);
    }
}
