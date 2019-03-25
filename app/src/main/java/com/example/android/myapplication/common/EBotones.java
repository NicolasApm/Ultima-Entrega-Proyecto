package com.example.android.myapplication.common;

public enum EBotones {
    BUTTON11("a"),
    BUTTON12("b"),
    BUTTON13("c"),
    BUTTON14("d"),
    BUTTON21("e"),
    BUTTON22("f"),
    BUTTON23("g"),
    BUTTON24("h"),
    BUTTON31("i"),
    BUTTON32("j"),
    BUTTON33("k"),
    BUTTON34("l"),
    BUTTON41("m"),
    BUTTON42("n"),
    BUTTON43("o"),
    BUTTON44("p"),
    BUTTONS1("1"),
    BUTTONS2("2"),
    BUTTONS3("3"),
    BUTTONS4("4"),
    BUTTONS5("5");

    private String id;
    EBotones(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

}
