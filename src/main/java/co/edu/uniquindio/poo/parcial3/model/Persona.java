package co.edu.uniquindio.poo.parcial3.model;

public abstract class Persona {
    private final String documento;
    private final String nombre;

    protected Persona(String documento, String nombre) {
        this.documento = documento;
        this.nombre = nombre;
    }


    //Getters
    public String getDocumento() {
        return documento;
    }
    public String getNombre() {
        return nombre;
    }
}
