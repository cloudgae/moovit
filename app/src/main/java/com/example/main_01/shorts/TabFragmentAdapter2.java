package com.example.main_01.shorts;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.main_01.shorts.Shorts_tab1_video1_fragment;
import com.example.main_01.shorts.Shorts_tab1_video2_fragment;
import com.example.main_01.shorts.Shorts_tab1_video3_fragment;

public class TabFragmentAdapter2 extends FragmentStateAdapter {

    public TabFragmentAdapter2(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        // 각 프래그먼트에 해당하는 정보를 전달하며 프래그먼트 생성
        switch (position) {
            case 0:
                return new Shorts_tab2_video1_fragment(); // 첫 번째 영상 프래그먼트
            case 1:
                return new Shorts_tab2_video2_fragment(); // 두 번째 영상 프래그먼트
            case 2:
                return new Shorts_tab2_video3_fragment(); // 세 번째 영상 프래그먼트
            default:
                return new Shorts_tab2_video1_fragment(); // 기본 프래그먼트
        }
    }

    @Override
    public int getItemCount() {
        return 3; // 3개의 영상 프래그먼트를 사용하는 예시
    }
}

