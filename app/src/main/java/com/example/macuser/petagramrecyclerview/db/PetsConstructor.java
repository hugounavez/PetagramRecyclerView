package com.example.macuser.petagramrecyclerview.db;

import android.content.ContentValues;
import android.content.Context;

import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * Created by macuser on 12/14/17.
 */

public class PetsConstructor {

    private Context context;
    public PetsConstructor(Context context) {
        this.context = context;

    }

    public Pets getPetsData(){
        /*
        ArrayList<Pet> temporalPets = new ArrayList<Pet>();
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 0));
        temporalPets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        temporalPets.add(new Pet("Sam", R.drawable.puppygolden, 0));
        temporalPets.add(new Pet("Bob", R.drawable.puppyhood, 0));

        return new Pets(temporalPets);

        */

        PetagramDatabase db = new PetagramDatabase(context);
        this.insertPets(db);
        return db.fetchPetsData();

    }


    public void insertPets(PetagramDatabase db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseConstants.TABLE_PET_NAME, "Pepe");
        contentValues.put(DatabaseConstants.TABLE_PET_IMG, R.drawable.puppy2);

        db.insertPet(contentValues);
    }


}
