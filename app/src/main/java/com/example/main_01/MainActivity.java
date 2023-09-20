package com.example.main_01;

import static android.content.ContentValues.TAG;

import android.app.TabActivity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;


@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {

    Button wc1, wc2, wc3, wc4, wc5, wc6;
    TextView typenm;

    LinearLayout typelayer;
    private SimpleExoPlayer player;
    private com.google.android.exoplayer2.ui.PlayerView playerView1;
    private com.google.android.exoplayer2.ui.PlayerView playerView2;
    private com.google.android.exoplayer2.ui.PlayerView playerView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tn = (TextView) findViewById(R.id.typenameclass);

//        주간 인기 클래스 버튼 TODO : 클래스 연결 시 텍스트, drawable 변경
        Button wc1 = (Button) findViewById(R.id.weeklyclass_1);
        Button wc2 = (Button) findViewById(R.id.weeklyclass_2);
        Button wc3 = (Button) findViewById(R.id.weeklyclass_3);
        Button wc4 = (Button) findViewById(R.id.weeklyclass_4);
        Button wc5 = (Button) findViewById(R.id.weeklyclass_5);
        Button wc6 = (Button) findViewById(R.id.weeklyclass_6);

        playerView1 = findViewById(R.id.player_view1);
        playerView2 = findViewById(R.id.player_view2);
        playerView3 = findViewById(R.id.player_view3);

        ////        TODO 1)파이어베이스 연결 - 수업 정보  2)유형 정보-영상 연결

        FirebaseFirestore db = FirebaseFirestore.getInstance();

//        탭 연결
        TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecClass = tabHost.newTabSpec("Class").setIndicator("클래스");
        tabSpecClass.setContent(R.id.tabclass);
        tabHost.addTab(tabSpecClass);

        TabHost.TabSpec tabSpecDancer = tabHost.newTabSpec("Dancer").setIndicator("댄서");
        tabSpecDancer.setContent(R.id.tabdancer);
        tabHost.addTab(tabSpecDancer);

        tabHost.setCurrentTab(0);

        //유형별 영상

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

                                                        String typetxt = (String) document.getData().get("name");
                                                        tn.setText(typetxt + "를 위한 클래스");

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

        //파이어베이스에서 수업 정보 조회
        // 파이어베이스에서 WEEKLY 값이 1부터 6까지인 데이터를 불러오는 쿼리
        for (int i = 1; i <= 6; i++) {
            final int weeklyValue = i; // 주간 값

            db.collection("Class")
                    .whereEqualTo("WEEKLY", weeklyValue)
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    String finTYPE = document.getId();
                                    Log.d(TAG, finTYPE);

                                    // 텍스트 바꾸는 작업
                                    String title = (String) document.getData().get("TITLE");

                                    // 해당 주간 값에 맞는 버튼을 찾아서 텍스트 설정
                                    switch (weeklyValue) {
                                        case 1:
                                            wc1.setText("    " + title + "");
                                            break;
                                        case 2:
                                            wc2.setText("    " + title + "");
                                            break;
                                        case 3:
                                            wc3.setText("    " + title + "");
                                            break;
                                        case 4:
                                            wc4.setText("    " + title + "");
                                            break;
                                        case 5:
                                            wc5.setText("    " + title + "");
                                            break;
                                        case 6:
                                            wc6.setText("    " + title + "");
                                            break;
                                    }

                                    // 이후 작업을 수행하거나 버튼을 클릭하는 등의 추가 동작을 여기에 추가할 수 있습니다.
                                }
                            }
                        }
                    });
        }

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