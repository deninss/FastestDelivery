package com.example.fastestdelivery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fastestdelivery.databinding.ActivitySignInScreenBinding;
import com.example.fastestdelivery.databinding.ActivitySingUpScreenBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sign_in_screen extends AppCompatActivity {
    private ActivitySignInScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    startActivity(new Intent(sign_in_screen.this, MainScreen.class));
                                    finish();
                                }
                            }
                        });
            }
        });
    }
}