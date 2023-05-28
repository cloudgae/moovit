package com.example.main_01.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.main_01.R;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class TypeTest_6 extends AppCompatActivity {

    RadioGroup rbg_T6;
    RadioButton rbT6_1, rbT6_2, rbT6_3, rbT6_4;
    ProgressBar prbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test6);

        rbg_T6 = (RadioGroup) findViewById(R.id.rbg_T6);
        rbT6_1 = (RadioButton) findViewById(R.id.rbT6_1);
        rbT6_2 = (RadioButton) findViewById(R.id.rbT6_2);
        rbT6_3 = (RadioButton) findViewById(R.id.rbT6_3);

        prbar = (ProgressBar) findViewById(R.id.progressbar);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference dbref = db.collection("TypeTest").document("User");

        rbT6_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_1.isChecked() == true){
//                    dbref.update("Q6", "1");
                    dbref.update("Q6", "S");
//                    dbref.update("TYPE", FieldValue.arrayUnion("S"));
//                    dbref.update("S", true);
//                    dbref.update("TYPE", "S", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_6.this, TypeTest_7.class);
                startActivity(i);
            }
        });

        rbT6_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_2.isChecked() == true){
//                    dbref.update("Q6", "2");
                    dbref.update("Q6", "M");
//                    dbref.update("TYPE", FieldValue.arrayUnion("M"));
//                    dbref.update("M", true);
//                    dbref.update("TYPE", "M", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_6.this, TypeTest_7.class);
                startActivity(i);
            }
        });

        rbT6_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rbT6_3.isChecked() == true){
//                    dbref.update("Q6", "3");
                    dbref.update("Q6", "M");
//                    dbref.update("TYPE", FieldValue.arrayUnion("M"));
//                    dbref.update("M", true);
//                    dbref.update("TYPE", "M", SetOptions.merge());
                }
                prbar.setIndeterminate(false);
                prbar.setProgress(100);

                Intent i = new Intent(TypeTest_6.this, TypeTest_7.class);
                startActivity(i);
            }
        });
    }
}