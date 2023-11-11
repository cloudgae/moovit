package com.example.main_01.shorts;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.main_01.R;

public class Tab_fragment1 extends Fragment {

    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        videoView = rootView.findViewById(R.id.videoView);

        // Amazon S3에서 영상 URL 설정
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
        });
        return rootView;
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