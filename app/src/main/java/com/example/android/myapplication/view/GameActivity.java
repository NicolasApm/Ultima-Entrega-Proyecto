package com.example.android.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.EBotones;
import com.example.android.myapplication.model.GameSequence;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenter;
import com.example.android.myapplication.presenter.ConectarBluetoothPresenterImpl;
import com.example.android.myapplication.presenter.GamePresenter;
import com.example.android.myapplication.presenter.GamePresenterImpl;
import com.google.gson.Gson;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity implements GameView, ConectarBluetoothView {

    public static final String SEQUENCE = "SEQ";
    public static final String BTADD = "BTADD";
    public static final String Name = "nameKey";
    public static final String Point = "pointKey";
    public static final String mypreference = "mypref";
    Intent j;
    private String Result = "";

    SharedPreferences sharedpreferences;


    @BindView(R.id.btn11)
    Button btn11;
    @BindView(R.id.btn12)
    Button btn12;
    @BindView(R.id.btn13)
    Button btn13;
    @BindView(R.id.btn14)
    Button btn14;

    @BindView(R.id.btn21)
    Button btn21;
    @BindView(R.id.btn22)
    Button btn22;
    @BindView(R.id.btn23)
    Button btn23;
    @BindView(R.id.btn24)
    Button btn24;

    @BindView(R.id.btn31)
    Button btn31;
    @BindView(R.id.btn32)
    Button btn32;
    @BindView(R.id.btn33)
    Button btn33;
    @BindView(R.id.btn34)
    Button btn34;

    @BindView(R.id.btn41)
    Button btn41;
    @BindView(R.id.btn42)
    Button btn42;
    @BindView(R.id.btn43)
    Button btn43;
    @BindView(R.id.btn44)
    Button btn44;

    @BindView(R.id.Siguiente)
    Button Siguiente;

    @BindView(R.id.Compare)
    Button Compare;

    private GamePresenter presenter;

    //Funcion para oprimir botones y saber posicion
   /* private View.OnClickListener clickbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            EBotones[] btns = EBotones.values();
            for (int i=0 ; i < btns.length; i++){
                if(v == getButtonFromEnum(btns[i])){
                    presenter.clickOnButton(btns[i]);
                }
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //Para implementar botones en el layout
        ButterKnife.bind(this);
        // configOnClick();
        //leer los extras
        String data = getIntent().getStringExtra(SEQUENCE);
        String address = getIntent().getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);
        //String address = getIntent().getStringExtra(BTADD);
        GameSequence gs = new Gson().fromJson(data, GameSequence.class);
        //Consigue la direccion MAC desde DeviceListActivity via EXTRA
//        String address = getIntent().getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);
//        Log.d("nnnnnnnnnnnnnnnn", address);
        // presenter = new ConectarBluetoothPresenterImpl(this, address);
        presenter = new GamePresenterImpl(this, gs, address);
        j = new Intent(this, InitGameActivity.class);


    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }


    //Apagar boton por boton
    public void offButton(EBotones btn) {
        // getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
    }

    @Override
    //Apagar todos los botones
    public void Borrar(String borrar) {
        if (borrar.equals("BorrarBotones")) {
            getButtonFromEnum(EBotones.BUTTON11).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON12).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON13).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON14).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON21).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON22).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON23).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON24).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON31).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON32).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON33).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON34).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON41).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON42).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON43).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            getButtonFromEnum(EBotones.BUTTON44).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
        }
    }

    @Override
    public void Result(String Result) {
        this.Result = Result;

        boolean flag;
        if (Result.equals("BIEN")) {
            flag = true;
            ToastPesonalizado(flag);
            //IdBufferIn.setText(Result);
        } else {
            flag = false;
            ToastPesonalizado(flag);
        }
    }

    @Override
    public void Msg(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.Siguiente)
    void Siguiente() {

        //Intent i = new Intent(this, InitGameActivity.class);
        //startActivity(i);
        //finish();
        String n = "Bien";
        String e = "Mal";
        sharedpreferences = getSharedPreferences(mypreference,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.commit();
        editor.putString(Name, n);
        editor.commit();
        Log.d("result", Result);
        if (Result.equals("BIEN")) {
            editor.putString(Name, n).commit();
           // editor.putInt(Point, 1).commit();
        } else {
            editor.putString(Name, e).commit();
       }
        finish();
    }

    @OnClick(R.id.Compare)
    void Compare() {
        try{
            presenter.process();
            }
        catch (Exception e){
            Log.e("ErrorProces", "No se pudo Compare: ",e);
        }
    }


    //Encender los botones
    @Override
    public void onButton(EBotones btn) {
        if (btn == EBotones.BUTTON11) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo11));
        }
        if (btn == EBotones.BUTTON12) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo12));
        }
        if (btn == EBotones.BUTTON13) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo13));
        }
        if (btn == EBotones.BUTTON14) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo14));
        }
        if (btn == EBotones.BUTTON21) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo21));
        }
        if (btn == EBotones.BUTTON22) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo22));
        }
        if (btn == EBotones.BUTTON23) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo23));
        }
        if (btn == EBotones.BUTTON24) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo24));
        }
        if (btn == EBotones.BUTTON31) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo31));
        }
        if (btn == EBotones.BUTTON32) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo32));
        }
        if (btn == EBotones.BUTTON33) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo33));
        }
        if (btn == EBotones.BUTTON34) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo34));
        }
        if (btn == EBotones.BUTTON41) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo41));
        }
        if (btn == EBotones.BUTTON42) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo42));
        }
        if (btn == EBotones.BUTTON43) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo43));
        }
        if (btn == EBotones.BUTTON44) {
            getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo44));
        }
       if (btn == EBotones.BUTTONS1||btn == EBotones.BUTTONS2||btn == EBotones.BUTTONS3||btn == EBotones.BUTTONS4){
           // getButtonFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
            Log.d("Bitfin","bitfin");

        }
    }

    Button getButtonFromEnum(EBotones btn) {
        switch (btn) {
            case BUTTON11:
                return btn11;
            case BUTTON12:
                return btn12;
            case BUTTON13:
                return btn13;
            case BUTTON14:
                return btn14;
            case BUTTON21:
                return btn21;
            case BUTTON22:
                return btn22;
            case BUTTON23:
                return btn23;
            case BUTTON24:
                return btn24;
            case BUTTON31:
                return btn31;
            case BUTTON32:
                return btn32;
            case BUTTON33:
                return btn33;
            case BUTTON34:
                return btn34;
            case BUTTON41:
                return btn41;
            case BUTTON42:
                return btn42;
            case BUTTON43:
                return btn43;
            //case BUTTON44:
            default:
                return btn44;
        }
    }

    @Override
    public void showData(String data) {
        Toast.makeText(getBaseContext(), data, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void RunOnMain(Runnable action) {

    }
    public void ToastPesonalizado(boolean flag) {

        LayoutInflater inflater = getLayoutInflater();
        if (flag) {

            View view = inflater.inflate(R.layout.custom_toast_layout, (ViewGroup) findViewById(R.id.relativeLayout1));
            Toast toast = new Toast(this);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            View view = inflater.inflate(R.layout.custom_toast_layout2, (ViewGroup) findViewById(R.id.relativeLayout2));
            Toast toast = new Toast(this);
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

}
