package com.example.macuser.petagramrecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class PetLikesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_likes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.myActionBar2);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button2);
        button.setVisibility(View.INVISIBLE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
