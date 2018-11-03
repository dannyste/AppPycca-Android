package com.pycca.pycca.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String photoUrl;
    private String name;
    private String email;
    private String password;
    private boolean clubPyccaPartner;
    private String identificationCard;
    private String clubPyccaCardNumber;
    private int accountNumber;
    private String clientSince;
    private String registrationProvider;
    private String token;
    private Date creationDate;

    public User() {

    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public boolean isClubPyccaPartner() {
        return clubPyccaPartner;
    }

    public void setClubPyccaPartner(boolean clubPyccaPartner) {
        this.clubPyccaPartner = clubPyccaPartner;
    }

    public String getIdentificationCard() {
        return identificationCard;
    }

    public void setIdentificationCard(String identificationCard) {
        this.identificationCard = identificationCard;
    }

    public String getClubPyccaCardNumber() {
        return clubPyccaCardNumber;
    }

    public void setClubPyccaCardNumber(String clubPyccaCardNumber) {
        this.clubPyccaCardNumber = clubPyccaCardNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getClientSince() {
        return clientSince;
    }

    public void setClientSince(String clientSince) {
        this.clientSince = clientSince;
    }

    public String getRegistrationProvider() {
        return registrationProvider;
    }

    public void setRegistrationProvider(String registrationProvider) {
        this.registrationProvider = registrationProvider;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("photoUrl", this.photoUrl);
        map.put("name", this.name);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("clubPyccaPartner", this.clubPyccaPartner);
        map.put("identificationCard", this.identificationCard);
        map.put("clubPyccaCardNumber", this.clubPyccaCardNumber);
        map.put("accountNumber", this.accountNumber);
        map.put("clientSince", this.clientSince);
        map.put("token", this.token);
        map.put("creationDate", this.creationDate);
        return map;
    }

}
