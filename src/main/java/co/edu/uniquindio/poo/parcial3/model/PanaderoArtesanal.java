package co.edu.uniquindio.poo.parcial3.model;

public class PanaderoArtesanal extends Empleado implements IProduccion {
    private final int PANES_POR_BANDEJA=12;
    public PanaderoArtesanal(String documento, String nombre, String codigo, int minutosDisponibles){
        super(documento, nombre, codigo, minutosDisponibles);
    }
    @Override
    public int calcularMinutos(TipoPan tipoPan, int cantidad) {
        if(cantidad<=0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        int bandejas= (cantidad+PANES_POR_BANDEJA-1)/PANES_POR_BANDEJA;
        int minutosPorBandeja=0;
        if(tipoPan == TipoPan.INTEGRAL) minutosPorBandeja=40;
        else if (tipoPan == TipoPan.TRADICIONAL) minutosPorBandeja=30;

        return bandejas*minutosPorBandeja;
    }
}
