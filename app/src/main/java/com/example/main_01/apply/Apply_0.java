package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.main_01.R;
import com.google.android.material.tabs.TabLayout;

public class Apply_0 extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply0);

        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        Button btn1 = (Button) findViewById(R.id.btn1);
//        Button btn2 = (Button) findViewById(R.id.btn2);
//        Button btn3 = (Button) findViewById(R.id.btn3);
//        pager.setAdapter(new pagerAdapter(getSupportFragmentManager()));
//        pager.setCurrentItem(0);

        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(),this));

//        View.OnClickListener movePageListener = new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int tag = (int) view.getTag();
//                pager.setCurrentItem(tag);
//            }
//        };
//
//        btn1.setOnClickListener(movePageListener);
//        btn1.setTag(1);
//        btn2.setOnClickListener(movePageListener);
//        btn2.setTag(2);
//        btn3.setOnClickListener(movePageListener);
//        btn3.setTag(3);
    }

    static class PageAdapter extends FragmentStatePagerAdapter{
        PageAdapter(FragmentManager fm, Context context){
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0){
                return new Apply_frag1();
            }else if (position ==1){
                return new Apply_frag2();
            }else{
                return new Apply_frag3();
            }
        }

        @Override
        public int getCount() {
            return 0;
        }
    }

    public CharSequence getPageTitle(int position){
        if(position ==0){
            return "Tab1";
        }
        else if(position ==1){
            return "Tab2";
        }
        else
            return "Tab3";
    }
//
//    private class pagerAdapter extends FragmentStatePagerAdapter{
//        public pagerAdapter(FragmentManager fm){
//            super(fm);
//        }
//        public Fragment getItem(int position){
//            switch (position){
//                case 0:
//                    return new Apply_frag1();
//                case 1:
//                    return new Apply_frag2();
//                case 2:
//                    return new Apply_frag3();
//                default:
//                    return null;
//            }
//        }
//        public int getCount(){
//            return 3;
//        }
//    }
}