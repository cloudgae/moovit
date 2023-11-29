package com.example.main_01.shorts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.main_01.MainActivity;
import com.example.main_01.map.Map_0;
import com.example.main_01.R;
import com.example.main_01.mypage.MyPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class shorts1 extends AppCompatActivity {

    private Tab_fragment1 fragment1;
    private Tab_fragment2 fragment2;
    private int currentTab = 1;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts1);

        fragment1 = new Tab_fragment1();
        fragment2 = new Tab_fragment2();

        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        pager.setOffscreenPageLimit(2);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), this));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openMainActivity();
                        return true;
                    case R.id.map:
                        openMap();
                        return true;
                    case R.id.shorts:
                        return true;
                    case R.id.mypage:
                        openMyPage();
                        return true;
                }
                return false;
            }
        });

// 초기 화면 설정
        bottomNavigationView.setSelectedItemId(R.id.shorts);

    }

    private void switchTab(int tab) {
        if (currentTab == tab) {
            return;
        }

        if (currentTab == 1) {
            fragment1.pauseVideoAndMusic();
        } else if (currentTab == 2) {
            fragment2.pauseVideoAndMusic();
        }

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, tab == 1 ? fragment1 : fragment2)
                .commit();

        if (tab == 1) {
            fragment1.resumeVideoAndMusic();
        } else if (tab == 2) {
            fragment2.resumeVideoAndMusic();
        }

        currentTab = tab;
    }

    private void onTab1Clicked() {
        switchTab(1);
    }

    private void onTab2Clicked() {
        switchTab(2);
    }

    static class PageAdapter extends FragmentStatePagerAdapter {

        PageAdapter(FragmentManager fm, Context context) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Tab_fragment1();
            } else {
                return new Tab_fragment2();
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "클래스";
            } else {
                return "댄서";
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
