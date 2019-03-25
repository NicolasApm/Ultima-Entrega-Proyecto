package com.example.android.myapplication.view;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

import OpenHelper.Sqlite_OpenHelper;


public class Registro extends Fragment {

    private Button btnGrabarUsu, btnPrueba1, btnPrueba2, btnPrueba3, btnPrueba4, btnPrueba5;
    Intent i, j;
    public EditText txtNomusu, txtApellidousu, txtEdadusu;
    private List<EBotones> sequence = new ArrayList<>();
    private SecuenciaNiv5 secuenciaNiv5 = new SecuenciaNiv5(sequence);
    private List<ENnum> sequence2 = new ArrayList<>();
    private Niv6_7Secuence secuenciaNiv67 = new Niv6_7Secuence(sequence2);

    public String getBtAdress() {
        return btAdress;
    }

    public void setBtAdress(String btAdress) {
        this.btAdress = btAdress;
    }

    private String btAdress;


    // secuenciaNiv5 = new SecuenciaNiv5(sequence);
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Registro() {
        // Required empty public constructor
    }

    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_registro_, container, false);

        txtNomusu = (rootView.findViewById(R.id.txtnomusu));
        txtApellidousu = (rootView.findViewById(R.id.txtciudadusu));
        txtEdadusu = (rootView.findViewById(R.id.txtedadusu));
        i = new Intent(getActivity(), GameActivity.class);
        j = new Intent(getActivity(), GameSecNumActivity.class);
        // Inflate the layout for this fragment
        return rootView;
    }

    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        btnGrabarUsu = (Button) getActivity().findViewById(R.id.RegistroUsu);
        btnGrabarUsu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Sqlite_OpenHelper helper=new Sqlite_OpenHelper(getActivity(),"usuario",null,1);
                helper.abrirdb();
                helper.insertarReg(String.valueOf(txtNomusu.getText()),
                String.valueOf(txtApellidousu.getText()),
                String.valueOf(txtEdadusu.getText()));
                helper.cerrardb();

            }
        });
/*
        btnPrueba1 = getActivity().findViewById(R.id.pruebaNivel);
        btnPrueba1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                secuenciaNiv5.PrimeraSec();
                CallGame();
                sequence.clear();
            }
        });
        btnPrueba2 = getActivity().findViewById(R.id.pruebaNive2);
        btnPrueba2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.SegundaSec();
                CallGame();
                sequence.clear();
            }
        });
        btnPrueba3 = getActivity().findViewById(R.id.pruebaNive3);
        btnPrueba3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.TerceraSec();
                CallGame();
                sequence.clear();
            }
        });

        btnPrueba4 = getActivity().findViewById(R.id.PruebaNive4);
        btnPrueba4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv5.CuartaSec();
                CallGame();
                sequence.clear();
            }
        });

        btnPrueba5 = getActivity().findViewById(R.id.PruebaNive5);
        btnPrueba5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                secuenciaNiv67.PrimeraSec();
                CallGame2();
                sequence2.clear();
            }
        });*/
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

   /* public void CallGame() {

        if (getBtAdress() != null) {
            GameSequence seq = new GameSequence();
            seq.setSequence(sequence);

            String tx = new Gson().toJson(seq);

            i.putExtra(GameActivity.SEQUENCE, tx);
            i.putExtra(GameActivity.BTADD, getBtAdress());
            startActivity(i);
        } else {
            Log.d("nnnnnnnnnn", "Seleccione dispositivo BT primero");
        }
    }

    public void CallGame2() {

        GameNumSequence seq = new GameNumSequence();
        seq.setSequence(sequence2);

        String tx = new Gson().toJson(seq);

        j.putExtra(GameSecNumActivity.SEQUENCE, tx);
        startActivity(j);
    }*/

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
