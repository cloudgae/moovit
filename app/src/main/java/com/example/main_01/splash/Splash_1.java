package com.example.main_01.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.main_01.R;
import com.example.main_01.onboarding.TypeTest_1;
import com.example.main_01.onboarding.TypeTest_2;

public class Splash_1 extends AppCompatActivity {

    Button starttest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

        starttest = (Button) findViewById(R.id.starttest);

        starttest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Splash_1.this, TypeTest_1.class);
                startActivity(i);
            }
        });
    }
}