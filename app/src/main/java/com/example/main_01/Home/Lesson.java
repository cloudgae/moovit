package com.example.main_01.Home;

import com.google.firebase.firestore.DocumentReference;

public class Lesson {
    private String rank;
    private  String name;
    private  String address;
    private String rate;
    private String num_review;
    private int imageResource;
    private boolean isChecked; // 즐겨찾기 상태 추가
    private DocumentReference lessonDocument; // Firebase Firestore 문서에 대한 참조 추가
    private  String documentId;

    public Lesson(String rank, String name, String address, String rate, String num_review, int imageResource, String documentId, boolean isChecked){
        this.rank = rank;
        this.name = name;
        this.address = address;
        this.rate = rate;
        this.num_review = num_review;
        this.imageResource = imageResource;
        this.isChecked = isChecked; // 초기에는 즐겨찾기 상태가 false로 설정
        this.documentId = documentId;
    }
    public String getRank(){
        return rank;
    }
    public  String getName(){
        return name;
    }
    public  String getAddress(){
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

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    public DocumentReference getLessonDocument() {
        return lessonDocument;
    }

    public void setLessonDocument(DocumentReference lessonDocument) {
        this.lessonDocument = lessonDocument;
    }
    public String getId() {
        // Firestore 문서의 ID를 반환
        return lessonDocument.getId();
    }
    public  String getDocumentId() {
        return documentId;
    }



    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
