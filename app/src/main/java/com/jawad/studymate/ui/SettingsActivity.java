package com.jawad.studymate.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jawad.studymate.R;

public class SettingsActivity extends AppCompatActivity {

    private Button btnLight, btnDark, btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        btnLight = findViewById(R.id.btnLight);
        btnDark = findViewById(R.id.btnDark);
        btnLogout = findViewById(R.id.btnLogout);

        btnLight.setOnClickListener(v -> {
            saveTheme("light");
            Toast.makeText(this, "Switched to Light Theme", Toast.LENGTH_SHORT).show();
            restartApp();
        });

        btnDark.setOnClickListener(v -> {
            saveTheme("dark");
            Toast.makeText(this, "Switched to Dark Theme", Toast.LENGTH_SHORT).show();
            restartApp();
        });

        btnLogout.setOnClickListener(v -> {
            SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
            prefs.edit().putBoolean("isLoggedIn", false).apply();
            Intent i = new Intent(this, LoginActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        });
    }

    private void saveTheme(String theme) {
        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);
        prefs.edit().putString("theme", theme).apply();
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

    private void restartApp() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
