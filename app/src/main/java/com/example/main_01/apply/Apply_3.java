package com.example.main_01.apply;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.main_01.MainActivity;
import com.example.main_01.R;

public class Apply_3 extends AppCompatActivity {
    Button back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply3);

        back_button = findViewById(R.id.back_button);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveBack(1);
            }
        });
    }

    private void showPopup() {
        // 다이얼로그 변수 선언
        final AlertDialog[] alertDialog = {null};

        AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(Apply_3.this);
        LayoutInflater inflater = getLayoutInflater();
        View customLayout = inflater.inflate(R.layout.activity_apply_popup, null);
        dlgBuilder.setView(customLayout);

        // 버튼 찾기
        Button positiveButton = customLayout.findViewById(R.id.positivebtn);
        Button negativeButton = customLayout.findViewById(R.id.negativebtn);

        // 긍정 버튼 클릭 이벤트 설정
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 여기에 긍정 버튼을 클릭했을 때 수행할 동작을 추가하세요.
                Intent i2 = new Intent(Apply_3.this, MainActivity.class);
                startActivity(i2);
                showPopup();

                // 다이얼로그를 닫는 코드
                if (alertDialog[0] != null) {
                    alertDialog[0].dismiss();
                }
            }
        });

        // 부정 버튼 클릭 이벤트 설정
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 여기에 부정 버튼을 클릭했을 때 수행할 동작을 추가하세요.
                // 예: 다이얼로그를 닫거나 다른 동작 수행

                // 다이얼로그를 닫는 코드
                if (alertDialog[0] != null) {
                    alertDialog[0].dismiss();
                }
            }
        });

        alertDialog[0] = dlgBuilder.create();
        alertDialog[0].show();
        alertDialog[0].getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // 로그 추가
        Log.d("Popup", "Popup shown");
    }


    private void moveBack(int sec) {
        // 2초 후에 Apply_2로 이동
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Apply_3.this, Apply_2.class);
                startActivity(i);
                // showPopup 함수를 이동이 완료된 후에 호출
            }
        }, 2000 * sec); // sec초 정도 딜레이를 준 후 시작

        // 2초 후에 팝업 표시
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showPopup();
            }
        }, 2000 * sec + 2000); // 이전 딜레이에 추가로 2초 더 기다림
    }

}
