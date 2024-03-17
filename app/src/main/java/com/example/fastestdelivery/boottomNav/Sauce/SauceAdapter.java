package com.example.fastestdelivery.boottomNav.Sauce;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastestdelivery.R;
import com.example.fastestdelivery.boottomNav.Snacks.SnacksClass;
import com.example.fastestdelivery.boottomNav.Snacks.SnacksViewHolder;

import java.util.ArrayList;

public class SauceAdapter extends RecyclerView.Adapter<SauceViewHolder>{
    private ArrayList<SauceClass> sauces = new ArrayList<>();

    public SauceAdapter(ArrayList<SauceClass> sauces) {
        this.sauces = sauces;
    }

    @NonNull
    @Override
    public SauceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);
        return new SauceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SauceViewHolder holder, int position) {
        SauceClass sauce = sauces.get(position);
        holder.name.setText(sauce.name);
        holder.price.setText(sauce.price);
        Glide.with(holder.itemView.getContext()).load(sauce.img).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return sauces.size();
    }
}

