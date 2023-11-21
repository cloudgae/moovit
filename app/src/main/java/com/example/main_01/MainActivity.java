package com.example.main_01;

import static android.content.ContentValues.TAG;

import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.main_01.Home.Adapter;
import com.example.main_01.Home.Lesson;
import com.example.main_01.Home.LessonAdapter;
import com.example.main_01.Home.Model;
import com.example.main_01.apply.Apply_0;
import com.example.main_01.mypage.MyPage;
import com.example.main_01.shorts.shorts1;
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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.common.primitives.Shorts;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button wc1, wc2, wc3, wc4, wc5, wc6, mn1, mn2, mn3, mn4;
    TextView typenm;
    Button kpopbtn, streetbtn;
    Button menu2;

    LinearLayout typelayer;
    private BottomNavigationView bottomNavigationView;
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

    ImageView class1;
    TextView class1name, class1txt;
    ImageView typeimage, typeimage2;
    TextView typename, typename2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        ImageView typeimage = (ImageView) findViewById(R.id.typeimage);
        TextView typename = (TextView) findViewById(R.id.typename);
        ImageView typeimage2 = (ImageView) findViewById(R.id.typeimage2);
        TextView typename2 = (TextView) findViewById(R.id.typename2);
        /*TextView tn = (TextView) findViewById(R.id.typenameclass);*/

        /*Button mn3 = (Button) findViewById(R.id.menu3);
        Button mn4 = (Button) findViewById(R.id.menu4);*/
        Button kpopbtn = (Button) findViewById(R.id.kpopbtn);
        Button streetbtn = (Button) findViewById(R.id.streetbtn);
        /*Button menu2 = (Button) findViewById(R.id.menu2);*/

        class1 = (ImageView) findViewById(R.id.class1);
        class1name = (TextView) findViewById(R.id.class1name);
        class1txt = (TextView) findViewById(R.id.class1txt);

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
        models.add(new Model(R.drawable.banner_1, "목적지", "어디로 여행을 갈지 골라보자"));
        models.add(new Model(R.drawable.banner_2, "숙소", "근처 숙소들을 찾아보고 숙소 가격을 알아보자"));
        models.add(new Model(R.drawable.banner_3, "장보기", "무엇을 사고 대충 비용이 얼마나 드는지 알아보자"));
        /* models.add(new Model(R.drawable.capuccino, "이동수단", "어떤 이동수단을 사용하고 비용이 얼마나 드는지 알아보자"));
         */

        adapter = new Adapter(models, this);
        List<Model> models;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(80, 250, 70, 0);


        Integer[] colors_temp = {
                R.drawable.banner_bg1,
                R.drawable.banner_bg2,
                R.drawable.banner_bg3
        };
        colors = colors_temp;

        kpopbtn = (Button) findViewById(R.id.kpopbtn);
        Drawable drawable = getResources().getDrawable(R.drawable.kpop_icon);

        // dp 값을 px로 변환
        int widthInDp = 60;
        int heightInDp = 60;
        float scale = getResources().getDisplayMetrics().density;
        int widthInPx = (int) (widthInDp * scale + 0.5f);
        int heightInPx = (int) (heightInDp * scale + 0.5f);

        drawable.setBounds(0, 0, widthInPx, heightInPx);
        kpopbtn.setCompoundDrawables(null, drawable, null, null);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.map:
                        openMap();
                        return true;
                    case R.id.shorts:
                        openShorts();
                        return true;
                    case R.id.mypage:
                        openMyPage();
                        return true;
                }
                return false;
            }
        });

        // onCreate 메서드 내부에서 PlayerView를 초기화한 후에 다음 코드를 추가합니다.
        playerView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 사용자가 영상에 상호 작용할 때만 재생바를 표시합니다.
                playerView1.showController();
                return false;
            }
        });

        playerView2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 사용자가 영상에 상호 작용할 때만 재생바를 표시합니다.
                playerView2.showController();
                return false;
            }
        });

        playerView3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // 사용자가 영상에 상호 작용할 때만 재생바를 표시합니다.
                playerView3.showController();
                return false;
            }
        });

        class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Apply_C7.class);
                startActivity(i);
                finish();
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position < (adapter.getCount() - 1) && position < (colors.length - 1)) {
                    viewPager.setBackgroundResource(colors[position]);
                } else {
                    viewPager.setBackgroundResource(colors[colors.length - 1]);
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
        Query query2 = collectionRef.whereGreaterThanOrEqualTo("hot_rank", 1).whereLessThanOrEqualTo("hot_rank", 6);
        //        Query query1 = collectionRef.whereEqualTo("weekly_rank", 1);


        // 쿼리 실행
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                RecyclerView lessonRecyclerView = findViewById(R.id.lessonRecyclerView);
                List<Lesson> lessons = new ArrayList<>();
                // 이미지 리소스 배열
                int[] imageResources = {R.drawable.c1, R.drawable.c2, R.drawable.c3, R.drawable.c4, R.drawable.c5, R.drawable.c6};

                // 반복문으로 각 문서에 대한 데이터 처리
                int index = 0;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    // 문서 데이터에 접근
                    String documentId = documentSnapshot.getId();
                    String weeklyRank = String.valueOf(documentSnapshot.getLong("weekly_rank")); // 'weekly_rank' 필드 값 가져오기
                    String address = documentSnapshot.getString("location"); // 다른 필드 값 가져오기
                    String name = documentSnapshot.getString("name");
                    String num_review = documentSnapshot.getString("review");
                    String rate = documentSnapshot.getString("rate");
                    boolean isliked = documentSnapshot.getBoolean("like");

                    // 이미지 리소스를 배열에서 가져와서 설정
                    int imageResource = imageResources[index % imageResources.length];

                    lessons.add(new Lesson(weeklyRank, name, address, rate, num_review, imageResource, documentId, isliked));


                    Integer thumb = R.drawable.c1;
                    Log.d("FirestoreData", "Document ID: " + documentId);
                    Log.d("FirestoreData", "Address: " + address);
                    // 이미지 리소스 배열의 인덱스 증가
                    index++;
                }

                LessonAdapter lessonAdapter = new LessonAdapter(lessons, null);

                List<Lesson> rearrangedList = lessonAdapter.rearrangeLessonList(lessons);

                lessonAdapter = new LessonAdapter(rearrangedList, null);

                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                lessonRecyclerView.setLayoutManager(layoutManager);

                lessonAdapter.setOnItemClickListener(new LessonAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Lesson lesson) {
                        // Apply_0 액티비티로 이동하는 Intent 생성
                        Intent intent = new Intent(MainActivity.this, Apply_0.class);
                        // Apply_0 액티비티 시작
                        startActivity(intent);
                    }
                });


                lessonRecyclerView.setAdapter(lessonAdapter);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FirestoreData", "데이터 검색 중 오류 발생: " + e.getMessage());
            }
        });


        // hot_rank 쿼리 실행
        query2.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                RecyclerView lessonRecyclerView2 = findViewById(R.id.lessonRecyclerView2);
                List<Lesson> lessons = new ArrayList<>();
                // 이미지 리소스 배열
                int[] imageResources = {R.drawable.c8, R.drawable.c9, R.drawable.c10, R.drawable.c11, R.drawable.c12, R.drawable.c13};

                // 반복문으로 각 문서에 대한 데이터 처리
                int index = 0;
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    // 문서 데이터에 접근
                    String documentId = documentSnapshot.getId();
                    String weeklyRank = String.valueOf(documentSnapshot.getLong("hot_rank")); // 'hot_rank' 필드 값 가져오기
                    String address = documentSnapshot.getString("location"); // 다른 필드 값 가져오기
                    String name = documentSnapshot.getString("name");
                    String num_review = documentSnapshot.getString("review");
                    String rate = documentSnapshot.getString("rate");
                    boolean isliked = documentSnapshot.getBoolean("like");

                    // 이미지 리소스를 배열에서 가져와서 설정
                    int imageResource = imageResources[index % imageResources.length];

                    lessons.add(new Lesson(weeklyRank, name, address, rate, num_review, imageResource, documentId, isliked));


                    Integer thumb = R.drawable.c1;
                    Log.d("FirestoreData", "Document ID: " + documentId);
                    Log.d("FirestoreData", "Address: " + address);
                    // 이미지 리소스 배열의 인덱스 증가
                    index++;
                }
                LessonAdapter lessonAdapter2 = new LessonAdapter(lessons, null);

                List<Lesson> rearrangedList = lessonAdapter2.rearrangeLessonList(lessons);

                lessonAdapter2 = new LessonAdapter(rearrangedList, null);

                GridLayoutManager layoutManager = new GridLayoutManager(MainActivity.this, 2);
                lessonRecyclerView2.setLayoutManager(layoutManager);
                lessonRecyclerView2.setAdapter(lessonAdapter2);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("FirestoreData", "데이터 검색 중 오류 발생: " + e.getMessage());
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

                                                        typename.setText((String) document.getData().get("name"));
                                                        typename2.setText((String) document.getData().get("name"));
                                                        // TCODE에 따라 영상 링크 다르게 설정하여 재생
                                                        switch (TCODE) {
                                                            case "PCS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCS3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.pcs3);
                                                                typeimage2.setBackgroundResource(R.drawable.pcs3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PCM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PCM3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.pcm3);
                                                                typeimage2.setBackgroundResource(R.drawable.pcm3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UCS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCS3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.ucs3);
                                                                typeimage2.setBackgroundResource(R.drawable.ucs3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UCM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UCM3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.ucm3);
                                                                typeimage2.setBackgroundResource(R.drawable.ucm3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PIS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIS3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.pis3);
                                                                typeimage2.setBackgroundResource(R.drawable.pis3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "PIM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/PIM3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.pim3);
                                                                typeimage2.setBackgroundResource(R.drawable.pim3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UIS":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.uis3);
                                                                typeimage2.setBackgroundResource(R.drawable.uis3);
                                                                initializePlayer(videoURL1, playerView1);
                                                                initializePlayer(videoURL2, playerView2);
                                                                initializePlayer(videoURL3, playerView3);
                                                                break;
                                                            case "UIM":
                                                                // Amazon S3에서 영상 URL 설정
                                                                videoURL1 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM1.mp4";
                                                                videoURL2 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM2.mp4";
                                                                videoURL3 = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIM3.mp4";
                                                                typeimage.setBackgroundResource(R.drawable.uim3);
                                                                typeimage2.setBackgroundResource(R.drawable.uim3);
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

        DocumentReference docRef = db.collection("Class").document("C7");

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    class1name.setText(document.getString("name"));
                    //장르 . 난이도 상/중/하
                    class1txt.setText(document.getString("genre") + "・" +
                            "난이도 " + document.getString("difficulty"));

//                    // AWS S3에서 이미지를 로드하여 이미지뷰에 설정
//                    String imageName = "C7image/C7image"; // S3 버킷 내 이미지 파일의 경로 및 파일명
//                    loadImageFromS3(imageName);
                    String imageUrl = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/C7image/C7image"; // AWS S3 버킷의 이미지 URL로 변경
                    Glide.with(MainActivity.this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true).into(class1);
                }
            }
        });

    }

    private void loadImageFromS3(String imageName) {
        // 디바이스 독립적인 픽셀 (dp)를 픽셀로 변환
        int widthInPixels = (int) (120 * getResources().getDisplayMetrics().density);
        int heightInPixels = (int) (120 * getResources().getDisplayMetrics().density);

        Glide.with(MainActivity.this)
                .load("https://moovitbucket2.s3.amazonaws.com/" + imageName)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        e.printStackTrace(); // 로드 실패 시 오류 메시지 출력
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .centerCrop()
                .override(widthInPixels, heightInPixels)  // 디바이스 독립적인 픽셀로 크기 지정
                .into(class1);
    }

    private void initializePlayer(String videoUrl, com.google.android.exoplayer2.ui.PlayerView playerView) {
        // ExoPlayer 초기화
        SimpleExoPlayer player = new SimpleExoPlayer.Builder(this).build();
//        player = new SimpleExoPlayer.Builder(this).build();
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
        playerView.setUseController(false); // 재생바를 처음부터 숨깁니다.

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

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openMap() {
        Intent intent = new Intent(this, Map_0.class);
        startActivity(intent);
    }

    private void openShorts() {
        Intent intent = new Intent(this, shorts1.class);
        startActivity(intent);
    }


    private void openMyPage() {
        Intent intent = new Intent(this, MyPage.class);
        startActivity(intent);
    }

    public interface OnItemClickListener {
        void onItemClick(Lesson lesson);
    }

}


//1104

/*
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
        });*/


        /*menu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Map_0.class);
                startActivity(i);
                finish();
            }
        });*/

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


