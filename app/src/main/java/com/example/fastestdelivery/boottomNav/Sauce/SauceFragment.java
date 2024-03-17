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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fastestdelivery.DbHelper;
import com.example.fastestdelivery.boottomNav.Drinks.DrinksAdapter;
import com.example.fastestdelivery.boottomNav.Drinks.DrinksClass;
import com.example.fastestdelivery.boottomNav.Foods.FoodsAdapter;
import com.example.fastestdelivery.boottomNav.Foods.FoodsClass;
import com.example.fastestdelivery.boottomNav.SelectedItem.SelectedItemAdapter;
import com.example.fastestdelivery.boottomNav.SelectedItem.SelectedItemClass;
import com.example.fastestdelivery.databinding.FragmentFoodsBinding;
import com.example.fastestdelivery.databinding.FragmentSauceBinding;

import java.util.ArrayList;

public class SauceFragment extends Fragment implements SauceAdapter.OnSauceClickListener, SelectedItemAdapter.OnFoodClickListener{
    private FragmentSauceBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSauceBinding.inflate(inflater, container, false);
        loadItem();
        return binding.getRoot();
    }
    public void loadItem(){
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
            binding.sauceList.setAdapter(new SauceAdapter(sauce,this));
        } else {
            // Handle case when there are no rows in the cursor
        }
    }
    @Override
    public void onSauceClick(SauceClass food) {
        ArrayList<SelectedItemClass> sic = new ArrayList<>();
        sic.add(new SelectedItemClass(food.name, food.price, food.img));

        binding.sauceList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.sauceList.setAdapter(new SelectedItemAdapter(sic,this));
    }

    @Override
    public void onItemClicked() {
        loadItem();
    }
}
