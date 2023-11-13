package com.example.main_01;


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
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.main_01.apply.Apply_0;
import com.example.main_01.apply.Apply_1;
import com.example.main_01.apply.Apply_frag1;
import com.example.main_01.apply.Apply_frag2;
import com.example.main_01.apply.Apply_frag3;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Apply_C7 extends AppCompatActivity {
    ImageButton backbtn, applybtn;
    TextView c7name, c7name2, c7genre, c7diff, c7day, c7loc, c7price;
    ViewPager pager;
    ImageView c7image;

    private FirebaseFirestore db;  // Firestore 인스턴스 선언 추가


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_c7);

        // Firestore 초기화
        db = FirebaseFirestore.getInstance();

        backbtn = (ImageButton) findViewById(R.id.backbtn);
        applybtn = (ImageButton) findViewById(R.id.apply_button);
        c7name = (TextView) findViewById(R.id.c7name);
        c7name2 = (TextView) findViewById(R.id.c7name2);
        c7genre = (TextView) findViewById(R.id.c7genre);
        c7diff = (TextView) findViewById(R.id.c7diff);
        c7day = (TextView) findViewById(R.id.c7day);
        c7loc = (TextView) findViewById(R.id.c7loc);
        c7price = (TextView) findViewById(R.id.c7price);

        c7image = (ImageView) findViewById(R.id.c7image);

        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new Apply_C7.PageAdapter(getSupportFragmentManager(), this));



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
                    c7name.setText(title);
                    c7name2.setText(title);
                    String genre = documentSnapshot.getString("genre");
                    // 다른 필드도 유사하게 가져올 수 있음
                    String diff = documentSnapshot.getString("difficulty");
                    String day = documentSnapshot.getString("frequency");
                    String loc = documentSnapshot.getString("location");
                    c7loc.setText(loc);
                    String price = String.valueOf(documentSnapshot.getLong("fee"));
                    c7price.setText(price + "원");

                    // Firestore 데이터를 기반으로 TextView 업데이트
                    updateTextViews(genre, diff, day /*, 다른 필요한 필드 */);

                }
            }
        });

        // AWS S3에서 이미지를 로드하여 이미지뷰에 설정
        String imageName = "C7image/C7image"; // S3 버킷 내 이미지 파일의 경로 및 파일명
        loadImageFromS3(imageName);

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
            return "상급 난이도";
        } else if ("중".equals(diff)) {
            return "중급 난이도";
        } else if ("하".equals(diff)){
           // 필요한 경우 다른 장르에 대한 매핑 추가
            return "코레오";
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
        return "";// 매핑이 없을 경우 원래의 장르로 기본 설정
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
}