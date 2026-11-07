package co.edu.uniquindio.poo.parcial3.model;

public enum TipoPan {
    TRADICIONAL(0),INTEGRAL(1);
    private final int valor;
    TipoPan(int valor) { this.valor = valor; }
    public int getValor() { return valor; }
}
