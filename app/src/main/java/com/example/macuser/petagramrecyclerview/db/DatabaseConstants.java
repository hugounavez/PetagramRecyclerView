package com.example.macuser.petagramrecyclerview.db;

/**
 * Created by macuser on 12/17/17.
 */

public final class DatabaseConstants {
    public static final String DATABASE_NAME = "PETS";
    public static final int DATABASE_VERSION = 1;

    // Entity's fields
    public static final String TABLE_PETS = "PET";
    public static final String TABLE_PETS_ID = "id";
    public static final String TABLE_PET_NAME = "name";
    public static final String TABLE_PET_IMG = "picture";

    public static final String TABLE_LIKES = "LIKE";
    public static final String TABLE_LIKES_ID = "id";
    public static final String TABLE_LIKES_PETS_ID = "pets_id";


}
