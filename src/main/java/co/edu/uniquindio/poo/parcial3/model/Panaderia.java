package co.edu.uniquindio.poo.parcial3.model;

import java.util.ArrayList;
import java.util.List;

public class Panaderia {
    
    private String nombre;
    private final List<Empleado> listaEmpleados = new ArrayList<>();
    private final List<Pedido> listaPedidos = new ArrayList<>();

    public Panaderia(String nombre) {
        this.nombre = nombre;
    }

    public void registrarEmpleado(Empleado e) {
        listaEmpleados.add(e);
    }

    public void eliminarEmpleado(String codigo) {
        listaEmpleados.removeIf(e -> e.getCodigo().equals(codigo));
    }

    public Empleado buscarEmpleado(String codigo) {
        for (Empleado e : listaEmpleados) {
            if (e.getCodigo().equals(codigo)) {
                return e;
            }
        }
        return null;
    }

    public void registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int unidades) {
        if (unidades <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        for (Pedido p : listaPedidos) {
            if (p.getCodigo().equals(codigo)) {
                throw new IllegalArgumentException("El código ya existe");
            }
        }
        Pedido pedido = new Pedido(codigo, nombreCliente, tipoPan, unidades);
        listaPedidos.add(pedido);
    }

    public void asignarPedido(String codigoPedido) {
        Pedido pedido = null;
        for (Pedido p : listaPedidos) {
            if (p.getCodigo().equals(codigoPedido)) {
                pedido = p;
                break;
            }
        }
        if (pedido == null) {
            throw new IllegalArgumentException("No existe el pedido");
        }
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
        Pedido pedido = null;
        for (Pedido p : listaPedidos) {
            if (p.getCodigo().equals(codigoPedido)) {
                pedido = p;
                break;
            }
        }
        if (pedido == null) {
            throw new IllegalArgumentException("No existe el pedido");
        }
        if (pedido.getEstadoPedido() == EstadoPedido.CANCELADO) {
            throw new IllegalStateException("El pedido ya está cancelado");
        }
        if (pedido.getEstadoPedido() == EstadoPedido.ASIGNADO) {
            Empleado empleado = buscarEmpleado(pedido.getCodigoEmpleadoAsignado());
            if (empleado != null) {
                empleado.agregarMinutos(pedido.getMinutosReservados());
            }
        }
        pedido.cancelar();
    }

    public Pedido buscarPedido(String codigo) {
        for (Pedido p : listaPedidos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }
<<<<<<< Updated upstream

    //Funcionalidades
    public Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int cantidad){
        if(buscarPedido(codigo).isPresent()) throw new IllegalArgumentException("Ya existe un pedido con el codigo: "+codigo);
        Pedido pedido= new Pedido(codigo, nombreCliente, tipoPan, cantidad);
        listaPedidos.add(pedido);
        return pedido;
    }


    private Pedido obtenerPedido(String codigo){
        return buscarPedido(codigo).orElseThrow(()->new IllegalArgumentException("No existe un pedido con el codigo: "+codigo));
    }
=======
>>>>>>> Stashed changes
}
