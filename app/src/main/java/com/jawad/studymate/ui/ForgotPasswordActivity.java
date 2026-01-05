package com.jawad.studymate.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.jawad.studymate.R;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        EditText etUser = findViewById(R.id.etForgotUser);
        Button btnReset = findViewById(R.id.btnReset);

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);

        btnReset.setOnClickListener(v -> {
            String savedUser = prefs.getString("savedUser", "student");
            if (etUser.getText().toString().equals(savedUser)) {
                startActivity(new Intent(this, ResetPasswordActivity.class));
            } else {
                Toast.makeText(this, "User not found!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
