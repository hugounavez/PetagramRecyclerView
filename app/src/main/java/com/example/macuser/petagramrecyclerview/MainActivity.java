package com.example.macuser.petagramrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        ArrayList<Pet> pets = new ArrayList<Pet>();
        pets.add(new Pet("Tony", R.drawable.puppy2, 0));
        pets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        pets.add(new Pet("Sam", R.drawable.puppygolden, 1));
        pets.add(new Pet("Bob", R.drawable.puppyhood, 2));

        PetAdapter petAdapter = new PetAdapter(pets);
        recyclerView.setAdapter(petAdapter);

        recyclerView.setLayoutManager(llm);


    }
}
