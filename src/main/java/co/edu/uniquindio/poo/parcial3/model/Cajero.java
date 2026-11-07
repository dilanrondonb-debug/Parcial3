package co.edu.uniquindio.poo.parcial3.model;

public class Cajero extends Empleado{
    private final String cajaAsignada;

    public Cajero( String documento, String nombre, String codigo, int minutosDisponible, String cajaAsignada){
        super(documento, nombre, codigo, minutosDisponible);
        this.cajaAsignada = cajaAsignada;
    }

    public String getCajaAsignada() {
        return cajaAsignada;
    }
}
