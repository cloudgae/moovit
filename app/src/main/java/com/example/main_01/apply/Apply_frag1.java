package com.example.main_01.apply;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import com.example.main_01.Home.Lesson;
import com.example.main_01.Home.LessonAdapter;
import com.example.main_01.OnDocumentIdReceivedListener;
import com.example.main_01.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Apply_frag1 extends Fragment {
    TextView content, content2, dancername, dancercareer, dancer_intro;
    ImageView thumb, dancerprofile;

    private FirebaseFirestore db;
    private OnDocumentIdReceivedListener onDocumentIdReceivedListener;
    private String receivedDocumentId;
    private String documentId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_apply1, container, false);

        // Firestore 인스턴스 초기화
        db = FirebaseFirestore.getInstance();

        content = (TextView) view.findViewById(R.id.content);
//        content2 = (TextView) view.findViewById(R.id.content2);
        dancername = (TextView) view.findViewById(R.id.dancer_name);
        dancercareer = (TextView) view.findViewById(R.id.dancer_career);
        thumb = (ImageView) view.findViewById(R.id.thumb);
//        dancerprofile = (ImageView) view.findViewById(R.id.dancer_profile);
//        dancer_intro = (TextView) view.findViewById(R.id.dancer_intro);

        // getIntent()로 Intent를 받아옴
//        Intent intent = getIntent();



        return view;
        // 최초에는 documentId가 없으므로 초기 데이터를 가져오지 않도록 주의
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
