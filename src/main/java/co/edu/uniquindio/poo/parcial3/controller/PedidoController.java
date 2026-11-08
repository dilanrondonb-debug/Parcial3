package co.edu.uniquindio.poo.parcial3.controller;

import co.edu.uniquindio.poo.parcial3.model.*;
import co.edu.uniquindio.poo.parcial3.app.App;

import java.util.List;
import java.util.Optional;

public class PedidoController {
    private final Panaderia panaderia;

    public PedidoController() {
        this.panaderia = App.empresa;
    }

    public Pedido registrarPedido(String codigo, String nombreCliente, TipoPan tipoPan, int unidades) {
        if (codigo.isBlank()) {
            throw new IllegalArgumentException("El código del pedido es obligatorio");
        }
        if (nombreCliente.isBlank()) {
            throw new IllegalArgumentException("El nombre del cliente es obligatorio");
        }
        if (tipoPan == null) {
            throw new IllegalArgumentException("Seleccione el tipo de pan");
        }
        return panaderia.registrarPedido(codigo, nombreCliente, tipoPan, unidades);
    }

    public Pedido asignarPedido(String codigo) {
        panaderia.asignarPedido(codigo);
        return panaderia.obtenerPedido(codigo);
    }

    public int cancelarPedido(String codigo) {
        Pedido pedido = panaderia.obtenerPedido(codigo);
        int minutosDevueltos = 0;
        if (pedido.getEstadoPedido() == EstadoPedido.ASIGNADO) {
            minutosDevueltos = pedido.getMinutosReservados();
        }
        panaderia.cancelarPedido(codigo);
        return minutosDevueltos;
    }

    public List<Pedido> obtenerPedidos() {
        return panaderia.getListaPedidos();
    }

    public String obtenerDescripcionPanadero(Pedido pedido) {
        String codigoEmpleado = pedido.getCodigoEmpleadoAsignado();
        if (codigoEmpleado == null) {
            return "sin asignar";
        }
        Optional<Empleado> empleadoOptional = panaderia.buscarEmpleado(codigoEmpleado);
        if (empleadoOptional.isPresent()) {
            return codigoEmpleado + " - " + empleadoOptional.get().getNombre();
        }
        return codigoEmpleado;
    }
}
