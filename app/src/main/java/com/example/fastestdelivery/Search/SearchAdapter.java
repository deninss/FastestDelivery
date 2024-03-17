package com.example.fastestdelivery.Search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.fastestdelivery.R;
import com.example.fastestdelivery.boottomNav.Foods.FoodsAdapter;
import com.example.fastestdelivery.boottomNav.Foods.FoodsClass;
import com.example.fastestdelivery.boottomNav.Snacks.SnacksClass;
import com.example.fastestdelivery.boottomNav.Snacks.SnacksViewHolder;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder>{
    private ArrayList<SearchClass> searchs = new ArrayList<>();
    private OnSearchClickListener listener;

    public SearchAdapter(ArrayList<SearchClass> searchs,OnSearchClickListener listener) {
        this.searchs = searchs;
        this.listener = listener;
    }
    public interface OnSearchClickListener {
        void onSearchClick(SearchClass search);
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foods_item,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        SearchClass search = searchs.get(position);
        holder.name.setText(search.name);
        holder.price.setText(search.price);
        Glide.with(holder.itemView.getContext()).load(search.img).into(holder.img);
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onSearchClick(search);
            }
        });
    }

    @Override
    public int getItemCount() {
        return searchs.size();
    }
}