package org.example.excepciones;

public class TurnoNoHabilitado extends RuntimeException {
    private String jugadorActual;

    public TurnoNoHabilitado(String nombreJugador) {
        super("Todavia no es tu turno, Esperemenos a tu rival");
        this.jugadorActual = nombreJugador;
    }

    public String getJugadorQueIntento() {
        return jugadorActual;
    }
}
