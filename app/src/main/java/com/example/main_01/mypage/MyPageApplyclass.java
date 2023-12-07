package com.example.main_01.mypage;

import android.widget.ImageView;
import android.widget.TextView;

public class MyPageApplyclass {
    private int imageResource;
    private String name, location, daytime, thumbnailResourceName;

    public MyPageApplyclass(String thumbnailResourceName, String name, String location, String daytime){
        this.thumbnailResourceName = thumbnailResourceName;
        this.name = name;
        this.location = location;
        this.daytime = daytime;
    }

    public String getName(){
        return name;
    }

    public String getLocation(){
        return location;
    }
    public String getDaytime(){
        return daytime;
    }
    public String getThumbnailResourceName() {
        return thumbnailResourceName;
    }

}
