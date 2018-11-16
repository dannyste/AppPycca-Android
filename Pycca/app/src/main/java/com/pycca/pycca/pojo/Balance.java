package com.pycca.pycca.pojo;

public class Balance {
    private String clubPyccaCardNumber;
    private double availableCredit;
    private double usedCredit;
    private double aprovedQuota;
    private String payUntil;

    public Balance() {
    }

    public String getClubPyccaCardNumber() {
        return clubPyccaCardNumber;
    }

    public void setClubPyccaCardNumber(String clubPyccaCardNumber) {
        this.clubPyccaCardNumber = clubPyccaCardNumber;
    }

    public double getAvailableCredit() {
        return availableCredit;
    }

    public void setAvailableCredit(double availableCredit) {
        this.availableCredit = availableCredit;
    }

    public double getUsedCredit() {
        return usedCredit;
    }

    public void setUsedCredit(double usedCredit) {
        this.usedCredit = usedCredit;
    }

    public double getAprovedQuota() {
        return aprovedQuota;
    }

    public void setAprovedQuota(double aprovedQuota) {
        this.aprovedQuota = aprovedQuota;
    }

    public String getPayUntil() {
        return payUntil;
    }

    public void setPayUntil(String payUntil) {
        this.payUntil = payUntil;
    }
}
