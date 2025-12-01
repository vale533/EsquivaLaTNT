package org.example.excepciones;

public class CasillaIsAtacadaException extends RuntimeException {
    private int filaSeleccionada;
    private int columnaSelecionada;

    public CasillaIsAtacadaException(int fila, int columna) {
        super("Esta casilla ya fue atacada anteriormente");
        this.filaSeleccionada = fila;
        this.columnaSelecionada = columna;
    }

    public String getPosicionAtacada() {
        return "Fila: " + filaSeleccionada + ", Columna: " + columnaSelecionada;
    }
}
