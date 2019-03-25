package com.example.android.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.model.Niv6_7Secuence;
import com.example.android.myapplication.model.SecuenciaNiv5;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitGameActivity extends AppCompatActivity {

    public static String EXTRA_DEVICE_ADDRESS = "device_address";
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Point = "pointKey";
    public static final String PointInd = "poinindtKey";
    public static final String Intentos = "intentosKey";
    private SharedPreferences sharedpreferences;
    private SharedPreferences.Editor editor;

    private String address;
    private String SharedSave = "";
    private String findAgeUser;
    private int SharedPoint = 0, IndexLvl = 0, SharedIntentos, intentos = 0;
    private Intent i, j;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_game);
        ButterKnife.bind(this);
        i = new Intent(this, GameActivity.class);
        j = new Intent(this, GameSecNumActivity.class);
        Intent intent = getIntent();
        findAgeUser = intent.getStringExtra(ListaDispositivosFragment.EXTRA_FIND);
        address = intent.getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.prutng)
    public void InitGameLVL() {

        try {
            int findAgeUserInt = Integer.parseInt(findAgeUser);
            if (findAgeUserInt <= 5) {
                CallGame();
            } else if (findAgeUserInt == 6) {
                Toast.makeText(getBaseContext(), findAgeUserInt, Toast.LENGTH_SHORT).show();
            } else if (findAgeUserInt == 7) {
                Toast.makeText(getBaseContext(), findAgeUserInt, Toast.LENGTH_SHORT).show();
            } else if (findAgeUserInt == 8) {
                Toast.makeText(getBaseContext(), findAgeUserInt, Toast.LENGTH_SHORT).show();
               /* secuenciaNiv67.CuartaSec();
                CallGame2();
                sequence2.clear();*/
            }
        } catch (Exception e) {
            //Toast.makeText(getBaseContext(), "No hay registos en DB", Toast.LENGTH_SHORT).show();
            Log.e("ERRORINIT", "error init", e);
        }
    }

    public void SharedPref() {
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        if (sharedpreferences.contains(Name)) {
            SharedSave = sharedpreferences.getString(Name, "");
            Log.d("Shared name", SharedSave);
        }
        if (sharedpreferences.contains(Point)) {
            SharedPoint = sharedpreferences.getInt(Point, 0);
            Log.d("Shared point", String.valueOf(SharedPoint));
        }
        if (sharedpreferences.contains(PointInd)) {
            IndexLvl = sharedpreferences.getInt(PointInd, 0);
            Log.d("Shared pointind", String.valueOf(IndexLvl));
        }
        if (sharedpreferences.contains(Intentos)) {
            SharedIntentos = sharedpreferences.getInt(Intentos, 0);
            Log.d("Shared intentos", String.valueOf(Intentos));
        }
    }

    public void CallGame() {

        SharedPref();
        List<EBotones> SecuenceL1 = new ArrayList<>();
        SecuenceL1.add(EBotones.BUTTON11);
        SecuenceL1.add(EBotones.BUTTON22);
        SecuenceL1.add(EBotones.BUTTON33);
        SecuenceL1.add(EBotones.BUTTON44);
        SecuenceL1.add(EBotones.BUTTONS1);

        List<EBotones> SecuenceL2 = new ArrayList<>();
        SecuenceL2.add(EBotones.BUTTON22);
        SecuenceL2.add(EBotones.BUTTON23);
        SecuenceL2.add(EBotones.BUTTON33);
        SecuenceL2.add(EBotones.BUTTON44);
        SecuenceL2.add(EBotones.BUTTONS2);

        List<EBotones> SecuenceL3 = new ArrayList<>();
        SecuenceL3.add(EBotones.BUTTON11);
        SecuenceL3.add(EBotones.BUTTON33);
        SecuenceL3.add(EBotones.BUTTON32);
        SecuenceL3.add(EBotones.BUTTON34);
        SecuenceL3.add(EBotones.BUTTON44);
        SecuenceL3.add(EBotones.BUTTONS3);

        List<List<EBotones>> SecuenceList = new ArrayList<>();
        SecuenceList.add(0, SecuenceL1);
        SecuenceList.add(1, SecuenceL2);
        SecuenceList.add(2, SecuenceL3);
        //SecuenceList.add(3, SecuenceL3);
        GameSequence seq = new GameSequence();

        seq.setSequence(SecuenceList.get(2));

        if (SharedSave.equals(null) || SharedSave.equals("")) {
            seq.setSequence(SecuenceList.get(0));
        }

        if (SharedSave.equals("Bien")) {
            IndexLvl = SharedPoint + 1;
            if (IndexLvl <= SecuenceList.size() - 1) {
                editor.putInt(Point, IndexLvl);
                editor.commit();
                seq.setSequence(SecuenceList.get(IndexLvl));
                Log.d("Shared pointind", String.valueOf(IndexLvl));
            } else {
                Toast.makeText(getBaseContext(), "Nivel maximo", Toast.LENGTH_SHORT).show();
                editor.putInt(Intentos, 0);
                editor.putInt(Point, 0);
                editor.putInt(PointInd, 0);
                editor.putString(Name, "");
                editor.commit();
                return;
            }
        }
        if (SharedSave.equals("Mal")) {
            seq.setSequence(SecuenceList.get(SharedPoint));
            intentos = SharedIntentos + 1;
            Log.d("Shared intentos", String.valueOf(intentos));
            editor.putInt(Intentos, intentos);
            editor.commit();
            if (SharedIntentos == 3) {
                Toast.makeText(getBaseContext(), "Perdiste", Toast.LENGTH_SHORT).show();
                editor.putInt(Intentos, 0);
                editor.putInt(PointInd, 0);
                editor.putInt(Point, 0);
                editor.putString(Name, "");
                editor.commit();
                return;
            }
        }
        editor.putString(Name, "").commit();

        String tx = new Gson().toJson(seq);
        i.putExtra(GameActivity.SEQUENCE, tx);
        i.putExtra(EXTRA_DEVICE_ADDRESS, address);
        startActivity(i);
    }

    public void CallGame2() {
        /*GameNumSequence seq = new GameNumSequence();
        seq.setSequence(sequence2);
        String tx = new Gson().toJson(seq);
        j.putExtra(GameSecNumActivity.SEQUENCE, tx);
        j.putExtra(EXTRA_DEVICE_ADDRESS, address);
        startActivity(j);*/
    }
}

