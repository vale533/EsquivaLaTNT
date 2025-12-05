package org.example;

import java.io.*;
import java.net.*;

public class JugadorConectado extends Jugador implements ComunicacionJugador {

    private Socket socket;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private boolean conectado;

    private int[] miTNT = new int[2];
    private int[] tntRival = new int[2];
    private boolean miTNTLista = false;
    private boolean rivalListo = false;

    public JugadorConectado(String nombre, JuegoPrincipal partida, String ip) {
        super(nombre, partida);
        conectado = false;

        try {
            socket = new Socket(ip, 5000);
            salida = new ObjectOutputStream(socket.getOutputStream());
            entrada = new ObjectInputStream(socket.getInputStream());
            conectado = true;
            System.out.println("conectado a " + ip);

            escuchar();

        } catch (IOException e) {
            System.out.println("no se pudo conectar: " + e.getMessage());
        }
    }

    private void escuchar() {
        new Thread(() -> {
            try {
                while (true) {
                    String msg = (String) entrada.readObject();
                    procesarMensaje(msg);
                }
            } catch (Exception e) {
                System.out.println("conexion perdida");
            }
        }).start();
    }

    private void procesarMensaje(String msg) {
        String[] partes = msg.split(":");

        if (partes[0].equals("TNT")) {
            tntRival[0] = Integer.parseInt(partes[1]);
            tntRival[1] = Integer.parseInt(partes[2]);
            rivalListo = true;
            if (miTNTLista) {
                System.out.println("ambos listos!");
            }
        }
    }

    @Override
    public void realizarJugada(int fila, int columna) {
        try {
            partida.procesarJugada(fila, columna);
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    @Override
    public void enviarMovimiento(int fila, int columna) {
        try {
            String msg = "ATAQUE:" + fila + ":" + columna;
            salida.writeObject(msg);
            salida.flush();
        } catch (IOException e) {
            System.out.println("error enviando");
        }
    }

    @Override
    public void recibirMovimiento(int fila, int columna) {
        System.out.println("movimiento recibido: " + fila + "," + columna);
    }

    public void enviarTNT(int fila, int columna) {
        miTNT[0] = fila;
        miTNT[1] = columna;
        miTNTLista = true;

        try {
            String msg = "TNT:" + fila + ":" + columna;
            salida.writeObject(msg);
            salida.flush();
            System.out.println("mi tnt enviada");
        } catch (IOException e) {
            System.out.println("error enviando");
        }
    }

    public boolean estaConectado() {
        return conectado;
    }
}