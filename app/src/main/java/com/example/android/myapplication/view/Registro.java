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

    private Button btnGrabarUsu;
    Intent i, j;
    public EditText txtNomusu, txtApellidousu, txtEdadusu;
    private List<EBotones> sequence = new ArrayList<>();
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

                String Nomusu=String.valueOf(txtNomusu.getText());
                String ape=String.valueOf(txtNomusu.getText());
                String edad=String.valueOf(txtNomusu.getText());

                if(Nomusu.equals("")||ape.equals("")||edad.equals("")){
                    Toast.makeText(getContext(), "Debe llenar todos los datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    Sqlite_OpenHelper helper = new Sqlite_OpenHelper(getActivity(), "usuario", null, 1);
                    helper.abrirdb();
                    helper.insertarReg(String.valueOf(txtNomusu.getText()),
                            String.valueOf(txtApellidousu.getText()),
                            String.valueOf(txtEdadusu.getText()));
                    helper.cerrardb();
                    Toast.makeText(getContext(), "Registro almacenado", Toast.LENGTH_SHORT).show();
                    txtNomusu.setText("");
                    txtApellidousu.setText("");
                    txtEdadusu.setText("");
                }
            }
        });
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

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
