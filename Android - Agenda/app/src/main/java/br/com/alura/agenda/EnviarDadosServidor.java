package br.com.alura.agenda;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;

import br.com.alura.agenda.converter.AlunoConverter;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.modelo.Aluno;

public class EnviarDadosServidor extends AsyncTask<Object, Object, String>{
    private Context context;
    private ProgressDialog dialog;

    public EnviarDadosServidor(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        dialog = ProgressDialog.show(context, "Aguarde", "Enviando dados");
        dialog.show();

    }

    @Override
    protected String doInBackground(Object... objects) {

        AlunoDAO alunoDAO = new AlunoDAO(context);
        List<Aluno> alunos = alunoDAO.buscaAlunos();
        alunoDAO.close();

        AlunoConverter conversor = new AlunoConverter();
        String jsonAlunos = conversor.converteToJson(alunos);

        WebClient webClient = new WebClient();
        String resposta = webClient.post(jsonAlunos);


        return resposta;
    }

    @Override
    protected void onPostExecute(String  reposta) {
        dialog.dismiss();
        Toast.makeText(context, reposta, Toast.LENGTH_SHORT).show();
    }
}
