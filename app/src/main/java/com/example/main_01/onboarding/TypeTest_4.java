package com.example.main_01.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.main_01.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class TypeTest_4 extends AppCompatActivity {

    RadioGroup rbg_T4;
    RadioButton rbT4_hip, rbT4_nice, rbT4_hot, rbT4_conf, rbT4_unique, rbT4_enjoy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test4);

        rbg_T4 = (RadioGroup) findViewById(R.id.rbg_T4);
        rbT4_hip = (RadioButton) findViewById(R.id.rbT4_hip);
        rbT4_nice = (RadioButton) findViewById(R.id.rbT4_nice);
        rbT4_hot = (RadioButton) findViewById(R.id.rbT4_hot);
        rbT4_conf = (RadioButton) findViewById(R.id.rbT4_conf);
        rbT4_unique = (RadioButton) findViewById(R.id.rbT4_unique);
        rbT4_enjoy = (RadioButton) findViewById(R.id.rbT3_enjoy);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> test4 = new HashMap<>();
        test4.put("hip", false);
        test4.put("nice", false);
        test4.put("hot", false);
        test4.put("conf", false);
        test4.put("unique", false);
        test4.put("enjoy", false);

        db.collection("TypeTest")
                .document("Q4")
                .set(test4);

        DocumentReference mood = db.collection("TypeTest").document("Q4");

        //ischecked==false 추가해서 각 해당항목만 컨트롤할지 고민
        rbT4_hip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT4_hip.isChecked() == true){
                    mood.update("hip", true);
                    mood.update("nice", false);
                    mood.update("hot", false);
                    mood.update("conf", false);
                    mood.update("unique", false);
                    mood.update("enjoy", false);
                }
                Intent i = new Intent(TypeTest_4.this, TypeTest_5.class);
                startActivity(i);
            }
        });

        rbT4_nice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT4_nice.isChecked() == true){
                    mood.update("hip", false);
                    mood.update("nice", true);
                    mood.update("hot", false);
                    mood.update("conf", false);
                    mood.update("unique", false);
                    mood.update("enjoy", false);
                }
                Intent i = new Intent(TypeTest_4.this, TypeTest_5.class);
                startActivity(i);
            }
        });

        rbT4_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT4_hot.isChecked() == true){
                    mood.update("hip", false);
                    mood.update("nice", false);
                    mood.update("hot", true);
                    mood.update("conf", false);
                    mood.update("unique", false);
                    mood.update("enjoy", false);
                }
                Intent i = new Intent(TypeTest_4.this, TypeTest_5.class);
                startActivity(i);
            }
        });

        rbT4_conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT4_conf.isChecked() == true){
                    mood.update("hip", false);
                    mood.update("nice", false);
                    mood.update("hot", false);
                    mood.update("conf", true);
                    mood.update("unique", false);
                    mood.update("enjoy", false);
                }
                Intent i = new Intent(TypeTest_4.this, TypeTest_5.class);
                startActivity(i);
            }
        });

        rbT4_unique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT4_unique.isChecked() == true){
                    mood.update("hip", false);
                    mood.update("nice", false);
                    mood.update("hot", false);
                    mood.update("conf", false);
                    mood.update("unique", true);
                    mood.update("enjoy", false);
                }
                Intent i = new Intent(TypeTest_4.this, TypeTest_5.class);
                startActivity(i);
            }
        });

        rbT4_enjoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT4_enjoy.isChecked() == true){
                    mood.update("hip", false);
                    mood.update("nice", false);
                    mood.update("hot", false);
                    mood.update("conf", false);
                    mood.update("unique", false);
                    mood.update("enjoy", true);
                }
                Intent i = new Intent(TypeTest_4.this, TypeTest_5.class);
                startActivity(i);
            }
        });
    }
}