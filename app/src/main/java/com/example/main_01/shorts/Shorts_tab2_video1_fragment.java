package com.example.main_01.shorts;

import android.content.Intent;
import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.example.main_01.apply.Apply_0;

import java.io.IOException;

public class Shorts_tab2_video1_fragment extends Fragment implements TextureView.SurfaceTextureListener {

    private VideoView videoView;
    private FrameLayout framelayout;
    private TextureView textureView;
    private TextView dancer_name;
    private ImageView profileimage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shorts_tab2_video1, container, false);
        textureView = rootView.findViewById(R.id.textureView1);
        dancer_name = rootView.findViewById(R.id.dancer_name);
        profileimage = rootView.findViewById(R.id.profileimage);

        // Amazon S3에서 영상 URL 설정
        String videoURL = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/lusher/lusher_class1.mp4";

        // TextureView에 SurfaceTextureListener 설정
        textureView.setSurfaceTextureListener(this);

        dancer_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ShortsDancer1Profile.class);
                startActivity(i);
            }
        });

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ShortsDancer1Profile.class);
                startActivity(i);
            }
        });

        return rootView;


    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface videoSurface = new Surface(surface);

        try {
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/lusher/lusher_class1.mp4");
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
}
