package com.example.main_01.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.main_01.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TypeTest_11 extends AppCompatActivity {
    private SimpleExoPlayer player;
    private com.google.android.exoplayer2.ui.PlayerView playerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test11);

        playerView = findViewById(R.id.player_view);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Class").document("test");

        docRef.get().addOnSuccessListener(documentSnapshot -> {
            // 필드에서 영상 이름 가져오기
            String videoName = documentSnapshot.getString("name");
            // 가져온 영상 이름을 사용하여 Storage에서 참조 생성
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageRef = storage.getReference();
            StorageReference videoRef = storageRef.child("videos/" + videoName);

            videoRef.getDownloadUrl().addOnSuccessListener(uri -> {
                // URL을 사용하여 영상 로드 및 표시
                String videoUrl = uri.toString();
                initializePlayer(videoUrl);
            }).addOnFailureListener(e -> {
                // 영상 URL을 가져오는 도중 에러 발생
                // TODO: 에러 처리
            });
        }).addOnFailureListener(e -> {
            // Firestore에서 데이터 가져오는 도중 에러 발생
            // TODO: 에러 처리
        });
    }

    private void initializePlayer(String videoUrl) {
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);

        // 미디어 소스 생성
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));
        MediaSource mediaSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(videoUrl));

        // 플레이어에 미디어 소스 설정
        player.setMediaSource(mediaSource);
        player.prepare();
        player.setPlayWhenReady(true); // 재생 시작
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.release(); // 플레이어 해제
        }
    }
}
