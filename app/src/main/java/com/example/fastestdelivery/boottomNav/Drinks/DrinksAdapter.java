package com.example.fastestdelivery.boottomNav.Drinks;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastestdelivery.R;
import com.example.fastestdelivery.boottomNav.Foods.FoodsClass;
import com.example.fastestdelivery.boottomNav.Foods.FoodsViewHolder;

import java.util.ArrayList;

public class DrinksAdapter  extends RecyclerView.Adapter<DrinksViewHolder>{
    private ArrayList<DrinksClass> drinks = new ArrayList<>();

    public DrinksAdapter(ArrayList<DrinksClass> drinks) {
        this.drinks = drinks;
    }

    @NonNull
    @Override
    public DrinksViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);
        return new DrinksViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinksViewHolder holder, int position) {
        DrinksClass drink = drinks.get(position);
        holder.name.setText(drink.name);
        holder.price.setText(drink.price);
        Glide.with(holder.itemView.getContext()).load(drink.img).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return drinks.size();
    }
}
