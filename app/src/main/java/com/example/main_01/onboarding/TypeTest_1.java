package com.example.main_01.onboarding;

import static android.content.ContentValues.TAG;

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
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
    ImageButton btnT1_next;
    LinearLayout chkT1_sample;
    CheckedTextView chkTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test1);

        chkT1_kpop = (CheckBox) findViewById(R.id.chkT1_kpop);
        chkT1_street = (CheckBox) findViewById(R.id.chkT1_street);
        chkT1_choreo = (CheckBox) findViewById(R.id.chk1_choreo);
        btnT1_next = (ImageButton) findViewById(R.id.btnT1_next);


//Cloud Firestore 인스턴스를 초기화합니다.
        FirebaseFirestore db = FirebaseFirestore.getInstance();

       // Map<String, Object> test1 = new HashMap<>();

        DocumentReference dbref = db.collection("TypeTest").document("User");

        //배열의 0번에 kpop, 1번에 street, 2번에 choreo 자리가 지정되어 있도록 하는법 고민-자료가 별로 없는듯..
        //체크가 되는 순서대로 인덱스 0,1,2에 저장됨-이렇게 되도 탐색에 문제 없을지?
        chkT1_kpop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checked가 true면 firestore에 저장
                if (chkT1_kpop.isChecked() == true) {
                    dbref.update("Q1",FieldValue.arrayUnion("kpop"));
                }
                else{
                    dbref.update("Q1",FieldValue.arrayRemove("kpop"));
                }
            }
        });

        chkT1_street.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checked가 true면 firestore에 저장
                if (chkT1_street.isChecked() == true) {
                    dbref.update("Q1",FieldValue.arrayUnion("street"));
                }
                else{
                    dbref.update("Q1",FieldValue.arrayRemove("street"));
                }
            }
        });

        chkT1_choreo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //checked가 true면 firestore에 저장
                if (chkT1_choreo.isChecked() == true) {
                    dbref.update("Q1",FieldValue.arrayUnion("choreo"));
                }
                else{
                    dbref.update("Q1",FieldValue.arrayRemove("choreo"));
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
