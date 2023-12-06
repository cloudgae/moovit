package com.example.main_01.apply;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Apply_1_recycle extends AppCompatActivity {
    ImageView image;
    TextView name, txt;
    CalendarView calendarView;
    Calendar lastSelectedDate;
    ImageButton back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply1_recycle);

        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        txt = (TextView) findViewById(R.id.txt);
        calendarView = findViewById(R.id.calendarView);
        back_btn = (ImageButton) findViewById(R.id.back_btn);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

// getIntent()로 Intent를 받아옴
        Intent intent = getIntent();

// Intent에서 데이터 추출
        String documentId = intent.getStringExtra("documentId1");

// documentId가 null이거나 비어있는지 확인 후 초기화
        if (documentId == null || documentId.isEmpty()) {
            // documentId를 적절한 값으로 초기화 (예: 기본값 또는 다른 값)
            documentId = "defaultDocumentId";
        }


        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("Class").document(documentId);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        name.setText(document.getString("name"));
                        //장르 . 난이도 상/중/하
                        txt.setText(document.getString("genre") + "・" +
                                " " + document.getString("difficulty"));

                        // 이미지 리소스 설정
                        int imageResource = intent.getIntExtra("imageResource1", 0);
                        image.setImageResource(imageResource);
//                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                // 여기에서 선택한 날짜에 따라 bottomSheetDialog를 띄우는 로직을 추가합니다.
                // 이 예시에서는 showClassSelectionBottomSheet() 메서드를 호출하도록 했습니다.
                showClassSelectionBottomSheet();
            }
        });

    }

    private void showClassSelectionBottomSheet() {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.activity_apply1_bottomsheet); // 레이아웃 파일을 지정

        ImageButton applyButton = bottomSheetDialog.findViewById(R.id.apply_button);

        // 텍스트뷰 객체 초기화
        TextView txtmax = bottomSheetDialog.findViewById(R.id.txtmax);
        TextView txttime = bottomSheetDialog.findViewById(R.id.txttime);

        // 여기에서 텍스트 설정
        if (txtmax != null && txttime != null) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            DocumentReference docRef = db.collection("Class").document("C7");

            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String max = String.valueOf(document.getLong("maxstudent"));
                            String st = document.getString("starttime");
                            String et = document.getString("endtime");

                            txtmax.setText("0/" + max + "명");
                            txttime.setText(st + " ~ " + et);
                        } else {
                            Log.d(TAG, "No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                    }
                }
            });
        }

        if (applyButton != null) {
            applyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // apply_button을 누를 때 Apply_2.java로 이동
                    Intent intent = new Intent(Apply_1_recycle.this, Apply_2.class);
                    startActivity(intent);
                    bottomSheetDialog.dismiss(); // 바텀 시트 닫기
                }
            });
        }
        bottomSheetDialog.show(); // 바텀 시트를 표시합니다.
    }
}