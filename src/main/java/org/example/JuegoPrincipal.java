package org.example;

import org.example.excepciones.BombayaExistenteException;
import org.example.excepciones.CasillaIsAtacadaException;
import org.example.excepciones.PartidaNoIniciadaException;

import java.util.List;
import java.util.stream.Collectors;

public class JuegoPrincipal {

    private Tablero tableroDeJuego;
    private boolean bombaColocada;
    private int posFilaBomba;
    private int posColumnaBomba;
    private boolean juegoIniciado;
    private boolean partidaFinalizada;

    public JuegoPrincipal(int filas, int columnas) {
        tableroDeJuego = new Tablero(filas, columnas);
        bombaColocada = false;
        partidaFinalizada = false;
        juegoIniciado = false;
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

    public String procesarJugada(int fila, int columna) throws PartidaNoIniciadaException, CasillaIsAtacadaException {

        if (!juegoIniciado) {
            throw new PartidaNoIniciadaException("Ambos jugadores deben colocar sus TNT primero");
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

        return "Casilla segura.";
    }

    public void iniciarJuego() {
        if (bombaColocada) {
            juegoIniciado = true;
        }
    }

    public boolean juegoEstaIniciado() {
        return juegoIniciado;
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
    public List<int[]> obtenerCasillasSeguras() {
        return tableroDeJuego.obtenerTodasLasCasillas().stream()
                .filter(c -> !c.tieneBomba())
                .map(c -> new int[]{c.fila, c.columna})
                .collect(Collectors.toList());
    }
    public Tablero getTablero() {
        return tableroDeJuego;
    }
}