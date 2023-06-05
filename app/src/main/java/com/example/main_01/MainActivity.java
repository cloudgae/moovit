package com.example.main_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.main_01.onboarding.TypeTest_1;
import com.example.main_01.splash.Splash_0;

public class MainActivity extends AppCompatActivity {

    Button gotobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //온보딩으로 바로 이동
        Intent i = new Intent(MainActivity.this, Splash_0.class);
        startActivity(i);

        finish();


    }
}