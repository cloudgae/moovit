package com.example.main_01.mypage;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyPageLikelistTab1 extends Fragment {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private List<ClassItem> classList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_page_likelist_tab1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // RecyclerView 초기화
        recyclerView = view.findViewById(R.id.recyclerView);
        classList = new ArrayList<>();
        adapter = new RecyclerViewAdapter(classList, requireContext());


        // Firebase Firestore에서 데이터 가져오기 예제
        db.collection("Class")
                .whereEqualTo("like", true) // 좋아요가 true인 데이터만 가져오기
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                // Firestore에서 가져온 데이터를 ClassItem 객체로 변환하여 리스트에 추가
                                String className = document.getString("name");
                                String dayloc = document.getString("frequency") + " · " + document.getString("location");
                                boolean isLiked = true;
                                String rate = document.getString("rate");
                                String num_review = "(" + document.getString("review") + ")";
                                String price = document.getString("price") + "원";

                                // 썸네일 이미지 리소스 이름 가져오기
                                String thumbnailResourceName = document.getString("thumbnailResourceName");

                                // ClassItem 객체에 설정된 값으로 생성
                                ClassItem classItem = new ClassItem(className, thumbnailResourceName, isLiked, dayloc, rate, num_review, price);

                                classList.add(classItem);
                            }

                            // RecyclerView 갱신
                            adapter.notifyDataSetChanged();
                        } else {
                            // Firestore에서 데이터 가져오기 실패
                            // 처리 필요
                        }
                    }
                });




        // RecyclerView 설정
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));


    }

    private void setupRecyclerView(List<ClassItem> classList) {
        RecyclerView recyclerView = requireView().findViewById(R.id.recyclerView);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(classList, requireContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));
    }

    // Drawable에서 리소스 ID를 가져오는 메서드
    private int getResourceIdFromDrawable(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return R.drawable.c3; // 기본 이미지 리소스 ID
        } else if (drawable instanceof VectorDrawableCompat || drawable instanceof VectorDrawable) {
            return R.drawable.c3; // 기본 이미지 리소스 ID
        } else {
            return 0;
        }
    }
}