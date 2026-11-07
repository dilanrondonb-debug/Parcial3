package co.edu.uniquindio.poo.parcial3.model;

import java.util.ArrayList;

public class Pedido {
    private String codigo;
    private String nombreCliente;
    private int unidades;
    private int minutosReservados;
    private String codigoEmpleadoAsignado;
    private EstadoPedido estadoPedido=EstadoPedido.PENDIENTE;
    private TipoPan tipoPan;

    public Pedido(String codigo, String nombreCliente, TipoPan tipoPan, int unidades) {

        this.unidades = unidades;
        this.tipoPan = tipoPan;
        this.nombreCliente = nombreCliente;
        this.codigo = codigo;

    }

    public void asignar(String codigoEmpleado, int minutos) {
        if(estadoPedido!=EstadoPedido.PENDIENTE) throw new IllegalStateException("Solo se puede asignar un pedido pendiente");
        this.codigoEmpleadoAsignado=codigoEmpleado;
        this.minutosReservados=minutos;
        this.estadoPedido=EstadoPedido.ASIGNADO;
    }
    public void cancelar(){
        if(estadoPedido==EstadoPedido.CANCELADO) throw new IllegalStateException("El pedido ya está cancelado");
        this.estadoPedido=EstadoPedido.CANCELADO;
    }

    //Getters & Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getMinutosReservados() {
        return minutosReservados;
    }

    public void setMinutosReservados(int minutosReservados) {
        this.minutosReservados = minutosReservados;
    }

    public String getCodigoEmpleadoAsignado() {
        return codigoEmpleadoAsignado;
    }

    public void setCodigoEmpleadoAsignado(String codigoEmpleadoAsignado) {
        this.codigoEmpleadoAsignado = codigoEmpleadoAsignado;
    }


}
