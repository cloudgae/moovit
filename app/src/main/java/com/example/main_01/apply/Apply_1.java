package com.example.main_01.apply;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;

public class Apply_1 extends AppCompatActivity {
    ImageView image;
    TextView name, txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply1);

        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
        txt = (TextView) findViewById(R.id.txt);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        DocumentReference docRef = db.collection("Class").document("C7");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    name.setText(document.getString("name"));
                    //장르 . 난이도 상/중/하
                    txt.setText(document.getString("genre") + "・" +
                            "난이도 " + document.getString("difficulty"));

//                    // AWS S3에서 이미지를 로드하여 이미지뷰에 설정
//                    String imageName = "C7image/C7image"; // S3 버킷 내 이미지 파일의 경로 및 파일명
//                    loadImageFromS3(imageName);
                    String imageUrl = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/C7image/C7image"; // AWS S3 버킷의 이미지 URL로 변경
                    Glide.with(Apply_1.this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true).into(image);
                }
            }
        });


        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar selectedDate = Calendar.getInstance();
                selectedDate.set(year, month, dayOfMonth);

                // 선택한 날짜가 금요일인지 확인합니다.
                if (selectedDate.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
                    // 선택한 날짜가 금요일인 경우 원하는 동작을 수행합니다.
                    // 여기에 코드를 추가하세요.
                    /*Toast.makeText(Apply_1.this, "금요일을 선택했습니다.", Toast.LENGTH_SHORT).show();*/
                    showClassSelectionBottomSheet();
                } else {
                    // 선택한 날짜가 금요일이 아닌 경우 메시지를 표시하거나 선택을 취소할 수 있습니다./**/
                    /*Toast.makeText(Apply_1.this, "금요일을 선택해야 합니다.", Toast.LENGTH_SHORT).show();*/
                }
            }
        });

        // CalendarView에서 현재 날짜를 기본 선택합니다.
        Calendar currentDate = Calendar.getInstance();
        calendarView.setDate(currentDate.getTimeInMillis());
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
                    Intent intent = new Intent(Apply_1.this, Apply_2.class);
                    startActivity(intent);
                    bottomSheetDialog.dismiss(); // 바텀 시트 닫기
                }
            });
        }
        bottomSheetDialog.show(); // 바텀 시트를 표시합니다.
    }
}