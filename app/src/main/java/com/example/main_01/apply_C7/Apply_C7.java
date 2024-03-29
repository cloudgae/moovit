package com.example.main_01.apply_C7;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.example.main_01.apply.Apply_1;
import com.example.main_01.map.Map_0;
import com.example.main_01.mypage.MyPage;
import com.example.main_01.shorts.shorts1;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Apply_C7 extends AppCompatActivity {
    ImageButton backbtn, applybtn;
    TextView c7name, c7name2, c7genre, c7diff, c7day, c7loc, c7price, dday;
    ViewPager pager;
    ImageView c7image;
    private BottomNavigationView bottomNavigationView;

    private FirebaseFirestore db;  // Firestore 인스턴스 선언 추가


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_c7);

        // Firestore 초기화
        db = FirebaseFirestore.getInstance();
        DocumentReference newClassRef = db.collection("Class").document("C7");
//        새로운 문서의 id 가져오기
        String newClassId = newClassRef.getId();
        Map<String, Object> data = new HashMap<>();

        backbtn = (ImageButton) findViewById(R.id.back_btn);
        applybtn = (ImageButton) findViewById(R.id.apply_button);
//        c7name = (TextView) findViewById(R.id.c7name);
        c7name2 = (TextView) findViewById(R.id.c7name2);
        c7genre = (TextView) findViewById(R.id.c7genre);
        c7diff = (TextView) findViewById(R.id.c7diff);
        c7day = (TextView) findViewById(R.id.c7day);
        c7loc = (TextView) findViewById(R.id.c7loc);
        c7price = (TextView) findViewById(R.id.c7price);

        c7image = (ImageView) findViewById(R.id.c7image);
        dday= (TextView) findViewById(R.id.dday);

        Drawable drawable = getResources().getDrawable(R.drawable.genre_icon);
        // dp 값을 px로 변환
        int widthInDp = 35;
        int heightInDp = 35;
        float scale = getResources().getDisplayMetrics().density;
        int widthInPx = (int) (widthInDp * scale + 0.5f);
        int heightInPx = (int) (heightInDp * scale + 0.5f);
        drawable.setBounds(0, 0, widthInPx, heightInPx);
        c7genre.setCompoundDrawables(null, drawable, null, null);

        Drawable drawable1 = getResources().getDrawable(R.drawable.class_detail_icon3);
        // dp 값을 px로 변환
        int widthInDp1 = 35;
        int heightInDp1 = 35;
        float scale1 = getResources().getDisplayMetrics().density;
        int widthInPx1 = (int) (widthInDp1 * scale1 + 0.5f);
        int heightInPx1 = (int) (heightInDp1 * scale1 + 0.5f);
        drawable1.setBounds(0, 0, widthInPx1, heightInPx1);
        c7day.setCompoundDrawables(null, drawable1, null, null);




        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new Apply_C7.PageAdapter(getSupportFragmentManager(), this));


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
        bottomNavigationView.setSelectedItemId(R.id.home);



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Apply_C7.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Apply_C7.this, Apply_1.class);
                startActivity(i);
                finish();
            }
        });

        // Firestore에서 문서 데이터 가져오기
        DocumentReference docRef = db.collection("Class").document("C7");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Firestore 문서에서 데이터 가져오기
                    String title = documentSnapshot.getString("name");
//                    c7name.setText(title);
                    c7name2.setText(title);
                    String genre = documentSnapshot.getString("genre");
                    // 다른 필드도 유사하게 가져올 수 있음
                    String diff = documentSnapshot.getString("difficulty");
                    String day = documentSnapshot.getString("frequency");
                    String loc = documentSnapshot.getString("location");
                    c7loc.setText(loc);
                    String price = documentSnapshot.getString("price");
                    c7price.setText(price + "원");

                    // Firestore 데이터를 기반으로 TextView 업데이트
                    updateTextViews(genre, diff, day /*, 다른 필요한 필드 */);

                    // 'date' 필드 값 가져오기
                    String dateString = documentSnapshot.getString("date");

                    // dateString을 Date 객체로 변환
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                    Date eventDate = null;
                    try {
                        eventDate = dateFormat.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // 오늘 날짜 가져오기
                    Date today = Calendar.getInstance().getTime();

                    // 디데이 계산
                    int dDay = calculateDDay(today, eventDate);

                    dday.setText("D-" + dDay);

                }
            }
        });

