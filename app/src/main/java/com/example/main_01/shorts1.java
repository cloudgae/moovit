package com.example.main_01;
import static androidx.fragment.app.FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

import android.content.Context;
import android.os.Bundle;
/*import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;*/

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class shorts1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shorts1);


        ViewPager pager = findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);


        pager.setOffscreenPageLimit(2); //현재 페이지의 양쪽에 보유해야하는 페이지 수를 설정 (상황에 맞게 사용하시면 됩니다.)
        tabLayout.setupWithViewPager(pager); //텝레이아웃과 뷰페이저를 연결
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),this)); //뷰페이저 어뎁터 설정 연결


    }
    static class PageAdapter extends FragmentStatePagerAdapter { //뷰 페이저 어뎁터


        PageAdapter(FragmentManager fm, Context context) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        }


        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) { //프래그먼트 사용 포지션 설정 0 이 첫탭
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
            if (position == 0) { //텝 레이아웃의 타이틀 설정
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

