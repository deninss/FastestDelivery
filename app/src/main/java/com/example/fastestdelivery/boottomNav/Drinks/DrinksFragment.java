package com.example.fastestdelivery.boottomNav.Drinks;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fastestdelivery.DbHelper;
import com.example.fastestdelivery.boottomNav.Foods.FoodsAdapter;
import com.example.fastestdelivery.boottomNav.Foods.FoodsClass;
import com.example.fastestdelivery.boottomNav.SelectedItem.SelectedItemAdapter;
import com.example.fastestdelivery.boottomNav.SelectedItem.SelectedItemClass;
import com.example.fastestdelivery.databinding.FragmentDrinksBinding;
import com.example.fastestdelivery.databinding.FragmentSauceBinding;

import java.util.ArrayList;

public class DrinksFragment extends Fragment implements DrinksAdapter.OnDrinksClickListener, SelectedItemAdapter.OnFoodClickListener {
    private FragmentDrinksBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrinksBinding.inflate(inflater, container, false);
        loadItem();
        return binding.getRoot();
    }
    public void loadItem(){
        ArrayList<DrinksClass> drings = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM drinks", null);

        if (cursor.moveToFirst()) {
            do {
                int ni =cursor.getColumnIndex("name");
                int pi =cursor.getColumnIndex("price");
                int ii =cursor.getColumnIndex("img");
                String name = cursor.getString(ni);
                String price = cursor.getString(pi);
                String img = cursor.getString(ii);
                drings.add(new DrinksClass(name,price,img));
            } while (cursor.moveToNext());
            cursor.close();

            // Set up the RecyclerView
            binding.drinksList.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.drinksList.setAdapter(new DrinksAdapter(drings,this));
        } else {
            // Handle case when there are no rows in the cursor
        }
    }
    @Override
    public void onDrinksClick(DrinksClass drinks) {
        ArrayList<SelectedItemClass> sic = new ArrayList<>();
        sic.add(new SelectedItemClass(drinks.name, drinks.price, drinks.img));

        binding.drinksList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.drinksList.setAdapter(new SelectedItemAdapter(sic,this));
    }

    @Override
    public void onItemClicked() {
        loadItem();
    }
}
