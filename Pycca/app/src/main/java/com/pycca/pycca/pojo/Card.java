package com.pycca.pycca.pojo;

public class Card {

    private String clubPyccaCardNumber;
    private String clubPyccaCardName;
    private boolean holder;

    public Card() {

    }

    public Card(String clubPyccaCardNumber, String clubPyccaCardName, boolean holder) {
        this.clubPyccaCardNumber = clubPyccaCardNumber;
        this.clubPyccaCardName = clubPyccaCardName;
        this.holder = holder;
    }

    public String getClubPyccaCardNumber() {
        return clubPyccaCardNumber;
    }

    public void setClubPyccaCardNumber(String clubPyccaCardNumber) {
        this.clubPyccaCardNumber = clubPyccaCardNumber;
    }

    public String getClubPyccaCardName() {
        return clubPyccaCardName;
    }

    public void setClubPyccaCardName(String clubPyccaCardName) {
        this.clubPyccaCardName = clubPyccaCardName;
    }

    public boolean isHolder() {
        return holder;
    }

    public void setHolder(boolean holder) {
        this.holder = holder;
    }
    
}
