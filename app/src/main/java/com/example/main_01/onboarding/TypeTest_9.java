package com.example.main_01.onboarding;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.example.main_01.mypage.MyPage;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
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
    ImageButton btnNext;
    Long moover, starter;
    String value4, value5, value6;
    String TCODE; // 클래스의 멤버 변수로 선언

    private SimpleExoPlayer player;
    private com.google.android.exoplayer2.ui.PlayerView playerView1;
    private com.google.android.exoplayer2.ui.PlayerView playerView2;
    private com.google.android.exoplayer2.ui.PlayerView playerView3;

//    private VideoView videoView1;
//    private VideoView videoView2;
//    private VideoView videoView3;
    String videoURL1, videoURL2, videoURL3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test9);

//        ScrollView scrollView = findViewById(R.id.scrollView);
//        scrollView.scrollTo(0, 0);
//        HorizontalScrollView horizontalScrollView = findViewById(R.id.horizontalScrollView);
//        horizontalScrollView.scrollTo(0, 0);

        typename = (TextView) findViewById(R.id.typename);
        typefeature1 = (TextView) findViewById(R.id.typefeature1);
        typefeature2 = (TextView) findViewById(R.id.typefeature2);
        typefeature3 = (TextView) findViewById(R.id.typefeature3);
        typelayer = (LinearLayout) findViewById(R.id.typelayer);
        btnNext = (ImageButton) findViewById(R.id.btnT1_next);

//        videoView1 = findViewById(R.id.player_view1);
//        videoView2 = findViewById(R.id.player_view2);
//        videoView3 = findViewById(R.id.player_view3);
        playerView1 = findViewById(R.id.player_view1);
        playerView2 = findViewById(R.id.player_view2);
        playerView3 = findViewById(R.id.player_view3);

        moover = Long.valueOf(0);
        starter = Long.valueOf(0);
        value4 = ".";
        value5 = ".";
        value6 = ".";
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
                                value4 = (String) document.get("Q4");
                                value5 = (String) document.get("Q5");

                                moover = (Long) document.get("M");
                                starter = (Long) document.get("S");
                                if (moover == null && starter != null) {
                                    value6 = "S";
                                } else if (starter == null && moover != null) {
                                    value6 = "M";
                                } else if (moover != null && starter != null) {
                                    Integer m = Integer.parseInt(String.valueOf(moover));
                                    Integer s = Integer.parseInt(String.valueOf(starter));
                                    if (m > s) {
                                        value6 = "M";
                                    } else if (m == s) {
                                        value6 = "S";
                                    } else {
                                        value6 = "S";
                                    }
                                }
//                                String value6 = (String) document.get("Q6");
                                TCODE = value4 + value5 + value6; // 도출된 유형 코드
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

//                                                        // Firestore에서 영상 이름 가져오기
//                                                        String videoName1 = (String) document.getData().get("video1");
//                                                        String videoName2 = (String) document.getData().get("video2");
//                                                        String videoName3 = (String) document.getData().get("video3");
//
//                                                        // 영상 다운로드 URL 가져오기
//                                                        FirebaseStorage storage = FirebaseStorage.getInstance();
//                                                        StorageReference storageRef = storage.getReference();
//                                                        StorageReference videoRef1 = storageRef.child("videos/" + videoName1);
//                                                        StorageReference videoRef2 = storageRef.child("videos/" + videoName2);
//                                                        StorageReference videoRef3 = storageRef.child("videos/" + videoName3);
//
//                                                        videoRef1.getDownloadUrl().addOnSuccessListener(uri -> {
//                                                            String videoUrl1 = uri.toString();
//                                                            initializePlayer(videoUrl1, playerView1);
//                                                        }).addOnFailureListener(e -> {
//                                                            e.printStackTrace();
//                                                        });
//
//                                                        videoRef2.getDownloadUrl().addOnSuccessListener(uri -> {
//                                                            String videoUrl2 = uri.toString();
//                                                            initializePlayer(videoUrl2, playerView2);
//                                                        }).addOnFailureListener(e -> {
//                                                            e.printStackTrace();
//                                                        });
//
//                                                        videoRef3.getDownloadUrl().addOnSuccessListener(uri -> {
//                                                            String videoUrl3 = uri.toString();
//                                                            initializePlayer(videoUrl3, playerView3);
//                                                        }).addOnFailureListener(e -> {
//                                                            e.printStackTrace();
//                                                        });

                                                        // TCODE에 따라 배경 이미지 설정
                                                        switch (TCODE) {
                                                            case "PCS":
                                                                typelayer.setBackgroundResource(R.drawable.pcs);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PCM":
                                                                typelayer.setBackgroundResource(R.drawable.pcm);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UCS":
                                                                typelayer.setBackgroundResource(R.drawable.ucs);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UCM":
                                                                typelayer.setBackgroundResource(R.drawable.ucm);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PIS":
                                                                typelayer.setBackgroundResource(R.drawable.pis);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PIM":
                                                                typelayer.setBackgroundResource(R.drawable.pim);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UIS":
                                                                typelayer.setBackgroundResource(R.drawable.uis);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UIM":
                                                                typelayer.setBackgroundResource(R.drawable.uim);
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM3.mp4";

                                                                // VideoView에 영상 설정
//                                                                videoView1.setVideoURI(Uri.parse(videoURL1));
//                                                                videoView2.setVideoURI(Uri.parse(videoURL2));
//                                                                videoView3.setVideoURI(Uri.parse(videoURL3));
//                                                                videoView1.start(); // 영상 재생 시작
//                                                                videoView2.start();
//                                                                videoView3.start();
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
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
                Intent i = new Intent(TypeTest_9.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        db.collection("TypeTest")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                value4 = (String) document.get("Q4");
                                value5 = (String) document.get("Q5");

                                moover = (Long) document.get("M");
                                starter = (Long) document.get("S");
                                if (moover == null && starter != null) {
                                    value6 = "S";
                                } else if (starter == null && moover != null) {
                                    value6 = "M";
                                } else if (moover != null && starter != null) {
                                    Integer m = Integer.parseInt(String.valueOf(moover));
                                    Integer s = Integer.parseInt(String.valueOf(starter));
                                    if (m > s) {
                                        value6 = "M";
                                    } else if (m == s) {
                                        value6 = "S";
                                    } else {
                                        value6 = "S";
                                    }
                                }


//                                String value6 = (String) document.get("Q6");
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

                                                        // TCODE에 따라 영상 링크 다르게 설정하여 재생
                                                        switch (TCODE) {
                                                            case "PCS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS3.mp4";


                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PCM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM3.mp4";


                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UCS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS3.mp4";


                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UCM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM3.mp4";


                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PIS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS3.mp4";

                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PIM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM3.mp4";


                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UIS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS3.mp4";


                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UIM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM3.mp4";

                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
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

        // Player.EventListener를 추가하여 영상 재생 상태 변경 이벤트를 감지합니다.
        player.addListener(new Player.EventListener() {
            @Override
            public void onPlaybackStateChanged(int state) {
                // 영상이 시작될 때 재생바를 감추고, 터치해도 나타나지 않도록 설정합니다.
                if (state == Player.STATE_READY) {
                    playerView.hideController();
                    playerView.setUseController(false);
                }

                // 영상이 끝나면 자동으로 반복 재생합니다.
                if (state == Player.STATE_ENDED) {
                    player.seekTo(0); // 영상을 처음으로 되감깁니다.
                    player.setPlayWhenReady(true); // 자동으로 재생합니다.
                }
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release(); // 플레이어 해제
        }
    }


}
