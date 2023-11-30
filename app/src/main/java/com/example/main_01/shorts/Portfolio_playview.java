package com.example.main_01.shorts;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.main_01.R;

public class Portfolio_playview extends AppCompatActivity {

    private VideoView videoView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_playview);

        VideoView videoView = findViewById(R.id.videoview); // 실제 VideoView ID로 교체
        String videoUrl = getIntent().getStringExtra("videoUrl");

        if (videoUrl != null && !videoUrl.isEmpty()) {
            Uri uri = Uri.parse(videoUrl);
            videoView.setVideoURI(uri);
            videoView.start();
        }
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

                // FrameLayout을 사용하여 VideoView을 가운데로 정렬
                FrameLayout frameLayout = findViewById(android.R.id.content); // Use the root view of the activity
                FrameLayout.LayoutParams frameLayoutParams = (FrameLayout.LayoutParams) videoView.getLayoutParams();
                frameLayoutParams.gravity = Gravity.CENTER;
                videoView.setLayoutParams(frameLayoutParams);


                // 영상 재생 시작
                videoView.start();
            }
        });
    }
}
