package com.example.main_01;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.main_01.apply.Apply_0;
import com.example.main_01.apply.Apply_1;
import com.example.main_01.apply.Apply_frag1;
import com.example.main_01.apply.Apply_frag2;
import com.example.main_01.apply.Apply_frag3;
import com.google.android.material.tabs.TabLayout;

public class Apply_C7 extends AppCompatActivity {
    Button applybtn;

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_c7);

        Button applybtn = (Button) findViewById(R.id.apply_button);

        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new Apply_C7.PageAdapter(getSupportFragmentManager(), this));

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Apply_C7.this, Apply_1.class);
                startActivity(i);
                finish();
            }
        });

    }



    static class PageAdapter extends FragmentStatePagerAdapter {
        PageAdapter(FragmentManager fm, Context context) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new Apply_frag1();
            } else if (position == 1) {
                return new Apply_frag2();
            } else {
                return new Apply_frag3();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }


        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "상세정보";
            } else if (position == 1) {
                return "후기";
            } else {
                return "Q&A";
            }
        }


    }
}