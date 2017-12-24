package com.example.macuser.petagramrecyclerview.fragments;

import android.content.Context;

import com.example.macuser.petagramrecyclerview.db.PetsConstructor;
import com.example.macuser.petagramrecyclerview.models.Pets;
import com.example.macuser.petagramrecyclerview.presenters.IRecyclerViewFragmentPresenter;

/**
 * Created by macuser on 12/14/17.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private PetsConstructor petsConstructor;
    private Pets pets;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context) {
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;
        petsConstructor = new PetsConstructor(context);
        petsConstructor.insertPets();
        this.getDataFromDatabase();
    }

    @Override
    public void getDataFromDatabase() {
         this.pets = this.petsConstructor.getPetsData();
         this.showContactosRV();
    }

    @Override
    public void showContactosRV() {
        // Mostrar datos en el adaptador
        iRecyclerViewFragmentView.initializeAdapterRV(iRecyclerViewFragmentView.createAdapter(this.pets));
        iRecyclerViewFragmentView.linearLayoutGeneration();
    }

}
