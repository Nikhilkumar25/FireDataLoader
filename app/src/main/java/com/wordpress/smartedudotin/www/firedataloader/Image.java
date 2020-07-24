package com.wordpress.smartedudotin.www.firedataloader;

import android.net.Uri;

public class Image {

    private String mImageUri;
    private String mImageTitle;

    public Image(){

    }

    public Image(String imageName,String imageUrl) {
        this.mImageUri = imageUrl;
        this.mImageTitle = imageName;
    }

    public Image(Object imageName, Object imageUrl) {
        this.mImageUri = (String) imageUrl;
        this.mImageTitle = (String) imageName;
    }

    public String getmImageUri() {
        return mImageUri;
    }

    public void setmImageUri(String mImageUri) {
        this.mImageUri = mImageUri;
    }

    public String getmImageTitle() {
        return mImageTitle;
    }

    public void setmImageTitle(String mImageTitle) {
        this.mImageTitle = mImageTitle;
    }
}
