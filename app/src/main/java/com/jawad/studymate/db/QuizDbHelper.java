package com.jawad.studymate.db;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.jawad.studymate.network.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "studymate.db";
    private static final int DB_VERSION = 1;

    public static final String TABLE_QUIZ = "quiz_questions";
    public static final String COL_ID = "id";
    public static final String COL_TITLE = "title";
    public static final String COL_BODY = "body";

    public QuizDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUIZ + " (" +
                COL_ID + " INTEGER PRIMARY KEY," +
                COL_TITLE + " TEXT," +
                COL_BODY + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Keep it simple
    }

    public void saveQuestions(List<QuizQuestion> list) {
        SQLiteDatabase db = getWritableDatabase();
        for (QuizQuestion q : list) {
            ContentValues cv = new ContentValues();
            cv.put(COL_ID, q.id);
            cv.put(COL_TITLE, q.title);
            cv.put(COL_BODY, q.body);
            db.insertWithOnConflict(TABLE_QUIZ, null, cv, SQLiteDatabase.CONFLICT_REPLACE);
        }
    }

    public List<QuizQuestion> getQuestions() {
        List<QuizQuestion> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(TABLE_QUIZ, null, null, null, null, null, COL_ID + " ASC");
        if (c != null) {
            while (c.moveToNext()) {
                QuizQuestion q = new QuizQuestion();
                q.id = c.getInt(c.getColumnIndexOrThrow(COL_ID));
                q.title = c.getString(c.getColumnIndexOrThrow(COL_TITLE));
                q.body = c.getString(c.getColumnIndexOrThrow(COL_BODY));
                list.add(q);
            }
            c.close();
        }
        return list;
    }
}
