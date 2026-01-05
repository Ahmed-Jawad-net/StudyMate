package com.jawad.studymate.ui;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jawad.studymate.R;
import com.jawad.studymate.adapter.NoteAdapter;
import com.jawad.studymate.db.NoteDbHelper;
import com.jawad.studymate.model.Note;

import java.util.List;

public class NotesActivity extends AppCompatActivity {

    private NoteDbHelper db;
    private NoteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        db = new NoteDbHelper(this);
        RecyclerView rv = findViewById(R.id.rvNotes);
        rv.setLayoutManager(new LinearLayoutManager(this));

        List<Note> notes = db.getAllNotes();
        adapter = new NoteAdapter(notes, new NoteAdapter.OnItemClick() {
            @Override
            public void onEdit(Note note) {
                showEditDialog(note);
            }

            @Override
            public void onDelete(Note note) {
                db.deleteNote(note.getId());
                refresh();
            }
        });
        rv.setAdapter(adapter);

        Button btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(v -> showAddDialog());

        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                adapter.moveItem(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // No swipe action
            }
        };
        new ItemTouchHelper(callback).attachToRecyclerView(rv);
    }

    private void showAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_add_note));

        EditText etTitle = new EditText(this);
        etTitle.setHint("Task Title");
        EditText etContent = new EditText(this);
        etContent.setHint("Task Description");
        etContent.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);

        LinearLayoutCompat layout = new LinearLayoutCompat(this);
        layout.setOrientation(LinearLayoutCompat.VERTICAL);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        layout.setPadding(pad, pad, pad, pad);
        layout.addView(etTitle);
        layout.addView(etContent);

        builder.setView(layout);
        builder.setPositiveButton(getString(R.string.dialog_save), (dialog, which) -> {
            db.insertNote(etTitle.getText().toString(), etContent.getText().toString());
            refresh();
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), (dialog, which) -> dialog.dismiss());
        builder.show();
    }

    private void showEditDialog(Note note) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.dialog_edit_note));

        EditText etTitle = new EditText(this);
        etTitle.setText(note.getTitle());
        EditText etContent = new EditText(this);
        etContent.setText(note.getContent());

        LinearLayoutCompat layout = new LinearLayoutCompat(this);
        layout.setOrientation(LinearLayoutCompat.VERTICAL);
        int pad = (int) (16 * getResources().getDisplayMetrics().density);
        layout.setPadding(pad, pad, pad, pad);
        layout.addView(etTitle);
        layout.addView(etContent);

        builder.setView(layout);
        builder.setPositiveButton(getString(R.string.dialog_update), (dialog, which) -> {
            db.updateNote(note.getId(), etTitle.getText().toString(), etContent.getText().toString());
            refresh();
        });
        builder.setNeutralButton(getString(R.string.dialog_delete), (dialog, which) -> {
            db.deleteNote(note.getId());
            refresh();
        });
        builder.setNegativeButton(getString(R.string.dialog_cancel), (DialogInterface dialog, int which) -> dialog.dismiss());
        builder.show();
    }

    private void refresh() {
        adapter.setNotes(db.getAllNotes());
    }

    private void applySavedTheme() {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        String theme = prefs.getString("theme", "light");
        if ("dark".equals(theme)) {
            setTheme(R.style.Theme_StudyMate_Dark);
        } else {
            setTheme(R.style.Theme_StudyMate_Light);
        }
    }
}
