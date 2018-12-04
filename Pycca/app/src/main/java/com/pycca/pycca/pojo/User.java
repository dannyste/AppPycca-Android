package com.pycca.pycca.pojo;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {

    private String registrationProvider;
    private String photoUrl;
    private String name;
    private String email;
    private String password;
    private String accountPhoneNumber;
    private String nativePhoneNumber;
    private boolean clubPyccaPartner;
    private String identificationCard;
    private String clubPyccaCardNumber;
    private String namesClubPyccaPartner;
    private String surnamesClubPyccaPartner;
    private int accountNumber;
    private String clientSince;
    private String token;
    private Date creationDate;
    private Date modificationDate;

    public User() {
        this.registrationProvider = "";
        this.photoUrl = "";
        this.name = "";
        this.email = "";
        this.password = "";
        this.accountPhoneNumber = "";
        this.nativePhoneNumber = "";
        this.clubPyccaPartner = false;
        this.identificationCard = "";
        this.clubPyccaCardNumber = "";
        this.namesClubPyccaPartner = "";
        this.surnamesClubPyccaPartner = "";
        this.accountNumber = 0;
        this.clientSince = "";
        this.token = "";
        this.creationDate = null;
        this.modificationDate = null;
    }

    public String getRegistrationProvider() {
        return registrationProvider;
    }

    public void setRegistrationProvider(String registrationProvider) {
        this.registrationProvider = registrationProvider;
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

    public String getAccountPhoneNumber() {
        return accountPhoneNumber;
    }

    public void setAccountPhoneNumber(String accountPhoneNumber) {
        this.accountPhoneNumber = accountPhoneNumber;
    }

    public String getNativePhoneNumber() {
        return nativePhoneNumber;
    }

    public void setNativePhoneNumber(String nativePhoneNumber) {
        this.nativePhoneNumber = nativePhoneNumber;
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

    public String getNamesClubPyccaPartner() {
        return namesClubPyccaPartner;
    }

    public void setNamesClubPyccaPartner(String namesClubPyccaPartner) {
        this.namesClubPyccaPartner = namesClubPyccaPartner;
    }

    public String getSurnamesClubPyccaPartner() {
        return surnamesClubPyccaPartner;
    }

    public void setSurnamesClubPyccaPartner(String surnamesClubPyccaPartner) {
        this.surnamesClubPyccaPartner = surnamesClubPyccaPartner;
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

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public Map<String, Object> getMap(){
        Map<String, Object> map = new HashMap<>();
        map.put("registrationProvider", this.registrationProvider);
        map.put("photoUrl", this.photoUrl);
        map.put("name", this.name);
        map.put("email", this.email);
        map.put("password", this.password);
        map.put("accountPhoneNumber", this.accountPhoneNumber);
        map.put("nativePhoneNumber", this.nativePhoneNumber);
        map.put("clubPyccaPartner", this.clubPyccaPartner);
        map.put("identificationCard", this.identificationCard);
        map.put("clubPyccaCardNumber", this.clubPyccaCardNumber);
        map.put("namesClubPyccaPartner", this.namesClubPyccaPartner);
        map.put("surnamesClubPyccaPartner", this.surnamesClubPyccaPartner);
        map.put("accountNumber", this.accountNumber);
        map.put("clientSince", this.clientSince);
        map.put("token", this.token);
        map.put("creationDate", this.creationDate);
        map.put("modificationDate", this.modificationDate);
        return map;
    }

}
