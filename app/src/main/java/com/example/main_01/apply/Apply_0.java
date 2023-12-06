package com.example.main_01.apply;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.main_01.MainActivity;
import com.example.main_01.map.Map_0;
import com.example.main_01.OnDocumentIdReceivedListener;
import com.example.main_01.R;
import com.example.main_01.mypage.MyPage;
import com.example.main_01.shorts.shorts1;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Apply_0 extends AppCompatActivity implements OnDocumentIdReceivedListener {

    ImageButton applybtn;
    ImageButton backbtn;

    ViewPager pager;
    TextView genre;
    private BottomNavigationView bottomNavigationView;
    ImageView image;
    TextView cname, cgenre, cdiff, cday, cloc, cprice, crate_numreview;
    private FirebaseFirestore db;  // Firestore 인스턴스 선언 추가
    CheckBox checkbox_like;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply0);

        ImageButton applybtn = (ImageButton) findViewById(R.id.apply_button);
        ImageButton backbtn = (ImageButton) findViewById(R.id.back_btn);
        //
        cname = (TextView) findViewById(R.id.name);
        cgenre = (TextView) findViewById(R.id.genre);
        cdiff = (TextView) findViewById(R.id.diff);
        cday = (TextView) findViewById(R.id.day);
        cloc = (TextView) findViewById(R.id.loc);
        cprice = (TextView) findViewById(R.id.price);
        crate_numreview = (TextView) findViewById(R.id.rate_numreview);
        image = (ImageView) findViewById(R.id.image);
        checkbox_like = (CheckBox) findViewById(R.id.checkbox_like);

        // getIntent()로 Intent를 받아옴
        Intent intent = getIntent();

        // Intent에서 데이터 추출
        String documentId = intent.getStringExtra("documentId");
        String weeklyRank = intent.getStringExtra("weeklyRank");
        String address = intent.getStringExtra("address");
        String name = intent.getStringExtra("name");
        String numReview = intent.getStringExtra("numReview");
        String rate = intent.getStringExtra("rate");
        int imageResource = intent.getIntExtra("imageResource", 0);
        boolean isLiked = intent.getBooleanExtra("isLiked", false);

        // Firestore 초기화
        db = FirebaseFirestore.getInstance();
// Firestore에서 문서 가져오기
        DocumentReference documentRef = db.collection("Class").document(documentId);
        documentRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    // 문서가 존재할 경우 해당 데이터를 UI에 표시
                    cname.setText(documentSnapshot.getString("name"));
                    cgenre.setText(documentSnapshot.getString("genre"));
                    cdiff.setText(documentSnapshot.getString("difficulty"));
                    cday.setText(documentSnapshot.getString("frequency"));
                    cloc.setText(documentSnapshot.getString("location"));
                    cprice.setText(documentSnapshot.getString("price") + "원");
//                    crate_numreview.setText(documentSnapshot.getString("rate") + " ("
//                            + documentSnapshot.getString("review") + ")");
                    // 이미지 리소스 설정
                    int imageResource = intent.getIntExtra("imageResource", 0);
                    image.setImageResource(imageResource);
                    crate_numreview.setText(rate + " (" + numReview + ")");
                    checkbox_like.setChecked(isLiked);
                    // 나머지 필요한 UI 업데이트 작업 수행
                    // ...
                } else {
                    Log.d("Apply_0", "Document does not exist");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Apply_0", "Error fetching document: " + e.getMessage());
            }
        });

        //


        //
        pager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);

        pager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(pager);
        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), this));

        genre = (TextView) findViewById(R.id.genre);
        Drawable drawable = getResources().getDrawable(R.drawable.genre_icon);

        // dp 값을 px로 변환
        int widthInDp = 35;
        int heightInDp = 35;
        float scale = getResources().getDisplayMetrics().density;
        int widthInPx = (int) (widthInDp * scale + 0.5f);
        int heightInPx = (int) (heightInDp * scale + 0.5f);

        drawable.setBounds(0, 0, widthInPx, heightInPx);
        genre.setCompoundDrawables(null, drawable, null, null);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.map:
                        openMap();
                        return true;
                    case R.id.shorts:
                        openShorts();
                        return true;
                    case R.id.mypage:
                        openMyPage();
                        return true;
                }
                return false;
            }
        });
        bottomNavigationView.setSelectedItemId(R.id.home);

        applybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Apply_0.this, Apply_1_recycle.class);
                i.putExtra("documentId1", documentId);
                //썸네일,제목,장르,난이도
                i.putExtra("imageResource1", imageResource);
//                intent.putExtra("name", name);

                startActivity(i);
                finish();
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Apply_0.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    @Override
    public void onDocumentIdReceived(String documentId) {

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

    private void openMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void openMap() {
        Intent intent = new Intent(this, Map_0.class);
        startActivity(intent);
    }

    private void openShorts() {
        Intent intent = new Intent(this, shorts1.class);
        startActivity(intent);
    }


    private void openMyPage() {
        Intent intent = new Intent(this, MyPage.class);
        startActivity(intent);
    }


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
