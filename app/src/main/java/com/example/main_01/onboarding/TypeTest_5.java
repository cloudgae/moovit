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

public class TypeTest_5 extends AppCompatActivity {

    RadioGroup rbg_T5;
    RadioButton rbT5_cheer, rbT5_concentrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test5);

        rbg_T5 = (RadioGroup) findViewById(R.id.rbg_T5);
        rbT5_cheer = (RadioButton) findViewById(R.id.rbT5_cheer);
        rbT5_concentrate = (RadioButton) findViewById(R.id.rbT5_concentrate);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        Map<String, Object> test3 = new HashMap<>();
//        test3.put("cheer", false);
//        test3.put("concentrate", false);
//
//        db.collection("TypeTest")
//                .document("Q5")
//                .set(test3);

        DocumentReference dbref = db.collection("TypeTest").document("User");

        rbT5_cheer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT5_cheer.isChecked() == true){
//                    classmood.update("cheer", true);
//                    classmood.update("concentrate", false);
                    dbref.update("Q5", "cheer");
                }
                Intent i = new Intent(TypeTest_5.this, TypeTest_6.class);
                startActivity(i);
            }
        });

        rbT5_concentrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT5_concentrate.isChecked() == true){
//                    classmood.update("cheer", false);
//                    classmood.update("concentrate", true);
                    dbref.update("Q5", "concentrate");
                }
                Intent i = new Intent(TypeTest_5.this, TypeTest_6.class);
                startActivity(i);
            }
        });
    }
}