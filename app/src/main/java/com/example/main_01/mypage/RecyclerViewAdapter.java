package com.example.main_01.mypage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_01.R;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<ClassItem> classList;
    private Context context; // 추가: 컨텍스트 변수


    public RecyclerViewAdapter(List<ClassItem> classList, Context context) {
        this.classList = classList;
        this.context = context; // 컨텍스트 설정
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_likedclass, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClassItem classItem = classList.get(position);
        holder.titleTextView.setText(classItem.getTitle());
        // 이미지 로딩 등 추가적인 처리를 진행할 수 있습니다.
        // holder.imageView.setImageUrl(classItem.getImageUrl());
        // 각 텍스트뷰에 데이터 설정
        holder.day_loc.setText(classItem.getDay_loc());
        holder.rate.setText(classItem.getRate());
        holder.num_review.setText(classItem.getNum_review());
        holder.price.setText(classItem.getPrice());
        holder.checkbox_like.setChecked(classItem.isLiked());

        // 썸네일 이미지 설정
        String thumbnailResourceName = classItem.getThumbnailResourceName();
        if (thumbnailResourceName != null) {
            int thumbnailResourceId = getResourceIdByName(context, thumbnailResourceName);
            holder.imageView.setImageResource(thumbnailResourceId);
        } else {
            holder.imageView.setImageResource(R.drawable.c1);
            // 썸네일 리소스 이름이 null이면 기본 이미지 또는 처리를 원하는 방식으로 설정
            // 예: holder.imageView.setImageResource(R.drawable.default_thumbnail);
        }
    }
    // 이미지 리소스 이름으로부터 리소스 ID를 가져오는 메서드
    private int getResourceIdByName(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
    @Override
    public int getItemCount() {
        return classList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView;
        private ImageView imageView;
        private TextView day_loc;
        private TextView rate;
        private TextView num_review;
        private TextView price;
        private CheckBox checkbox_like;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.lessonImageView);
            day_loc = itemView.findViewById(R.id.day_loc);
            rate = itemView.findViewById(R.id.rate);
            num_review = itemView.findViewById(R.id.num_review);
            price = itemView.findViewById(R.id.price);
            checkbox_like = itemView.findViewById(R.id.checkbox_like);
        }


    }
}

