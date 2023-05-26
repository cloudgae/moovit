package com.example.main_01.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.main_01.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class TypeTest_2 extends AppCompatActivity {

    RadioGroup rbgT2;
    RadioButton rbT2_personal, rbT2_group;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test2);

        rbgT2 = (RadioGroup) findViewById(R.id.rbg_T2);
        rbT2_personal = (RadioButton) findViewById(R.id.rbT2_personal);
        rbT2_group = (RadioButton) findViewById(R.id.rbT2_group);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference dbref = db.collection("TypeTest").document("User");

        //라디오 버튼 중 하나가 눌리면 다음 액티비티로 전환

        rbT2_personal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT2_personal.isChecked() == true){
                    dbref.update("Q2", "personal");
                }
                Intent i = new Intent(TypeTest_2.this, TypeTest_3.class);
                startActivity(i);
            }
        });

        rbT2_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT2_group.isChecked() == true){
                    dbref.update("Q2", "group");
                }
                Intent i = new Intent(TypeTest_2.this, TypeTest_3.class);
                startActivity(i);
            }
        });
    }
}
