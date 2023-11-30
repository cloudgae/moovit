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

public class ShortsDancer3PortfolioFragment1 extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shorts_dancer3_portfolio1, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageView pf1 = view.findViewById(R.id.pf1);
        ImageView pf2 = view.findViewById(R.id.pf2);

        pf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPortfolioPlayViewActivity("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/kinky/kinky_pf1.mp4");
            }
        });

        pf2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startPortfolioPlayViewActivity("https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/dancer/kinky/kinky_pf2.mp4");
            }
        });


    }

    private void startPortfolioPlayViewActivity(String videoUrl) {
        Intent intent = new Intent(getActivity(), Portfolio_playview.class);
        intent.putExtra("videoUrl", videoUrl);
        startActivity(intent);

    }

}