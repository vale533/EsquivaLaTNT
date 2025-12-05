package org.example;

import org.example.excepciones.BombayaExistenteException;
import org.example.excepciones.CasillaIsAtacadaException;
import org.example.excepciones.TurnoNoHabilitado;

public class JuegoPrincipal {

    private Tablero tableroDeJuego;
    private boolean bombaColocada;
    private int posFilaBomba;
    private int posColumnaBomba;

    private boolean esMiTurno;
    private boolean partidaFinalizada;

    public JuegoPrincipal(int filas, int columnas) {
        tableroDeJuego = new Tablero(filas, columnas);
        bombaColocada = false;
        partidaFinalizada = false;
        esMiTurno = false;
    }

    public boolean seleccionarUbicacionBomba(int fila, int columna) throws BombayaExistenteException {

        if (partidaFinalizada) {
            return false;
        }

        if (bombaColocada) {
            throw new BombayaExistenteException(posFilaBomba, posColumnaBomba);
        }

        posFilaBomba = fila;
        posColumnaBomba = columna;

        tableroDeJuego.ponerBomba(fila, columna);
        bombaColocada = true;

        return true;
    }

    public String procesarJugada(int fila, int columna) throws TurnoNoHabilitado, CasillaIsAtacadaException {

        if (!esMiTurno) {
            throw new TurnoNoHabilitado("Jugador");
        }

        if (partidaFinalizada) {
            return "La partida ya termino.";
        }

        if (tableroDeJuego.obtenerCasilla(fila,columna).estaUsada()) {
            throw new CasillaIsAtacadaException(fila, columna);
        }

        tableroDeJuego.marcarUsada(fila, columna);

        if (tableroDeJuego.obtenerCasilla(fila, columna).tieneBomba()) {
            partidaFinalizada = true;
            return "Has detonado la tnt del rival!";
        }

        cambiarTurno();
        return "Casilla segura, turno cambiado.";
    }

    public void cambiarTurno() {
        esMiTurno = !esMiTurno;
    }

    public void asignarTurnoInicial(boolean miTurno) {
        this.esMiTurno = miTurno;
    }

    public boolean partidaTerminada() {
        return partidaFinalizada;
    }

    public boolean yaPusoBomba() {
        return bombaColocada;
    }

    public int[] getPosicionBomba() {
        return new int[]{posFilaBomba, posColumnaBomba};
    }
}