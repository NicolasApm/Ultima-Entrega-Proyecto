package com.example.android.myapplication.common;

public enum EBotones {
    BUTTON11("1"),
    BUTTON12("2"),
    BUTTON13("3"),
    BUTTON14("a"),
    BUTTON21("4"),
    BUTTON22("5"),
    BUTTON23("6"),
    BUTTON24("b"),
    BUTTON31("7"),
    BUTTON32("8"),
    BUTTON33("9"),
    BUTTON34("c"),
    BUTTON41("*"),
    BUTTON42("0"),
    BUTTON43("#"),
    BUTTON44("d"),
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
