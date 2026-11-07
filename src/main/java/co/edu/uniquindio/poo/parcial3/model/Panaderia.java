package co.edu.uniquindio.poo.parcial3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Panaderia {
    private String nombre;
    private final List<Empleado> listaEmpleados=new ArrayList<>();
    private final List<Pedido> listaPedidos=new ArrayList<>();

    public Panaderia(String nombre){this.nombre=nombre;}

    //CRUD Empleado
    public void agregarEmpleado(Empleado e){
        if(buscarEmpleado(e.getCodigo()).isPresent()) throw new IllegalArgumentException("Ya existe un empleado con el codigo: "+e.getCodigo());
        listaEmpleados.add(e);
    }
    public Optional<Empleado> buscarEmpleado(String codigo){
        return listaEmpleados.stream().filter(e->e.getCodigo().equals(codigo)).findFirst();
    }
    public void eliminarEmpleado(String codigo){
        Empleado empleado=buscarEmpleado(codigo).orElseThrow(()->new IllegalArgumentException("No existe empleado con el codigo: "+codigo));
        listaEmpleados.remove(empleado);
    }
    public Optional<Pedido> buscarPedido(String codigo){
        return listaPedidos.stream().filter(e->e.getCodigo().equals(codigo)).findFirst();
    }

    //Getters & Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    //Funcionalidades
    public Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad){
        if(buscarPedido(codigo).isPresent()) throw new IllegalArgumentException("Ya existe un pedido con el codigo: "+codigo);
        Pedido pedido= new Pedido(codigo, nombreCliente, tipoPan, cantidad);
        listaPedidos.add(pedido);
        return pedido;
    }
}
