package com.example.fastestdelivery.boottomNav.Foods;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastestdelivery.R;

import java.util.ArrayList;

public class FoodsAdapter extends RecyclerView.Adapter<FoodsViewHolder>{
    private ArrayList<FoodsClass> foods = new ArrayList<>();

    public FoodsAdapter(ArrayList<FoodsClass> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public FoodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);
        return new FoodsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodsViewHolder holder, int position) {
        FoodsClass food = foods.get(position);
        holder.name.setText(food.name);
        holder.price.setText(food.price);
        Glide.with(holder.itemView.getContext()).load(food.img).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }
}
