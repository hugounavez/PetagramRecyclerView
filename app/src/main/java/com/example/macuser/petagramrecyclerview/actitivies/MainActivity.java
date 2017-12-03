package com.example.macuser.petagramrecyclerview.actitivies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private Pets pets;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.customToolbarInitialization();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            // Parse the string to a User object
            String objAsJson = bundle.getString("pets");
            ArrayList<Pet> test = Pets.fromJson(objAsJson).getPets();
            this.pets = new Pets(test);
        }else{
            this.petsInitialization();
        }

        this.adapterInitialization();
    }


    void startSecondActivity(View view){
        Intent intent = new Intent(MainActivity.this, PetLikesActivity.class);
        String petJson = pets.toJson();
        intent.putExtra("pets", petJson);
        startActivity(intent);
        finish();
    }


    void customToolbarInitialization(){
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.myActionBar);
        // Support action bar to different screen
        setSupportActionBar(toolbar);
    }

    void petsInitialization(){
        ArrayList<Pet> temporalPets = new ArrayList<Pet>();
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 0));
        temporalPets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        temporalPets.add(new Pet("Sam", R.drawable.puppygolden, 0));
        temporalPets.add(new Pet("Bob", R.drawable.puppyhood, 0));

        this.pets = new Pets(temporalPets);

    }

    void adapterInitialization(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        final PetAdapter petAdapter = new PetAdapter(pets.getPets(), new PetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, int action) {
                switch (action){
                    case 0:
                        // Update likes in puppy
                        int previousRating = pets.getPets().get(i).getRating();
                        pets.getPets().get(i).setRating(previousRating + 1);
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
