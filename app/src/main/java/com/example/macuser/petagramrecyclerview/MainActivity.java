package com.example.macuser.petagramrecyclerview;

import android.app.Activity;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Pet> pets;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.customToolbarInitialization();
        this.petsInitialization();
        this.adapterInitialization();

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.myActionBar);
        // Support action bar to different screen
        setSupportActionBar(toolbar);

    }


    void customToolbarInitialization(){

    }

    void petsInitialization(){
        pets = new ArrayList<Pet>();
        pets.add(new Pet("Tony", R.drawable.puppy2, 0));
        pets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        pets.add(new Pet("Sam", R.drawable.puppygolden, 0));
        pets.add(new Pet("Bob", R.drawable.puppyhood, 0));

    }

    void adapterInitialization(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        final PetAdapter petAdapter = new PetAdapter(pets, new PetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, int action) {
                switch (action){
                    case 0:
                        // Update likes in puppy
                        int previousRating = pets.get(i).getRating();
                        pets.get(i).setRating(previousRating + 1);
                        break;
                    default:
                        break;
                }
            }
        });

        recyclerView.setAdapter(petAdapter);
        recyclerView.setLayoutManager(llm);
    }
}
