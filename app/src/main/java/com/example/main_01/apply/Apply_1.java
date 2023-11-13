package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.main_01.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Calendar;

public class Apply_1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply1);

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
                    Toast.makeText(Apply_1.this, "금요일을 선택했습니다.", Toast.LENGTH_SHORT).show();
                    showClassSelectionBottomSheet();
                } else {
                    // 선택한 날짜가 금요일이 아닌 경우 메시지를 표시하거나 선택을 취소할 수 있습니다.
                    Toast.makeText(Apply_1.this, "금요일을 선택해야 합니다.", Toast.LENGTH_SHORT).show();
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

        Button applyButton = bottomSheetDialog.findViewById(R.id.apply_button);

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