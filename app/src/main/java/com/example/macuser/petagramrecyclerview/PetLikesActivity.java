package com.example.macuser.petagramrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class PetLikesActivity extends AppCompatActivity {

    private Pets petsArrayList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_likes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myActionBar2);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button2);
        button.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getIntent().getStringExtra("pets");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            // Parse the string to a User object
            String objAsJson = bundle.getString("pets");

            ArrayList<Pet> test = Pets.fromJson(objAsJson).getPets();
            System.out.println("test");
            //this.petsArrayList.setPets(Pets.fromJson(objAsJson).getPets());
        }

    }



}
