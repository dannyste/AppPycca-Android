package com.pycca.pycca.restApi.model;

public class CardResponse {

    private int tarjeta;
    private String ta_plnombre1;
    private String ta_princiadicio;

    public CardResponse() {

    }

    public CardResponse(int tarjeta, String ta_plnombre1, String ta_princiadicio) {
        this.tarjeta = tarjeta;
        this.ta_plnombre1 = ta_plnombre1;
        this.ta_princiadicio = ta_princiadicio;
    }

    public int getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(int tarjeta) {
        this.tarjeta = tarjeta;
    }

    public String getTa_plnombre1() {
        return ta_plnombre1;
    }

    public void setTa_plnombre1(String ta_plnombre1) {
        this.ta_plnombre1 = ta_plnombre1;
    }

    public String getTa_princiadicio() {
        return ta_princiadicio;
    }

    public void setTa_princiadicio(String ta_princiadicio) {
        this.ta_princiadicio = ta_princiadicio;
    }

}
