package com.pycca.pycca.pojo;

public class Division {
    private String imageLink;
    private String date;
    private int order;

    public Division(String imageLink, String date, int order) {
        this.imageLink = imageLink;
        this.date = date;
        this.order = order;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
