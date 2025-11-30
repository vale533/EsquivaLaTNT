package org.example.excepciones;

public class BombayaExistenteException extends Exception{

    private int filaTest;
    private int columnaTest;

    public BombayaExistenteException(int fila, int columna) {
        super("No es posible colocar esta tnt. En esya posicion ya existe una (" + fila + "," + columna + ")");
        this.filaTest = fila;
        this.columnaTest = columna;
    }

    public int obtenerFilaAnterior() {
        return filaTest;
    }

    public int obtenerColumnaAnterior() {
        return columnaTest;
    }

    public String obtenerPosicionAnterior() {
        return  filaTest + "," + columnaTest;
    }
}
