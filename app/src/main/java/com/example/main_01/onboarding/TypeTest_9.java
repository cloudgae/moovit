package com.example.main_01.onboarding;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main_01.R;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.main_01.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


public class TypeTest_9 extends AppCompatActivity {

    TextView typename, typefeature1, typefeature2, typefeature3;
    LinearLayout typelayer;
    Button btnNext;

    private SimpleExoPlayer player;
    private com.google.android.exoplayer2.ui.PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test9);

        typename = (TextView) findViewById(R.id.typename);
        typefeature1 = (TextView) findViewById(R.id.typefeature1);
        typefeature2 = (TextView) findViewById(R.id.typefeature2);
        typefeature3 = (TextView) findViewById(R.id.typefeature3);
        typelayer = (LinearLayout) findViewById(R.id.typelayer);
        btnNext = (Button) findViewById(R.id.btnT1_next);

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
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                String value4 = (String) document.get("Q4");
                                String value5 = (String) document.get("Q5");
                                String value6 = (String) document.get("Q6");
                                String TCODE = value4 + value5 + value6; //도출된 유형 코드
                                Log.d(TAG, TCODE);

                                //세자리수 코드와 같은 값을 가진 유형 타입을 조회
                                db.collection("Type")
                                        .whereEqualTo("code",TCODE )
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        String finTYPE = document.getId();
                                                        Log.d(TAG, finTYPE);
//                                                        Log.d(TAG, document.getId() + " => " + document.getData());

                                                        //텍스트 바꾸는 작업
                                                        //유형 이름
                                                        typename.setText((String) document.getData().get("name"));
                                                        //유형 설명
                                                        typefeature1.setText((String) document.getData().get("feature1"));
                                                        typefeature2.setText((String) document.getData().get("feature2"));
                                                        typefeature3.setText((String) document.getData().get("feature3"));

                                                        switch (TCODE){
                                                            case "PCS":
                                                                typelayer.setBackgroundResource(R.drawable.pcs);
                                                                break;
                                                            case "PCM":
                                                                typelayer.setBackgroundResource(R.drawable.pcm);
                                                                break;
                                                            case "UCS":
                                                                typelayer.setBackgroundResource(R.drawable.ucs);
                                                                break;
                                                            case "UCM":
                                                                typelayer.setBackgroundResource(R.drawable.ucm);
                                                                break;
                                                            case "PIS":
                                                                typelayer.setBackgroundResource(R.drawable.pis);
                                                                break;
                                                            case "PIM":
                                                                typelayer.setBackgroundResource(R.drawable.pim);
                                                                break;
                                                            case "UIS":
                                                                typelayer.setBackgroundResource(R.drawable.uis);
                                                                break;
                                                            case "UIM":
                                                                typelayer.setBackgroundResource(R.drawable.uim);
                                                                break;
                                                        }

                                                    }
                                                } else {
                                                    Log.d(TAG, "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });


        //다음코드 여기서부터..

        playerView = findViewById(R.id.player_view);

        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
        DocumentReference docRef = db1.collection("Class").document("test");

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            // 필드에서 영상 이름 가져오기
            String videoName = documentSnapshot.getString("name");
            // 가져온 영상 이름을 사용하여 Storage에서 참조 생성
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference videoRef = storageRef.child("videos/" + videoName);

            videoRef.getDownloadUrl().addOnSuccessListener(uri -> {
                // URL을 사용하여 영상 로드 및 표시
                String videoUrl = uri.toString();
                initializePlayer(videoUrl);
            }).addOnFailureListener(e -> {
                // 영상 URL을 가져오는 도중 에러 발생
                // TODO: 에러 처리
            });
        }).addOnFailureListener(e -> {
            // Firestore에서 데이터 가져오는 도중 에러 발생
            // TODO: 에러 처리
        });
    }

    private void initializePlayer(String videoUrl) {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        // 미디어 소스 생성
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl));

        // 플레이어에 미디어 소스 설정
        player.setMediaSource(mediaSource);
        player.prepare();
        player.setPlayWhenReady(true); // 재생 시작
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release(); // 플레이어 해제
        }
    }


//        btnNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(TypeTest_9.this, TypeTest_11.class);
//
//                startActivity(i);	//intent 에 명시된 액티비티로 이동
//
//                finish();	//현재 액티비티 종료
//            }
//        });

//    }
}