package com.example.main_01;
import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class shorts1 extends AppCompatActivity {

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





        }

