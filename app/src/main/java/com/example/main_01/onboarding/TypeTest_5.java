package com.example.main_01.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.main_01.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;

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

        DocumentReference dbref = db.collection("TypeTest").document("User");

        Map<String, Object> data1 = new HashMap<>();
        data1.put("TYPE", "I");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("TYPE", "C");

        rbT5_cheer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT5_cheer.isChecked() == true){
//                    dbref.update("Q5", "cheer");
                    dbref.update("Q5", "I");
//                    dbref.update("TYPE", FieldValue.arrayUnion("I"));
//                    dbref.update("I", true);
//                    dbref.update("TYPE", SetOptions.mergeFields("I"));
//                    db.collection("TypeTest").document("User")
//                            .set(data1, SetOptions.merge());
//                    db.collection("TypeTest").document("User")
//                            .set("I", SetOptions.mergeFields("TYPE"));
                }
                Intent i = new Intent(TypeTest_5.this, TypeTest_6.class);
                startActivity(i);
            }
        });

        rbT5_concentrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT5_concentrate.isChecked() == true){
//                    dbref.update("Q5", "concentrate");
                    dbref.update("Q5", "I");
//                    dbref.update("TYPE", FieldValue.arrayUnion("C"));
//                    dbref.update("C", true);
//                    dbref.update("TYPE", SetOptions.mergeFields("C"));
//                    db.collection("TypeTest").document("User")
//                            .set(data2, SetOptions.merge());
//                    db.collection("TypeTest").document("User")
//                            .set("C", SetOptions.mergeFields("TYPE"));
                }
                Intent i = new Intent(TypeTest_5.this, TypeTest_6.class);
                startActivity(i);
            }
        });
    }
}