package com.pycca.pycca.pojo;

public class Coupon {
    private String imageLink;
    private String date;

    public Coupon(String imageLink, String date) {
        this.imageLink = imageLink;
        this.date = date;
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
}
