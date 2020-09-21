package com.immortal.assignment.view_model;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.immortal.assignment.repository.ImageRepository;
import com.immortal.assignment.response.ImageResponse;

import static com.immortal.assignment.constants.AppConstant.API_FORMAT;
import static com.immortal.assignment.constants.AppConstant.API_KEY;
import static com.immortal.assignment.constants.AppConstant.API_METHOD;
import static com.immortal.assignment.constants.AppConstant.API_PAGE;
import static com.immortal.assignment.constants.AppConstant.API_PER_PAGE;
import static com.immortal.assignment.constants.AppConstant.API_TAGS;
import static com.immortal.assignment.constants.AppConstant.NO_JSON_CALLBACK;
import static com.immortal.assignment.constants.AppConstant.SAFE_SEARCH;

public class ImageViewModel extends AndroidViewModel {

    private ImageRepository imageRepository;
    private LiveData<ImageResponse> imageResponseLiveData;
    private Intent intent;

    public ImageViewModel(@NonNull Application application) {
        super(application);

        imageRepository = new ImageRepository();
        this.imageResponseLiveData = imageRepository.getImages(API_METHOD, API_KEY, API_FORMAT,
                NO_JSON_CALLBACK, SAFE_SEARCH, API_TAGS, API_PER_PAGE, API_PAGE);

    }

    public LiveData<ImageResponse> getImageResponseLiveData() {
        return imageResponseLiveData;
    }
}
