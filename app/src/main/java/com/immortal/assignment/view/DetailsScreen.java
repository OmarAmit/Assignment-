package com.immortal.assignment.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.immortal.assignment.R;
import com.immortal.assignment.utils.ZoomableImageView;

public class DetailsScreen extends AppCompatActivity {

    private ZoomableImageView zoomableImageView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_screen);
        initId();
        getdatafromIntent();
        Glide.with(this)
                .load(url)
                .into(zoomableImageView);
    }

    private void initId() {
        zoomableImageView = findViewById(R.id.zoomableImageView);
    }

    private void getdatafromIntent() {
        if (getIntent() != null) {
            url = getIntent().getStringExtra("img_url");
        }
    }
}