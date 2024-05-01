package com.visually.translate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.visually.R;
import com.visually.search.SearchActivity;

import java.util.Objects;

public class TranslateSLActivity extends AppCompatActivity {

    private static final int REQUEST_PICK_VIDEO = 101;

    private CardView realTimeVideoCardView;

    private CardView recordedVideoCardView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_translate_slactivity);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        realTimeVideoCardView = findViewById(R.id.realtimeRecordSLCardView);
        recordedVideoCardView = findViewById(R.id.recordedVideoSLCardView);

        realTimeVideoCardView.setOnClickListener(this::goToRealTimeActivity);
        recordedVideoCardView.setOnClickListener(this::goToRecordedActivity);
    }

    private void goToRealTimeActivity(View view) {
        startActivity(new Intent(getApplicationContext(), TranslateRealTimeSLActivity.class));
    }

    private void goToRecordedActivity(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_PICK_VIDEO);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PICK_VIDEO && resultCode == RESULT_OK && data != null) {
            Uri selectedVideoUri = data.getData();
            if (selectedVideoUri != null) {
                Intent intent = new Intent(TranslateSLActivity.this, ViewSignLanguageActivity.class);
                intent.putExtra("videoUri", selectedVideoUri.toString());
                startActivity(intent);
            }
        }
    }
}