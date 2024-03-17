package com.example.fastestdelivery.boottomNav.Sauce;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.fastestdelivery.DbHelper;
import com.example.fastestdelivery.boottomNav.Drinks.DrinksAdapter;
import com.example.fastestdelivery.boottomNav.Drinks.DrinksClass;
import com.example.fastestdelivery.databinding.FragmentFoodsBinding;
import com.example.fastestdelivery.databinding.FragmentSauceBinding;

import java.util.ArrayList;

public class SauceFragment extends Fragment {
    private FragmentSauceBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSauceBinding.inflate(inflater, container, false);
        ArrayList<SauceClass> sauce = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM sauce", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                sauce.add(new SauceClass(name,price,img));
            } while (cursor.moveToNext());
            cursor.close();

            // Set up the RecyclerView
            binding.sauceList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.sauceList.setAdapter(new SauceAdapter(sauce));
        } else {
            // Handle case when there are no rows in the cursor
        }
        return binding.getRoot();
    }
}
