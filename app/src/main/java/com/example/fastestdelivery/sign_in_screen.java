package com.example.fastestdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fastestdelivery.databinding.ActivitySignInScreenBinding;
import com.example.fastestdelivery.databinding.ActivitySingUpScreenBinding;

public class sign_in_screen extends AppCompatActivity {
    private ActivitySignInScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}