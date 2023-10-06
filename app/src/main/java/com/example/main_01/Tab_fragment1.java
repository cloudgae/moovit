package com.example.main_01;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class Tab_fragment1 extends Fragment {

    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);
        videoView = rootView.findViewById(R.id.videoView);

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

        return rootView;
    }
}
