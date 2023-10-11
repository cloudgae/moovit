package com.example.main_01.mypage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.main_01.R;

public class MyTypeInfo extends AppCompatActivity {

    Button arw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type_info);

        Button arw = (Button) findViewById(R.id.goback);

        arw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyTypeInfo.this, MyPage.class);
                startActivity(i);
                finish();
            }
        });
    }
}