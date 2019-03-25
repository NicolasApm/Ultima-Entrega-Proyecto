package com.example.android.myapplication.common;

public enum ENnum {
    TXTVIEW11("a"),
    TXTVIEW12("b"),
    TXTVIEW13("c"),
    TXTVIEW14("d"),
    TXTVIEW21("e"),
    TXTVIEW22("f"),
    TXTVIEW23("g"),
    TXTVIEW24("h");

    private String id;
    ENnum(String id) {
        this.id = id;
    }
    public String getId() {
        return this.id;
    }
}
