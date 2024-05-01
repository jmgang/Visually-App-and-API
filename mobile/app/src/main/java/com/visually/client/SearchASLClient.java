package com.visually.client;

import com.visually.types.SearchASLResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchASLClient {

    @GET("api/v1/search/")
    Call<List<SearchASLResponse>> searchASLMaterials(@Query("query") String query, @Query("size") Integer size);

}
