package com.example.main_01;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import android.provider.MediaStore.Images.Thumbnails;


public class C7_tab1 extends Fragment {
    TextView content, content2, dancername, dancercareer;
    ImageView thumb, dancerprofile;

    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c7_tab1, container, false);

        // Firestore 인스턴스 초기화
        db = FirebaseFirestore.getInstance();

        content = (TextView) view.findViewById(R.id.c7content);
        content2 = (TextView) view.findViewById(R.id.c7content2);
        dancername = (TextView) view.findViewById(R.id.dancer_name);
        dancercareer = (TextView) view.findViewById(R.id.dancer_career);
        thumb = (ImageView) view.findViewById(R.id.thumb);
        dancerprofile = (ImageView) view.findViewById(R.id.dancer_profile);

        // Firestore에서 "C7" 문서 데이터 가져오기
        DocumentReference docRef = db.collection("Class").document("C7");
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // Firestore 문서에서 데이터 가져오기
                    String contentValue = documentSnapshot.getString("intro");
                    String contentValue2 = documentSnapshot.getString("intro2");
                    String dancerNameValue = documentSnapshot.getString("dancername");
                    String dancerCareerValue = documentSnapshot.getString("dancercareer");

                    // TextView 업데이트
                    content.setText(contentValue);
                    content2.setText(contentValue2);
                    dancername.setText(dancerNameValue);
                    dancercareer.setText(dancerCareerValue);
                }
            }
        });
//        String videoName = "C7video";
//        loadVideoThumbnailFromS3(videoName);
        // AWS S3에서 이미지를 로드하여 이미지뷰에 설정
        String imageName = "C7image/C7image"; // S3 버킷 내 이미지 파일의 경로 및 파일명
        loadImageFromS3(imageName);

        return view;
    }
    private void loadImageFromS3(String imageName) {
        // 디바이스 독립적인 픽셀 (dp)를 픽셀로 변환
        int widthInPixels = (int) (320 * getResources().getDisplayMetrics().density);
        int heightInPixels = (int) (180 * getResources().getDisplayMetrics().density);

        Glide.with(C7_tab1.this)
                .load("https://moovitbucket2.s3.amazonaws.com/" + imageName)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        e.printStackTrace(); // 로드 실패 시 오류 메시지 출력
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        return false;
                    }
                })
                .centerCrop()
                .override(widthInPixels, heightInPixels)  // 디바이스 독립적인 픽셀로 크기 지정
                .into(thumb);
    }
//    private void loadVideoThumbnailFromS3(String videoName) {
//        // MediaMetadataRetriever를 사용하여 동영상 썸네일을 추출하고 thumb 이미지뷰에 설정
//        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//        retriever.setDataSource("https://moovitbucket2.s3.amazonaws.com/C7video/" + videoName); // 동영상 경로
//        Bitmap thumbBitmap = retriever.getFrameAtTime(); // 썸네일 추출
//
//        // Bitmap 크기를 조절
////        thumbBitmap = resizeBitmap(thumbBitmap, 320, 180);
//
//        // 이미지를 크롭하여 일부분만 가져오기
//        thumbBitmap = cropBitmap(thumbBitmap, 0, 0, 320, 180);
//
//        thumb.setImageBitmap(thumbBitmap); // ImageView에 썸네일 설정
//    }
//    // Bitmap 일부분을 크롭하는 함수
//    private Bitmap cropBitmap(Bitmap originalBitmap, int left, int top, int width, int height) {
//        return Bitmap.createBitmap(originalBitmap, left, top, width, height);
//    }
//    // Bitmap 크기 조절 함수
//    private Bitmap resizeBitmap(Bitmap originalBitmap, int newWidth, int newHeight) {
//        int width = originalBitmap.getWidth();
//        int height = originalBitmap.getHeight();
//
//        // 크기를 변경하려는 크기와 비율 계산
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//
//        // 크기 변경 매트릭스 설정
//        Matrix matrix = new Matrix();
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        // 새로운 크기의 Bitmap 생성
//        Bitmap resizedBitmap = Bitmap.createBitmap(originalBitmap, 0, 0, width, height, matrix, true);
//
//        // 기존 Bitmap 리소스 해제
//        originalBitmap.recycle();
//
//        return resizedBitmap;
//    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}