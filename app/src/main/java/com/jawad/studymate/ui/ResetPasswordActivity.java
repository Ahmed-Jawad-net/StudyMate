package com.jawad.studymate.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.jawad.studymate.R;

public class ResetPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        EditText etNewPass = findViewById(R.id.etNewPassword);
        Button btnSave = findViewById(R.id.btnSaveNew);

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);

        btnSave.setOnClickListener(v -> {
            prefs.edit().putString("savedPass", etNewPass.getText().toString()).apply();
            Toast.makeText(this, "Password updated!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
