package com.pycca.pycca.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class ImageResource implements Serializable {
    private String name;
    private String description;
    private String path;
    private String url;

    public ImageResource() {
    }

    public ImageResource(String name, String description, String path, String url) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
