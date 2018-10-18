package br.com.alura.agenda.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.telephony.SmsMessage;
import android.widget.Toast;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;

/**
 * Created by jefferson on 24/05/18.
 */

public class SMSreceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {

        Object[] pdus = (Object[]) intent.getSerializableExtra("pdus");
        byte[] pdu = (byte[]) pdus[0];
        String formato = (String) intent.getSerializableExtra("format");

        SmsMessage sms = SmsMessage.createFromPdu(pdu, formato);

        String telefone = sms.getDisplayOriginatingAddress();

        AlunoDAO alunoDAO = new AlunoDAO(context);

        if(alunoDAO.ehAluno(telefone)){
            Toast.makeText(context, "Mensagem recebida de " + telefone, Toast.LENGTH_SHORT).show();
            MediaPlayer  mp = MediaPlayer.create(context, R.raw.msg);
            mp.start();
        }

        alunoDAO.close();
    }
}
