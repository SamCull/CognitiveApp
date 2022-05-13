package com.vogella.android.cognitiveapp;

public class Category {
    public static final int PROGRAMMING = 1; //change
    public static final int GEOGRAPHY = 2; // change
    public static final int MATH = 3;

    private int id;
    private String name;

    public Category(){
    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}