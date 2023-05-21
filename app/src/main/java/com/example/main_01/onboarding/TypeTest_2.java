package com.example.main_01.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.main_01.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

public class TypeTest_2 extends AppCompatActivity {

    RadioGroup rbgT2;
    RadioButton rbArray_T2[] = new RadioButton[2];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test2);

        rbgT2 = (RadioGroup) findViewById(R.id.rbg_T2);
        rbArray_T2[0] = (RadioButton) findViewById(R.id.rbT2_personal);
        rbArray_T2[1] = (RadioButton) findViewById(R.id.rbT2_group);
        //굳이 배열로 컨트롤할 필요는 없는 것 같음**

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, Object> test2 = new HashMap<>();
        test2.put("personal", false);
        test2.put("group", false);

        db.collection("TypeTest")
                .document("Q2")
                .set(test2);

        DocumentReference classscale = db.collection("TypeTest").document("Q2");

        //라디오 버튼 중 하나가 눌리면 다음 액티비티로 전환

        rbArray_T2[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbArray_T2[0].isChecked() == true) {
                    classscale.update("personal", true);
                    classscale.update("group", false);
                }
                Intent i = new Intent(TypeTest_2.this, TypeTest_3.class);
                startActivity(i);
            }
        });

        rbArray_T2[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rbArray_T2[1].isChecked() == true) {
                    classscale.update("personal", false);
                    classscale.update("group", true);
                }
                Intent i = new Intent(TypeTest_2.this, TypeTest_3.class);
                startActivity(i);
            }
        });
    }
}
