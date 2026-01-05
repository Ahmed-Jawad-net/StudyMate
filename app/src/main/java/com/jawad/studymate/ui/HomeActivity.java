package com.jawad.studymate.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.jawad.studymate.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnNotes = findViewById(R.id.btnNotes);
        Button btnQuiz = findViewById(R.id.btnQuiz);
        Button btnWeb = findViewById(R.id.btnWeb);
        Button btnSettings = findViewById(R.id.btnSettings);

        btnNotes.setOnClickListener(v -> startActivity(new Intent(this, NotesActivity.class)));
        btnQuiz.setOnClickListener(v -> startActivity(new Intent(this, QuizActivity.class)));
        btnWeb.setOnClickListener(v -> startActivity(new Intent(this, WebViewActivity.class)));
        btnSettings.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if (id == R.id.menu_logout) {
            SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
            prefs.edit().putBoolean("isLoggedIn", false).apply();
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
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
