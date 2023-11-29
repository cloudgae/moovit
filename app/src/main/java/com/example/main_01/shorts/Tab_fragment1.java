package com.example.main_01.shorts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.main_01.R;

public class Tab_fragment1 extends Fragment {

    private ViewPager2 viewPager2;
    private TabFragmentAdapter fragmentAdapter; // TabFragmentAdapter 추가
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab1, container, false);

        viewPager2 = rootView.findViewById(R.id.viewPager2); // ViewPager2 초기화
        fragmentAdapter = new TabFragmentAdapter(getChildFragmentManager(), getLifecycle()); // TabFragmentAdapter 초기화

        // ViewPager2에 Adapter 설정
        viewPager2.setAdapter(fragmentAdapter);

        return rootView;
    }

    // 프래그먼트 생성 및 초기화
    public static Tab_fragment1 newInstance(int position) {
        Tab_fragment1 fragment = new Tab_fragment1();
        // 각 프래그먼트에 필요한 정보를 전달하는 방법 (position에 따라 다른 정보 설정)
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragment.setArguments(args);
        return fragment;
    }


    public void pauseVideoAndMusic() {
        if (videoView.isPlaying()) {
            videoView.pause();
        }
        // 음악 정지 로직 추가
    }

    public void resumeVideoAndMusic() {
        if (!videoView.isPlaying()) {
            videoView.start();
        }
        // 음악 재생 로직 추가
    }
}