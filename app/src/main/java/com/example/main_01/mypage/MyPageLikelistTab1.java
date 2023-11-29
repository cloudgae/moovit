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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
                                String frequency = document.getString("frequency");
                                String location = document.getString("location");
                                String dayloc = frequency + (frequency != null && location != null ? " · " + location : "");
                                boolean isLiked = true;
                                String rate = document.getString("rate");
                                String review = document.getString("review");
                                String num_review = "(" + (review != null ? review : "0") + ")";
                                String price = document.getString("price") + "원";

                                // 썸네일 이미지 리소스 이름 가져오기
                                String thumbnailResourceName = document.getString("thumbnailResourceName");

                                // ClassItem 객체에 설정된 값으로 생성
                                ClassItem classItem = new ClassItem(className, thumbnailResourceName, isLiked, dayloc, rate, num_review, price);

                                // Firestore 문서 ID 설정
                                classItem.setDocumentId(document.getId());

                                classList.add(classItem);
                            }

                            // RecyclerView 갱신
                            adapter.notifyDataSetChanged();
                        } else {
                            // Firestore에서 데이터 가져오기 실패
                            // 처리 필요
                            Exception exception = task.getException();
                            if (exception != null) {
                                Log.e("MyPageLikelistTab1", "Error fetching data: " + exception.getMessage());
                            }
                        }
                    }
                });





        // RecyclerView 설정
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));


        // 클릭 리스너 설정
        adapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
//                // 아이템이 클릭되었을 때의 동작 처리
//                ClassItem classItem = classList.get(position);
//                boolean newLikeState = !classItem.isLiked(); // 현재 상태의 반대로 변경
//
//                // Firestore에서 해당 문서의 like 필드 업데이트
//                String documentId = classItem.getDocumentId(); // 여기서 documentId를 가져옵니다.
//                updateFirestoreLikeState(documentId, newLikeState);
//
//                // RecyclerView에서 아이템의 like 필드 갱신
//                adapter.updateItem(position, newLikeState);
                // 아이템이 클릭되었을 때의 동작 처리
                ClassItem classItem = classList.get(position);
                boolean newLikeState = !classItem.isLiked(); // 현재 상태의 반대로 변경
                classItem.setLiked(newLikeState);
                classItem.setChecked(newLikeState); // 체크박스 상태 업데이트

                // Firestore에서 해당 문서의 like 필드 업데이트
                String documentId = classItem.getDocumentId(); // 여기서 documentId를 가져옵니다.
                updateFirestoreLikeState(documentId, newLikeState);

                // RecyclerView에서 아이템의 like 필드 갱신
//                adapter.updateItem(position, newLikeState);
                adapter.notifyItemChanged(position);
                // 아이템이 좋아요에서 해제되면 리사이클러뷰에서 제거
                if (!newLikeState) {
                    adapter.removeClass(position);
                }
            }
        });

    }
    // Firestore에서 like 필드 업데이트 메서드
    private void updateFirestoreLikeState(String documentId, boolean newLikeState) {
        db.collection("Class")
                .document(documentId)
                .update("like", newLikeState)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 업데이트 성공 처리
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 업데이트 실패 처리
                    }
                });
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