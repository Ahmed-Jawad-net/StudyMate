package com.jawad.studymate.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jawad.studymate.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText etUser, etPass;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUser = findViewById(R.id.etNewUser);
        etPass = findViewById(R.id.etNewPass);
        btnRegister = findViewById(R.id.btnRegister);

        SharedPreferences prefs = getSharedPreferences("app_prefs", MODE_PRIVATE);

        btnRegister.setOnClickListener(v -> {
            String username = etUser.getText().toString().trim();
            String password = etPass.getText().toString().trim();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
                return;
            }

            prefs.edit()
                    .putString("savedUser", username)
                    .putString("savedPass", password)
                    .apply();

            Toast.makeText(this, "Registered successfully!", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
