package com.example.macuser.petagramrecyclerview.db;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * Created by macuser on 12/14/17.
 */

public class PetsConstructor {

    PetagramDatabase db;
    private Context context;
    public PetsConstructor(Context context) {
        this.context = context;
        this.db = new PetagramDatabase(context);


        //--SAVE Data
        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", context.MODE_PRIVATE);

        if (preferences.getInt("alreadyOpenned", 0) == 0) {
            // With this line we insert pets
            this.insertPets();
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt("alreadyOpenned", 1);
            editor.commit();
        }


    }

    public Pets getPetsData(){
        return db.fetchPetsData();

    }

    public Pets getFavoritesPets(){
        return db.getFavorites();
    }


    public void insertPets(){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Tony");
        contentValues.put(DatabaseConstants.TABLE_PET_IMG, R.drawable.puppy2);
        this.db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Marta");
        contentValues.put(DatabaseConstants.TABLE_PET_IMG, R.drawable.puppybeagle);
        this.db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Sam");
        contentValues.put(DatabaseConstants.TABLE_PET_IMG, R.drawable.puppygolden);
        this.db.insertPet(contentValues);

        contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Bob");
        contentValues.put(DatabaseConstants.TABLE_PET_IMG, R.drawable.puppyhood);
        this.db.insertPet(contentValues);
    }


    public void insertLike(int petId){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_LIKES_PETS_ID, petId);
        db.insertLike(contentValues);
        db.close();
    }


}
