package com.example.macuser.petagramrecyclerview.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macuser.petagramrecyclerview.R;
import com.example.macuser.petagramrecyclerview.models.Pet;

import java.util.ArrayList;

/**
 * Created by macuser on 12/7/17.
 */

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileAdapterViewHolder>{

    ArrayList<Pet> pets;

    public ProfileAdapter(ArrayList<Pet> pets){
        this.pets = pets;
    }

    @Override
    public ProfileAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_mini_card_layout, parent, false);
        return new ProfileAdapter.ProfileAdapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ProfileAdapterViewHolder holder, int position) {
        Pet pet = pets.get(position);
        holder.petPicture.setImageResource(pet.getPicture());
        holder.numberLikes.setText(String.valueOf(pet.getRating()));

    }

    @Override
    public int getItemCount() {
        return pets.size();
    }

    public static class ProfileAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView numberLikes;
        private ImageView petPicture;

        public ProfileAdapterViewHolder(View itemView) {
            super(itemView);

            numberLikes = (TextView) itemView.findViewById(R.id.tvNumberLikesProfile);
            petPicture = (ImageView) itemView.findViewById(R.id.imagPicUser);


        }
    }
}
