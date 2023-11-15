package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Apply_2 extends AppCompatActivity {
    ImageButton finish_button;
    private FirebaseFirestore db;  // Firestore 인스턴스 선언 추가

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply2);

        // Firestore 초기화
        db = FirebaseFirestore.getInstance();
//        DocumentReference newClassRef = db.collection("Class").document("C7");
        DocumentReference docRef = db.collection("Class").document("C7");
//        새로운 문서의 id 가져오기
//        String newClassId = newClassRef.getId();
        // 업데이트할 필드 및 값 설정
        Map<String, Object> updates = new HashMap<>();
        updates.put("mozip", "1");

        finish_button = findViewById(R.id.finish_button);

        finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //모집인원 1로 수정
                // 문서 업데이트
                docRef.update(updates)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // 업데이트 성공 시 처리
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // 업데이트 실패 시 처리
                            }
                        });
                // 팝업 창 표시
                showPopup();
            }
        });
    }

    private void showPopup() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(getLayoutInflater().inflate(R.layout.apply_popup_layout, null));
        builder.setCancelable(false);

        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                // MainActivity로 이동
                Intent intent = new Intent(Apply_2.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // 확인 버튼 텍스트 색상 설정
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setTextColor(getResources().getColor(R.color.pink));
    }
}
