package com.immortal.assignment.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.immortal.assignment.R;
import com.immortal.assignment.model.Image;
import com.immortal.assignment.view.DetailsScreen;

import java.util.ArrayList;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private Context context;
    ArrayList<Image> imageArrayList;
    private Intent intent;

    public ImageAdapter(Context context, ArrayList<Image> imageArrayList) {
        this.context = context;
        this.imageArrayList = imageArrayList;
    }

    @NonNull
    @Override
    public ImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.cardview_image, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ViewHolder viewHolder, int i) {
        Image image = imageArrayList.get(i);
        Log.e("image", image.getId() + "-" + image.getSecret());

        String image_url = "https://farm" + image.getFarm() + "." + "staticflickr.com/" + image.getServer() + "/" + image.getId() + "_" + image.getSecret() + ".jpg";

        Log.e("image_url", image_url);
        Glide.with(context)
                .load(image_url)
                .into(viewHolder.imgView);

        viewHolder.imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent = new Intent(context, DetailsScreen.class);
                intent.putExtra("img_url", image_url);
                context.startActivity(intent);
//    }
            }
        });
    }

    @Override
    public int getItemCount() {
        return imageArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imgView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView = (ImageView) itemView.findViewById(R.id.imgView);
        }
    }

}
