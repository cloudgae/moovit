package com.example.main_01.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.main_01.MainActivity;
import com.example.main_01.R;

public class MyPage extends AppCompatActivity {

    Button arw;
    ImageButton btn1, btn2, btn3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        Button arw = (Button) findViewById(R.id.goback);
        ImageButton btn1 = (ImageButton) findViewById(R.id.classlist);
        ImageButton btn2 = (ImageButton) findViewById(R.id.likelist);
        ImageButton btn3 = (ImageButton) findViewById(R.id.mytype_info);

        arw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, MyPageClass.class);
                startActivity(i);
                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, MyPageLikelist.class);
                startActivity(i);
                finish();
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPage.this, MyTypeInfo.class);
                startActivity(i);
                finish();
            }
        });


    }
}