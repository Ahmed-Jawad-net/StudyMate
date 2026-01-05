package com.jawad.studymate.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jawad.studymate.model.Note;

import java.util.ArrayList;
import java.util.List;

public class NoteDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "studymate.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_NOTES = "notes";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_CONTENT = "content";

    public NoteDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NOTES + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " +
                COL_CONTENT + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
        onCreate(db);
    }

    public long insertNote(String title, String content) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_CONTENT, content);
        return db.insert(TABLE_NOTES, null, values);
    }

    public List<Note> getAllNotes() {
        List<Note> notes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NOTES, null, null, null, null, null, COL_ID + " DESC");

        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(cursor.getColumnIndexOrThrow(COL_ID));
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COL_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(COL_CONTENT));
                notes.add(new Note(id, title, content));
            } while (cursor.moveToNext());
        }

        cursor.close();
        return notes;
    }

    public int updateNote(long id, String title, String content) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, title);
        values.put(COL_CONTENT, content);
        return db.update(TABLE_NOTES, values, COL_ID + "=?", new String[]{String.valueOf(id)});
    }

    public int deleteNote(long id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE_NOTES, COL_ID + "=?", new String[]{String.valueOf(id)});
    }
}
