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
    private List<ENnum> sequence2 = new ArrayList<>();
    private Niv6_7Secuence secuenciaNiv67 = new Niv6_7Secuence(sequence2);
    List<List<ENnum>> SecuenceList = new ArrayList<>();
    List<List<EBotones>> SecuenceListColors = new ArrayList<>();

    private String IndLvl;
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
            }
            if (findAgeUserInt == 6) {

                IndLvl = "6años";
                //secuenciaNiv67.CuartaSec();
                CallGame2(IndLvl);
                SecuenceList.clear();

                //           Toast.makeText(this, findAgeUser, Toast.LENGTH_SHORT).show();
            }
            if (findAgeUserInt == 7) {
                IndLvl = "7años";
                //secuenciaNiv67.CuartaSec();
                CallGame2(IndLvl);
                SecuenceList.clear();
                // Toast.makeText(this, findAgeUser, Toast.LENGTH_SHORT).show();
            }
            if (findAgeUserInt == 8) {
                IndLvl = "8años";
                //secuenciaNiv67.CuartaSec();
                CallGame2(IndLvl);
                SecuenceList.clear();
                //Toast.makeText(this, findAgeUser, Toast.LENGTH_SHORT).show();
               /* secuenciaNiv67.CuartaSec();
                CallGame2();
                sequence2.clear();*/
            }
        } catch (Exception e) {
            Toast.makeText(this, "No hay registos en DB", Toast.LENGTH_SHORT).show();
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
        SecuenceL1.add(EBotones.BUTTON12);
        SecuenceL1.add(EBotones.BUTTON13);
        SecuenceL1.add(EBotones.BUTTON23);
        SecuenceL1.add(EBotones.BUTTON33);
        SecuenceL1.add(EBotones.BUTTON32);
        SecuenceL1.add(EBotones.BUTTON31);
        SecuenceL1.add(EBotones.BUTTON21);
        SecuenceL1.add(EBotones.BUTTONS1);

        List<EBotones> SecuenceL2 = new ArrayList<>();
        SecuenceL2.add(EBotones.BUTTON12);
        SecuenceL2.add(EBotones.BUTTON22);
        SecuenceL2.add(EBotones.BUTTON23);
        SecuenceL2.add(EBotones.BUTTON14);
        SecuenceL2.add(EBotones.BUTTON24);
        SecuenceL2.add(EBotones.BUTTON34);
        SecuenceL2.add(EBotones.BUTTON44);
        SecuenceL2.add(EBotones.BUTTONS2);

        List<EBotones> SecuenceL3 = new ArrayList<>();
        SecuenceL3.add(EBotones.BUTTON12);
        SecuenceL3.add(EBotones.BUTTON13);
        SecuenceL3.add(EBotones.BUTTON24);
        SecuenceL3.add(EBotones.BUTTON34);
        SecuenceL3.add(EBotones.BUTTON43);
        SecuenceL3.add(EBotones.BUTTON42);
        SecuenceL3.add(EBotones.BUTTON31);
        SecuenceL3.add(EBotones.BUTTON21);
        SecuenceL3.add(EBotones.BUTTONS3);

        List<EBotones> SecuenceL4 = new ArrayList<>();
        SecuenceL4.add(EBotones.BUTTON11);
        SecuenceL4.add(EBotones.BUTTON12);
        SecuenceL4.add(EBotones.BUTTON13);
        SecuenceL4.add(EBotones.BUTTON14);
        SecuenceL4.add(EBotones.BUTTON24);
        SecuenceL4.add(EBotones.BUTTON34);
        SecuenceL4.add(EBotones.BUTTON44);
        SecuenceL4.add(EBotones.BUTTON33);
        SecuenceL4.add(EBotones.BUTTON22);
        SecuenceL4.add(EBotones.BUTTONS4);


        SecuenceListColors.add(0, SecuenceL1);
        SecuenceListColors.add(1, SecuenceL2);
        SecuenceListColors.add(2, SecuenceL3);
        SecuenceListColors.add(3, SecuenceL4);


        //SecuenceList.add(3, SecuenceL3);
        GameSequence seq = new GameSequence();

        //  seq.setSequence(SecuenceList.get(2));

        if (SharedSave.equals(null) || SharedSave.equals("")) {
            seq.setSequence(SecuenceListColors.get(0));
        }

        if (SharedSave.equals("Bien")) {
            IndexLvl = SharedPoint + 1;
            if (IndexLvl <= SecuenceListColors.size() - 1) {
                editor.putInt(Point, IndexLvl);
                editor.commit();
                seq.setSequence(SecuenceListColors.get(IndexLvl));
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
            seq.setSequence(SecuenceListColors.get(SharedPoint));
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

        SecuenceListColors.clear();
    }

    public void CallGame2(String indLvl) {

        SharedPref();
        List<ENnum> SecuenceL1 = new ArrayList<>();
        SecuenceL1.add(ENnum.TXTVIEW11);
        SecuenceL1.add(ENnum.TXTVIEW12);
        SecuenceL1.add(ENnum.TXTVIEW13);
        SecuenceL1.add(ENnum.TXTVIEWS1);

        List<ENnum> SecuenceL2 = new ArrayList<>();
        SecuenceL2.add(ENnum.TXTVIEW11);
        SecuenceL2.add(ENnum.TXTVIEW12);
        SecuenceL2.add(ENnum.TXTVIEW13);
        SecuenceL2.add(ENnum.TXTVIEW14);
        SecuenceL2.add(ENnum.TXTVIEWS2);

        List<ENnum> SecuenceL3 = new ArrayList<>();
        SecuenceL3.add(ENnum.TXTVIEW11);
        SecuenceL3.add(ENnum.TXTVIEW12);
        SecuenceL3.add(ENnum.TXTVIEW13);
        SecuenceL3.add(ENnum.TXTVIEW14);
        SecuenceL3.add(ENnum.TXTVIEW21);
        SecuenceL3.add(ENnum.TXTVIEWS3);

        List<ENnum> SecuenceL4 = new ArrayList<>();
        SecuenceL4.add(ENnum.TXTVIEW11);
        SecuenceL4.add(ENnum.TXTVIEW12);
        SecuenceL4.add(ENnum.TXTVIEW13);
        SecuenceL4.add(ENnum.TXTVIEW14);
        SecuenceL4.add(ENnum.TXTVIEW21);
        SecuenceL4.add(ENnum.TXTVIEW22);
        SecuenceL4.add(ENnum.TXTVIEWS4);

        List<ENnum> SecuenceL5 = new ArrayList<>();
        SecuenceL5.add(ENnum.TXTVIEW11);
        SecuenceL5.add(ENnum.TXTVIEW12);
        SecuenceL5.add(ENnum.TXTVIEW13);
        SecuenceL5.add(ENnum.TXTVIEW14);
        SecuenceL5.add(ENnum.TXTVIEW21);
        SecuenceL5.add(ENnum.TXTVIEW22);
        SecuenceL5.add(ENnum.TXTVIEW23);
        SecuenceL5.add(ENnum.TXTVIEWS5);

        switch (indLvl) {
            case "6años":

                SecuenceList.add(0, SecuenceL1);
                SecuenceList.add(1, SecuenceL2);
                SecuenceList.add(2, SecuenceL3);
                break;
            //SecuenceList.add(3, SecuenceL3);
            case "7años":
                SecuenceList.add(0, SecuenceL2);
                SecuenceList.add(1, SecuenceL3);
                SecuenceList.add(2, SecuenceL4);
                break;

            case "8años":
                SecuenceList.add(0, SecuenceL3);
                SecuenceList.add(1, SecuenceL4);
                SecuenceList.add(2, SecuenceL5);
                break;
        }


        GameNumSequence seq = new GameNumSequence();
        // seq.setSequence(SecuenceList.get(0));
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
        j.putExtra(GameSecNumActivity.SEQUENCE, tx);
        j.putExtra(EXTRA_DEVICE_ADDRESS, address);
        startActivity(j);
    }
}

