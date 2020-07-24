package com.wordpress.smartedudotin.www.firedataloader;

import android.net.Uri;

public class Video {

    private String mVideoUri;
    private String mVideoName;

    public Video(String videoName,String videoUrl) {
        this.mVideoName = videoName;
        this.mVideoUri = videoUrl;
    }

    public Video(Object videoName,Object videoUrl) {
        this.mVideoName = (String) videoName;
        this.mVideoUri = (String) videoUrl;
    }

    public String getmVideoUri() {
        return mVideoUri;
    }

    public void setmVideoUri(String mVideoUri) {
        this.mVideoUri = mVideoUri;
    }

    public String getmVideoName() {
        return mVideoName;
    }

    public void setmVideoName(String mVideoName) {
        this.mVideoName = mVideoName;
    }
}
