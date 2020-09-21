package com.immortal.assignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.immortal.assignment.R;
import com.immortal.assignment.adapter.ImageAdapter;
import com.immortal.assignment.model.Image;
import com.immortal.assignment.view_model.ImageViewModel;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recycler_view;
    private ProgressBar progress_circular;
    private LinearLayoutManager layoutManager;
    private ImageAdapter adapter;
    private ArrayList<Image> imageArrayList = new ArrayList<>();
    ImageViewModel imageViewModel;
    private Context context;
    private FloatingActionButton fab_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        initView();
        setAdapter();
        getImages();
    }

    private void setAdapter() {
        layoutManager = new GridLayoutManager(context, 2);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setHasFixedSize(true);
        adapter = new ImageAdapter(MainActivity.this, imageArrayList);
        recycler_view.setAdapter(adapter);
        imageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
    }

    private void initView() {
        progress_circular = findViewById(R.id.progress_circular);
        recycler_view = findViewById(R.id.recycler_view);
        fab_button = findViewById(R.id.fab_button);
    }

    private void getImages() {
        imageViewModel.getImageResponseLiveData().observe(this, imageResponse -> {
            if (imageResponse != null) {

                progress_circular.setVisibility(View.GONE);
                Log.e("getTotal", imageResponse.getStatus());
                Log.e("getTotal", imageResponse.getPhoto().getTotal());
                List<Image> imageList = imageResponse.getPhoto().getImageList();
                imageArrayList.addAll(imageList);
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void changeLayout(View view) {
        Log.e("List", fab_button.getTag() + "-");
        if (fab_button.getTag().equals("list")) {
            layoutManager = new GridLayoutManager(context, 1);
            recycler_view.setLayoutManager(layoutManager);
            fab_button.setTag("grid");

        } else {
            layoutManager = new GridLayoutManager(context, 2);
            recycler_view.setLayoutManager(layoutManager);
            fab_button.setTag("list");
        }
    }
}