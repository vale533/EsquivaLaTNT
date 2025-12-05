package org.example;

import java.io.IOException;

import java.io.*;
import java.net.*;

public class JugadorSystem extends Jugador {
    private ServerSocket servidor;
    private Socket cliente;
    private ObjectOutputStream salida;
    private ObjectInputStream entrada;
    private boolean tieneConexion;

    private int[] miTNT = new int[2];
    private int[] tntRival = new int[2];
    private boolean miTNTLista = false;
    private boolean rivalListo = false;

    public JugadorSystem(String nombre, JuegoPrincipal partida) {
        super(nombre, partida);
        tieneConexion = false;
    }

    public void crearServidor() {
        new Thread(() -> {
            try {
                servidor = new ServerSocket(5000);
                System.out.println("esperando rival...");

                cliente = servidor.accept();
                System.out.println("rival conectado!");

                salida = new ObjectOutputStream(cliente.getOutputStream());
                entrada = new ObjectInputStream(cliente.getInputStream());
                tieneConexion = true;

                escuchar();

            } catch (IOException e) {
                System.out.println("error servidor: " + e.getMessage());
            }
        }).start();
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

    public void enviarTNT(int fila, int columna) {
        miTNT[0] = fila;
        miTNT[1] = columna;
        miTNTLista = true;

        if (tieneConexion) {
            try {
                String msg = "TNT:" + fila + ":" + columna;
                salida.writeObject(msg);
                salida.flush();
                System.out.println("mi tnt enviada");
            } catch (IOException e) {
                System.out.println("error enviando");
            }
        }
    }

    public boolean hayConexion() {
        return tieneConexion;
    }





}
