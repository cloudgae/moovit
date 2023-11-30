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

import com.example.main_01.R;

public class ShortsDancer2PortfolioFragment2 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shorts_dancer2_portfolio2, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView class1 = view.findViewById(R.id.class1);
        ImageView class2 = view.findViewById(R.id.class2);
        ImageView class3 = view.findViewById(R.id.class3);

        class1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPortfolioPlayViewActivity("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/tatter/tatter_class1.mp4");
            }
        });

        class2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPortfolioPlayViewActivity("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/tatter/tatter_class2.mp4");
            }
        });

        class3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPortfolioPlayViewActivity("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/tatter/tatter_class3.mp4");
            }
        });


    }

    private void startPortfolioPlayViewActivity(String videoUrl) {
        Intent intent = new Intent(getActivity(), Portfolio_playview.class);
        intent.putExtra("videoUrl", videoUrl);
        startActivity(intent);


    }
}