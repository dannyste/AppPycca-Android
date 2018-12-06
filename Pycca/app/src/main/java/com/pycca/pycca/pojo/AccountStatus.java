package com.pycca.pycca.pojo;

public class AccountStatus {

    private String clubPyccaCardNumber;
    private double availableCredit;
    private double usedCredit;
    private double aprovedQuota;
    private String payUntil;
    private double quotaToPay;
    private boolean isOverdue;
    private String cutDate;

    public AccountStatus() {

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

    public double getQuotaToPay() {
        return quotaToPay;
    }

    public void setQuotaToPay(double quotaToPay) {
        this.quotaToPay = quotaToPay;
    }

    public boolean isOverdue() {
        return isOverdue;
    }

    public void setOverdue(boolean overdue) {
        isOverdue = overdue;
    }

    public String getCutDate() {
        return cutDate;
    }

    public void setCutDate(String cutDate) {
        this.cutDate = cutDate;
    }
}
