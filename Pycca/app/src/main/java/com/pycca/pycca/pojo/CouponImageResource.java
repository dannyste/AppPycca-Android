package com.pycca.pycca.pojo;

public class CouponImageResource {
    private String name;
    private String description;
    private String path;
    private String url;
    private String code;

    public CouponImageResource() {
    }

    public CouponImageResource(String name, String description, String path, String url, String code) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.url = url;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
