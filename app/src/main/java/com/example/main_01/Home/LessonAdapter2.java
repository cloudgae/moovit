package com.example.main_01.Home;

import android.annotation.SuppressLint;
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

import com.example.main_01.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonAdapter2 extends RecyclerView.Adapter<LessonAdapter2.LessonViewHolder> {

    private List<Lesson2> lessonList;
    private AdapterView.OnItemClickListener listener;
    private OnCheckboxClickListener onCheckboxClickListener;

    public interface OnItemClickListener {
        void onItemClick(Lesson2 lesson);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = (AdapterView.OnItemClickListener) listener;
    }

    public LessonAdapter2(List<Lesson2> lessonList, OnCheckboxClickListener onCheckboxClickListener) {
        this.lessonList = lessonList;
        this.onCheckboxClickListener = onCheckboxClickListener;
    }

    @NonNull
    @Override
    public LessonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_lesson, parent, false);
        return new LessonViewHolder(view, (OnItemClickListener) listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LessonViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Lesson2 lesson = lessonList.get(position);

        holder.rankTextView.setText(lesson.getRank());
        holder.lessonImageView.setImageResource(lesson.getImageResource());
        holder.nameTextView.setText(lesson.getName());
        holder.addressTextView.setText(lesson.getAddress());
        holder.rateTextView.setText(lesson.getRate());
        holder.num_reviewTextView.setText(lesson.getNum_review());

        holder.favoriteCheckBox.setChecked(lesson.isChecked());
        holder.favoriteCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                lesson.setChecked(isChecked);
                // 새로운 체크박스 상태로 Firestore 문서 업데이트
                updateFirestoreDocument(lesson);
                if (onCheckboxClickListener != null) {
                    onCheckboxClickListener.onCheckboxClick(position, isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return lessonList.size();
    }

    public class LessonViewHolder extends RecyclerView.ViewHolder {
        public TextView rankTextView;
        public ImageView lessonImageView;
        public TextView nameTextView;
        public TextView addressTextView;
        public TextView rateTextView;
        public TextView num_reviewTextView;
        private OnItemClickListener listener;
        public CheckBox favoriteCheckBox;

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
                    if (position != RecyclerView.NO_POSITION && LessonViewHolder.this.listener != null) {
                        LessonViewHolder.this.listener.onItemClick(lessonList.get(position));
                    }
                }
            });
        }
    }

    private void updateFirestoreDocument(Lesson2 lesson) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentRef = db.collection("Class").document(lesson.getDocumentId());

        // 체크박스 필드를 업데이트할 맵 생성
        Map<String, Object> updates = new HashMap<>();
        updates.put("like", lesson.isChecked());

        // Firestore 문서 업데이트
        documentRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // 문서가 성공적으로 업데이트됨
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // 오류 처리
                    }
                });
    }

    public interface OnCheckboxClickListener {
        void onCheckboxClick(int position, boolean isChecked);
    }

    public void setOnCheckboxClickListener(OnCheckboxClickListener listener) {
        this.onCheckboxClickListener = listener;
    }
}
