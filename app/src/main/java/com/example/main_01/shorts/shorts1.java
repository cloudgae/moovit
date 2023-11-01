package com.example.main_01.shorts;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.main_01.R;
import com.google.android.material.tabs.TabLayout;

public class shorts1 extends AppCompatActivity {

    private Tab_fragment1 fragment1;
    private Tab_fragment2 fragment2;
    private int currentTab = 1;

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
}



/*public class shorts1 extends AppCompatActivity {

    private VideoView videoView;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts1);

        videoView = findViewById(R.id.videoView);

        // 파이어베이스 스토리지 참조 생성
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // 파이어베이스 스토리지에서 영상 다운로드 URL 가져오기
        StorageReference videoRef = storageRef.child("videos/PCM1.mp4"); // 영상 파일의 경로를 지정해주세요

        videoRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri videoUri = task.getResult();

                    // VideoView에 영상 설정
                    videoView.setVideoURI(videoUri);
                    videoView.start(); // 영상 재생 시작
                } else {
                    // 영상 URL 가져오기 실패
                }
            }
        });




    }





        }*/

