package com.example.macuser.petagramrecyclerview.actitivies;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.db.PetsConstructor;
import com.example.macuser.petagramrecyclerview.fragments.ProfileFragmet;
import com.example.macuser.petagramrecyclerview.fragments.RecyclerViewFragment;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.lang.reflect.Constructor;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragment.OnUpdateModelListener, ProfileFragmet.OnFragmentInteractionListener {

    private Pets pets;
    private RecyclerView recyclerView;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        this.customToolbarInitialization();
        this.petsInitialization();


        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            // Parse the string to a User object
            String objAsJson = bundle.getString("pets");
            ArrayList<Pet> test = Pets.fromJson(objAsJson).getPets();
            this.pets = new Pets(test);
            this.setUpViewPager(objAsJson);
            //getSupportFragmentManager().beginTransaction().add(R.id.content, RecyclerViewFragment.newInstance(objAsJson),"RecyclerViewFragment").commit();
        }else{
            this.setUpViewPager(null);
        }


        this.activity = this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_about:
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
                break;
            case R.id.menu_contact:
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);
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
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 0, 0));
        temporalPets.add(new Pet("Tony888", R.drawable.puppy2, 0, 6));
        this.pets = new Pets(temporalPets);

    }

    private ArrayList<android.support.v4.app.Fragment> addFragments(String objAsJson){
        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();

        fragments.add(RecyclerViewFragment.newInstance(objAsJson));
        fragments.add(new ProfileFragmet());
        return fragments;
    }

    private void setUpViewPager(String objAsJson){

        this.viewPager.setAdapter(new com.example.macuser.petagramrecyclerview.actitivies.PageAdapter(getSupportFragmentManager(), this.addFragments(objAsJson)));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.home);
        tabLayout.getTabAt(1).setIcon(R.drawable.person);
    }

    @Override
    public void onUpdateModelElement(int position) {
        int previousRating = pets.getPets().get(position).getRating();
        this.pets.getPets().get(position).setRating(previousRating + 1);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}