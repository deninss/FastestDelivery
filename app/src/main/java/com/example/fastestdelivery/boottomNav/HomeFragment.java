package com.example.fastestdelivery.boottomNav;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.load.model.ByteArrayLoader;
import com.example.fastestdelivery.DbHelper;
import com.example.fastestdelivery.R;
import com.example.fastestdelivery.Search.SearchAdapter;
import com.example.fastestdelivery.Search.SearchClass;
import com.example.fastestdelivery.boottomNav.Foods.FoodsAdapter;
import com.example.fastestdelivery.boottomNav.Foods.FoodsClass;
import com.example.fastestdelivery.boottomNav.SelectedItem.SelectedItemAdapter;
import com.example.fastestdelivery.boottomNav.SelectedItem.SelectedItemClass;
import com.example.fastestdelivery.boottomNav.Snacks.SnacksAdapter;
import com.example.fastestdelivery.boottomNav.Snacks.SnacksClass;
import com.example.fastestdelivery.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements SearchAdapter.OnSearchClickListener, SelectedItemAdapter.OnFoodClickListener{
    private FragmentHomeBinding binding;
    HomeAdapter homeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        homeAdapter = new HomeAdapter(this);
        binding.viewPager.setAdapter(homeAdapter);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                binding.tabLayout.getTabAt(position).select();
            }
        });
        binding.clickSearch.setOnClickListener(v -> {
            binding.Lmeal.setVisibility(View.VISIBLE);
            binding.Ladress.setVisibility(View.GONE);
            binding.adresSearch.setText("");
            binding.mealSearch.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.length() > 0){
                        binding.blockRes.setVisibility(View.VISIBLE);
                        binding.blockMenu.setVisibility(View.GONE);
                        loadItem();
                    }
                    else {
                        binding.blockRes.setVisibility(View.GONE);
                        binding.blockMenu.setVisibility(View.VISIBLE);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        });
        binding.clickAdres.setOnClickListener(v -> {
            binding.Lmeal.setVisibility(View.GONE);
            binding.Ladress.setVisibility(View.VISIBLE);
            binding.blockRes.setVisibility(View.GONE);
            binding.blockMenu.setVisibility(View.VISIBLE);
            binding.mealSearch.setText("");
        });
        binding.cleartext.setOnClickListener(v -> {
            binding.mealSearch.setText("");
        });


        return binding.getRoot();
    }
    public void loadItem(){
        ArrayList<SearchClass> search = new ArrayList<>();
        DbHelper dbHelper = new DbHelper(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery( "SELECT * FROM foods WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' " +
                "UNION " +
                "SELECT * FROM drinks WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' " +
                "UNION " +
                "SELECT * FROM sauce WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' " +
                "UNION " +
                "SELECT * FROM snacks WHERE name LIKE '%"+binding.mealSearch.getText().toString()+"%' ", null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    int ni =cursor.getColumnIndex("name");
                    int pi =cursor.getColumnIndex("price");
                    int ii =cursor.getColumnIndex("img");
                    String name = cursor.getString(ni);
                    String price = cursor.getString(pi);
                    String img = cursor.getString(ii);
                    search.add(new SearchClass(name,price,img));
                } while (cursor.moveToNext());
            }
            cursor.close();
            binding.searchResult.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
            binding.searchResult.setAdapter(new SearchAdapter(search,this));
        }
    }
    @Override
    public void onSearchClick(SearchClass food) {
        ArrayList<SelectedItemClass> sic = new ArrayList<>();
        sic.add(new SelectedItemClass(food.name, food.price, food.img));

        binding.searchResult.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false));
        binding.searchResult.setAdapter(new SelectedItemAdapter(sic,this));
    }

    @Override
    public void onItemClicked() {
        loadItem();
    }
}
