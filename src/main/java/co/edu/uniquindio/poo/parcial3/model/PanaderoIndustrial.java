package co.edu.uniquindio.poo.parcial3.model;

public class PanaderoIndustrial extends Empleado implements IProduccion {
 private static final int PANES_POR_LOTE=30;
 private static final int MINUTOS_PREPARACION=20;
 public PanaderoIndustrial(String documento, String nombre, String codigo, int minutosDisponibles){
     super(documento, nombre, codigo, minutosDisponibles);
 }

 @Override
 public int  calcularMinutos(TipoPan tipoPan, int cantidad){
     if(cantidad<=0) throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
     int lote= (cantidad+PANES_POR_LOTE-1)/PANES_POR_LOTE;
     int minutosPorBandeja=0;
     if(tipoPan==TipoPan.INTEGRAL) minutosPorBandeja=35;
     if(tipoPan==TipoPan.TRADICIONAL) minutosPorBandeja=25;
     return lote*minutosPorBandeja+MINUTOS_PREPARACION;
 }
}
