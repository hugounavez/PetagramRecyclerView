package com.example.macuser.petagramrecyclerview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * Created by macuser on 12/3/17.
 */

public class RecyclerViewFragment extends Fragment{

    private Pets pets;
    private RecyclerView recyclerView;

    OnUpdateModelListener mCallback;

    // Container Activity must implement this interface
    public interface OnUpdateModelListener {
        public void onUpdateModelElement(int position);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception

        Activity activity;

        if (context instanceof Activity){
            activity = (Activity) context;

            try {
                mCallback = (OnUpdateModelListener) activity;
            } catch (ClassCastException e) {
                throw new ClassCastException(activity.toString()
                        + " must implement OnUpdateModelListener");
            }

        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.frament_recyclerview, container, false);

        this.petsInitialization();
        this.adapterInitialization(v);

        return v;
    }



    void adapterInitialization(View v){

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        final PetAdapter petAdapter = new PetAdapter(pets.getPets(), new PetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, int action) {
                switch (action){
                    case 0:
                        // Update likes in puppy
                        int previousRating = pets.getPets().get(i).getRating();
                        pets.getPets().get(i).setRating(previousRating + 1);
                        mCallback.onUpdateModelElement(i);
                        break;
                    default:
                        break;
                }
            }
        });

        recyclerView.setAdapter(petAdapter);
        recyclerView.setLayoutManager(llm);

    }

    void petsInitialization(){
        ArrayList<Pet> temporalPets = new ArrayList<Pet>();
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 0));
        temporalPets.add(new Pet("Marta", R.drawable.puppybeagle, 0));
        temporalPets.add(new Pet("Sam", R.drawable.puppygolden, 0));
        temporalPets.add(new Pet("Bob", R.drawable.puppyhood, 0));

        this.pets = new Pets(temporalPets);

    }


}