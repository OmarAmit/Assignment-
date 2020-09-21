package com.immortal.assignment.DataSource;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.immortal.assignment.model.Image;
import com.immortal.assignment.response.ImageResponse;
import com.immortal.assignment.retrofit.RetrofitRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.immortal.assignment.constants.AppConstant.API_FORMAT;
import static com.immortal.assignment.constants.AppConstant.API_KEY;
import static com.immortal.assignment.constants.AppConstant.API_METHOD;
import static com.immortal.assignment.constants.AppConstant.API_PAGE;
import static com.immortal.assignment.constants.AppConstant.API_PER_PAGE;
import static com.immortal.assignment.constants.AppConstant.API_TAGS;
import static com.immortal.assignment.constants.AppConstant.FIRST_PAGE;
import static com.immortal.assignment.constants.AppConstant.NO_JSON_CALLBACK;
import static com.immortal.assignment.constants.AppConstant.SAFE_SEARCH;

public class ItemDataSource extends PageKeyedDataSource<Integer, Image> {

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Image> callback) {

        RetrofitRequest.getInsance()
                .getApi()
                .getImages(API_METHOD, API_KEY, API_FORMAT,
                        NO_JSON_CALLBACK, SAFE_SEARCH, API_TAGS, API_PER_PAGE, API_PAGE).enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                Log.e("response:: initial", "" + response);

                if (response.body() != null) {

                    callback.onResult(response.body().getPhoto().getImageList(), null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Image> callback) {

        RetrofitRequest.getInsance()
                .getApi()
                .getImages(API_METHOD, API_KEY, API_FORMAT,
                        NO_JSON_CALLBACK, SAFE_SEARCH, API_TAGS, API_PER_PAGE, params.key + "")
                .enqueue(new Callback<ImageResponse>() {
                    @Override
                    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {

                        Log.e("response:: before ", "" + response);

                        if (response.body() != null) {
                            Integer key = (params.key > 1) ? params.key - 1 : null;
                            callback.onResult(response.body().getPhoto().getImageList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageResponse> call, Throwable t) {

                    }
                });

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Image> callback) {

        RetrofitRequest.getInsance()
                .getApi()
                .getImages(API_METHOD, API_KEY, API_FORMAT,
                        NO_JSON_CALLBACK, SAFE_SEARCH, API_TAGS, API_PER_PAGE, params.key + "")
                .enqueue(new Callback<ImageResponse>() {
                    @Override
                    public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                        Log.e("response:: after ", "" + response);

                        if (response.body() != null) {
                            Integer key = response.body().getStatus().equals("ok") ? params.key + 1 : null;
                            callback.onResult(response.body().getPhoto().getImageList(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ImageResponse> call, Throwable t) {

                    }
                });
    }
}
