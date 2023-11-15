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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.LessonViewHolder> {

    private List<Lesson> lessonList;
    private AdapterView.OnItemClickListener listener;
    private OnCheckboxClickListener onCheckboxClickListener;

    private String itemId; // LessonAdapter 클래스 내에서 사용할 itemId 변수
    private OnItemClickListener onItemClickListener;


    public interface OnItemClickListener{
        void onItemClick(Lesson lesson);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public LessonAdapter(List<Lesson> lessonList, OnCheckboxClickListener onCheckboxClickListener){
        this.lessonList = lessonList;
//        this.listener = (AdapterView.OnItemClickListener) listener;
        this.onCheckboxClickListener = onCheckboxClickListener; // 이 부분 수정
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
    public void onBindViewHolder(@NonNull LessonAdapter.LessonViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Lesson lesson = lessonList.get(position);

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
                // Update the Firestore document with the new checkbox state
                updateFirestoreDocument(lesson);
                if (onCheckboxClickListener != null) {
                    onCheckboxClickListener.onCheckboxClick(position, isChecked);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(lessonList.get(position));
                }
            }
        });
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

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int position = getAdapterPosition();
//                    if(position != RecyclerView.NO_POSITION && LessonViewHolder.this.listener != null){
//                        LessonViewHolder.this.listener.onItemClick(lessonList.get(position));
//
//                    }
//                }
//            });
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
    private void updateFirestoreDocument(Lesson lesson) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference documentRef = db.collection("Class").document(lesson.getDocumentId());

        // Create a map to update the checkbox field
        Map<String, Object> updates = new HashMap<>();
        updates.put("like", lesson.isChecked());

        // Update the Firestore document
        documentRef.update(updates)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Document updated successfully
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle the error
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
