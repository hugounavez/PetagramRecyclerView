package com.example.macuser.petagramrecyclerview;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by iMac1 on 11/29/17.
 */

public class Pets {
    private ArrayList<Pet> pets;

    public Pets(ArrayList<Pet> pets) {
        this.setPets(pets);
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public String toJson(){
        return new Gson().toJson(this);
    }

    public static Pets fromJson(String json){

        return new Gson().fromJson(json, Pets.class);
    }
}