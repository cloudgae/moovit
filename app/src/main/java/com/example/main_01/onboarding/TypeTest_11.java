package com.example.main_01.onboarding;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.main_01.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class TypeTest_11 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test11);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Class").document("test");

        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                // 필드에서 영상 이름 가져오기
                String videoName = documentSnapshot.getString("name");

                // Firebase Storage에서 참조 생성
                FirebaseStorage storage = FirebaseStorage.getInstance();
                StorageReference storageRef = storage.getReference();
                StorageReference videoRef = storageRef.child("videos/" + videoName);

                // 영상 다운로드 URL 가져오기
                videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        // URL을 사용하여 영상 로드 및 표시
                        String videoUrl = uri.toString();
                        VideoView videoView = findViewById(R.id.video1);

                        // VideoView에 MediaController 추가
                        MediaController mediaController = new MediaController(TypeTest_11.this);
                        videoView.setMediaController(mediaController);
                        mediaController.setAnchorView(videoView);

                        // 영상 설정
                        videoView.setVideoURI(Uri.parse(videoUrl));
                        videoView.start();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 영상 URL을 가져오는 도중 에러 발생
                        e.printStackTrace();
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Firestore에서 데이터 가져오는 도중 에러 발생
                e.printStackTrace();
            }
        });
    }
}