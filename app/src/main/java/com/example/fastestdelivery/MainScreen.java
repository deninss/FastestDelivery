package com.example.fastestdelivery;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.fastestdelivery.boottomNav.HomeFragnent;
import com.example.fastestdelivery.databinding.ActivityMainBinding;
import com.example.fastestdelivery.databinding.ActivityMainScreenBinding;

import java.util.HashMap;
import java.util.Map;

public class MainScreen extends AppCompatActivity {
    private ActivityMainScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportFragmentManager().beginTransaction().replace(binding.fragmentContainer.getId(),new HomeFragnent()).commit();
        binding.bottomNav.setSelectedItemId(R.id.questionnaire);
        Map<Integer, Fragment> fragmentMap = new HashMap<>();
    }
}