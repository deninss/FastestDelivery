package com.example.fastestdelivery.boottomNav.Snacks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastestdelivery.R;
import com.example.fastestdelivery.boottomNav.Drinks.DrinksClass;
import com.example.fastestdelivery.boottomNav.Drinks.DrinksViewHolder;

import java.util.ArrayList;

public class SnacksAdapter extends RecyclerView.Adapter<SnacksViewHolder>{
    private ArrayList<SnacksClass> snacks = new ArrayList<>();

    public SnacksAdapter(ArrayList<SnacksClass> snacks) {
        this.snacks = snacks;
    }

    @NonNull
    @Override
    public SnacksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);
        return new SnacksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SnacksViewHolder holder, int position) {
        SnacksClass snack = snacks.get(position);
        holder.name.setText(snack.name);
        holder.price.setText(snack.price);
        Glide.with(holder.itemView.getContext()).load(snack.img).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return snacks.size();
    }
}
