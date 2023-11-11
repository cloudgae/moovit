package com.example.main_01;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class C7_tab2 extends Fragment {

    private ProgressBar progressBar1, progressBar2, progressBar3;
    private TextView description1, description2, description3;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_c7_tab2, container, false);

        progressBar1 = view.findViewById(R.id.progressBar1);
        description1 = view.findViewById(R.id.description1);
        progressBar2 = view.findViewById(R.id.progressBar2);
        description2 = view.findViewById(R.id.description2);
        progressBar3 = view.findViewById(R.id.progressBar3);
        description3 = view.findViewById(R.id.description3);

        // Firestore 초기화
        db = FirebaseFirestore.getInstance();

        // Firestore에서 데이터 가져오기
        db.collection("Class").document("C1").get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {
                                // 데이터 가져오기
                                String review1 = document.getString("review1");
                                long review1_data = document.getLong("review1_data");
                                String review2 = document.getString("review2");
                                long review2_data = document.getLong("review2_data");
                                String review3 = document.getString("review3");
                                long review3_data = document.getLong("review3_data");

                                // ProgressBar 업데이트
                                progressBar1.setProgress((int) review1_data);
                                progressBar2.setProgress((int) review2_data);
                                progressBar3.setProgress((int) review3_data);

                                // 설명 텍스트 설정
                                description1.setText(review1);
                                description2.setText(review2);
                                description3.setText(review3);

                                // 데이터 텍스트 설정
                                TextView data1 = view.findViewById(R.id.data1);
                                TextView data2 = view.findViewById(R.id.data2);
                                TextView data3 = view.findViewById(R.id.data3);

                                data1.setText(String.valueOf(review1_data));
                                data2.setText(String.valueOf(review2_data));
                                data3.setText(String.valueOf(review3_data));
                            }
                        } else {
                            // 처리 중 에러 발생 시
                        }
                    }
                });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}