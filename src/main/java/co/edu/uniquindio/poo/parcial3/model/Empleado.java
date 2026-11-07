package co.edu.uniquindio.poo.parcial3.model;

public abstract class Empleado extends Persona {
    private final String codigo;
    private int minutosDisponible;

    protected Empleado(String documento, String nombre, String codigo, int minutosDisponible) {
        super(documento, nombre);
        if(minutosDisponible<=0) throw new IllegalArgumentException("Los minutos disponibles deben ser mayores a 0");
        this.codigo = codigo;
        this.minutosDisponible=minutosDisponible;

    }

    //Getters & Setters
    public String getCodigo() {
        return codigo;
    }

    public int getMinutosDisponible() {
        return minutosDisponible;
    }

    public void setMinutosDisponible(int minutosDisponible) {
        this.minutosDisponible = minutosDisponible;
    }


    //Métodos requeridos
    public void descontarMinutos(int minutos){
        if(minutos<=0) throw new IllegalArgumentException("Los minutos a descontar deben ser mayores a 0");
        if(minutos>minutosDisponible) throw new IllegalArgumentException("El empleado no tiene minutos suficientes");
        minutosDisponible -= minutos;
    }
    public void agregarMinutos(int minutos){
        if(minutos<=0) throw new IllegalArgumentException("Los minutos a agregar deben ser mayores a 0");
        minutosDisponible += minutos;
    }

}
