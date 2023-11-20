package com.example.main_01.apply;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.example.main_01.mypage.MyPage;

public class Apply_3 extends AppCompatActivity {
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply3);

        back_button = (Button) findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveBack(1);
            }
        });
    }

    private void showPopup() {


        // 팝업 창 표시 코드 추가
    }

    private void moveBack(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Apply_2로 돌아가서 팝업 표시
                Intent i = new Intent(Apply_3.this, Apply_2.class);
                startActivity(i);
                showPopup();
            }
        }, 1500 * sec); // sec초 정도 딜레이를 준 후 시작
    }
}