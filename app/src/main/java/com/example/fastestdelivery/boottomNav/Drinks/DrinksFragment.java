package com.example.fastestdelivery.boottomNav.Drinks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fastestdelivery.databinding.FragmentDrinksBinding;
import com.example.fastestdelivery.databinding.FragmentSauceBinding;

public class DrinksFragment extends Fragment {
    private FragmentDrinksBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDrinksBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
