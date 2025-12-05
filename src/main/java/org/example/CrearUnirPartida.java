
package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class CrearUnirPartida extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CrearUnirPartida.class.getName());

    public CrearUnirPartida() {
        initComponents();
        this.setSize(600, 500);
        setLocationRelativeTo(null);
    }
    public void reproducirSonido(String ruta) {
    try {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(ruta));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

    @SuppressWarnings("unchecked")

    private void initComponents() {

        btnCrear = new javax.swing.JButton();
        btnUnirse = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


        btnCrear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/crearPartida.gif"))); // NOI18N
        btnCrear.setText("490*140");
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        btnCrear.setBounds(80, 90, 440, 130);
        getContentPane().add(btnCrear);

        btnUnirse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/unirsePsrtida.gif"))); // NOI18N
        btnUnirse.setText("jButton2");
        btnUnirse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUnirseActionPerformed(evt);
            }
        });
        btnUnirse.setBounds(90, 260, 410, 110);
        getContentPane().add(btnUnirse);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoMine.png"))); // NOI18N
        jLabel1.setBounds(0, 0, 590, 500);
        getContentPane().add(jLabel1);

        pack();
    }

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
         reproducirSonido("/sonido/select.wav");

        JOptionPane.showMessageDialog(this,
                "Creando servidor...\nEsperando rival",
                "Servidor waiting",
                JOptionPane.INFORMATION_MESSAGE);

        ventanaSeleccionBomba ventana = new ventanaSeleccionBomba();
        ventana.setVisible(true);
        this.dispose();
    }

    private void btnUnirseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUnirseActionPerformed
          reproducirSonido("/sonido/select.wav");
        String ip = JOptionPane.showInputDialog(this,
                "IP del servidor:");
        if (ip != null && !ip.trim().isEmpty()) {
            ventanaSeleccionBomba ventana = new ventanaSeleccionBomba(ip);
            ventana.setVisible(true);
            this.dispose();
        }
    }


    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new CrearUnirPartida().setVisible(true));
    }


    private javax.swing.JButton btnCrear;
    private javax.swing.JButton btnUnirse;
    private javax.swing.JLabel jLabel1;

}
