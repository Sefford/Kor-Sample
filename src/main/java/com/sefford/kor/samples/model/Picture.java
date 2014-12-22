package com.sefford.kor.samples.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sefford on 16/11/14.
 */
public class Picture {

    @SerializedName("large")
    String largeUrl;
    @SerializedName("medium")
    String normalUrl;
    @SerializedName("thumbnail")
    String thumb;

    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    public String getNormalUrl() {
        return normalUrl;
    }

    public void setNormalUrl(String normalUrl) {
        this.normalUrl = normalUrl;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }
}