//        // AWS S3에서 이미지를 로드하여 이미지뷰에 설정
//        String imageName = "C7image/C7image"; // S3 버킷 내 이미지 파일의 경로 및 파일명
//        loadImageFromS3(imageName);
        String imageUrl = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/C7image/C7image"; // AWS S3 버킷의 이미지 URL로 변경
        Glide.with(Apply_C7.this).load(imageUrl).diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true).into(c7image);

    }
    private void loadImageFromS3(String imageName) {
        // 디바이스 독립적인 픽셀 (dp)를 픽셀로 변환
        int widthInPixels = (int) (360 * getResources().getDisplayMetrics().density);
        int heightInPixels = (int) (200 * getResources().getDisplayMetrics().density);

        Glide.with(Apply_C7.this)
                .load("https://moovitbucket2.s3.amazonaws.com/" + imageName)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        e.printStackTrace(); // 로드 실패 시 오류 메시지 출력
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .centerCrop()
                .override(widthInPixels, heightInPixels)  // 디바이스 독립적인 픽셀로 크기 지정
                .into(c7image);
    }

    private void updateTextViews(String genre, String diff, String day /*, 필요한 경우 다른 매개변수 추가 */) {
        // Firestore 데이터를 기반으로 TextView 업데이트
        c7genre.setText(mapGenreToDisplayName(genre));
        // 다른 TextView도 유사하게 업데이트
        c7diff.setText(mapDiffToDisplayName(diff));
        c7day.setText(mapDayToDisplayName(day));
    }
    private String mapGenreToDisplayName(String genre) {
        // Firestore 장르를 표시 이름으로 매핑
        if ("kpop".equals(genre)) {
            return "K-POP";
        } else if ("street".equals(genre)) {
            return "스트릿";
        } else if ("choreo".equals(genre)){
            // 필요한 경우 다른 장르에 대한 매핑 추가
            return "코레오";
        }
        return genre;// 매핑이 없을 경우 원래의 장르로 기본 설정
    }
    private String mapDiffToDisplayName(String diff) {
        // Firestore 장르를 표시 이름으로 매핑
        if ("상".equals(diff)) {
            return "난이도 상";
        } else if ("중".equals(diff)) {
            return "난이도 중";
        } else if ("하".equals(diff)){
           // 필요한 경우 다른 장르에 대한 매핑 추가
            return "난이도 하";
        }
        return diff;// 매핑이 없을 경우 원래의 장르로 기본 설정
    }
    private String mapDayToDisplayName(String day) {
        // Firestore 장르를 표시 이름으로 매핑
        if ("1".equals(day)) {
            return "원데이 클래스";
        } else if ("n".equals(day)) {
            return "다회차 클래스";
        }
        return day;// 매핑이 없을 경우 원래의 장르로 기본 설정
    }

    static class PageAdapter extends FragmentStatePagerAdapter {
        PageAdapter(FragmentManager fm, Context context) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new C7_tab1();
            } else if (position == 1) {
                return new C7_tab2();
            } else {
                return new C7_tab3();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }


        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "상세정보";
            } else if (position == 1) {
                return "후기";
            } else {
                return "Q&A";
            }
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

    private int calculateDDay(Date today, Date eventDate) {
        // 디데이 계산 (오늘 날짜와 이벤트 날짜의 차이를 일수로 계산)
        long diffInMillies = eventDate.getTime() - today.getTime();
        long diffInDays = diffInMillies / (24 * 60 * 60 * 1000);
        return (int) (diffInDays + 1); // 디데이는 오늘 포함
    }
}