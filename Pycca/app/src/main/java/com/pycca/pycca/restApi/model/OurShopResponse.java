package com.pycca.pycca.restApi.model;

public class OurShopResponse {

    private String ciudad;
    private String descripcion;
    private String telefono1;
    private String telefono2;
    private String direccion;
    private double latitud;
    private double longitud;
    private String horario_atencion;

    public OurShopResponse() {

    }

    public OurShopResponse(String ciudad, String descripcion, String telefono1, String telefono2, String direccion, double latitud, double longitud, String horario_atencion) {
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.telefono1 = telefono1;
        this.telefono2 = telefono2;
        this.direccion = direccion;
        this.latitud = latitud;
        this.longitud = longitud;
        this.horario_atencion = horario_atencion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getHorario_atencion() {
        return horario_atencion;
    }

    public void setHorario_atencion(String horario_atencion) {
        this.horario_atencion = horario_atencion;
    }

}
