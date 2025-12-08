
package org.example;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class inicio extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(inicio.class.getName());

    public inicio() {
        initComponents();
        this.setSize(600, 500);
        setLocationRelativeTo(null);
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
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
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        BtnJugar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6=new javax.swing.JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/explosion.png"))); // NOI18N
        jLabel4.setBounds(350, 330, 170, 110);
        getContentPane().add(jLabel4);

        BtnJugar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/botonJugar (1) (1).png"))); // NOI18N
        BtnJugar.setText("JUGAR");
        BtnJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                BtnJugarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                BtnJugarMouseExited(evt);
            }
        });
        BtnJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnJugarActionPerformed(evt);
            }
        });

        BtnJugar.setBounds(160, 320, 270, 90);
        getContentPane().add(BtnJugar);
        jLabel2.setBounds(420, 290, -1, -1);
        getContentPane().add(jLabel2);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/creeper.png")));
        jLabel5.setBounds(50, 260, 230, 220);
        getContentPane().add(jLabel5);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/fondoMine.png")));
        jLabel1.setBounds(0, 270, 600, 330);
        getContentPane().add(jLabel1);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tntIcon.gif")));
        jLabel6.setBounds(390, 120, 180, 140);
        getContentPane().add(jLabel6);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/tituloInicial.png")));
        jLabel3.setBounds(0, 0, 610, 270);
        getContentPane().add(jLabel3);
        pack();

    }

    private void BtnJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnJugarActionPerformed
       CrearUnirPartida c= new CrearUnirPartida();
       reproducirSonido("/sonido/select.wav");
        c.setVisible(true);
        this.dispose();
    }

    private void BtnJugarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnJugarMouseExited
        jLabel4.setVisible(false);
        jLabel5.setVisible(false);
    }

    private void BtnJugarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BtnJugarMouseEntered
     jLabel4.setVisible(true);
      jLabel5.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new inicio().setVisible(true));
    }


    private javax.swing.JButton BtnJugar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;

}
