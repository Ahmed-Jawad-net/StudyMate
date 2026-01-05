package com.jawad.studymate.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jawad.studymate.R;
import com.jawad.studymate.adapter.QuizAdapter;
import com.jawad.studymate.db.QuizDbHelper;
import com.jawad.studymate.network.QuizApi;
import com.jawad.studymate.network.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuizActivity extends AppCompatActivity {

    private QuizAdapter adapter;
    private QuizDbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        db = new QuizDbHelper(this);

        RecyclerView rv = findViewById(R.id.rvQuiz);
        rv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new QuizAdapter(new ArrayList<>());
        rv.setAdapter(adapter);

        Button btnFetch = findViewById(R.id.btnFetch);
        btnFetch.setText(getString(R.string.btn_fetch_questions));
        btnFetch.setOnClickListener(v -> fetchQuestions());

        // Load cached data if offline
        adapter.setData(db.getQuestions());
    }

    private void fetchQuestions() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/") // Replace with real API if needed
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        QuizApi api = retrofit.create(QuizApi.class);
        Call<List<QuizQuestion>> call = api.getQuestions();

        call.enqueue(new Callback<List<QuizQuestion>>() {
            @Override
            public void onResponse(Call<List<QuizQuestion>> call, Response<List<QuizQuestion>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<QuizQuestion> list = response.body();
                    db.saveQuestions(list);
                    adapter.setData(list);
                } else {
                    Toast.makeText(QuizActivity.this, getString(R.string.toast_empty_response), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<QuizQuestion>> call, Throwable t) {
                Toast.makeText(QuizActivity.this, getString(R.string.toast_network_error), Toast.LENGTH_SHORT).show();
                adapter.setData(db.getQuestions());
            }
        });
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
