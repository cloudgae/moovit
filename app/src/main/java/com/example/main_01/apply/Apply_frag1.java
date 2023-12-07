package com.example.main_01.apply;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Apply_frag1 extends Fragment {
    TextView content, content2, dancername, dancercareer, dancer_intro;
    ImageView thumb, dancerprofile;
    Button moreDetailButton;

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
        // more_detail 버튼 참조
        moreDetailButton = view.findViewById(R.id.more_detail);
//        dancerprofile = (ImageView) view.findViewById(R.id.dancer_profile);
//        dancer_intro = (TextView) view.findViewById(R.id.dancer_intro);

        // getIntent()로 Intent를 받아옴
//        Intent intent = getIntent();

        moreDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Bottom Sheet 표시
                showBottomSheet();
            }
        });

        return view;
        // 최초에는 documentId가 없으므로 초기 데이터를 가져오지 않도록 주의
    }

    private void showBottomSheet() {
        // Bottom Sheet XML 파일을 inflate하여 View 생성
        View bottomSheetView = getLayoutInflater().inflate(R.layout.activity_apply1_classdetail_bottomsheet, null);

        // Bottom Sheet Dialog 생성
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(requireContext());
        bottomSheetDialog.setContentView(bottomSheetView);

        // Bottom Sheet Dialog의 상단 10dp 영역을 클릭하면 Bottom Sheet 숨기기
        bottomSheetView.findViewById(R.id.top_area).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();
            }
        });

        // Bottom Sheet 표시
        bottomSheetDialog.show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

}
