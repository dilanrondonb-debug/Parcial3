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
    void asignarPedidoPanaderoMasRapido() {
        Empleado ana= new PanaderoArtesanal("100","ana","E1",100);
        Empleado carlos= new PanaderoIndustrial("101", "carlos","E2", 45);
        Pedido p= panaderia.registrarPedido("P01","Juan",TipoPan.TRADICIONAL,25);
        panaderia.agregarEmpleado(ana);
        panaderia.agregarEmpleado(carlos);
        panaderia.asignarPedido("P01");

        assertEquals(EstadoPedido.ASIGNADO,p.getEstadoPedido());
        assertEquals("E2",p.getCodigoEmpleadoAsignado());
        assertEquals(45,p.getMinutosReservados());
        assertEquals(0, carlos.getMinutosDisponible());
        assertEquals(100,ana.getMinutosDisponible());
    }

    @Test
    void empateUtilizaElPrimeroDeLaLista(){
        Empleado ana= new PanaderoArtesanal("100","ana","E1",80);
        Empleado diana= new PanaderoArtesanal("101","diana","E2",80);
        Empleado carlos= new PanaderoIndustrial("102","carlos","E3",54);

        panaderia.agregarEmpleado(ana);
        panaderia.agregarEmpleado(diana);
        panaderia.agregarEmpleado(carlos);
        Pedido p= panaderia.registrarPedido("P01","Juan",TipoPan.INTEGRAL,13);

        panaderia.asignarPedido("P01");

        assertEquals(EstadoPedido.ASIGNADO,p.getEstadoPedido());
        assertEquals("E1", p.getCodigoEmpleadoAsignado());
        assertEquals(80, p.getMinutosReservados());
        assertEquals(0, ana.getMinutosDisponible());
        assertEquals(80, diana.getMinutosDisponible());
        assertEquals(54,carlos.getMinutosDisponible());
    }

    @Test
    void asignarSinTiempoSuficiente(){
        Empleado carlos= new PanaderoIndustrial("100","carlos", "E1",44);
        Empleado lucia= new Cajero("101", "lucia","E2",120, "1");
        Pedido p= panaderia.registrarPedido("P01", "Juan", TipoPan.TRADICIONAL, 25);

        panaderia.agregarEmpleado(carlos);
        panaderia.agregarEmpleado(lucia);
        panaderia.asignarPedido("P01");

        assertThrows(IllegalStateException.class, () -> panaderia.asignarPedido("P01"));
        assertEquals(EstadoPedido.PENDIENTE,p.getEstadoPedido());
        assertEquals(44, carlos.getMinutosDisponible());
        assertEquals(120, lucia.getMinutosDisponible());
    }



    @Test
    void cancelarPedidoAsignado() {
        Empleado carlos= new PanaderoIndustrial("100","carlos", "E1",120);
        panaderia.agregarEmpleado(carlos);
        Pedido p1= panaderia.registrarPedido("P1","Juan",TipoPan.TRADICIONAL,25);
        Pedido p2= panaderia.registrarPedido("P2","Juan",TipoPan.TRADICIONAL,25);

        panaderia.asignarPedido("P1");
        panaderia.asignarPedido("P2");
        assertEquals(30,carlos.getMinutosDisponible());

        panaderia.cancelarPedido("P1");
        assertEquals(75,carlos.getMinutosDisponible());
        assertEquals(EstadoPedido.CANCELADO,p1.getEstadoPedido());
        assertEquals(EstadoPedido.ASIGNADO,p2.getEstadoPedido());

    }
    @Test
    void cancelarPedidoPendiente(){
        Empleado carlos=new PanaderoIndustrial("100","carlos", "E1",100);
        panaderia.agregarEmpleado(carlos);
        Pedido p=panaderia.registrarPedido("P1","Juan",TipoPan.TRADICIONAL,25);

        panaderia.cancelarPedido("P1");

        assertEquals(EstadoPedido.CANCELADO,p.getEstadoPedido());
        assertEquals(1,panaderia.getListaPedidos().size());
        assertEquals(100, carlos.getMinutosDisponible());
    }

    @Test
    void cancelarPedidoDosVeces(){
        Empleado carlos= new PanaderoIndustrial("100","carlos", "E1",100);
        panaderia.agregarEmpleado(carlos);
        Pedido p=panaderia.registrarPedido("P1","Juan",TipoPan.TRADICIONAL,25);

        panaderia.asignarPedido("P1");
        panaderia.cancelarPedido("P1");
        assertEquals(100, carlos.getMinutosDisponible());
        
        assertThrows(IllegalStateException.class, () -> panaderia.cancelarPedido("P1"));

        assertEquals(100,carlos.getMinutosDisponible());
        assertEquals(EstadoPedido.CANCELADO,p.getEstadoPedido());
    }

}