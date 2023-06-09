package com.example.main_01.onboarding;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.main_01.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TypeTest_9 extends AppCompatActivity {
    TextView typename, typefeature1, typefeature2, typefeature3;
    LinearLayout typelayer;
    Button btnNext;

    private SimpleExoPlayer player;
    private com.google.android.exoplayer2.ui.PlayerView playerView1;
    private com.google.android.exoplayer2.ui.PlayerView playerView2;
    private com.google.android.exoplayer2.ui.PlayerView playerView3;

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

        playerView1 = findViewById(R.id.player_view1);
        playerView2 = findViewById(R.id.player_view2);
        playerView3 = findViewById(R.id.player_view3);

        // 파이어베이스
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // 응답456 조회+연결해서 유형을 세자리수 코드로 로그에 출력
        db.collection("TypeTest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String value4 = (String) document.get("Q4");
                                String value5 = (String) document.get("Q5");
                                String value6 = (String) document.get("Q6");
                                String TCODE = value4 + value5 + value6; // 도출된 유형 코드
                                Log.d(TAG, TCODE);

                                // 세자리수 코드와 같은 값을 가진 유형 타입을 조회
                                db.collection("Type")
                                        .whereEqualTo("code", TCODE)
                                        .get()
                                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                        String finTYPE = document.getId();
                                                        Log.d(TAG, finTYPE);

                                                        // 텍스트 바꾸는 작업
                                                        // 유형 이름
                                                        typename.setText((String) document.getData().get("name"));
                                                        // 유형 설명
                                                        typefeature1.setText((String) document.getData().get("feature1"));
                                                        typefeature2.setText((String) document.getData().get("feature2"));
                                                        typefeature3.setText((String) document.getData().get("feature3"));

                                                        // Firestore에서 영상 이름 가져오기
                                                        String videoName1 = (String) document.getData().get("video1");
                                                        String videoName2 = (String) document.getData().get("video2");
                                                        String videoName3 = (String) document.getData().get("video3");

                                                        // 영상 다운로드 URL 가져오기
                                                        FirebaseStorage storage = FirebaseStorage.getInstance();
                                                        StorageReference storageRef = storage.getReference();
                                                        StorageReference videoRef1 = storageRef.child("videos/" + videoName1);
                                                        StorageReference videoRef2 = storageRef.child("videos/" + videoName2);
                                                        StorageReference videoRef3 = storageRef.child("videos/" + videoName3);

                                                        videoRef1.getDownloadUrl().addOnSuccessListener(uri -> {
                                                            String videoUrl1 = uri.toString();
                                                            initializePlayer(videoUrl1, playerView1);
                                                        }).addOnFailureListener(e -> {
                                                            e.printStackTrace();
                                                        });

                                                        videoRef2.getDownloadUrl().addOnSuccessListener(uri -> {
                                                            String videoUrl2 = uri.toString();
                                                            initializePlayer(videoUrl2, playerView2);
                                                        }).addOnFailureListener(e -> {
                                                            e.printStackTrace();
                                                        });

                                                        videoRef3.getDownloadUrl().addOnSuccessListener(uri -> {
                                                            String videoUrl3 = uri.toString();
                                                            initializePlayer(videoUrl3, playerView3);
                                                        }).addOnFailureListener(e -> {
                                                            e.printStackTrace();
                                                        });

                                                        // TCODE에 따라 배경 이미지 설정
                                                        switch (TCODE) {
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

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(TypeTest_9.this, TypeTest_11.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void initializePlayer(String videoUrl, com.google.android.exoplayer2.ui.PlayerView playerView) {
        // ExoPlayer 초기화
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
        player.setVolume(0f);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release(); // 플레이어 해제
        }
    }
}
