package co.edu.uniquindio.poo.parcial3.controller;
import co.edu.uniquindio.poo.parcial3.model.*;
import co.edu.uniquindio.poo.parcial3.app.App;
import java.util.List;

public class EmpleadoController {
    private final Panaderia panaderia;

    public EmpleadoController() {
        this.panaderia = App.empresa;
    }

    public Empleado registrarEmpleado(String tipo, String documento, String nombre, String codigo,
                                      int minutosDisponibles, String cajaAsignada) {
        if (tipo == null || tipo.isBlank()) {
            throw new IllegalArgumentException("Seleccione el tipo de empleado");
        }
        if (documento.isBlank()) {
            throw new IllegalArgumentException("El documento es obligatorio");
        }
        if (nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("El código del empleado es obligatorio");
        }

        Empleado empleado;
        if (tipo.equals("Panadero artesanal")) {
            empleado = new PanaderoArtesanal(documento, nombre, codigo, minutosDisponibles);
        } else if (tipo.equals("Panadero industrial")) {
            empleado = new PanaderoIndustrial(documento, nombre, codigo, minutosDisponibles);
        } else if (tipo.equals("Cajero")) {
            if (cajaAsignada.isBlank()) {
                throw new IllegalArgumentException("El cajero debe tener una caja asignada");
            }
            empleado = new Cajero(documento, nombre, codigo, minutosDisponibles, cajaAsignada);
        } else {
            throw new IllegalArgumentException("Tipo de empleado no reconocido");
        }

        panaderia.agregarEmpleado(empleado);
        return empleado;
    }

    public void eliminarEmpleado(String codigo) {
        panaderia.eliminarEmpleado(codigo);
    }

    public List<Empleado> obtenerEmpleados() {
        return panaderia.getListaEmpleados();
    }

    public String obtenerTipo(Empleado empleado) {
        if (empleado instanceof PanaderoArtesanal) {
            return "Panadero artesanal";
        }
        if (empleado instanceof PanaderoIndustrial) {
            return "Panadero industrial";
        }
        return "Cajero";
    }

    public String obtenerCaja(Empleado empleado) {
        if (empleado instanceof Cajero) {
            return ((Cajero) empleado).getCajaAsignada();
        }
        return "-";
    }
}

