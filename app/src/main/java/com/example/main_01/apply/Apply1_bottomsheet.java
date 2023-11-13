package com.example.main_01.apply;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.main_01.R;

public class Apply1_bottomsheet extends AppCompatActivity {
    Button apply_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply1_bottomsheet);

        apply_button = (Button) findViewById(R.id.apply_button);

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Apply_2 액티비티로 이동
                Intent intent = new Intent(Apply1_bottomsheet.this, Apply_2.class);
                startActivity(intent);
                Log.d("Apply1_bottomsheet", "Apply_2 액티비티 시작됨"); // 로그 메시지 추가
            }
        });


    }

}