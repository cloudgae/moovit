package com.example.main_01.onboarding;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;

public class TypeTest_8 extends AppCompatActivity {

    //private DocumentReference dbref;
    public String TCODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test8);

//파이어베이스
        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        //User도큐먼트 참조 생성
//        DocumentReference dbref = db.collection("TypeTest").document("User");

        //응답456 조회+연결해서 유형을 세자리수 코드로 로그에 출력
        db.collection("TypeTest")
                .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if(task.isSuccessful()){
                                    for (QueryDocumentSnapshot document : task.getResult()){
//                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        String value4 = document.get("Q4").toString();
                                        String value5 = document.get("Q5").toString();
                                        String value6 = document.get("Q6").toString();
                                        Log.d(TAG, value4+value5+value6);
                                        TCODE = value4 + value5 + value6;
                                        Log.d(TAG, TCODE);
                                    }
                                    }else{
                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                }
                            }
                        });

        //세자리수 코드값과 동일한 값을 Type컬렉션에서 조회해보려 했는데 이 부분이 잘 안됨..
        db.collection("Type")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                if(document.get("code").toString() != TCODE)
                                {
                                    String value = TCODE;
                                    Log.d(TAG, value);
                                }
//                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        }else{
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        moveNext(1);
    }

    private void moveNext(int sec) {
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                //new Intent(현재 context, 이동할 activity)
                Intent i = new Intent(TypeTest_8.this, TypeTest_9.class);

                startActivity(i);	//intent 에 명시된 액티비티로 이동

                finish();	//현재 액티비티 종료
            }
        }, 1500 * sec); // sec초 정도 딜레이를 준 후 시작
    }
}