package com.example.main_01.shorts;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main_01.R;

public class ShortsDancer2PortfolioFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shorts_dancer2_portfolio1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView pf1 = view.findViewById(R.id.pf1);
        ImageView pf2 = view.findViewById(R.id.pf2);
        TextView dancer_name = view.findViewById(R.id.dancer_name);
        ImageView profileimage = view.findViewById(R.id.profileimage);


        pf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dancerName = "태터\nTATTER"; // 변경 예시 데이터, 실제 데이터로 변경
                startPortfolioPlayViewActivity(dancerName, R.drawable.dancer2_image_1, "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/tatter/tatter_pf1.mp4");
            }
        });

        pf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dancerName = "태터\nTATTER"; // 변경 예시 데이터, 실제 데이터로 변경
                startPortfolioPlayViewActivity(dancerName, R.drawable.dancer2_image_1, "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/tatter/tatter_pf2.mp4");
            }
        });


    }

    private void startPortfolioPlayViewActivity(String dancerName, int profileImageResource,String videoUrl) {
        Intent intent = new Intent(getActivity(), Portfolio_playview.class);
        intent.putExtra("videoUrl", videoUrl);
        intent.putExtra("dancer_name", dancerName);
        intent.putExtra("profile_image_resource", profileImageResource);
        startActivity(intent);
    }

}