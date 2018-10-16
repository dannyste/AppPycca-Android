package com.pycca.pycca.pojo;

public class ClubPycca {

    private int color;
    private int image;
    private String name;

    public ClubPycca(int color, int image, String name) {
        this.color = color;
        this.image = image;
        this.name = name;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
