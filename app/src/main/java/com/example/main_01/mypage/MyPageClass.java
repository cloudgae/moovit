package com.example.main_01.mypage;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class MyPageClass extends AppCompatActivity {

    Button arw;
    TextView n1,n2,n3,l1,l2,l3;
    ImageButton i1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_class);

        Button arw = (Button) findViewById(R.id.goback);

        n1 = (TextView) findViewById(R.id.name1);
        n2 = (TextView) findViewById(R.id.name2);
        n3 = (TextView) findViewById(R.id.name3);
        l1 = (TextView) findViewById(R.id.location1);
        l2 = (TextView) findViewById(R.id.location2);
        l3 = (TextView) findViewById(R.id.location3);
        i1 = (ImageButton) findViewById(R.id.image1);

        arw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPageClass.this, MyPage.class);
                startActivity(i);
                finish();
            }
        });

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        for (int i = 1; i <= 6; i++) {
            final int number = i; // 주간 값

            db.collection("Danceclass")
                    .whereEqualTo("no", number)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String finTYPE = document.getId();
                                    Log.d(TAG, finTYPE);

                                    // 텍스트 바꾸는 작업
                                    String title = (String) document.getData().get("name");
                                    String location = (String) document.getData().get("location");

                                    // 해당 주간 값에 맞는 버튼을 찾아서 텍스트 설정
                                    switch (number) {
                                        case 1:
                                            n1.setText(title);
                                            l1.setText(location);
                                            break;
                                        case 2:
                                            n2.setText(title);
                                            l2.setText(location);
                                            break;
                                        case 3:
                                            n3.setText(title);
                                            l3.setText(location);
                                            break;
                                    }

                                    // 이후 작업을 수행하거나 버튼을 클릭하는 등의 추가 동작을 여기에 추가할 수 있습니다.
                                }
                            }
                        }
                    });
        }
    }
}