package com.example.fastestdelivery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

import com.example.fastestdelivery.databinding.ActivityMainBinding;
import com.example.fastestdelivery.databinding.ActivityOnBoardingScreenBinding;

public class on_boarding_screen extends AppCompatActivity {
    private ActivityOnBoardingScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOnBoardingScreenBinding.inflate(getLayoutInflater());

        setContentView(R.layout.on_boarding_screen_one);
    }
    int start_x = 0;
    int end_x = 0;
    @Override
    public  boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                start_x = (int) event.getX();
                break;
            case MotionEvent.ACTION_DOWN:
                end_x = (int) event.getX();
                break;
        }
        if(start_x != 0 && end_x != 0)
        {
            if(start_x > end_x) setContentView((R.layout.on_boarding_screen_one));
            else if(start_x < end_x)
            {
                setContentView(R.layout.on_boarding_screen_two);
                Button sign_in = findViewById(R.id.sign_in);
                Button sign_up = findViewById(R.id.sign_up);
                sign_in.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(on_boarding_screen.this,sign_in_screen.class));
                    }
                });
                sign_up.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(on_boarding_screen.this, sign_up_screen.class));
                    }
                });
            }
            start_x = 0;
            end_x = 0;
        }
        return false;
    }
}