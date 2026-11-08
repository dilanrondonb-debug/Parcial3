package co.edu.uniquindio.poo.parcial3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Panaderia {
    
    private String nombre;
    private final List<Empleado> listaEmpleados = new ArrayList<>();
    private final List<Pedido> listaPedidos = new ArrayList<>();

    public Panaderia(String nombre) {
        this.nombre = nombre;
    }

    // CRUD Empleado
    public void agregarEmpleado(Empleado e) {
        if (buscarEmpleado(e.getCodigo()).isPresent()) {
            throw new IllegalArgumentException("Ya existe un empleado con el codigo: " + e.getCodigo());
        }
        listaEmpleados.add(e);
    }



    public Optional<Empleado> buscarEmpleado(String codigo) {
        return listaEmpleados.stream().filter(e -> e.getCodigo().equals(codigo)).findFirst();
    }

    public void eliminarEmpleado(String codigo) {
        Empleado empleado = buscarEmpleado(codigo).orElseThrow(() -> new IllegalArgumentException("No existe empleado con el codigo: " + codigo));
        listaEmpleados.remove(empleado);
    }

    // CRUD Pedido
    public Optional<Pedido> buscarPedido(String codigo) {
        return listaPedidos.stream().filter(e -> e.getCodigo().equals(codigo)).findFirst();
    }

    public Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (buscarPedido(codigo).isPresent()) {
            throw new IllegalArgumentException("Ya existe un pedido con el codigo: " + codigo);
        }
        Pedido pedido = new Pedido(codigo, nombreCliente, tipoPan, cantidad);
        listaPedidos.add(pedido);
        return pedido;
    }



    public Pedido obtenerPedido(String codigo) {
        return buscarPedido(codigo).orElseThrow(() -> new IllegalArgumentException("No existe un pedido con el codigo: " + codigo));
    }

    // Funcionalidades: Asignar y Cancelar Pedido
    public void asignarPedido(String codigoPedido) {
        Pedido pedido = obtenerPedido(codigoPedido);
        if (pedido.getEstadoPedido() != EstadoPedido.PENDIENTE) {
            throw new IllegalStateException("Solo se asignan pedidos pendientes");
        }
        Empleado mejor = null;
        int minutosMinimos = Integer.MAX_VALUE;
        for (Empleado e : listaEmpleados) {
            if (e instanceof IProduccion) {
                IProduccion productor = (IProduccion) e;
                int necesarios = productor.calcularMinutos(pedido.getTipoPan(), pedido.getUnidades());
                if (e.getMinutosDisponible() >= necesarios) {
                    if (mejor == null || necesarios < minutosMinimos) {
                        mejor = e;
                        minutosMinimos = necesarios;
                    }
                }
            }
        }
        if (mejor == null) {
            throw new IllegalStateException("Ningún panadero tiene tiempo disponible");
        }
        pedido.asignar(mejor.getCodigo(), minutosMinimos);
        mejor.descontarMinutos(minutosMinimos);
    }

    public void cancelarPedido(String codigoPedido) {
        Pedido pedido = obtenerPedido(codigoPedido);
        if (pedido.getEstadoPedido() == EstadoPedido.CANCELADO) {
            throw new IllegalStateException("El pedido ya está cancelado");
        }
        if (pedido.getEstadoPedido() == EstadoPedido.ASIGNADO) {
            buscarEmpleado(pedido.getCodigoEmpleadoAsignado()).ifPresent(empleado -> {
                empleado.agregarMinutos(pedido.getMinutosReservados());
            });
        }
        pedido.cancelar();
    }

    // Getters & Setters
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
}
