package com.visually;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.visually.search.SearchActivity;
import com.visually.translate.TranslateRealTimeSLActivity;
import com.visually.translate.TranslateSLActivity;

public class MainActivity extends AppCompatActivity {

    private CardView translateSLBtn;

    private CardView searchASLBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        translateSLBtn = findViewById(R.id.signLanguageTranslationCardView);
        searchASLBtn = findViewById(R.id.searchASLCardView);

        translateSLBtn.setOnClickListener(this::goToTranslateActivity);
        searchASLBtn.setOnClickListener(this::goToSearchActivity);
    }

    private void goToSearchActivity(View view) {
        startActivity(new Intent(getApplicationContext(), SearchActivity.class));
    }

    private void goToTranslateActivity(View view) {
        startActivity(new Intent(getApplicationContext(), TranslateSLActivity.class));
    }
}