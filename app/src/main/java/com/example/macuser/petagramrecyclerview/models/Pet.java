package com.example.macuser.petagramrecyclerview.models;

/**
 * Created by macuser on 11/22/17.
 */

public class Pet {
    private String name;
    private int picture;
    private int rating;

    public Pet(String name, int picture, int rating){
        this.setName(name);
        this.setPicture(picture);
        this.setRating(rating);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
