package com.example.macuser.petagramrecyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by macuser on 11/22/17.
 */

public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    ArrayList<Pet> pets;

    public PetAdapter(ArrayList<Pet> pets){
        this.pets = pets;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_card_layout, parent, false);

        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PetViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.tvPetName.setText(pet.getName());
        holder.petPicture.setImageResource(pet.getPicture());
        holder.tvRating.setText(String.valueOf(pet.getRating()));
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private TextView tvPetName;
        private ImageView petPicture;
        private TextView tvRating;

        public PetViewHolder(View itemView) {
            super(itemView);

            tvPetName = (TextView) itemView.findViewById(R.id.tvPetName);
            petPicture = (ImageView) itemView.findViewById(R.id.imgPet);
            tvRating = (TextView)   itemView.findViewById(R.id.tvNumberBones);

        }
    }

}
