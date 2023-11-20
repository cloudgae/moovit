package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.main_01.Home.Lesson;
import com.example.main_01.MainActivity;
import com.example.main_01.Map_0;
import com.example.main_01.R;
import com.example.main_01.mypage.MyPage;
import com.example.main_01.shorts.shorts1;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class Apply_0 extends AppCompatActivity {

    ImageButton applybtn;
    ImageButton backbtn;

    ViewPager pager;
    TextView genre;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply0);

        ImageButton applybtn = (ImageButton) findViewById(R.id.apply_button);
        ImageButton backbtn = (ImageButton) findViewById(R.id.back_btn);

        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager);
        /*pager.setAdapter(new PageAdapter(getSupportFragmentManager(), this));
*/
        /*// 인텐트에서 선택된 Lesson 객체를 가져옵니다.
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedLesson")) {
            Lesson selectedLesson = (Lesson) intent.getSerializableExtra("selectedLesson");

            // 이제 선택된 Lesson의 정보로 레이아웃을 채울 수 있습니다.
            ImageView imageView = findViewById(R.id.image);
            TextView nameTextView = findViewById(R.id.name);
            RatingBar ratingbar = findViewById(R.id.ratingbar);
            TextView rateTextView = findViewById(R.id.rate);
            TextView locationTextView = findViewById(R.id.loc);

            // 값 설정
            imageView.setImageResource(selectedLesson.getImageResource());
            nameTextView.setText(selectedLesson.getName());
            ratingbar.setRating(Float.parseFloat(selectedLesson.getRate()));
            rateTextView.setText(selectedLesson.getRate());
            locationTextView.setText(selectedLesson.getAddress());*/

            // 필요한 경우 다른 UI 요소를 추가합니다.

        genre = (TextView) findViewById(R.id.genre);
        Drawable drawable = getResources().getDrawable(R.drawable.genre_icon);

        // dp 값을 px로 변환
        int widthInDp = 35;
        int heightInDp = 35;
        float scale = getResources().getDisplayMetrics().density;
        int widthInPx = (int) (widthInDp * scale + 0.5f);
        int heightInPx = (int) (heightInDp * scale + 0.5f);

        drawable.setBounds(0, 0, widthInPx, heightInPx);
        genre.setCompoundDrawables(null, drawable, null, null);

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

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Apply_0.this, Apply_1.class);
                startActivity(i);
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Apply_0.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    static class PageAdapter extends FragmentStatePagerAdapter {
        PageAdapter(FragmentManager fm, Context context) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Apply_frag1();
            } else if (position == 1) {
                return new Apply_frag2();
            } else {
                return new Apply_frag3();
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



}
//
//    private class pagerAdapter extends FragmentStatePagerAdapter{
//        public pagerAdapter(FragmentManager fm){
//            super(fm);
//        }
//        public Fragment getItem(int position){
//            switch (position){
//                case 0:
//                    return new Apply_frag1();
//                case 1:
//                    return new Apply_frag2();
//                case 2:
//                    return new Apply_frag3();
//                default:
//                    return null;
//            }
//        }
//        public int getCount(){
//            return 3;
//        }
//    }
