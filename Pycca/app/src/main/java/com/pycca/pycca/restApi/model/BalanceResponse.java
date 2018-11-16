package com.pycca.pycca.restApi.model;

public class BalanceResponse {

    private double cupo;
    private int minimoPagar;
    private String fechaTopePago;
    private double disponibleCuenta;
    //private double deudaTotal;

    public BalanceResponse() {
    }

    public BalanceResponse(double cupo, int minimoPagar, String fechaTopePago, double disponibleCuenta) {
        this.cupo = cupo;
        this.minimoPagar = minimoPagar;
        this.fechaTopePago = fechaTopePago;
        this.disponibleCuenta = disponibleCuenta;
    }

    public double getCupo() {
        return cupo;
    }

    public void setCupo(double cupo) {
        this.cupo = cupo;
    }

    public int getMinimoPagar() {
        return minimoPagar;
    }

    public void setMinimoPagar(int minimoPagar) {
        this.minimoPagar = minimoPagar;
    }

    public String getFechaTopePago() {
        return fechaTopePago;
    }

    public void setFechaTopePago(String fechaTopePago) {
        this.fechaTopePago = fechaTopePago;
    }

    public double getDisponibleCuenta() {
        return disponibleCuenta;
    }

    public void setDisponibleCuenta(double disponibleCuenta) {
        this.disponibleCuenta = disponibleCuenta;
    }
}
