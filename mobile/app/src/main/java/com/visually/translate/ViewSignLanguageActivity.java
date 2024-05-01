package com.visually.translate;

import static com.visually.utils.GCSUploader.uploadObject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.visually.MainActivity;
import com.visually.R;
import com.visually.client.SearchASLClient;
import com.visually.client.TranslateSignLanguageClient;
import com.visually.environment.Environment;
import com.visually.executors.RetrofitCallExecutor;
import com.visually.types.SignLanguageTranslationResponse;
import com.visually.types.VideoRequest;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewSignLanguageActivity extends AppCompatActivity {

    private TextView signLanguageMeaning;

    private VideoView videoView;
    private Button backButton;
    private Uri videoUri;

    private RetrofitCallExecutor retrofitCallExecutor;

    private TranslateSignLanguageClient translateSignLanguageClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_sign_language);

        retrofitCallExecutor = new RetrofitCallExecutor(Environment.ROOT_PATH);
        translateSignLanguageClient = retrofitCallExecutor.getService(TranslateSignLanguageClient.class);

        if (getIntent().hasExtra("videoUri")) {
            String videoUriString = getIntent().getStringExtra("videoUri");
            videoUri = Uri.parse(videoUriString);
            Log.d("VideoURI", "Video URI: " + videoUriString);
        }

        signLanguageMeaning = findViewById(R.id.textViewMeaning);
        videoView = findViewById(R.id.translateVideoView);
        backButton = findViewById(R.id.buttonBackToHome);

        if (videoUri != null) {
            videoView.setVideoURI(videoUri);
            videoView.start();

            translateSignLanguageVideo();
        }


        backButton.setOnClickListener(v -> {
            deleteVideo();
            startActivity(new Intent(ViewSignLanguageActivity.this, MainActivity.class));
        });
    }

    private void uploadAndTranslateSLVideo() {
        try {
            String objectName = uploadObject(getApplicationContext(), videoUri);

            Call<SignLanguageTranslationResponse> call = translateSignLanguageClient.translateSignLanguage(
                    new VideoRequest(null, objectName));
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<SignLanguageTranslationResponse> call, Response<SignLanguageTranslationResponse> response) {
                    if (response.isSuccessful()) {
                        SignLanguageTranslationResponse translationResponse = response.body();

                        final StringBuilder translationResult = new StringBuilder();
                        translationResult.append("You signed ")
                                .append(
                                        "No Response".equalsIgnoreCase(translationResponse.signLanguage()) ? "None" :
                                                translationResponse.signLanguage()
                                )
                                .toString();

                        signLanguageMeaning.setText(translationResult);
                    } else {
                        Log.d("translateSignLanguageVideo", "Response not successful");
                    }
                }

                @Override
                public void onFailure(Call<SignLanguageTranslationResponse> call, Throwable t) {
                    Log.d("uploadAndTranslateSLVideo", "Failed call.");
                    Log.d("uploadAndTranslateSLVideo", t.getMessage());
                }
            });
        } catch (IOException e) {
            Log.e("uploadAndTranslateSLVideo", e.getMessage());
        }
    }

    private void translateSignLanguageVideo() {
        try {
            InputStream inputStream = getContentResolver().openInputStream(videoUri);
            byte[] videoBytes = readBytes(inputStream);
            String videoBase64 = Base64.encodeToString(videoBytes, Base64.DEFAULT);

            Log.i("translateSignLanguageVideo", videoBase64);

            Call<SignLanguageTranslationResponse> call = translateSignLanguageClient.translateSignLanguage(
                    new VideoRequest(videoBase64, null));
            call.enqueue(new Callback<>() {
                @Override
                public void onResponse(Call<SignLanguageTranslationResponse> call, Response<SignLanguageTranslationResponse> response) {
                    if (response.isSuccessful()) {
                        SignLanguageTranslationResponse translationResponse = response.body();

                        final StringBuilder translationResult = new StringBuilder();
                        translationResult.append("You signed ")
                                .append(
                                        "No Response".equalsIgnoreCase(translationResponse.signLanguage()) ? "None" :
                                                translationResponse.signLanguage()
                                )
                                .toString();

                        signLanguageMeaning.setText(translationResult);
                    } else {
                        Log.d("translateSignLanguageVideo", "Response not successful");
                    }
                }

                @Override
                public void onFailure(Call<SignLanguageTranslationResponse> call, Throwable t) {
                    Log.d("translateSignLanguageVideo", "Failed call.");
                    Log.d("translateSignLanguageVideo", t.getMessage());
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("translateSignLanguageVideo", e.getMessage());
        }
    }

    private byte[] readBytes(InputStream inputStream) throws IOException {
        var byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void deleteVideo() {
        // Delete the video file if it exists
        if (videoUri != null) {
            File videoFile = new File(Objects.requireNonNull(videoUri.getPath()));
            if (videoFile.exists()) {
                boolean deleted = videoFile.delete();
                if (deleted) {
                    // Video file deleted successfully
                    Log.i("deleteVideo", "Video deleted.");
                } else {
                    // Failed to delete video file
                    Log.i("deleteVideo", "Failed to delete video file");
                }
            }
        }
    }
}
