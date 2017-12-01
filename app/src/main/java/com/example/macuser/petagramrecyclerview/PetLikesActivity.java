package com.example.macuser.petagramrecyclerview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                // do something here, such as start an Intent to the parent activity.

                Intent intent = new Intent(PetLikesActivity.this, MainActivity.class);
                String petJson = petsArrayList.toJson();
                intent.putExtra("pets", petJson);
                startActivity(intent);
                finish();

            }
        });


        getIntent().getStringExtra("pets");

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            // Parse the string to a User object
            String objAsJson = bundle.getString("pets");

            ArrayList<Pet> test = Pets.fromJson(objAsJson).getPets();
            this.petsArrayList = new Pets(test);
            this.adapterInitialization();
        }

    }


    void adapterInitialization(){
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view2);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        final PetAdapter petAdapter = new PetAdapter(petsArrayList.getPets(), new PetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, int action) {
                switch (action){
                    case 0:
                        // Update likes in puppy
                        int previousRating = petsArrayList.getPets().get(i).getRating();
                        petsArrayList.getPets().get(i).setRating(previousRating + 1);
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
