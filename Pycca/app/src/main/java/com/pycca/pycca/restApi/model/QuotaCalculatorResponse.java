package com.pycca.pycca.restApi.model;

public class QuotaCalculatorResponse {

    private int NPlazo;
    private double ValorCuota;
    private double TotalAPagar;

    QuotaCalculatorResponse() {

    }

    public QuotaCalculatorResponse(int NPlazo, double ValorCuota, double TotalAPagar) {
        this.NPlazo = NPlazo;
        this.ValorCuota = ValorCuota;
        this.TotalAPagar = TotalAPagar;
    }

    public int getNPlazo() {
        return NPlazo;
    }

    public void setNPlazo(int NPlazo) {
        this.NPlazo = NPlazo;
    }

    public double getValorCuota() {
        return ValorCuota;
    }

    public void setValorCuota(double valorCuota) {
        ValorCuota = valorCuota;
    }

    public double getTotalAPagar() {
        return TotalAPagar;
    }

    public void setTotalAPagar(double totalAPagar) {
        TotalAPagar = totalAPagar;
    }

}
