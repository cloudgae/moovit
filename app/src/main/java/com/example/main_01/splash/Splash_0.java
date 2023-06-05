package com.example.main_01.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.main_01.R;
import com.example.main_01.onboarding.TypeTest_8;
import com.example.main_01.onboarding.TypeTest_9;

public class Splash_0 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash0);

        moveNext(1);
    }

    private void moveNext(int sec) {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //new Intent(현재 context, 이동할 activity)
                Intent i = new Intent(Splash_0.this, Splash_1.class);

                startActivity(i);	//intent 에 명시된 액티비티로 이동

                finish();	//현재 액티비티 종료
            }
        }, 1500 * sec); // sec초 정도 딜레이를 준 후 시작
    }

}

