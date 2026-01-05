package com.jawad.studymate.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.jawad.studymate.R;

public class WebViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        applySavedTheme();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        WebView webView = findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true); // Safe if loading trusted content
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://en.wikipedia.org/wiki/Android_(operating_system)");
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
