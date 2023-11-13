package com.example.main_01.shorts;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.main_01.R;

public class Tab_fragment2 extends Fragment {

    private VideoView videoView;
    Button dp1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        videoView = rootView.findViewById(R.id.videoView);

       /* // Amazon S3에서 영상 URL 설정
        String videoURL = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/UIS2.mp4";

        // VideoView에 영상 설정
        videoView.setVideoURI(Uri.parse(videoURL));*/

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

        dp1 = rootView.findViewById(R.id.dancer_profile); // dp1 버튼을 초기화합니다.

        dp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ShortsDancerProfile 액티비티로 이동합니다.
                Intent i = new Intent(getActivity(), ShortsDancerProfile.class);
                startActivity(i);
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



/*
package com.example.main_01.shorts;
import android.content.Intent;
import android.widget.Button;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tab_fragment2 extends Fragment {

    private Button dp1;
    private VideoView videoView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);
        videoView = rootView.findViewById(R.id.videoView);

        // 파이어베이스 스토리지 참조 생성
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();

        // 파이어베이스 스토리지에서 영상 다운로드 URL 가져오기
        StorageReference videoRef = storageRef.child("videos/PCM2.mp4"); // 영상 파일의 경로를 지정해주세요

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

        dp1 = rootView.findViewById(R.id.dancer_profile); // dp1 버튼을 초기화합니다.

        dp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ShortsDancerProfile 액티비티로 이동합니다.
                Intent i = new Intent(getActivity(), ShortsDancerProfile.class);
                startActivity(i);
            }
        });

        return rootView;
    }
}
*/
