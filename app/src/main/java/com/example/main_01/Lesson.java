package com.example.main_01;

public class Lesson {
    private String rank;
    private String name;
    private String address;
    private String rate;
    private String num_review;
    private int imageResource;

    public Lesson(String rank, String name, String address, String rate, String num_review, int imageResource){
        this.rank = rank;
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.num_review = num_review;
        this.imageResource = imageResource;
    }
    public String getRank(){
        return rank;
    }
    public String getName(){
        return name;
    }
    public String getAddress(){
        return address;
    }
    public String getRate(){
        return rate;
    }
    public String getNum_review(){
        return num_review;
    }
    public int getImageResource(){
        return imageResource;
    }
}
