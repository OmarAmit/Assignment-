package com.immortal.assignment.view_model;

import android.app.Application;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.immortal.assignment.DataSource.ItemDataSource;
import com.immortal.assignment.DataSource.ItemDataSourceFactory;
import com.immortal.assignment.model.Image;

import static com.immortal.assignment.constants.AppConstant.PAGE_SIZE;

public class ImageViewModel extends AndroidViewModel {

    public LiveData<PagedList<Image>> itemPagedList;

    private LiveData<PageKeyedDataSource<Integer, Image>> liveDataSource;

    private Intent intent;

    public ImageViewModel(@NonNull Application application) {
        super(application);

        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(PAGE_SIZE)
                        .build();
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, config)).build();

    }
}
