package co.edu.uniquindio.poo.parcial3.model;

public enum EstadoPedido {
    PENDIENTE(0),ASIGNADO(1),CANCELADO(2);
    private final int valor;
    EstadoPedido(int valor) { this.valor = valor; }
    public int getValor() { return valor; }
}
