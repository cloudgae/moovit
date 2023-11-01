package com.example.main_01;

import static android.content.ContentValues.TAG;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> lessonList;
    private AdapterView.OnItemClickListener listener;
    private List<Boolean> favoriteList; // 각 아이템의 즐겨찾기 상태를 저장하는 리스트
    private String itemId; // LessonAdapter 클래스 내에서 사용할 itemId 변수

    public interface OnItemClickListener{
        void onItemClick(Lesson lesson);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = (AdapterView.OnItemClickListener) listener;
    }
    public LessonAdapter(List<Lesson> lessonList, OnItemClickListener listener){
        this.lessonList = lessonList;
        this.listener = (AdapterView.OnItemClickListener) listener;
    }
    public void setFavoriteList(List<Boolean> favoriteList) {
        this.favoriteList = favoriteList;
    }
    @NonNull
    @Override
    public LessonAdapter.LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lesson,
                        parent, false);
        return new LessonViewHolder(view, (OnItemClickListener) listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonAdapter.LessonViewHolder holder, int position) {
        Lesson lesson = lessonList.get(position);

        holder.rankTextView.setText(lesson.getRank());
        holder.lessonImageView.setImageResource(lesson.getImageResource());
        holder.nameTextView.setText(lesson.getName());
        holder.addressTextView.setText(lesson.getAddress());
        holder.rateTextView.setText(lesson.getRate());
        holder.num_reviewTextView.setText(lesson.getNum_review());

    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder{
        public TextView rankTextView;
        public ImageView lessonImageView;
        public TextView nameTextView;
        public TextView addressTextView;
        public TextView rateTextView;
        public TextView num_reviewTextView;
        private OnItemClickListener listener;
        private CheckBox favoriteCheckBox;

        public LessonViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            lessonImageView = itemView.findViewById(R.id.lessonImageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            addressTextView = itemView.findViewById(R.id.addressTextView);
            rateTextView = itemView.findViewById(R.id.rateTextView);
            num_reviewTextView = itemView.findViewById(R.id.num_reviewTextView);
            favoriteCheckBox = itemView.findViewById(R.id.checkbox_like);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION && LessonViewHolder.this.listener != null){
                        LessonViewHolder.this.listener.onItemClick(lessonList.get(position));

                    }
                }
            });
        }
    }

    public List<Lesson> rearrangeLessonList(List<Lesson> originalList){
        List<Lesson> rearrangedList = new ArrayList<>();

        int numRows = 2; //행 수
        int numColumns = 3;

        for (int col = 0; col < numColumns; col++){
            for (int row = 0; row < numRows; row++){
                int index = col + row * numColumns;
                if(index < originalList.size()){
                    rearrangedList.add(originalList.get(index));
                }
            }
        }
        return rearrangedList;
    }
}
