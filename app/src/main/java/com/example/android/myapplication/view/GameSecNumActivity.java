package com.example.android.myapplication.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.myapplication.R;
import com.example.android.myapplication.common.ENnum;
import com.example.android.myapplication.model.GameNumSequence;
import com.example.android.myapplication.presenter.GameSecNumPresenter;
import com.example.android.myapplication.presenter.GameSecNumPresenterImpl;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameSecNumActivity extends AppCompatActivity implements GameSecNumView, MenuUcc.OnFragmentInteractionListener {

    public static final String SEQUENCE = "SEQ";
    static String Randstg;
    public static final String Name = "nameKey";
    public static final String Point = "pointKey";
    public static final String mypreference = "mypref";
    private String Result = "";

    SharedPreferences sharedpreferences;

    @BindView(R.id.Txtv11)
    TextView txtv11;
    @BindView(R.id.Txtv12)
    TextView txtv12;
    @BindView(R.id.Txtv13)
    TextView txtv13;
    @BindView(R.id.Txtv14)
    TextView txtv14;

    @BindView(R.id.Txtv21)
    TextView txtv21;
    @BindView(R.id.Txtv22)
    TextView txtv22;
    @BindView(R.id.Txtv23)
    TextView txtv23;
    @BindView(R.id.Txtv24)
    TextView txtv24;

    @BindView(R.id.IdBufferIn)
    TextView IdBufferIn;

    @BindView(R.id.Siguiente)
    Button Siguiente;

    private GameSecNumPresenter presenter;

    //Funcion para oprimir botones y saber posicion
   /* private View.OnClickListener clickbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            ENnum[] btns = ENnum.values();
            for (int i=0 ; i < btns.length; i++){
                if(v == getTextViewFromEnum(btns[i])){
                    presenter.clickOnButton(btns[i]);
                }
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_niveles6_7);
        //Para implementar botones en el layout
        ButterKnife.bind(this);

        // configOnClick();
        //leer los extras
        String address = getIntent().getStringExtra(ListaDispositivosFragment.EXTRA_DEVICE_ADDRESS);

        String data = getIntent().getStringExtra(SEQUENCE);
        GameNumSequence gs = new Gson().fromJson(data, GameNumSequence.class);

        presenter = new GameSecNumPresenterImpl(this, gs, address);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    //Apagar boton por boton
    public void offButton(ENnum btn) {
        // getTextViewFromEnum(btn).setBackground(getResources().getDrawable(R.drawable.boton_redondo));
    }

    @Override
    //Apagar todos los botones

    public void Borrar(String borrar) {

        if (borrar == "BorrarBotones") {

            String array[] = new String[9];

            String TXT1 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW11).getText());
            String TXT2 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW12).getText());
            String TXT3 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW13).getText());
            String TXT4 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW14).getText());
            String TXT5 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW21).getText());
            String TXT6 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW22).getText());
            String TXT7 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW23).getText());
            String TXT8 = String.valueOf(getTextViewFromEnum(ENnum.TXTVIEW24).getText());
            String conct = TXT1 + "," + TXT2 + "," + TXT3 + "," + TXT4 + "," + TXT5 + "," + TXT6 + "," + TXT7 + "," + TXT8;
            String conct2 = "";
            String[] txts = conct.split(",");

            for (int x = 0; x < array.length - 1; x++) {
                try {
                    if (txts[x].equals("")) {
                        //break;
                    } else {
                        array[x] = txts[x] + ",";
                        conct2 += array[x];
                    }
                } catch (Exception ex) {
                    break;
                }
            }
            presenter.SecuenceRecpt(conct2);
            getTextViewFromEnum(ENnum.TXTVIEW11).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW12).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW13).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW14).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW21).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW22).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW23).setText("");
            getTextViewFromEnum(ENnum.TXTVIEW24).setText("");
        }

        /*FragmentTransaction fragmentTransaction= getSupportFragmentManager().beginTransaction();
        menuUcc=new MenuUcc();
        fragmentTransaction.replace(R.id.ContenedorNiv,menuUcc);
        fragmentTransaction.commit();*/
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


    @OnClick(R.id.Siguiente)
    void Siguiente() {

        String n = "Bien";
        String e = "Mal";
        sharedpreferences = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
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
        try {
            presenter.process();
        } catch (Exception e) {
            Log.e("ErrorProces", "No se pudo Compare: ", e);
        }
    }

    @Override
    public void SentNum(String SentNum) {
        this.Randstg = SentNum;
    }

    @Override
    public void Msg(String msg) {
        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
    }

    //Encender los botones
    @Override
    public void onButton(ENnum txt) {

        presenter.generarSecuencia();

        if (txt == ENnum.TXTVIEW11) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW12) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW13) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW14) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW21) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW22) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW23) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else if (txt == ENnum.TXTVIEW24) {
            getTextViewFromEnum(txt).setText(Randstg);
        } else {
        }
    }

    TextView getTextViewFromEnum(ENnum txt) {
        switch (txt) {
            case TXTVIEW11:
                return txtv11;
            case TXTVIEW12:
                return txtv12;
            case TXTVIEW13:
                return txtv13;
            case TXTVIEW14:
                return txtv14;
            case TXTVIEW21:
                return txtv21;
            case TXTVIEW22:
                return txtv22;
            case TXTVIEW23:
                return txtv23;

            default:
                return txtv24;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

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
