package com.pycca.pycca.pojo;

public class Card {

    private String clubPyccaCardNumber;
    private String name;
    private boolean holder;

    public Card() {

    }

    public Card(String clubPyccaCardNumber, String name, boolean holder) {
        this.clubPyccaCardNumber = clubPyccaCardNumber;
        this.name = name;
        this.holder = holder;
    }

    public String getClubPyccaCardNumber() {
        return clubPyccaCardNumber;
    }

    public void setClubPyccaCardNumber(String clubPyccaCardNumber) {
        this.clubPyccaCardNumber = clubPyccaCardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHolder() {
        return holder;
    }

    public void setHolder(boolean holder) {
        this.holder = holder;
    }
    
}
