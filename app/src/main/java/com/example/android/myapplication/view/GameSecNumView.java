package com.example.android.myapplication.view;

import com.example.android.myapplication.common.ENnum;

public interface GameSecNumView {

    void onButton(ENnum btn);
    void offButton(ENnum btn);
    void Borrar(String borrar);
    void Result(String Result);
    void SentNum(String SentNum);
    void Msg(String msg);

}
