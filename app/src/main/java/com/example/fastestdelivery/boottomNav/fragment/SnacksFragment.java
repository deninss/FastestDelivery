package com.example.fastestdelivery.boottomNav.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.fastestdelivery.databinding.FragmentSauceBinding;
import com.example.fastestdelivery.databinding.FragmentSnacksBinding;

public class SnacksFragment extends Fragment {
    private FragmentSnacksBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSnacksBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }
}
