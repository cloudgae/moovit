package com.example.main_01.shorts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.main_01.R;

public class Tab_fragment1 extends Fragment {

    private ViewPager2 viewPager2;
    private TabFragmentAdapter fragmentAdapter; // TabFragmentAdapter 추가
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        videoView = rootView.findViewById(R.id.videoView);

        viewPager2 = rootView.findViewById(R.id.viewPager2); // ViewPager2 초기화
        fragmentAdapter = new TabFragmentAdapter(getChildFragmentManager(), getLifecycle()); // TabFragmentAdapter 초기화

        // ViewPager2에 Adapter 설정
        viewPager2.setAdapter(fragmentAdapter);

        /*// Amazon S3에서 영상 URL 설정
        String videoURL = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS1.mp4";

        // VideoView에 영상 설정
        videoView.setVideoURI(Uri.parse(videoURL));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();

                // 화면의 높이 가져오기
                int screenHeight = getResources().getDisplayMetrics().heightPixels;

                // 영상 크기를 화면의 높이에 맞게 조정
                ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
                layoutParams.width = (int) ((float) screenHeight / videoHeight * videoWidth);
                layoutParams.height = screenHeight;
                videoView.setLayoutParams(layoutParams);

                // 영상 재생 시작
                videoView.start();
            }
        });*/
        return rootView;
    }

    // 프래그먼트 생성 및 초기화
    public static Tab_fragment1 newInstance(int position) {
        Tab_fragment1 fragment = new Tab_fragment1();
        // 각 프래그먼트에 필요한 정보를 전달하는 방법 (position에 따라 다른 정보 설정)
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }


    public void pauseVideoAndMusic() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
        // 음악 정지 로직 추가
    }

    public void resumeVideoAndMusic() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
        // 음악 재생 로직 추가
    }
}