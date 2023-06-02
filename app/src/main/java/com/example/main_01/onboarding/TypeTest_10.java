package com.example.main_01.onboarding;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.main_01.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class TypeTest_10 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 앱이 첫 실행됬을때 이곳을 수행한다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_test10);


    }
}
