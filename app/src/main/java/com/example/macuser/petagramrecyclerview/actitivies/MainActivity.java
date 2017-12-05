package com.example.macuser.petagramrecyclerview.actitivies;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.FrameMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.RecyclerViewFragment;
import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewFragment.OnUpdateModelListener, ProfileFragmet.OnFragmentInteractionListener {

    private Pets pets;
    private RecyclerView recyclerView;

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

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


        // this.adapterInitialization();

        this.setUpViewPager();

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
                Intent intent = new Intent(MainActivity.this, ContactActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_contact:
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
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 0));
        temporalPets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        temporalPets.add(new Pet("Sam", R.drawable.puppygolden, 0));
        temporalPets.add(new Pet("Bob", R.drawable.puppyhood, 0));

        this.pets = new Pets(temporalPets);

    }

    private ArrayList<android.support.v4.app.Fragment> addFragments(){
        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new ProfileFragmet());
        return fragments;
    }



    private void setUpViewPager(){

        this.viewPager.setAdapter(new com.example.macuser.petagramrecyclerview.actitivies.PageAdapter(getSupportFragmentManager(), this.addFragments()));
        tabLayout.setupWithViewPager(viewPager);
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
