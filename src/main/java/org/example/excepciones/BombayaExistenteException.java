package org.example.excepciones;

public class BombayaExistenteException extends Exception{

    private int filaTest;
    private int columnaTest;

    public BombayaExistenteException(int fila, int columna) {
        super("No es posible colocar una tnt!, ya colocaste otra anteriormente En esta posicion ("+(fila+1)+","+(1+columna)+"");
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
