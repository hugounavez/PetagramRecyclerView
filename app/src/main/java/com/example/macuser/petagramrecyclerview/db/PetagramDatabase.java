package com.example.macuser.petagramrecyclerview.db;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * Created by macuser on 12/17/17.
 */

public class PetagramDatabase extends SQLiteOpenHelper {
    /*
    This class is a sql manager to be used in the rest of the app
    */

    Context context;

    public PetagramDatabase(Context context) {
        super(context, DatabaseConstants.DATABASE_NAME, null, DatabaseConstants.DATABASE_VERSION);
        this.context = context;

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // database is created
        String queryToCreatePetTable = "CREATE TABLE " + DatabaseConstants.TABLE_PETS + "(" +
                DatabaseConstants.TABLE_PETS_ID     + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseConstants.TABLE_PET_NAME    + " TEXT, " +
                DatabaseConstants.TABLE_PET_IMG     + " INTEGER " +
                ")";


        sqLiteDatabase.execSQL(queryToCreatePetTable);

        String queryToCreateLikeTable = "CREATE TABLE " + DatabaseConstants.TABLE_LIKES + "(" +
                DatabaseConstants.TABLE_LIKES_ID    + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DatabaseConstants.TABLE_PETS_ID     + " INTEGER, " +
                "FOREIGN KEY (" + DatabaseConstants.TABLE_PETS_ID + ")" +
                " REFERENCES " + DatabaseConstants.TABLE_PETS + "(" + DatabaseConstants.TABLE_PETS_ID + ")" + ")";

        sqLiteDatabase.execSQL(queryToCreateLikeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Delete old database
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.TABLE_PETS);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DatabaseConstants.TABLE_LIKES);
        // Create new one
        onCreate(sqLiteDatabase);
    }


    public Pets fetchPetsData(){
        ArrayList<Pet> temporalPets = new ArrayList<Pet>();
        // Make a query
        String query = "SELECT * FROM " + DatabaseConstants.TABLE_PETS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.rawQuery(query, null);

        // There are no values but the registers of the table
        while (registros.moveToNext()){
            int id = registros.getInt(0);
            String name = registros.getString(1);
            int picture = registros.getInt(2);
            Pet pet = new Pet(name, picture, 0 , id);

            temporalPets.add(pet);
        }

        db.close();

        return new Pets(temporalPets);
    }

    public void insertPet(ContentValues contentValues){
        SQLiteDatabase db = this.getWritableDatabase();

        db.insert(DatabaseConstants.TABLE_PETS,  null, contentValues);
        db.close();


    }


    public void insertLike (ContentValues contentValues) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(DatabaseConstants.TABLE_LIKES, null, contentValues);
        db.close();
    }


}
