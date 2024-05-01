package com.visually.search;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;

import com.visually.R;

import java.util.Objects;

public class ViewASLMaterialActivity extends AppCompatActivity {

    private TextView titleTextView, descriptionTextView, urlTextView;
    private VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_aslmaterial);

        titleTextView = findViewById(R.id.viewTitleTextView);
        descriptionTextView = findViewById(R.id.viewDescriptionTextView);
        urlTextView = findViewById(R.id.viewUrlTextView);
        videoView = findViewById(R.id.viewUrlVideoView);

        Intent intent = getIntent();
        if (intent != null) {
            String title = intent.getStringExtra("title");
            String description = intent.getStringExtra("description");
            String url = intent.getStringExtra("url");
            String videoUrl = intent.getStringExtra("videoUrl");

            titleTextView.setText(title);
            descriptionTextView.setText(description);
            urlTextView.setText(url);

            Uri uri = Uri.parse(videoUrl);
            videoView.setVideoURI(uri);
            videoView.start();
        }
    }

}