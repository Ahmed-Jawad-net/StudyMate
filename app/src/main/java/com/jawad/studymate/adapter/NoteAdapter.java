package com.jawad.studymate.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jawad.studymate.R;
import com.jawad.studymate.model.Note;

import java.util.Collections;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteVH> {

    public interface OnItemClick {
        void onEdit(Note note);
        void onDelete(Note note);
    }

    private List<Note> notes;
    private final OnItemClick listener;

    public NoteAdapter(List<Note> notes, OnItemClick listener) {
        this.notes = notes;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NoteVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVH holder, int position) {
        Note note = notes.get(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());

        int[] colors = holder.itemView.getResources().getIntArray(R.array.note_colors);
        holder.card.setCardBackgroundColor(colors[position % colors.length]);

        holder.itemView.setOnClickListener(v -> listener.onEdit(note));
        holder.itemView.setOnLongClickListener(v -> {
            listener.onDelete(note);
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> newNotes) {
        this.notes = newNotes;
        notifyDataSetChanged();
    }

    public void moveItem(int from, int to) {
        Collections.swap(notes, from, to);
        notifyItemMoved(from, to);
    }

    static class NoteVH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvContent;
        CardView card;

        public NoteVH(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvContent = itemView.findViewById(R.id.tvContent);
            card = itemView.findViewById(R.id.cardNote);
        }
    }
}
