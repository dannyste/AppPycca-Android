package com.pycca.pycca.pojo;

public class DivisionImageResource {
    private String name;
    private String description;
    private String path;
    private String url;
    private String order;

    public DivisionImageResource() {
    }

    public DivisionImageResource(String name, String description, String path, String url, String order) {
        this.name = name;
        this.description = description;
        this.path = path;
        this.url = url;
        this.order = order;
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

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}
