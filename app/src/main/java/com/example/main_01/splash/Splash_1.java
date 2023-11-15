package com.example.main_01.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.example.main_01.apply.Apply_0;
import com.example.main_01.onboarding.TypeTest_1;

import org.checkerframework.checker.units.qual.A;

public class Splash_1 extends AppCompatActivity {

    Button gotomain;
    ImageButton starttest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        starttest = (ImageButton) findViewById(R.id.starttest);
        gotomain = (Button) findViewById(R.id.gotomain);

        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Splash_1.this, TypeTest_1.class);
                startActivity(i);
            }
        });

        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Splash_1.this, MainActivity.class);
                startActivity(i);
            }

        });
    }
}