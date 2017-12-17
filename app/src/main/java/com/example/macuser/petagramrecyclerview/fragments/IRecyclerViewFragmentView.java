package com.example.macuser.petagramrecyclerview.fragments;

import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * Created by macuser on 12/14/17.
 */

public interface IRecyclerViewFragmentView {
    /*

    This is part of the View part according to the MVP methodology. This interface is implemented
    by the fragment or the activity. The activity or fragment with the layout and this interfaces
    froms together the 'View' in the MVP

    In the interface, the methods are only prototyped.
     */
    public void linearLayoutGeneration();
    public PetAdapter createAdapter(Pets pets);
    public void initializeAdapterRV(PetAdapter petAdapter);

}
