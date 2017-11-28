package com.example.macuser.petagramrecyclerview;

import android.app.Activity;
import android.support.constraint.solver.widgets.Snapshot;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Pet> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);


        pets = new ArrayList<Pet>();
        pets.add(new Pet("Tony", R.drawable.puppy2, 0));
        pets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        pets.add(new Pet("Sam", R.drawable.puppygolden, 1));
        pets.add(new Pet("Bob", R.drawable.puppyhood, 2));

        final Activity atc = this;
        PetAdapter petAdapter = new PetAdapter(pets, new PetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, int action) {
                switch (action){
                    case 0:
                        // Make some action
                        Toast.makeText(atc, pets.get(i).getName(), Toast.LENGTH_SHORT).show();
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
