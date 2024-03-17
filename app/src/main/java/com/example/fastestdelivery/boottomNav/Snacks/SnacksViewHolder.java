package com.example.fastestdelivery.boottomNav.Snacks;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fastestdelivery.R;

public class SnacksViewHolder extends RecyclerView.ViewHolder{
    TextView name;
    TextView price;
    ImageView img;
    public SnacksViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.Name);
        price = itemView.findViewById(R.id.Price);
        img = itemView.findViewById(R.id.imge);
    }
}
