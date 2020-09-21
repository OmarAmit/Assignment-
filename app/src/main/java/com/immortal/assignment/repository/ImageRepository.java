package com.immortal.assignment.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.immortal.assignment.response.ImageResponse;
import com.immortal.assignment.retrofit.ApiRequest;
import com.immortal.assignment.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageRepository{
    private static final String TAG = ImageRepository.class.getSimpleName();
    private ApiRequest apiRequest;

    public ImageRepository() {
        apiRequest = RetrofitRequest.getRetrofitInstance().create(ApiRequest.class);
    }

    public LiveData<ImageResponse> getImages(String apiMethod, String apiKey, String apiFormat, String noJsonCallback,
                                             String safeSearch, String apiTags, String apiPerPage, String apiPage) {
        final MutableLiveData<ImageResponse> data = new MutableLiveData<>();
        apiRequest.getImages(apiMethod, apiKey, apiFormat,
                noJsonCallback, safeSearch, apiTags, apiPerPage, apiPage)
                .enqueue(new Callback<ImageResponse>() {

                    @Override
                    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                        Log.e("response:: ", "" + response);


                        if (response.body() != null) {
                            data.setValue(response.body());
                            Log.e("response->body", "" + response.body().toString());

                            Log.e(TAG, "articles size:: " + response.body().getStatus());
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageResponse> call, Throwable t) {
                        data.setValue(null);
                    }
                });
        return data;
    }
}
