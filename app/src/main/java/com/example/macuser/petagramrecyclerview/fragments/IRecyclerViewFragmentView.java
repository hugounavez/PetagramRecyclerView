package com.example.macuser.petagramrecyclerview.fragments;

import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * Created by macuser on 12/14/17.
 */

public interface IRecyclerViewFragmentView {
    public void linearLayoutGeneration();
    public PetAdapter createAdapter(Pets pets);
    public void initializeAdapterRV(PetAdapter petAdapter);

}
