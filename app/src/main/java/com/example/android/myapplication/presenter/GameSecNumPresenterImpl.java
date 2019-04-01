package com.example.android.myapplication.presenter;

import android.util.Log;

import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.devices.BTCallback;
import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.view.GameSecNumView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GameSecNumPresenterImpl implements GameSecNumPresenter, BTCallback {

    private GameSecNumView view;
    private GameNumSequence sequence;
    private boolean started = false;
    // TIMEPO DE SECUENCIA
    private final int SEQ_TIME = 500;
    private int index = 0;
    private boolean isOn = false;
    private Timer timer = new Timer();
    private ENnum btn = null;
    private String btAdd;
    private BTUtil btUtil;
    private String NumRecpt = "";

    //ArrayList<String> lista = new ArrayList<>(sequence.getSequence().size());

    static Random RandonGenerator = new Random();
    static int randomInteger = 0, cont = 0, Rand1 = 0;
    static String Randstg;


    public GameSecNumPresenterImpl(GameSecNumView view, GameNumSequence sequence, String btAdd) {
        this.view = view;
        this.sequence = sequence;
        this.btAdd = btAdd;
        this.btUtil = new BTUtil();
    }

    @Override
    public void clickOnButton(ENnum button) {

    }

    @Override
    public void start() {
        if (started) return;
        started = true;
        //timmer
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    // si no se ha terminado
                    //cancelar si el indice es igual a numero de la secuencia
                    if (sequence.getSequence().size() <= index) {
                        timer.cancel();
                        view.Borrar("BorrarBotones");
                        return;
                    }
                    btn = sequence.getSequence().get(index);
                    if (isOn) {
                        //condicion para validar si estan encendidos los botones
                        //view.offButton(btn);
                        isOn = false;
                        index++;
                    } else {
                        //Encender si estan apagados
                        view.onButton(btn);
                        isOn = true;
                    }
                } catch (Exception e) {
                    view.Msg("Intenta de nuevo");
                }
            }
        }, 1000, SEQ_TIME);
    }


    @Override
    public void SecuenceRecpt(String dataNum) {
        this.NumRecpt = dataNum;
    }

    public void process() {
        try {
            String seqSize = String.valueOf(sequence.getSequence().size() - 1);
            String seqIndent = sequence.getSequence().get(sequence.getSequence().size() - 1).getId();
            btUtil.connect(btAdd, this);
            btUtil.write(seqSize, seqIndent);

            // btUtil.write(seqSize);
        } catch (Exception e) {
            view.Msg("Error Conexion Bluetooth");
            Log.e("Falla Connected", "Error Conexion Bluetooth ", e);
        }
        Log.d("RECEPCION", NumRecpt);
        //view.Result("MAL");
    }

    @Override
    public void onNext(String data) {
        try {
            btUtil.close();
        } catch (Exception ex) {
            view.Msg("Error cerrando Bluetooth");
            Log.e("Close Socket", "Error cerrando", ex);
        }
        Log.d("DataRecept", data);

        String[] btns = data.split(",");
        String[] NumSplit = NumRecpt.split(",");

        String[] invertido = new String[btns.length];
        for (int i = 0; i < btns.length; i++) {
            invertido[i] = btns[btns.length - 1 - i];
        }
        btns = invertido;

        boolean flag = true;

        for (int x = 0; x < sequence.getSequence().size() - 1; x++) {
            String tmp = NumSplit[x];

            try {
                if (!tmp.equals(btns[x])) {
                    flag = false;
                    break;
                }
            } catch (Exception ex) {
                flag = false;
                break;
            }
        }
        if (flag) {
            Log.d("Comparacion Secuencia", "Bien");
            view.Result("BIEN");
        } else {
            Log.d("Comparacion Secuencia", "Mal");
            view.Result("MAL");
        }
        btUtil.close();
    }

    @Override
    public void onError(Exception e) {

        Log.e("nnnnnnnnnnn", "Error2", e);

    }

    public void generarSecuencia() {

        for (cont = 0; cont < 7; cont++) {
            randomInteger = RandonGenerator.nextInt(9) + 1;

            Rand1 = randomInteger;
            Randstg = Integer.toString(Rand1);
            view.SentNum(Randstg);
        }
    }
}
