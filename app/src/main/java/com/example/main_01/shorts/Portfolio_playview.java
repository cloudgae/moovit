package com.example.main_01.shorts;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.main_01.MainActivity;
import com.example.main_01.R;
import com.example.main_01.map.Map_0;
import com.example.main_01.mypage.MyPage;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Portfolio_playview extends AppCompatActivity {

    private VideoView videoView;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio_playview);

        ImageButton backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        openMainActivity();
                        return true;
                    case R.id.map:
                        openMap();
                        return true;
                    case R.id.shorts:
                        return true;
                    case R.id.mypage:
                        openMyPage();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.shorts);



        Intent intent = getIntent();
        if (intent != null) {
            String dancerName = intent.getStringExtra("dancer_name");
            int profileImageResource = intent.getIntExtra("profile_image_resource", 0);

            // dancerName을 TextView에 설정
            TextView dancerNameTextView = findViewById(R.id.dancer_name);
            dancerNameTextView.setText(dancerName);

            // profileImageResource를 ImageView에 설정
            ImageView profileImageView = findViewById(R.id.profileimage);
            profileImageView.setImageResource(profileImageResource);
        }

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openMap() {
        Intent intent = new Intent(this, Map_0.class);
        startActivity(intent);
    }

    private void openShorts() {
        Intent intent = new Intent(this, shorts1.class);
        startActivity(intent);
    }


    private void openMyPage() {
        Intent intent = new Intent(this, MyPage.class);
        startActivity(intent);
    }
}
