package com.example.macuser.petagramrecyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macuser.petagramrecyclerview.models.Pet;
import com.example.macuser.petagramrecyclerview.R;

import java.util.ArrayList;

/**
 * Created by macuser on 11/22/17.
 */



public class PetAdapter extends RecyclerView.Adapter<PetAdapter.PetViewHolder>{

    private final OnItemClickListener listener;
    ArrayList<Pet> pets;


    public PetAdapter(ArrayList<Pet> pets, PetAdapter.OnItemClickListener listener){
        this.pets = pets;
        this.listener = listener;
    }

    @Override
    public PetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_card_layout, parent, false);
        return new PetViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PetViewHolder holder, final int position) {
        Pet pet = pets.get(position);
        holder.tvPetName.setText(pet.getName());
        holder.petPicture.setImageResource(pet.getPicture());
        holder.tvRating.setText(String.valueOf(pet.getRating()));

        holder.btnWhiteBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Actiones
                listener.onItemClick(position, 0);
                holder.tvRating.setText(String.valueOf(pets.get(position).getRating()));
            }
        });
    }

    public static class PetViewHolder extends RecyclerView.ViewHolder{
        private final TextView tvPetName;
        private final ImageView petPicture;
        private final TextView tvRating;
        private final Button btnWhiteBone;

        public PetViewHolder(View itemView) {
            super(itemView);

            tvPetName = (TextView) itemView.findViewById(R.id.tvPetName);
            petPicture = (ImageView) itemView.findViewById(R.id.imgPet);
            tvRating = (TextView)   itemView.findViewById(R.id.tvNumberBones);
            btnWhiteBone = (Button) itemView.findViewById(R.id.imgWhiteBone);

        }
    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public interface OnItemClickListener{
        void onItemClick(int i, int action);
    }

}
