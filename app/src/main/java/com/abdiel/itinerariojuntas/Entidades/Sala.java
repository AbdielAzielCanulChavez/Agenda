package com.abdiel.itinerariojuntas.Entidades;

public class Sala {

    int idsala;
    String area;
    String fecha;
    String horainicio;
    String horafin;
    String telefono;
    String empleado;
    String actividad;

    public Sala() {
    }

    public Sala(int idsala, String area, String fecha, String horainicio, String horafin, String telefono, String empleado, String actividad) {
        this.idsala = idsala;
        this.area = area;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.telefono = telefono;
        this.empleado = empleado;
        this.actividad = actividad;
    }

    public Sala(String area, String fecha, String horainicio, String horafin, String telefono, String empleado, String actividad) {
        this.area = area;
        this.fecha = fecha;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.telefono = telefono;
        this.empleado = empleado;
        this.actividad = actividad;
    }

    public int getIdsala() {
        return idsala;
    }

    public void setIdsala(int idsala) {
        this.idsala = idsala;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
}
