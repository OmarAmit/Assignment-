package com.immortal.assignment.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.immortal.assignment.model.Image;

import java.util.List;

public class ImageResponse {

    @SerializedName("photos")
    @Expose
    public Photos photos;

    public Photos getPhoto() {
        return photos;
    }

    public void setPhoto(Photos photo) {
        this.photos = photo;
    }

    @SerializedName("stat")
    @Expose
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public static class Photos {


        @SerializedName("total")
        @Expose
        private String total;

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        @SerializedName("photo")
        @Expose
        private List<Image> imageList = null;

        public List<Image> getImageList() {
            return imageList;
        }

        public void setImageList(List<Image> imageList) {
            this.imageList = imageList;
        }
    }
}
