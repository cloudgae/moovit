package com.example.main_01;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SlidingDrawer;

import com.example.main_01.apply.Apply_0;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.main_01.databinding.ActivityMap0Binding;

public class Map_0 extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMap0Binding binding;
    ImageButton more_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMap0Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ImageButton more_button = (ImageButton) findViewById(R.id.more_button);

        more_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Map_0.this, Map_1.class);
                startActivity(i);
                finish();
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Sliding Drawer 열기
        SlidingDrawer slidingDrawer = findViewById(R.id.slidingDrawer1);
        LinearLayout contentLayout = findViewById(R.id.content);

        // 슬라이딩 드로어의 최소 높이를 200dp로 설정
        ViewGroup.LayoutParams layoutParams = contentLayout.getLayoutParams();
        layoutParams.height = getResources().getDimensionPixelSize(R.dimen.min_sliding_content_height);
        contentLayout.setLayoutParams(layoutParams);

        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                // 슬라이딩 드로어가 열릴 때 최대 높이로 확장 (match_parent)
                ViewGroup.LayoutParams layoutParams = contentLayout.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
                contentLayout.setLayoutParams(layoutParams);
            }
        });

        slidingDrawer.setOnDrawerCloseListener(new SlidingDrawer.OnDrawerCloseListener() {
            @Override
            public void onDrawerClosed() {
                // 슬라이딩 드로어가 닫힐 때 다시 최소 높이 200dp로 설정
                ViewGroup.LayoutParams layoutParams = contentLayout.getLayoutParams();
                layoutParams.height = getResources().getDimensionPixelSize(R.dimen.min_sliding_content_height);
                contentLayout.setLayoutParams(layoutParams);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Seoul and move the camera
        LatLng SEOUL = new LatLng(37.556, 126.97);
        mMap.addMarker(new MarkerOptions().position(SEOUL).title("케이팝 걸그룹 타이틀곡 메들리").icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_png)));

        // Add 3 markers with specific coordinates
        addMarker(37.553, 126.9, "Smoke 챌린지 수업");
        addMarker(37.560, 127, "힙합 기본기 수업");
        addMarker(37.557, 126.92, "에스파 타이틀곡 챌린지 수업");

        // Set the zoom level to 10 (you can adjust this value as needed)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(SEOUL, 12));
    }

    private void addMarker(double latitude, double longitude, String title) {
        LatLng position = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions()
                .position(position)
                .title(title)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.marker_png))); // 마커 이미지 설정
    }
}