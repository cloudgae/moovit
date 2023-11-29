package com.example.main_01.mypage;

public class ClassItem {
    private String title;
    private String imageUrl;
    private boolean isLiked;
    private String day_loc;
    private String rate;
    private String num_review;
    private String price;
    private String thumbnailResourceName; // 리소스 이름 추가
    private String documentId; // Firestore 문서 ID
    private boolean isChecked;

    public ClassItem(String className, boolean isLiked, String dayloc, String rate, String num_review, String price) {
        // Default constructor required for Firestore
    }

    public ClassItem(String title, String thumbnailResourceName, boolean isLiked, String day_loc, String rate, String num_review, String price) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.isLiked = isLiked;
        this.day_loc = day_loc;
        this.rate = rate;
        this.num_review = num_review;
        this.price = price;
        this.thumbnailResourceName = thumbnailResourceName;
        this.isChecked = isLiked; // 초기에는 isLiked 상태와 동일하게 설정

    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public String getDay_loc() {
        return day_loc;
    }
    public String getRate() {
        return rate;
    }
    public String getNum_review() {
        return num_review;
    }
    public String getPrice() {
        return price;
    }
    // 썸네일 리소스 이름 설정하는 메서드
    public void setThumbnailResourceName(String thumbnailResourceName) {
        this.thumbnailResourceName = thumbnailResourceName;
    }

    // 썸네일 리소스 이름 가져오는 메서드
    public String getThumbnailResourceName() {
        return thumbnailResourceName;
    }
    public void setLiked(boolean liked) {
        isLiked = liked;
    }
    // Firestore 문서 ID를 반환하는 메서드
    public String getDocumentId() {
        return documentId;
    }
    // Firestore 문서 ID를 설정하는 메서드
    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
