package com.example.main_01.mypage;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

public class MyPageClass extends AppCompatActivity {

    Button arw;
    TextView n1,n2,n3,l1,l2,l3;
    ImageButton i1;
    private MyPageApplyclassAdapter adapter; // 어댑터 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_page_class);

        Button arw = (Button) findViewById(R.id.goback);
        arw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyPageClass.this, MyPage.class);
                startActivity(i);
                finish();
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Firestore 인스턴스 가져오기
        FirebaseFirestore db = FirebaseFirestore.getInstance();

// 'Class' 컬렉션에서 'mozip' 필드 값이 문자열 '0'과 일치하지 않는 문서 가져오기
        db.collection("Class")
                .whereNotEqualTo("mozip", "0")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<MyPageApplyclass> itemList = new ArrayList<>();

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // 각 문서에서 필요한 데이터 추출
                                String name = document.getString("name");
                                String location = document.getString("location");
                                String daytime = document.getString("date") + "  " +  document.getString("starttime") +
                                        " ~ " + document.getString("endtime");

                                // 썸네일 이미지 리소스 이름 가져오기
                                String thumbnailResourceName = document.getString("thumbnailResourceName");
                                // MyItem 객체 생성 및 리스트에 추가
                                MyPageApplyclass item = new MyPageApplyclass(thumbnailResourceName, name, location, daytime);
                                itemList.add(item);
                            }

                            // 어댑터 초기화
                            // 어댑터 초기화
                            adapter = new MyPageApplyclassAdapter(itemList, MyPageClass.this); // MyPageClass의 context 전달

                            // 어댑터에 데이터 설정
                            adapter.setItems(itemList);

                            // RecyclerView 설정
                            recyclerView.setAdapter(adapter);

                            adapter.notifyDataSetChanged();
                        } else {
                            // 실패 처리
                            Log.e(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });




    }

    private int getResourceId(String resourceName, String resourceType) {
        return getResources().getIdentifier(resourceName, resourceType, getPackageName());
    }

}