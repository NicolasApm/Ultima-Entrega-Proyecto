package com.example.android.myapplication.presenter;

import android.util.Log;

import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.devices.BTCallback;
import com.example.android.myapplication.devices.BTUtil;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.view.GameView;

import java.util.Timer;
import java.util.TimerTask;

public class GamePresenterImpl implements GamePresenter, BTCallback {

    private GameView view;
    private GameSequence sequence;
    private boolean started = false;
    // TIMEPO DE SECUENCIA
    private final int SEQ_TIME = 1000;
    private int index = 0;
    private boolean isOn = false;
    private Timer timer = new Timer();
    private EBotones btn = null;
    private String btAdd;
    private BTUtil btUtil;


    public GamePresenterImpl(GameView view, GameSequence sequence, String btAdd) {
        this.view = view;
        this.sequence = sequence;
        this.btAdd = btAdd;
        this.btUtil = new BTUtil();
    }

    @Override
    public void clickOnButton(EBotones button) {

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
                    try{
                    process();}
                    catch (Exception e){

                    }
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
            String seqSize = String.valueOf(sequence.getSequence().size()-1);
            String seqIndent = sequence.getSequence().get(sequence.getSequence().size()-1).getId();

            btUtil.connect(btAdd, this);
            btUtil.write(seqSize,seqIndent);
        } catch (Exception e) {
            Log.e("Falla Connected", "Error Conexion Bluetooth ", e);
        }
       // view.Result("MAL");
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
          //  List<EBotones> lista = new ArrayList<>();
        boolean flag = true;

        for (int x = 0; x < sequence.getSequence().size()-1; x++) {
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
}
