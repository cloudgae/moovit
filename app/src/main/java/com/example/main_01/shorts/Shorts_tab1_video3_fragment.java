package com.example.main_01.shorts;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.main_01.R;

import java.io.IOException;

public class Shorts_tab1_video3_fragment extends Fragment implements TextureView.SurfaceTextureListener{

    private TextureView textureView;
    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shorts_tab1_video3, container, false);
        textureView = rootView.findViewById(R.id.textureView1);

        // Amazon S3에서 영상 URL 설정
        String videoURL = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS3.mp4";

        // TextureView에 SurfaceTextureListener 설정
        textureView.setSurfaceTextureListener(this);

        return rootView;
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface videoSurface = new Surface(surface);

        try {
            mediaPlayer = new MediaPlayer(); // 클래스 레벨 mediaPlayer 사용
            mediaPlayer.setDataSource("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS3.mp4");
            mediaPlayer.setSurface(videoSurface);
            mediaPlayer.setLooping(true);
            mediaPlayer.prepareAsync();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int videoWidth = mp.getVideoWidth();
                    int videoHeight = mp.getVideoHeight();

                    // 화면의 높이 가져오기
                    int screenHeight = getResources().getDisplayMetrics().heightPixels;

                    // 비디오 크기를 화면의 높이에 맞게 조정
                    ViewGroup.LayoutParams layoutParams = textureView.getLayoutParams();
                    layoutParams.width = (int) ((float) screenHeight / videoHeight * videoWidth);
                    layoutParams.height = screenHeight;
                    textureView.setLayoutParams(layoutParams);

                    // 영상 재생 시작
                    mp.start();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
        // 크기 변경 시 필요한 로직
    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        // SurfaceTexture 소멸 시 필요한 로직
        return true;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
        // 업데이트 시 필요한 로직
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mediaPlayer.seekTo(0); // 일시 중지된 상태에서 다시 시작할 때 처음으로 돌아가도록 설정
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mediaPlayer != null) {
            mediaPlayer.start(); // 프래그먼트로 돌아올 때 비디오를 다시 시작
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
