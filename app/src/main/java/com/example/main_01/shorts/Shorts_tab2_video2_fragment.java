package com.example.main_01.shorts;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.main_01.R;

public class Shorts_tab2_video2_fragment extends Fragment {

    private VideoView videoView;
    private FrameLayout framelayout;

    private TextView dancer_name;
    private ImageView profileimage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_shorts_tab2_video2, container, false);
        videoView = rootView.findViewById(R.id.videoView1);
        dancer_name = rootView.findViewById(R.id.dancer_name);
        profileimage = rootView.findViewById(R.id.profileimage);

        // Amazon S3에서 영상 URL 설정
        String videoURL = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/tatter/tatter_class1.mp4";

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

                // FrameLayout을 사용하여 VideoView을 가운데로 정렬
                FrameLayout frameLayout = rootView.findViewById(R.id.framelayout);
                FrameLayout.LayoutParams frameLayoutParams = (FrameLayout.LayoutParams) videoView.getLayoutParams();
                frameLayoutParams.gravity = Gravity.CENTER;
                videoView.setLayoutParams(frameLayoutParams);

                // 영상 재생 시작
                videoView.start();
            }
        });
        dancer_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ShortsDancer2Profile.class);
                startActivity(i);
            }
        });

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ShortsDancer2Profile.class);
                startActivity(i);
            }
        });
        return rootView;

    }
}
