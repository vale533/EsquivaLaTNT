package org.example;

import org.example.excepciones.BombayaExistenteException;

import javax.swing.*;
import java.awt.Color;

public class ventanaSeleccionBomba extends JFrame {

    private JuegoPrincipal partidaActual;
    private Jugador system;
    private JLabel[][] tablero;

    public ventanaSeleccionBomba() {
        partidaActual = new JuegoPrincipal(3, 4);
        system = new JugadorSystem("Jugador1", partidaActual);

        configurarComponentes();
        this.setSize(600, 500);
        setLocationRelativeTo(null);
        setTitle("Coloca tu TNT");

        tablero = new JLabel[][] {
                {casilla1, casilla2, casilla3, casilla4},
                {casilla5, casilla6, casilla7, casilla8},
                {casilla9, casilla10, casilla11, casilla12}
        };
    }

    private void configurarComponentes() {
        casilla1 = new JLabel();
        casilla2 = new JLabel();
        casilla3 = new JLabel();
        casilla4 = new JLabel();
        casilla5 = new JLabel();
        casilla6 = new JLabel();
        casilla7 = new JLabel();
        casilla8 = new JLabel();
        casilla9 = new JLabel();
        casilla10 = new JLabel();
        casilla11 = new JLabel();
        casilla12 = new JLabel();
        fondoPantalla = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        casilla1.setBackground(new Color(255, 255, 255));
        casilla1.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon4.png")));
        casilla1.setOpaque(true);
        casilla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(0, 0);
            }
        });
        casilla1.setBounds(10, 80, 130, 100);
        getContentPane().add(casilla1);

        casilla2.setBackground(new Color(255, 255, 255));
        casilla2.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon3.png")));
        casilla2.setOpaque(true);
        casilla2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(0, 1);
            }
        });
        casilla2.setBounds(160, 80, 130, 100);
        getContentPane().add(casilla2);

        casilla3.setBackground(new Color(255, 255, 255));
        casilla3.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon4.png")));
        casilla3.setOpaque(true);
        casilla3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(0, 2);
            }
        });
        casilla3.setBounds(310, 80, 130, 100);
        getContentPane().add(casilla3);

        casilla4.setBackground(new Color(255, 255, 255));
        casilla4.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon4.png")));
        casilla4.setOpaque(true);
        casilla4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(0, 3);
            }
        });
        casilla4.setBounds(460, 80, 130, 100);
        getContentPane().add(casilla4);

        casilla5.setBackground(new Color(255, 255, 255));
        casilla5.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon3.png")));
        casilla5.setOpaque(true);
        casilla5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(1, 0);
            }
        });
        casilla5.setBounds(10, 200, 130, 100);
        getContentPane().add(casilla5);

        casilla6.setBackground(new Color(255, 255, 255));
        casilla6.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon2.png")));
        casilla6.setOpaque(true);
        casilla6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(1, 1);
            }
        });
        casilla6.setBounds(160, 200, 130, 100);
        getContentPane().add(casilla6);

        casilla7.setBackground(new Color(255, 255, 255));
        casilla7.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon4.png")));
        casilla7.setOpaque(true);
        casilla7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(1, 2);
            }
        });
        casilla7.setBounds(310, 200, 130, 100);
        getContentPane().add(casilla7);

        casilla8.setBackground(new Color(255, 255, 255));
        casilla8.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon2.png")));
        casilla8.setOpaque(true);
        casilla8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(1, 3);
            }
        });
        casilla8.setBounds(460, 200, 130, 100);
        getContentPane().add(casilla8);

        casilla9.setBackground(new Color(255, 255, 255));
        casilla9.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon1.png")));
        casilla9.setOpaque(true);
        casilla9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(2, 0);
            }
        });
        casilla9.setBounds(10, 330, 130, 100);
        getContentPane().add(casilla9);

        casilla10.setBackground(new Color(255, 255, 255));
        casilla10.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon1.png")));
        casilla10.setOpaque(true);
        casilla10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(2, 1);
            }
        });
        casilla10.setBounds(160, 330, 130, 100);
        getContentPane().add(casilla10);

        casilla11.setBackground(new Color(255, 255, 255));
        casilla11.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon4.png")));
        casilla11.setOpaque(true);
        casilla11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(2, 2);
            }
        });
        casilla11.setBounds(310, 330, 130, 100);
        getContentPane().add(casilla11);

        casilla12.setBackground(new Color(255, 255, 255));
        casilla12.setIcon(new ImageIcon(getClass().getResource("/imagenes/icon3.png")));
        casilla12.setOpaque(true);
        casilla12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccionarPosicion(2, 3);
            }
        });
        casilla12.setBounds(460, 330, 130, 100);
        getContentPane().add(casilla12);

        fondoPantalla.setIcon(new ImageIcon(getClass().getResource("/imagenes/Minecraft dirt (1) (1).png")));
        fondoPantalla.setBounds(0, 10, 600, 440);
        getContentPane().add(fondoPantalla);

        pack();
    }

    private void seleccionarPosicion(int f, int c) {
        try {
            partidaActual.seleccionarUbicacionBomba(f, c);

            tablero[f][c].setBackground(new Color(220, 20, 60));

            tablero[f][c].setHorizontalAlignment(SwingConstants.CENTER);

            System.out.println("Bomba puesta: " + (f+1) + "," + (c+1));

            JOptionPane.showMessageDialog(this,
                    "Tu bomba esta en la posicion " + (f+1) + "," + (c+1),
                    "Listo",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (BombayaExistenteException e) {
            System.err.println("Error: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Error - Tnt ya colocada",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaSeleccionBomba().setVisible(true);
            }
        });
    }

    private JLabel casilla1;
    private JLabel casilla2;
    private JLabel casilla3;
    private JLabel casilla4;
    private JLabel casilla5;
    private JLabel casilla6;
    private JLabel casilla7;
    private JLabel casilla8;
    private JLabel casilla9;
    private JLabel casilla10;
    private JLabel casilla11;
    private JLabel casilla12;
    private JLabel fondoPantalla;
}