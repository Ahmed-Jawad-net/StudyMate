package com.jawad.studymate.adapter;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jawad.studymate.R;
import com.jawad.studymate.network.QuizQuestion;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.QuizVH> {

    private List<QuizQuestion> list;

    public QuizAdapter(List<QuizQuestion> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public QuizVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_quiz, parent, false);
        return new QuizVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizVH holder, int position) {
        QuizQuestion q = list.get(position);
        holder.tvTitle.setText(q.title);
        holder.tvBody.setText(q.body);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setData(List<QuizQuestion> newData) {
        this.list = newData;
        notifyDataSetChanged();
    }

    static class QuizVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvBody;
        public QuizVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvQuizTitle);
            tvBody = itemView.findViewById(R.id.tvQuizBody);
        }
    }
}
