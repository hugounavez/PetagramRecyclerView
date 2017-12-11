package com.example.macuser.petagramrecyclerview.actitivies;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.example.macuser.petagramrecyclerview.R;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.actionBarAbout);
        setSupportActionBar(toolbar);

        Button button = (Button) findViewById(R.id.button2);
        button.setVisibility(View.INVISIBLE);




    }

}
