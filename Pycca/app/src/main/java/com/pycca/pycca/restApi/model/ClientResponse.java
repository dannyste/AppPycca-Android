package com.pycca.pycca.restApi.model;

public class ClientResponse {

    private int ma_cuenta;
    private String cl_nombres;
    private String cl_apellidos;
    private String no_estados;
    private String fe_apertura;

    public ClientResponse() {

    }

    public ClientResponse(int ma_cuenta, String cl_nombres, String cl_apellidos, String no_estados, String fe_apertura) {
        this.ma_cuenta = ma_cuenta;
        this.cl_nombres = cl_nombres;
        this.cl_apellidos = cl_apellidos;
        this.no_estados = no_estados;
        this.fe_apertura = fe_apertura;
    }

    public int getMa_cuenta() {
        return ma_cuenta;
    }

    public void setMa_cuenta(int ma_cuenta) {
        this.ma_cuenta = ma_cuenta;
    }

    public String getCl_nombres() {
        return cl_nombres;
    }

    public void setCl_nombres(String cl_nombres) {
        this.cl_nombres = cl_nombres;
    }

    public String getCl_apellidos() {
        return cl_apellidos;
    }

    public void setCl_apellidos(String cl_apellidos) {
        this.cl_apellidos = cl_apellidos;
    }

    public String getNo_estados() {
        return no_estados;
    }

    public void setNo_estados(String no_estados) {
        this.no_estados = no_estados;
    }

    public String getFe_apertura() {
        return fe_apertura;
    }

    public void setFe_apertura(String fe_apertura) {
        this.fe_apertura = fe_apertura;
    }

}
