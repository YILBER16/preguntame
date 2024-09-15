/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import clases.clasificacionLogica;
import clases.juegologica;
import javax.swing.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class clasificacionUi extends javax.swing.JFrame {
    private int idTorneo;
    
    public clasificacionUi(int idTorneo) {
        this.idTorneo = idTorneo;
        initComponents(); // Inicializa los componentes de la interfaz gráfica
        clasificacionLogica objetoLogica = new clasificacionLogica();
        objetoLogica.consultarClasificacion(idTorneo, nparticipantes1, nparticipantes2, nparticipantes3, nparticipantes4, imgpar1, imgpar2, imgpar3, imgpar4, npuntos1, npuntos2, npuntos3, npuntos4, n1, n2, n3, n4);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnsiguiente = new botones.boton();
        imgpar4 = new javax.swing.JLabel();
        npuntos4 = new javax.swing.JLabel();
        nparticipantes4 = new javax.swing.JLabel();
        n4 = new javax.swing.JLabel();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        imgpar3 = new javax.swing.JLabel();
        npuntos3 = new javax.swing.JLabel();
        nparticipantes3 = new javax.swing.JLabel();
        n3 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        imgpar2 = new javax.swing.JLabel();
        npuntos2 = new javax.swing.JLabel();
        nparticipantes2 = new javax.swing.JLabel();
        n2 = new javax.swing.JLabel();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        imgpar1 = new javax.swing.JLabel();
        npuntos1 = new javax.swing.JLabel();
        nparticipantes1 = new javax.swing.JLabel();
        n1 = new javax.swing.JLabel();
        rSLabelImage1 = new rojerusan.RSLabelImage();
        labelfondo = new rojerusan.RSLabelImage();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnsiguiente.setForeground(new java.awt.Color(255, 51, 51));
        btnsiguiente.setText("Siguiente pregunta");
        btnsiguiente.setToolTipText("");
        btnsiguiente.setFont(new java.awt.Font("Questions", 1, 36)); // NOI18N
        btnsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguienteActionPerformed(evt);
            }
        });
        jPanel1.add(btnsiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 730, 310, 60));
        jPanel1.add(imgpar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 690, 350, 80));

        npuntos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(npuntos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, 90, 120));

        nparticipantes4.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(nparticipantes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 650, 350, 40));

        n4.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n4.setForeground(new java.awt.Color(255, 51, 0));
        n4.setText("4");
        jPanel1.add(n4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 680, 30, 60));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        jPanel1.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 630, 530, 160));

        imgpar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(imgpar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 350, 80));

        npuntos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(npuntos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 500, 90, 120));

        nparticipantes3.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(nparticipantes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 500, 350, 40));

        n3.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n3.setForeground(new java.awt.Color(255, 51, 0));
        n3.setText("3");
        jPanel1.add(n3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 30, 60));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        jPanel1.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, 530, 160));

        imgpar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(imgpar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 350, 80));

        npuntos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(npuntos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 350, 90, 120));

        nparticipantes2.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(nparticipantes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, 350, 40));

        n2.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n2.setForeground(new java.awt.Color(255, 51, 0));
        n2.setText("2");
        jPanel1.add(n2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 30, 60));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        jPanel1.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 530, 160));

        imgpar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(imgpar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 350, 80));

        npuntos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(npuntos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 90, 110));

        nparticipantes1.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(nparticipantes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 350, 40));

        n1.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n1.setForeground(new java.awt.Color(255, 51, 0));
        n1.setText("1");
        jPanel1.add(n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 30, 60));

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        jPanel1.add(rSLabelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 530, 150));

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/clasificacion2_1.png"))); // NOI18N
        jPanel1.add(labelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 570, 800));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/fondo-clasificacion2.gif"))); // NOI18N
        jLabel1.setText("jLabel1");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguienteActionPerformed
        juegoencurso objetojuego = new juegoencurso(idTorneo);
        objetojuego.siguientePregunta(idTorneo);
    }//GEN-LAST:event_btnsiguienteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(clasificacionUi.class.getName()).log(Level.SEVERE, null, ex);
                }
                //new clasificacionUi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton btnsiguiente;
    private javax.swing.JLabel imgpar1;
    private javax.swing.JLabel imgpar2;
    private javax.swing.JLabel imgpar3;
    private javax.swing.JLabel imgpar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private rojerusan.RSLabelImage labelfondo;
    private javax.swing.JLabel n1;
    private javax.swing.JLabel n2;
    private javax.swing.JLabel n3;
    private javax.swing.JLabel n4;
    private javax.swing.JLabel nparticipantes1;
    private javax.swing.JLabel nparticipantes2;
    private javax.swing.JLabel nparticipantes3;
    private javax.swing.JLabel nparticipantes4;
    private javax.swing.JLabel npuntos1;
    private javax.swing.JLabel npuntos2;
    private javax.swing.JLabel npuntos3;
    private javax.swing.JLabel npuntos4;
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    // End of variables declaration//GEN-END:variables
}
