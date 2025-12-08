package org.example;

import org.example.excepciones.BombayaExistenteException;
import org.example.excepciones.CasillaIsAtacadaException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JuegoTNTTest {

    private JuegoPrincipal juego;
    private Tablero tablero;
    private Casilla casilla;

    @BeforeEach
    void setUp() {
        juego = new JuegoPrincipal(3, 4);
        tablero = new Tablero(3, 4);
        casilla = new Casilla(0, 0);
    }


    @Test
    void testCasillaInicialSinBomba() {
        Casilla c = new Casilla(1, 2);

        assertFalse(c.tieneBomba(), "La casilla no debe tener bomba inicialmente");
        assertFalse(c.estaUsada(), "La casilla no debe estar usada inicialmente");
        assertEquals(1, c.fila, "La fila debe ser 1");
        assertEquals(2, c.columna, "La columna debe ser 2");
    }

    @Test

    void testMarcarCasillaComoBomba() {
        casilla.marcarComoBomba();

        assertTrue(casilla.tieneBomba(), "La casilla debe tener bomba después de marcarla");
    }

    @Test
    void testMarcarCasillaComoUsada() {
        casilla.marcarComoUsada();

        assertTrue(casilla.estaUsada(), "La casilla debe estar marcada como usada");
    }

    @Test
    void testCasillaBombaYUsada() {
        casilla.marcarComoBomba();
        casilla.marcarComoUsada();

        assertTrue(casilla.tieneBomba(), "La casilla debe tener bomba");
        assertTrue(casilla.estaUsada(), "La casilla debe estar usada");
    }

    @Test
    void testCrearTablero() {
        Tablero t = new Tablero(3, 4);

        assertNotNull(t.obtenerCasilla(0, 0), "La casilla [0,0] debe existir");
        assertNotNull(t.obtenerCasilla(2, 3), "La casilla [2,3] debe existir");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                assertNotNull(t.obtenerCasilla(i, j),
                        "La casilla [" + i + "," + j + "] debe estar inicializada");
            }
        }
    }

    @Test
    void testPonerBombaEnTablero() {
        tablero.ponerBomba(1, 2);

        assertTrue(tablero.esBomba(1, 2), "La casilla [1,2] debe tener bomba");
        assertFalse(tablero.esBomba(0, 0), "La casilla [0,0] no debe tener bomba");
        assertFalse(tablero.esBomba(2, 3), "La casilla [2,3] no debe tener bomba");
    }

    @Test
    void testMarcarUsadaEnTablero() {
        tablero.marcarUsada(2, 1);

        assertTrue(tablero.yaFueUsada(2, 1), "La casilla [2,1] debe estar usada");
        assertFalse(tablero.yaFueUsada(0, 0), "La casilla [0,0] no debe estar usada");
    }

    @Test
    void testContarCasillasLibres() {
        assertEquals(12, tablero.contarLibres(), "Debe haber 12 casillas libres inicialmente");

        tablero.marcarUsada(0, 0);
        tablero.marcarUsada(1, 1);

        assertEquals(10, tablero.contarLibres(), "Debe haber 10 casillas libres después de usar 2");

        tablero.marcarUsada(2, 2);
        tablero.marcarUsada(0, 3);

        assertEquals(8, tablero.contarLibres(), "Debe haber 8 casillas libres después de usar 4");
    }

    @Test
    void testObtenerTodasLasCasillas() {
        List<Casilla> casillas = tablero.obtenerTodasLasCasillas();

        assertEquals(12, casillas.size(), "Debe haber 12 casillas en total (3x4)");
    }

    @Test
    void testTableroDetectaBomba() {
        assertFalse(tablero.hayBomba(), "No debe haber bomba inicialmente");

        tablero.ponerBomba(1, 2);

        assertTrue(tablero.hayBomba(), "Debe detectar que hay bomba en el tablero");
    }

    @Test
    void testTodasCasillasUsadas() {
        assertFalse(tablero.todasUsadas(), "No todas deben estar usadas inicialmente");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                tablero.marcarUsada(i, j);
            }
        }

        assertTrue(tablero.todasUsadas(), "Todas las casillas deben estar usadas");
    }

    @Test
    void testObtenerCasillasUsadas() {
        tablero.marcarUsada(0, 0);
        tablero.marcarUsada(1, 1);
        tablero.marcarUsada(2, 2);

        List<Casilla> usadas = tablero.obtenerCasillasUsadas();

        assertEquals(3, usadas.size(), "Debe haber 3 casillas usadas");

        for (Casilla c : usadas) {
            assertTrue(c.estaUsada(), "Todas las casillas en la lista deben estar usadas");
        }
    }

    @Test
    void testTableroMultiplesBombas() {
        tablero.ponerBomba(0, 0);
        tablero.ponerBomba(1, 1);
        tablero.ponerBomba(2, 2);

        assertTrue(tablero.esBomba(0, 0), "Casilla [0,0] debe tener bomba");
        assertTrue(tablero.esBomba(1, 1), "Casilla [1,1] debe tener bomba");
        assertTrue(tablero.esBomba(2, 2), "Casilla [2,2] debe tener bomba");
        assertFalse(tablero.esBomba(0, 1), "Casilla [0,1] no debe tener bomba");
    }

    @Test
    void testSeleccionarUbicacionBomba() throws BombayaExistenteException {
        boolean resultado = juego.seleccionarUbicacionBomba(1, 2);

        assertTrue(resultado, "Debe poder seleccionar ubicación de bomba");
        assertTrue(juego.yaPusoBomba(), "El juego debe registrar que ya se puso bomba");
        assertArrayEquals(new int[]{1, 2}, juego.getPosicionBomba(),
                "La posición de la bomba debe ser [1,2]");
    }

    @Test
    void testNoPermitirDosBombas() throws BombayaExistenteException {
        juego.seleccionarUbicacionBomba(0, 0);
        BombayaExistenteException exception = assertThrows(
                BombayaExistenteException.class,
                () -> juego.seleccionarUbicacionBomba(1, 1),
                "Debe lanzar BombayaExistenteException al intentar colocar segunda bomba"
        );

        assertTrue(exception.getMessage().contains("ya colocaste otra anteriormente"),
                "El mensaje debe indicar que ya existe una bomba");
    }

    @Test
    void testEstadoInicialJuego() {
        assertFalse(juego.yaPusoBomba(), "No debe haber bomba colocada inicialmente");
        assertFalse(juego.partidaTerminada(), "La partida no debe estar terminada inicialmente");
    }

    @Test
    void testObtenerCasillasSeguras() throws BombayaExistenteException {
        juego.seleccionarUbicacionBomba(1, 1);

        List<int[]> casillasSeguras = juego.obtenerCasillasSeguras();

        assertEquals(11, casillasSeguras.size(),
                "Debe haber 11 casillas seguras (12 total - 1 bomba)");

        for (int[] casilla : casillasSeguras) {
            assertFalse(casilla[0] == 1 && casilla[1] == 1,
                    "La posición de la bomba no debe estar en casillas seguras");
        }
    }

    @Test
    void testBombaEnEsquina() throws BombayaExistenteException {
        juego.seleccionarUbicacionBomba(0, 0);

        assertArrayEquals(new int[]{0, 0}, juego.getPosicionBomba(),
                "La bomba debe estar en [0,0]");

        List<int[]> seguras = juego.obtenerCasillasSeguras();
        assertEquals(11, seguras.size(), "Debe haber 11 casillas seguras");
    }

    @Test
    void testBombaEnEsquinaInferior() throws BombayaExistenteException {
        juego.seleccionarUbicacionBomba(2, 3);

        assertArrayEquals(new int[]{2, 3}, juego.getPosicionBomba(),
                "La bomba debe estar en [2,3]");
        assertTrue(juego.yaPusoBomba(), "Debe haber bomba colocada");
    }
    @Test
    void testBombayaExistenteExceptionPosicion() {
        BombayaExistenteException exception = new BombayaExistenteException(2, 3);

        assertEquals(2, exception.obtenerFilaAnterior(), "La fila debe ser 2");
        assertEquals(3, exception.obtenerColumnaAnterior(), "La columna debe ser 3");
        assertEquals("2,3", exception.obtenerPosicionAnterior(),
                "La posición debe ser '2,3'");
    }

    @Test
    void testCasillaIsAtacadaExceptionDatos() {
        CasillaIsAtacadaException exception = new CasillaIsAtacadaException(1, 2);

        String posicion = exception.getPosicionAtacada();
        assertTrue(posicion.contains("1"), "La posición debe contener fila 1");
        assertTrue(posicion.contains("2"), "La posición debe contener columna 2");
        assertTrue(exception.getMessage().contains("ya fue atacada"),
                "El mensaje debe indicar que ya fue atacada");
    }

    @Test
    void testMensajeBombayaExistente() {
        BombayaExistenteException exception = new BombayaExistenteException(1, 2);

        String mensaje = exception.getMessage();
        assertTrue(mensaje.contains("No es posible colocar una tnt"),
                "El mensaje debe indicar que no se puede colocar TNT");
        assertTrue(mensaje.contains("(2,3"),
                "El mensaje debe contener la posición (ajustada +1)");
    }

    @Test
    void testTableroDiferentesDimensiones() {
        Tablero t1 = new Tablero(5, 5);
        assertEquals(25, t1.obtenerTodasLasCasillas().size(),
                "Tablero 5x5 debe tener 25 casillas");

        Tablero t2 = new Tablero(2, 6);
        assertEquals(12, t2.obtenerTodasLasCasillas().size(),
                "Tablero 2x6 debe tener 12 casillas");
    }

    @Test
    void testPartidaNoFinalizaAlColocarBomba() throws BombayaExistenteException {
        juego.seleccionarUbicacionBomba(1, 1);

        assertFalse(juego.partidaTerminada(),
                "La partida no debe terminar al solo colocar la bomba");
    }

    @Test
    void testCoordenadasCasilla() {
        Casilla c = new Casilla(2, 3);

        assertEquals(2, c.fila, "La fila debe ser 2");
        assertEquals(3, c.columna, "La columna debe ser 3");
    }
}