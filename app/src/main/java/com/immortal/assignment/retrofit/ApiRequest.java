package com.immortal.assignment.retrofit;


import com.immortal.assignment.response.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiRequest {

    @GET("services/rest/")
    Call<ImageResponse> getImages(
            @Query("method") String method,
            @Query("api_key") String api_key,
            @Query("format") String format,
            @Query("nojsoncallback") String nojsoncallback,
            @Query("safe_search") String safe_search,
            @Query("tags") String tags,
            @Query("per_page") String per_page,
            @Query("page") String page


    );
}
//https://api.flickr.com/services/rest/?method=flickr.photos.search
//// &api_key=3e7cc266ae2b0e0d78e279ce8e361736
//// &format=json
//// &nojsoncallback=1
//// &safe_search=1
//// &tags=kitten
//// &per_page=10
//// &page=1