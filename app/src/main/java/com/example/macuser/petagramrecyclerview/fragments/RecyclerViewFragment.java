package com.example.macuser.petagramrecyclerview.fragments;

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

import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.models.Pets;
import com.example.macuser.petagramrecyclerview.presenters.IRecyclerViewFragmentPresenter;

import java.util.ArrayList;

/**
 * Created by macuser on 12/3/17.
 */

public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView{

    private Pets pets;
    private RecyclerView recyclerView;
    private static final String ARG_PARAM1 = "pets";
    private IRecyclerViewFragmentPresenter presenter;


    // This listener is just for update model in the fragment
    OnUpdateModelListener mCallback;

    @Override
    public void linearLayoutGeneration() {

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public PetAdapter createAdapter(Pets petsLocal) {

        final PetAdapter petAdapter = new PetAdapter(petsLocal.getPets(), new PetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int i, int action) {
                switch (action){
                    case 0:
                        // Update likes in puppy
                        mCallback.onUpdateModelElement(i);
                        break;
                    default:
                        break;
                }
            }
        });

        return petAdapter;
    }

    @Override
    public void initializeAdapterRV(PetAdapter petAdapter) {
        recyclerView.setAdapter(petAdapter);
    }

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


        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view);

        Bundle bundle = getArguments();
        if (bundle != null){
            String objAsJson = bundle.getString(ARG_PARAM1);
            if (objAsJson != null){
                this.pets = Pets.fromJson(objAsJson);
            }
        }

        presenter = new RecyclerViewFragmentPresenter(this, getContext());
        return v;
    }


    // TODO: Rename and change types and number of parameters
    public static RecyclerViewFragment newInstance(String objAsJson) {
        RecyclerViewFragment fragment = new RecyclerViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, objAsJson);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.pets = Pets.fromJson(getArguments().getString(ARG_PARAM1)) ;
        }
    }

}