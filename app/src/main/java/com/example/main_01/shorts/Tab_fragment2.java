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
import androidx.viewpager2.widget.ViewPager2;

import com.example.main_01.R;

public class Tab_fragment2 extends Fragment {

    private ViewPager2 viewPager2;
    private TabFragmentAdapter2 fragmentAdapter2; // TabFragmentAdapter 추가
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab2, container, false);

        viewPager2 = rootView.findViewById(R.id.viewPager2); // ViewPager2 초기화
        fragmentAdapter2 = new TabFragmentAdapter2(getChildFragmentManager(), getLifecycle()); // TabFragmentAdapter 초기화

        // ViewPager2에 Adapter 설정
        viewPager2.setAdapter(fragmentAdapter2);

        return rootView;
    }

    // 프래그먼트 생성 및 초기화
    public static Tab_fragment2 newInstance(int position) {
        Tab_fragment2 fragment = new Tab_fragment2();
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
