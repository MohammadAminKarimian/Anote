package com.example.anote.Objects;

public class Field {

    private int _id;
    private String name;

    public Field(int _id, String name){
        this._id = _id;
        this.name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
