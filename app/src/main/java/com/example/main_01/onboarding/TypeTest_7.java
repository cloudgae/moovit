package com.example.main_01.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class TypeTest_7 extends AppCompatActivity {
    RadioGroup rbg_T7;
    RadioButton rbT7_1, rbT7_2, rbT7_3, rbT7_4, rbT7_5;
    ProgressBar prbar;
    Long moover, starter;
    Integer m, s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test7);

        rbg_T7 = (RadioGroup) findViewById(R.id.rbg_T7);
        rbT7_1 = (RadioButton) findViewById(R.id.rbT7_1);
        rbT7_2 = (RadioButton) findViewById(R.id.rbT7_2);
        rbT7_3 = (RadioButton) findViewById(R.id.rbT7_3);
        rbT7_4 = (RadioButton) findViewById(R.id.rbT7_4);
        rbT7_5 = (RadioButton) findViewById(R.id.rbT7_5);

        prbar = (ProgressBar) findViewById(R.id.progressbar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference dbref = db.collection("TypeTest").document("User");

        moover = Long.valueOf(0);
        starter = Long.valueOf(0);
        m = 0;
        s = 0;

        // 응답456 조회+연결해서 유형을 세자리수 코드로 로그에 출력
        db.collection("TypeTest")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                //기존에 저장된 값을 불러와 변수에 저장
                                moover = (Long) document.get("M");
                                starter = (Long) document.get("S");
//
//                                m = Integer.parseInt(String.valueOf(moover));
//                                s = Integer.parseInt(String.valueOf(starter));


                            }
                        }
                    }
                });
        //TODO : m,s값을 설정하는 게 아니라, 기존에 저장된 값에 더해서 저장해야 함
        //기존에 저장된 값을 불러와, 그 값에 가중치를 더해, 저장
        rbT7_1.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
                if(rbT7_1.isChecked() == true){
                    m = Math.toIntExact(moover + 3);
                    s = Math.toIntExact(starter + 0);

                    dbref.update("M", m);
                    dbref.update("S", s);
//                    dbref.update("Q6", "1");
//                    dbref.update("Q6", "S");
//                    dbref.update("TYPE", FieldValue.arrayUnion("S"));
//                    dbref.update("S", true);
//                    dbref.update("TYPE", "S", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
//                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_7.this, TypeTest_8.class);
                startActivity(i);
            }
        });

        rbT7_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT7_2.isChecked() == true){
                    m = Math.toIntExact(moover + 2);
                    s = Math.toIntExact(starter + 0);

                    dbref.update("M", m);
                    dbref.update("S", s);

//                    dbref.update("Q6", "2");
//                    dbref.update("Q6", "M");
//                    dbref.update("TYPE", FieldValue.arrayUnion("M"));
//                    dbref.update("M", true);
//                    dbref.update("TYPE", "M", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
//                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_7.this, TypeTest_8.class);
                startActivity(i);
            }
        });

        rbT7_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT7_3.isChecked() == true){
                    m = Math.toIntExact(moover + 1);
                    s = Math.toIntExact(starter + 0);

                    dbref.update("M", m);
                    dbref.update("S", s);
//                    dbref.update("M", 1);
//                    dbref.update("S", 0);
//                    dbref.update("Q6", "3");
//                    dbref.update("Q6", "M");
//                    dbref.update("TYPE", FieldValue.arrayUnion("M"));
//                    dbref.update("M", true);
//                    dbref.update("TYPE", "M", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
//                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_7.this, TypeTest_8.class);
                startActivity(i);
            }
        });

        rbT7_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT7_4.isChecked() == true){
                    m = Math.toIntExact(moover + 1);
                    s = Math.toIntExact(starter + 1);

                    dbref.update("M", m);
                    dbref.update("S", s);
//                    dbref.update("M", 1);
//                    dbref.update("S", 1);
//                    dbref.update("Q6", "3");
//                    dbref.update("Q6", "M");
//                    dbref.update("TYPE", FieldValue.arrayUnion("M"));
//                    dbref.update("M", true);
//                    dbref.update("TYPE", "M", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
//                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_7.this, TypeTest_8.class);
                startActivity(i);
            }
        });

        rbT7_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT7_5.isChecked() == true){
                    m = Math.toIntExact(moover + 0);
                    s = Math.toIntExact(starter + 2);

                    dbref.update("M", m);
                    dbref.update("S", s);
//                    dbref.update("M", 0);
//                    dbref.update("S", 2);
//                    dbref.update("Q6", "3");
//                    dbref.update("Q6", "M");
//                    dbref.update("TYPE", FieldValue.arrayUnion("M"));
//                    dbref.update("M", true);
//                    dbref.update("TYPE", "M", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
//                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_7.this, TypeTest_8.class);
                startActivity(i);
            }
        });
    }
}