package com.example.main_01.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.main_01.R;

import java.util.List;

public class MyPageApplyclassAdapter extends RecyclerView.Adapter<MyPageApplyclassAdapter.ViewHolder> {
    private List<MyPageApplyclass> itemList; // MyItem은 당신이 정의한 데이터 모델 클래스
    private Context context; // 추가: 컨텍스트 변수

    // 생성자에서 데이터 리스트를 받아옴
    public MyPageApplyclassAdapter(List<MyPageApplyclass> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }
    // 뷰홀더 클래스 정의
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name1, location1, daytime;
        ImageView image1;
        Button btn;

        public ViewHolder(View itemView) {
            super(itemView);
            name1 = itemView.findViewById(R.id.name1);
            location1 = itemView.findViewById(R.id.location1);
            daytime = itemView.findViewById(R.id.daytime);
            image1 = itemView.findViewById(R.id.image1);
            btn = itemView.findViewById(R.id.btn);
        }
    }
    @NonNull
    @Override
    public MyPageApplyclassAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_applyclass, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPageApplyclassAdapter.ViewHolder holder, int position) {
        MyPageApplyclass currentItem = itemList.get(position);
//        holder.name1.setText(); // 예시로 setText 호출
        // MyPageApplyclass에 name, location 및 daytime을 가져오기 위한 적절한 getter가 있다고 가정합니다.
        holder.name1.setText(currentItem.getName());
        holder.location1.setText(currentItem.getLocation());
        holder.daytime.setText(currentItem.getDaytime());

        String thumbnailResourceName = currentItem.getThumbnailResourceName();
        if (thumbnailResourceName != null) {
            int thumbnailResourceId = getResourceIdByName(context, thumbnailResourceName);
            holder.image1.setImageResource(thumbnailResourceId);
        } else {
            // 썸네일 리소스 이름이 null이면 s3 버킷의 c7image 폴더에 있는 이미지로 설정
            String s3ImageUrl = "https://moovitbucket2.s3.ap-northeast-2.amazonaws.com/C7image/C7image"; // 여기에 자신의 S3 버킷 URL과 기본 이미지 경로를 넣으세요
            Glide.with(context).load(s3ImageUrl).into(holder.image1);
        }

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // 데이터 갱신 메서드 추가
    public void setItems(List<MyPageApplyclass> itemList) {
        this.itemList = itemList;
        notifyDataSetChanged();
    }
    // 이미지 리소스 이름으로부터 리소스 ID를 가져오는 메서드
    private int getResourceIdByName(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
}
