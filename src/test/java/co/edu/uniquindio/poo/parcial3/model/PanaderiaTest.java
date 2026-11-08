package co.edu.uniquindio.poo.parcial3.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class PanaderiaTest {

    Panaderia panaderia = new Panaderia("Peter-Pan");



    @Test
    void registrarPedido() {

        List<Integer> minutosAntes = panaderia.getListaEmpleados().stream().map(Empleado::getMinutosDisponible).toList();

       Pedido pedido = panaderia.registrarPedido("P01","",TipoPan.TRADICIONAL,25);


        assertEquals(EstadoPedido.PENDIENTE,pedido.getEstadoPedido());
        assertEquals(25,pedido.getUnidades());

        List<Integer> minutosDespues = panaderia.getListaEmpleados().stream().map(Empleado::getMinutosDisponible).toList();

        assertEquals(minutosAntes,minutosDespues);

    }

    @Test
    void asignarPedido() {
    }

    @Test
    void cancelarPedido() {
    }
}