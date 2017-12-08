package com.example.macuser.petagramrecyclerview.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.adapters.PetAdapter;
import com.example.macuser.petagramrecyclerview.adapters.ProfileAdapter;
import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.models.Pets;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ProfileFragmet.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ProfileFragmet#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProfileFragmet extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView  textView;
    private RecyclerView recyclerView;
    private Pets pets;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ProfileFragmet() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragmet.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragmet newInstance(String param1, String param2) {
        ProfileFragmet fragment = new ProfileFragmet();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.petsInitialization();
        View view = inflater.inflate(R.layout.fragment_profile_fragmet, container, false);
        this.adapterInitialization(view);

        return view;
    }


    void adapterInitialization(View v){

        recyclerView = (RecyclerView) v.findViewById(R.id.recycler_view_profile);
        textView    = (TextView) v.findViewById(R.id.tvPuppyName);

        GridLayoutManager llm = new GridLayoutManager(getActivity(), 3);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        final ProfileAdapter profileAdapter = new ProfileAdapter(pets.getPets());

        recyclerView.setAdapter(profileAdapter);
        recyclerView.setLayoutManager(llm);


        textView.setText(pets.getPets().get(0).getName());


    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    void petsInitialization(){
        ArrayList<Pet> temporalPets = new ArrayList<Pet>();
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 7));
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 10));
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 30));
        temporalPets.add(new Pet("Tony", R.drawable.puppy2, 10));

        this.pets = new Pets(temporalPets);

    }

}
