package org.example.excepciones;

public class PartidaNoIniciadaException extends RuntimeException {
    private String accionIntentada;
    private String motivoBloqueo;

    public PartidaNoIniciadaException(String accion, String motivo) {
        super("No se puede " + accion + ". " + motivo);
        this.accionIntentada = accion;
        this.motivoBloqueo = motivo;
    }

    public PartidaNoIniciadaException(String motivo) {
        super("La partida aún no ha iniciado. " + motivo);
        this.accionIntentada = "realizar acción";
        this.motivoBloqueo = motivo;
    }

    public String getAccionIntentada() {
        return accionIntentada;
    }

    public String getMotivoBloqueo() {
        return motivoBloqueo;
    }
}