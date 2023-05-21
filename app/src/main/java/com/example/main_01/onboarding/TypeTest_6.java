package com.example.main_01.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.main_01.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TypeTest_6 extends AppCompatActivity {

    RadioGroup rbg_T6;
    RadioButton rbT6_1, rbT6_2, rbT6_3, rbT6_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test6);

        rbg_T6 = (RadioGroup) findViewById(R.id.rbg_T6);
        rbT6_1 = (RadioButton) findViewById(R.id.rbT6_1);
        rbT6_2 = (RadioButton) findViewById(R.id.rbT6_2);
        rbT6_3 = (RadioButton) findViewById(R.id.rbT6_3);
        rbT6_4 = (RadioButton) findViewById(R.id.rbT6_4);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> test6 = new HashMap<>();
        test6.put("1", false);
        test6.put("2", false);
        test6.put("3", false);
        test6.put("4", false);

        db.collection("TypeTest")
                .document("Q6")
                .set(test6);

        DocumentReference grade = db.collection("TypeTest").document("Q6");

        rbT6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_1.isChecked() == true){
                    grade.update("1", true);
                    grade.update("2", false);
                    grade.update("3", false);
                    grade.update("4", false);
                }
//                Intent i = new Intent(TypeTest_6.this, TypeTest_6.class);
//                startActivity(i); 결과 화면 전환
            }
        });

        rbT6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_2.isChecked() == true){
                    grade.update("1", false);
                    grade.update("2", true);
                    grade.update("3", false);
                    grade.update("4", false);
                }
//                Intent i = new Intent(TypeTest_6.this, TypeTest_6.class);
//                startActivity(i); 결과 화면 전환
            }
        });

        rbT6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_3.isChecked() == true){
                    grade.update("1", false);
                    grade.update("2", false);
                    grade.update("3", true);
                    grade.update("4", false);
                }
//                Intent i = new Intent(TypeTest_6.this, TypeTest_6.class);
//                startActivity(i); 결과 화면 전환
            }
        });

        rbT6_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_4.isChecked() == true){
                    grade.update("1", false);
                    grade.update("2", false);
                    grade.update("3", false);
                    grade.update("4", true);
                }
//                Intent i = new Intent(TypeTest_6.this, TypeTest_6.class);
//                startActivity(i); 결과 화면 전환
            }
        });
    }
}