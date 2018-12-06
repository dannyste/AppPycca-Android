package com.pycca.pycca.restApi.model;

public class AccountStatusResponse {

    private double cupo;
    private int minimoPagar;
    private String fechaTopePago;
    private double disponibleCuenta;
    private boolean vencido;
    private String fechaUltimoCorte;

    public AccountStatusResponse() {

    }

    public AccountStatusResponse(double cupo, int minimoPagar, String fechaTopePago, double disponibleCuenta, boolean vencido, String fechaUltimoCorte) {
        this.cupo = cupo;
        this.minimoPagar = minimoPagar;
        this.fechaTopePago = fechaTopePago;
        this.disponibleCuenta = disponibleCuenta;
        this.vencido = vencido;
        this.fechaUltimoCorte = fechaUltimoCorte;
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

    public boolean isVencido() {
        return vencido;
    }

    public void setVencido(boolean vencido) {
        this.vencido = vencido;
    }

    public String getFechaUltimoCorte() {
        return fechaUltimoCorte;
    }

    public void setFechaUltimoCorte(String fechaUltimoCorte) {
        this.fechaUltimoCorte = fechaUltimoCorte;
    }
}
