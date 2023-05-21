package com.example.main_01.onboarding;

import android.content.Intent;
import android.os.Bundle;

import com.example.main_01.*;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TypeTest_1 extends AppCompatActivity {

    CheckBox chkT1_kpop, chkT1_street, chkT1_choreo;
    Button btnT1_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test1);

//        FirebaseFirestore db = FirebaseFirestore.getInstance();

        chkT1_kpop = (CheckBox) findViewById(R.id.chkT1_kpop);
        chkT1_street = (CheckBox) findViewById(R.id.chkT1_street);
        chkT1_choreo = (CheckBox) findViewById(R.id.chk1_choreo);
        btnT1_next = (Button) findViewById(R.id.btnT1_next);


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> test1 = new HashMap<>();
        test1.put("kpop", false);
        test1.put("street", false);
        test1.put("choreo", false);
        db.collection("TypeTest")
                .document("Q1")
                .set(test1);

        DocumentReference genre = db.collection("TypeTest").document("Q1");

        chkT1_kpop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checked가 true면 firestore에 저장
                if (chkT1_kpop.isChecked() == true) {

                    genre.update("kpop", true);
                }
                else{
                    genre.update("kpop",false);
                }
            }
        });

        chkT1_street.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checked가 true면 firestore에 저장
                if (chkT1_street.isChecked() == true) {
                    genre.update("street", true);
                }
                else{
                    genre.update("street",false);
                }
            }
        });

        chkT1_choreo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checked가 true면 firestore에 저장
                if (chkT1_choreo.isChecked() == true) {
                    genre.update("choreo", true);
                }
                else{
                    genre.update("choreo",false);
                }
            }
        });



        btnT1_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TypeTest_1.this, TypeTest_2.class);
                startActivity(i);
            }
        });


    }

}
