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

public class GameSecNumPresenterSecNumImpl implements GameSecNumPresenter, BTCallback {

    private GameSecNumView view;
    private GameNumSequence sequence;
    private boolean started = false;
    // TIMEPO DE SECUENCIA
    private final int SEQ_TIME = 1000;
    private int index = 0;
    private boolean isOn = false;
    private Timer timer = new Timer();
    private ENnum btn = null;
    private String btAdd;
    private BTUtil btUtil;

    //ArrayList<String> lista = new ArrayList<>(sequence.getSequence().size());

    static Random RandonGenerator = new Random();
    static int randomInteger = 0, cont = 0, Rand1 = 0, Rand2 = 0, Rand3 = 0, Rand4 = 0, Rand5 = 0;
    static String Randstg;


    public GameSecNumPresenterSecNumImpl(GameSecNumView view, GameNumSequence sequence, String btAdd) {
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
                // si no se ha terminado
                //cancelar si el indice es igual a numero de la secuencia
                if (sequence.getSequence().size() <= index) {
                    timer.cancel();
                    view.Borrar("BorrarBotones");
                    process();
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
            }
        }, 1000, SEQ_TIME);
    }

    private void process() {
        try {
            String seqSize = String.valueOf(sequence.getSequence().size());

            btUtil.connect(btAdd, this);
           // btUtil.write(seqSize);
        } catch (Exception e) {
            Log.e("Falla Connected", "Error Conexion Bluetooth ", e);
        }
    }

    @Override
    public void onNext(String data) {
        try {
            btUtil.close();
        } catch (Exception ex) {
            Log.e("Close Socket", "Error cerrando", ex);
        }

        Log.d("DataRecept", data);
        String[] btns = data.split(",");

        boolean flag = true;

        for (int x = 0; x < sequence.getSequence().size(); x++) {
            String tmp = sequence.getSequence().get(x).getId();

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

        for (cont = 0; cont < 4; cont++) {
            randomInteger = RandonGenerator.nextInt(9) + 1;

            switch (cont) {
                case 0:

                    Rand1 = randomInteger;
                    Randstg = Integer.toString(Rand1);
                    view.SentNum(Randstg);
                    break;
                case 1:
                    Rand2 = randomInteger;
                    Randstg = Integer.toString(Rand2);
                    view.SentNum(Randstg);
                    break;
                case 2:
                    Rand3 = randomInteger;
                    Randstg = Integer.toString(Rand3);
                    view.SentNum(Randstg);
                    break;
                case 3:
                    Rand4 = randomInteger;
                    Randstg = Integer.toString(Rand4);
                    view.SentNum(Randstg);
                    break;
                case 4:
                    Rand5 = randomInteger;
                    Randstg = Integer.toString(Rand5);
                    view.SentNum(Randstg);
                    break;

            }
           // lista.add(cont, Randstg);
        }
    }
}
