package com.example.main_01.mypage;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.main_01.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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



    // 아이템 클릭 리스너 인터페이스 정의
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    private static OnItemClickListener onItemClickListener;

    // 클릭 리스너 설정 메소드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
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
        // 체크박스 상태 설정
        holder.checkbox_like.setChecked(classItem.isChecked());

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

//        holder.checkbox_like.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int clickedPosition = holder.getAdapterPosition(); // 변경된 부분
//
//                if (onItemClickListener != null) {
//                    onItemClickListener.onItemClick(clickedPosition);
//
//                    // 클릭된 아이템이 체크 해제되면 해당 아이템의 like 필드 값을 업데이트
//                    boolean newLikeState = holder.checkbox_like.isChecked();
//                    updateItem(clickedPosition, newLikeState);
//                }
//            }
//        });
        holder.checkbox_like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int clickedPosition = holder.getAdapterPosition();

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(clickedPosition);

                    // 클릭된 아이템이 체크 해제되면 해당 아이템의 like 필드 값을 업데이트
                    boolean newLikeState = holder.checkbox_like.isChecked();
                    updateItem(clickedPosition, newLikeState);
                }
            }
        });


    }
    // 이미지 리소스 이름으로부터 리소스 ID를 가져오는 메서드
    private int getResourceIdByName(Context context, String resourceName) {
        return context.getResources().getIdentifier(resourceName, "drawable", context.getPackageName());
    }
    @Override
    public int getItemCount() {
        return classList.size();
    }

    public void updateItem(int position, boolean isLiked) {
        if (position >= 0 && position < classList.size()) {
            ClassItem classItem = classList.get(position);
            String documentId = classItem.getDocumentId();
            updateFirestoreLikeState(documentId, classItem.isChecked());

            // 아이템의 like 필드 갱신
            classItem.setLiked(classItem.isChecked());
            classItem.setChecked(classItem.isChecked());

            // 체크가 해제되었을 때 해당 아이템을 삭제하고 RecyclerView 갱신
            if (!classItem.isChecked()) {
////                classList.remove(position);
//                notifyItemRangeRemoved(position - 1, 1);
//
//                notifyDataSetChanged(); // 전체 데이터셋을 새로 고침
////                Log.d("MyAdapter", "Item removed at position: " + position + ", Total items: " + classList.size());
//                Log.d("MyAdapter", "Item removed at position: " + (position - 1) + ", Total items: " + classList.size());

                classList.remove(position);
                notifyItemRemoved(position);
                Log.d("MyAdapter", "Item removed at position: " + (position));

            } else {
                notifyItemChanged(position);
                Log.d("MyAdapter", "Item changed at position: " + position);
            }
        }
    }



    public void addClass(ClassItem classItem) {
        // 데이터를 추가하는 로직
        classList.add(classItem);
        notifyDataSetChanged(); // 데이터가 변경되었음을 알림
        Log.d("MyAdapter", "Item added, Total items: " + classList.size());
    }


    public void removeClass(int position) {
        // 데이터를 삭제하는 로직
        classList.remove(position);
        notifyDataSetChanged(); // 데이터가 변경되었음을 알림
    }



    // Firestore에서 like 필드 업데이트 메서드
    private void updateFirestoreLikeState(String documentId, boolean newLikeState) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("Class")
                .document(documentId)
                .update("like", newLikeState)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 업데이트 성공 처리
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 업데이트 실패 처리
                    }
                });
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

            // 아이템 전체에 대한 클릭 리스너 설정
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(getAdapterPosition());
                    }
                }
            });

        }


    }
}

