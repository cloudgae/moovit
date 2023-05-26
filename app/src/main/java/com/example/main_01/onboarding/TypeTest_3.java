package com.example.main_01.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import com.example.main_01.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.HashMap;
import java.util.Map;

public class TypeTest_3 extends AppCompatActivity {

    RadioGroup rbgT3;
    RadioButton rbT3_1day, rbT3_nday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test3);

        rbgT3 = (RadioGroup) findViewById(R.id.rbg_T3);
        rbT3_1day = (RadioButton) findViewById(R.id.rbT3_1day);
        rbT3_nday = (RadioButton) findViewById(R.id.rbT3_nday);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        Map<String, Object> test3 = new HashMap<>();
//        test3.put("1day", false);
//        test3.put("nday", false);
//
//        db.collection("TypeTest")
//                .document("Q3")
//                .set(test3);

        DocumentReference dbref = db.collection("TypeTest").document("User");

        rbT3_1day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT3_1day.isChecked() == true){
//                    period.update("1day", true);
//                    period.update("nday", false);
                    dbref.update("Q3", "1day");
                }
                Intent i = new Intent(TypeTest_3.this, TypeTest_4.class);
                startActivity(i);
            }
        });

        rbT3_nday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT3_nday.isChecked() == true){
//                    period.update("1day", false);
//                    period.update("nday", true);
                    dbref.update("Q3", "nday");
                }
                Intent i = new Intent(TypeTest_3.this, TypeTest_4.class);
                startActivity(i);
            }
        });

    }
}