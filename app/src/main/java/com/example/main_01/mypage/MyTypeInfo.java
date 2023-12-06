package com.example.main_01.mypage;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MyTypeInfo extends AppCompatActivity {

    Button arw;
    TextView typename, typefeature1, typefeature2, typefeature3;
    LinearLayout typelayer;
    ImageButton btnNext;
    Long moover, starter;
    String value4, value5, value6;
    String TCODE; // 클래스의 멤버 변수로 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_type_info);

        Button arw = (Button) findViewById(R.id.goback);
        typename = (TextView) findViewById(R.id.typename);
        typefeature1 = (TextView) findViewById(R.id.typefeature1);
        typefeature2 = (TextView) findViewById(R.id.typefeature2);
        typefeature3 = (TextView) findViewById(R.id.typefeature3);
        typelayer = (LinearLayout) findViewById(R.id.typelayer);
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        arw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyTypeInfo.this, MyPage.class);
                startActivity(i);
                finish();
            }
        });

        // 응답456 조회+연결해서 유형을 세자리수 코드로 로그에 출력
        db.collection("TypeTest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                value4 = (String) document.get("Q4");
                                value5 = (String) document.get("Q5");

                                moover = (Long) document.get("M");
                                starter = (Long) document.get("S");
                                if (moover == null && starter != null) {
                                    value6 = "S";
                                } else if (starter == null && moover != null) {
                                    value6 = "M";
                                } else if (moover != null && starter != null) {
                                    Integer m = Integer.parseInt(String.valueOf(moover));
                                    Integer s = Integer.parseInt(String.valueOf(starter));
                                    if (m > s) {
                                        value6 = "M";
                                    } else if (m == s) {
                                        value6 = "S";
                                    } else {
                                        value6 = "S";
                                    }
                                }
//                                String value6 = (String) document.get("Q6");
                                TCODE = value4 + value5 + value6; // 도출된 유형 코드
                                Log.d(TAG, TCODE);

                                // 세자리수 코드와 같은 값을 가진 유형 타입을 조회
                                db.collection("Type")
                                        .whereEqualTo("code", TCODE)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        String finTYPE = document.getId();
                                                        Log.d(TAG, finTYPE);

                                                        // 텍스트 바꾸는 작업
                                                        // 유형 이름
                                                        typename.setText((String) document.getData().get("name"));
                                                        // 유형 설명
                                                        typefeature1.setText((String) document.getData().get("feature1"));
                                                        typefeature2.setText((String) document.getData().get("feature2"));
                                                        typefeature3.setText((String) document.getData().get("feature3"));

                                                        // TCODE에 따라 배경 이미지 설정
                                                        switch (TCODE) {
                                                            case "PCS":
                                                                typelayer.setBackgroundResource(R.drawable.pcs);

                                                                break;
                                                            case "PCM":
                                                                typelayer.setBackgroundResource(R.drawable.pcm);

                                                                break;
                                                            case "UCS":
                                                                typelayer.setBackgroundResource(R.drawable.ucs);

                                                                break;
                                                            case "UCM":
                                                                typelayer.setBackgroundResource(R.drawable.ucm);

                                                                break;
                                                            case "PIS":
                                                                typelayer.setBackgroundResource(R.drawable.pis);

                                                                break;
                                                            case "PIM":
                                                                typelayer.setBackgroundResource(R.drawable.pim);

                                                                break;
                                                            case "UIS":
                                                                typelayer.setBackgroundResource(R.drawable.uis);

                                                                break;
                                                            case "UIM":
                                                                typelayer.setBackgroundResource(R.drawable.uim);

                                                                break;
                                                        }

                                                    }
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }
}