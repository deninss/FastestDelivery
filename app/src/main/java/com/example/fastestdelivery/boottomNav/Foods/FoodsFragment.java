package com.example.fastestdelivery.boottomNav.Foods;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fastestdelivery.DbHelper;
import com.example.fastestdelivery.R;
import com.example.fastestdelivery.databinding.FragmentFoodsBinding;

import java.util.ArrayList;
import java.util.List;

public class FoodsFragment extends Fragment {
    private FragmentFoodsBinding binding;
    SimpleCursorAdapter userAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFoodsBinding.inflate(inflater, container, false);
        ArrayList<FoodsClass> foods = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM foods", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                foods.add(new FoodsClass(name, price, img));
            } while (cursor.moveToNext());
            cursor.close();

            // Set up the RecyclerView
            binding.foodsList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.foodsList.setAdapter(new FoodsAdapter(foods));
        } else {
            // Handle case when there are no rows in the cursor
        }

        return binding.getRoot();
    }

}
