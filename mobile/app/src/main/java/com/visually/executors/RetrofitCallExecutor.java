package com.visually.executors;


import com.visually.environment.Environment;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;


public class RetrofitCallExecutor {

    private final Retrofit retrofit;

    public RetrofitCallExecutor() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Environment.ROOT_PATH)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public RetrofitCallExecutor(final String url) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // Set the desired timeout values (in seconds)
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);

        OkHttpClient client = httpClient.build();

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(client)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public <T> T getService( Class<T> clazz ) {
        return retrofit.create(clazz);
    }
}
