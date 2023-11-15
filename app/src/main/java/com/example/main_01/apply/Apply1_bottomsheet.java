package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Apply1_bottomsheet extends AppCompatActivity {
    ImageButton apply_button;
    TextView txtmax, txttime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply1_bottomsheet);

//        txtmax = (TextView)findViewById(R.id.txtmax);
//        txttime = (TextView)findViewById(R.id.txttime);
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        DocumentReference docRef = db.collection("Class").document("C7");

//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    String max = String.valueOf(document.getLong("maxstudent"));
//                    String st = document.getString("starttime");
//                    String et = document.getString("endtime");
//
//                    txtmax.setText("0/" + max + "명");
//                    txttime.setText(st + " ~ " + et);
//                }
//            }
//        });
        apply_button = (ImageButton) findViewById(R.id.apply_button);

        apply_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Apply_2 액티비티로 이동
                Intent intent = new Intent(Apply1_bottomsheet.this, Apply_2.class);
                startActivity(intent);
                Log.d("Apply1_bottomsheet", "Apply_2 액티비티 시작됨"); // 로그 메시지 추가
            }
        });


    }

}