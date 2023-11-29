package com.example.main_01.mypage;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main_01.MainActivity;
import com.example.main_01.map.Map_0;
import com.example.main_01.R;
import com.example.main_01.shorts.shorts1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MyPage extends AppCompatActivity {

    Button arw;
    ImageButton btn1, btn2, btn3;
    ImageView typelayer;
    LinearLayout typelayer2;
    TextView typename;
    Long moover, starter;
    String value4, value5, value6;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        arw = (Button) findViewById(R.id.goback);
        btn1 = (ImageButton) findViewById(R.id.classlist);
        btn2 = (ImageButton) findViewById(R.id.likelist);
        btn3 = (ImageButton) findViewById(R.id.mytype_info);

        typename = (TextView) findViewById(R.id.typename);
        typelayer = (ImageView) findViewById(R.id.typelayer);
        typelayer2 = (LinearLayout) findViewById(R.id.typelayer2);

        moover = Long.valueOf(0);
        starter = Long.valueOf(0);


        CollectionReference collectionRef = db.collection("Class");

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openMainActivity();
                        return true;
                    case R.id.map:
                        openMap();
                        return true;
                    case R.id.shorts:
                        openShorts();
                        return true;
                    case R.id.mypage:
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.mypage);


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
                                String TCODE = value4 + value5 + value6; // 도출된 유형 코드
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

                                                        // 유형 이름
                                                        typename.setText((String) document.getData().get("name"));
                                                        // 유형 설명
                                                        switch (TCODE) {
                                                            case "PCS":
                                                                typelayer.setBackgroundResource(R.drawable.pcs2);
                                                                typelayer2.setBackgroundResource(R.drawable.pcs4);
                                                                break;
                                                            case "PCM":
                                                                typelayer.setBackgroundResource(R.drawable.pcm2);
                                                                typelayer2.setBackgroundResource(R.drawable.pcm4);
                                                                break;
                                                            case "UCS":
                                                                typelayer.setBackgroundResource(R.drawable.ucs2);
                                                                typelayer2.setBackgroundResource(R.drawable.ucs4);
                                                                break;
                                                            case "UCM":
                                                                typelayer.setBackgroundResource(R.drawable.ucm2);
                                                                typelayer2.setBackgroundResource(R.drawable.ucm4);
                                                                break;
                                                            case "PIS":
                                                                typelayer.setBackgroundResource(R.drawable.pis2);
                                                                typelayer2.setBackgroundResource(R.drawable.pis4);
                                                                break;
                                                            case "PIM":
                                                                typelayer.setBackgroundResource(R.drawable.pim2);
                                                                typelayer2.setBackgroundResource(R.drawable.pim4);
                                                                break;
                                                            case "UIS":
                                                                typelayer.setBackgroundResource(R.drawable.uis2);
                                                                typelayer2.setBackgroundResource(R.drawable.uis4);
                                                                break;
                                                            case "UIM":
                                                                typelayer.setBackgroundResource(R.drawable.uim2);
                                                                typelayer2.setBackgroundResource(R.drawable.uim4);
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

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openMap() {
        Intent intent = new Intent(this, Map_0.class);
        startActivity(intent);
    }

    private void openShorts() {
        Intent intent = new Intent(this, shorts1.class);
        startActivity(intent);
    }


    private void openMyPage() {
        Intent intent = new Intent(this, MyPage.class);
        startActivity(intent);
    }
}
