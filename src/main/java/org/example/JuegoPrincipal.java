package org.example;

public class JuegoPrincipal {

    private Tablero tableroDeJuego;
    private boolean bombaColocada;
    private int posFilaBomba;
    private int posColumnaBomba;

    private boolean turnoPropio;
    private boolean partidaFinalizada;

    public JuegoPrincipal(int filas, int columnas) {
        tableroDeJuego = new Tablero(filas, columnas);
        bombaColocada = false;
        partidaFinalizada = false;
        turnoPropio = false;
    }

    public boolean seleccionarUbicacionBomba(int fila, int columna) {
        if (bombaColocada || partidaFinalizada) {
            return false;
        }

        posFilaBomba = fila;
        posColumnaBomba = columna;

        tableroDeJuego.ponerBomba(fila, columna);
        bombaColocada = true;

        return true;
    }

    public String procesarJugada(int fila, int columna) {

        if (!turnoPropio) {
            return "Aún no puedes jugar, esperemenos tu turno.";
        }

        if (partidaFinalizada) {
            return "La partida ya se acabó!";
        }

        if (tableroDeJuego.obtenerCasilla(fila,columna).estaUsada()) {
            return "Esa posición ya fue seleccionada anteriormente,";
        }

        tableroDeJuego.marcarUsada(fila, columna);

        if (tableroDeJuego.obtenerCasilla(fila, columna).tieneBomba()) {
            partidaFinalizada = true;
            return " ¡Has detonado la bomba del rival!";
        }

        cambiarTurno();
        return "Casilla segura, turno cambiado.";
    }

    public void cambiarTurno() {
        turnoPropio = !turnoPropio;
    }

    public void asignarTurnoInicial(boolean miTurno) {
        this.turnoPropio = miTurno;
    }

    public boolean partidaTerminada() {
        return partidaFinalizada;
    }

    public boolean yaPusoBomba() {
        return bombaColocada;
    }
}

