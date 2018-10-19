package com.pycca.pycca.pojo;

import java.util.HashMap;
import java.util.Map;

public class User {

    private String photo_url;
    private String name;
    private String identification;
    private String card_number;
    private String launching_date;
    private String email;
    private String password;
    private String register_origin;

    public User(){}

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String photo_url, String name, String identification, String card_number, String launching_date, String email, String password, String register_origin) {
        this.photo_url = photo_url;
        this.name = name;
        this.identification = identification;
        this.card_number = card_number;
        this.launching_date = launching_date;
        this.email = email;
        this.password = password;
        this.register_origin = register_origin;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getLaunching_date() {
        return launching_date;
    }

    public void setLaunching_date(String launching_date) {
        this.launching_date = launching_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegister_origin() {
        return register_origin;
    }

    public void setRegister_origin(String register_origin) {
        this.register_origin = register_origin;
    }

    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.name);
        map.put("email", this.email);
        map.put("card_number", this.card_number);
        map.put("identification", this.identification);
        map.put("launching_date", this.launching_date);
        map.put("photo_url", this.photo_url);
        map.put("password", this.password);
        map.put("register_origin", this.register_origin);
        return map;
    }

}
