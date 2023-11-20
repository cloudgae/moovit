package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
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
                                moveNext(1);
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
            }
        });

        /*finish_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다이얼로그 변수 선언
                final AlertDialog[] alertDialog = {null};

                AlertDialog.Builder dlgBuilder = new AlertDialog.Builder(Apply_2.this);
                LayoutInflater inflater = getLayoutInflater();
                View customLayout = inflater.inflate(R.layout.activity_apply_popup, null);
                dlgBuilder.setView(customLayout);

                // 버튼 찾기
                Button positiveButton = customLayout.findViewById(R.id.positivebtn);
                Button negativeButton = customLayout.findViewById(R.id.negativebtn);

                // 긍정 버튼 클릭 이벤트 설정
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 여기에 긍정 버튼을 클릭했을 때 수행할 동작을 추가하세요.
                        Intent i2 = new Intent(Apply_2.this, MainActivity.class);
                        startActivity(i2);
                        finish();

                        // 다이얼로그를 닫는 코드
                        if (alertDialog[0] != null) {
                            alertDialog[0].dismiss();
                        }
                    }
                });

                // 부정 버튼 클릭 이벤트 설정
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 여기에 부정 버튼을 클릭했을 때 수행할 동작을 추가하세요.
                        // 예: 다이얼로그를 닫거나 다른 동작 수행

                        // 다이얼로그를 닫는 코드
                        if (alertDialog[0] != null) {
                            alertDialog[0].dismiss();
                        }
                    }
                });

                alertDialog[0] = dlgBuilder.create();
                alertDialog[0].show();
                alertDialog[0].getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            }
        });*/
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

    private void moveNext(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Apply_3로 이동
                Intent i = new Intent(Apply_2.this, Apply_3.class);
                startActivity(i);

                // 1초 후에 다시 Apply_2로 돌아가서 팝업 표시
                moveBack(3);
            }
        }, 1500 * sec); // sec초 정도 딜레이를 준 후 시작
    }

    private void moveBack(int sec) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Apply_2로 돌아가서 팝업 표시
                showPopup();
            }
        }, 1500 * sec); // sec초 정도 딜레이를 준 후 시작
    }



}
