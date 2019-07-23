package com.example.firebasedatabasetest;

import android.net.Uri;

public class Photo {
    private String uri;
    private String uriKey;

    public String getUri() {
        return uri;
    }
    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUriKey() {
        return uriKey;
    }

    public void setUriKey(String uriKey) {
        this.uriKey = uriKey;
    }
}
