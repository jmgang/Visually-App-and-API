package com.visually.client;

import com.visually.types.SignLanguageTranslationResponse;
import com.visually.types.VideoRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TranslateSignLanguageClient {

    @POST("api/v1/translate/")
    Call<SignLanguageTranslationResponse> translateSignLanguage(@Body VideoRequest requestBody);

}
