package com.example.main_01;

import static android.content.ContentValues.TAG;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.main_01.Home.Adapter;
import com.example.main_01.Home.Model;
import com.example.main_01.apply.Apply_0;
import com.example.main_01.mypage.MyPage;
import com.example.main_01.shorts.shorts1;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    Button wc1, wc2, wc3, wc4, wc5, wc6, mn1, mn2, mn3, mn4;
    TextView typenm;
    Button kpopbtn, streetbtn;
    Button menu2;

    LinearLayout typelayer;
    private SimpleExoPlayer player;
    private com.google.android.exoplayer2.ui.PlayerView playerView1;
    private com.google.android.exoplayer2.ui.PlayerView playerView2;
    private com.google.android.exoplayer2.ui.PlayerView playerView3;
    Long moover, starter;
    String value4, value5, value6;
//    private VideoView videoView1;
//    private VideoView videoView2;
//    private VideoView videoView3;
    String videoURL1, videoURL2, videoURL3;


    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextView tn = (TextView) findViewById(R.id.typenameclass);

        Button mn3 = (Button) findViewById(R.id.menu3);
        Button mn4 = (Button) findViewById(R.id.menu4);
        Button kpopbtn = (Button) findViewById(R.id.kpopbtn);
        Button streetbtn = (Button) findViewById(R.id.streetbtn);
        Button menu2 = (Button) findViewById(R.id.menu2);


        playerView1 = findViewById(R.id.player_view1);
        playerView2 = findViewById(R.id.player_view2);
        playerView3 = findViewById(R.id.player_view3);
        moover = Long.valueOf(0);
        starter = Long.valueOf(0);
        value4 = ".";
        value5 = ".";
        value6 = ".";

        videoURL1 = ".";
        videoURL2 = ".";
        videoURL3 = ".";
        //지금 가장 관심도가 높은 클래스 리사이클러뷰

        models = new ArrayList<>();
        models.add(new Model(R.drawable.banner1, "목적지", "어디로 여행을 갈지 골라보자"));
        models.add(new Model(R.drawable.background_example, "숙소", "근처 숙소들을 찾아보고 숙소 가격을 알아보자"));
        models.add(new Model(R.drawable.thumbnail, "장보기", "무엇을 사고 대충 비용이 얼마나 드는지 알아보자"));
       /* models.add(new Model(R.drawable.capuccino, "이동수단", "어떤 이동수단을 사용하고 비용이 얼마나 드는지 알아보자"));
*/

        adapter = new Adapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 300, 100, 0);

        Integer[] colors_temp = {
                getResources().getColor(R.color.color1),
                getResources().getColor(R.color.color2),
                getResources().getColor(R.color.color3),
                getResources().getColor(R.color.color4)
        };
        colors = colors_temp;


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position < (adapter.getCount() -1) && position < (colors.length -1)){
                    viewPager.setBackgroundColor((Integer) argbEvaluator.evaluate(positionOffset,
                            colors[position], colors[position + 1]));
                }
                else{
                    viewPager.setBackgroundColor(colors[colors.length - 1]);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // Firestore 쿼리 생성
        CollectionReference collectionRef = db.collection("Class");
        Query query = collectionRef.whereGreaterThanOrEqualTo("weekly_rank", 1).whereLessThanOrEqualTo("weekly_rank", 6);
//        Query query1 = collectionRef.whereEqualTo("weekly_rank", 1);

        // 쿼리 실행
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                RecyclerView lessonRecyclerView = findViewById(R.id.lessonRecyclerView);
                List<Lesson> lessons = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    // 문서 데이터에 접근
                    String documentId = documentSnapshot.getId();
                    String weeklyRank = String.valueOf(documentSnapshot.getLong("weekly_rank")); // 'weekly_rank' 필드 값 가져오기
                    String address = documentSnapshot.getString("location"); // 다른 필드 값 가져오기
                    String name = documentSnapshot.getString("name");
                    String num_review = documentSnapshot.getString("review");
                    String rate = documentSnapshot.getString("rate");
                    boolean isliked = documentSnapshot.getBoolean("like");

//                    Integer thumb = R.drawable.c1;
                    Log.d("FirestoreData", "Document ID: " + documentId);
                    Log.d("FirestoreData", "Address: " + address);

                    lessons.add(new Lesson(weeklyRank, name, address, rate, num_review, R.drawable.c1, documentId, isliked));


                }
                LessonAdapter lessonAdapter = new LessonAdapter(lessons, null);

                List<Lesson> rearrangedList = lessonAdapter.rearrangeLessonList(lessons);

                lessonAdapter = new LessonAdapter(rearrangedList, null);

                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                lessonRecyclerView.setLayoutManager(layoutManager);
                lessonRecyclerView.setAdapter(lessonAdapter);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FirestoreData", "데이터 검색 중 오류 발생: " + e.getMessage());
            }
        });

        //1104


        mn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, shorts1.class);
                startActivity(i);
                finish();
            }
        });

        mn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, MyPage.class);
                startActivity(i);
                finish();
            }
        });

        kpopbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Apply_0.class);
                startActivity(i);
                finish();
            }
        });

        streetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Map_0.class);
                startActivity(i);
                finish();
            }
        });

        menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Map_0.class);
                startActivity(i);
                finish();
            }
        });

        ////        TODO 1)파이어베이스 연결 - 수업 정보  2)유형 정보-영상 연결



//        탭 연결
        /*TabHost tabHost = getTabHost();

        TabHost.TabSpec tabSpecClass = tabHost.newTabSpec("Class").setIndicator("클래스");
        tabSpecClass.setContent(R.id.tabclass);
        tabHost.addTab(tabSpecClass);

        TabHost.TabSpec tabSpecDancer = tabHost.newTabSpec("Dancer").setIndicator("댄서");
        tabSpecDancer.setContent(R.id.tabdancer);
        tabHost.addTab(tabSpecDancer);

        tabHost.setCurrentTab(0);*/

        //유형별 영상

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
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release(); // 플레이어 해제
        }
    }

}