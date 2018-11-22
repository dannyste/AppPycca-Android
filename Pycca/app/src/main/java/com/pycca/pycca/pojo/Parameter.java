package com.pycca.pycca.pojo;

import java.util.HashMap;
import java.util.Map;

public class Parameter {

    private String phoneNumber;
    private String email;

    public Parameter() {
        this.phoneNumber = "";
        this.email = "";
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("phoneNumber", this.phoneNumber);
        map.put("email", this.email);
        return map;
    }

}
