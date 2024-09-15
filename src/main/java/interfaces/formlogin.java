/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import clases.torneo;
import interfaces.participantes.equipos;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author YILBER
 */
public class formlogin extends JFrame {

    private static formlogin instancia;
    
    public formlogin() {
         initComponents();   
        
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Para cerrar solo la ventana sin cerrar la aplicación
        setResizable(false);
        this.setVisible(true);
    }      
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(equipos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new formlogin().setVisible(true);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSMaterialButtonRectangleBeanInfo1 = new rojerusan.RSMaterialButtonRectangleBeanInfo();
        rSComboMetroBeanInfo1 = new rojerusan.RSComboMetroBeanInfo();
        jPanel6 = new javax.swing.JPanel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        labelopciond = new javax.swing.JLabel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        boton1 = new botones.boton();
        jLabel3 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        jLabel2 = new javax.swing.JLabel();
        panelasignaturas = new javax.swing.JPanel();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        jLabel8 = new javax.swing.JLabel();
        paneljugar = new javax.swing.JPanel();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        jLabel7 = new javax.swing.JLabel();
        panelpreguntas = new javax.swing.JPanel();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        jLabel10 = new javax.swing.JLabel();
        panelparticipantes = new javax.swing.JPanel();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        labelopciond1 = new javax.swing.JLabel();
        labelopciond2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/logo.png"))); // NOI18N
        jPanel6.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 206, 202));

        labelopciond.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        labelopciond.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelopciond.setText("<html><span style=\"text-aling:center\">DOCENTE ING. ROCIO CARREÑO<br><br>INTEGRANTES SEMILLERO IDEATECH</span></html>");
        jPanel6.add(labelopciond, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 470, 310, 190));

        kGradientPanel3.setkEndColor(new java.awt.Color(255, 102, 0));
        kGradientPanel3.setkStartColor(new java.awt.Color(255, 204, 0));
        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton1.setForeground(new java.awt.Color(255, 51, 0));
        boton1.setText("salir :(");
        boton1.setActionCommand("Cerrar sesión :(");
        boton1.setFocusable(false);
        boton1.setFont(new java.awt.Font("Questions", 1, 36)); // NOI18N
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        kGradientPanel3.add(boton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1158, 22, 171, 53));

        jLabel3.setFont(new java.awt.Font("Questions", 3, 48)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("PREGUNTAME - SOFTWARE DE PREGUNTAS");
        kGradientPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 17, 646, 68));

        jPanel6.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 1350, 100));

        kGradientPanel4.setkEndColor(new java.awt.Color(255, 102, 0));
        kGradientPanel4.setkStartColor(new java.awt.Color(255, 204, 51));

        rSLabelImage12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/ideatech-NG_1.png"))); // NOI18N

        rSLabelImage11.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/COLCARMEN.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Questions", 3, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("BIENVENIDO");

        javax.swing.GroupLayout kGradientPanel4Layout = new javax.swing.GroupLayout(kGradientPanel4);
        kGradientPanel4.setLayout(kGradientPanel4Layout);
        kGradientPanel4Layout.setHorizontalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(rSLabelImage11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelImage12, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
            .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(kGradientPanel4Layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(7, 7, 7)))
        );
        kGradientPanel4Layout.setVerticalGroup(
            kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel4Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(rSLabelImage12, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 347, Short.MAX_VALUE)
                .addComponent(rSLabelImage11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(kGradientPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel4Layout.createSequentialGroup()
                    .addContainerGap(261, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(231, 231, 231)))
        );

        jPanel6.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 230, 560));

        panelasignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelasignaturasMouseClicked(evt);
            }
        });

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/text-books_1081025.png"))); // NOI18N

        jLabel8.setFont(new java.awt.Font("Questions", 3, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 102, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("ASIGNATURAS");

        javax.swing.GroupLayout panelasignaturasLayout = new javax.swing.GroupLayout(panelasignaturas);
        panelasignaturas.setLayout(panelasignaturasLayout);
        panelasignaturasLayout.setHorizontalGroup(
            panelasignaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelasignaturasLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelasignaturasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelImage8, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44))
        );
        panelasignaturasLayout.setVerticalGroup(
            panelasignaturasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelasignaturasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage8, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addContainerGap())
        );

        jPanel6.add(panelasignaturas, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 490, 280, 260));

        paneljugar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                paneljugarMouseClicked(evt);
            }
        });

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/joystick_808476.png"))); // NOI18N

        jLabel7.setFont(new java.awt.Font("Questions", 3, 48)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 102, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("JUGAR");

        javax.swing.GroupLayout paneljugarLayout = new javax.swing.GroupLayout(paneljugar);
        paneljugar.setLayout(paneljugarLayout);
        paneljugarLayout.setHorizontalGroup(
            paneljugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneljugarLayout.createSequentialGroup()
                .addGroup(paneljugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(paneljugarLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(rSLabelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(paneljugarLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(54, Short.MAX_VALUE))
        );
        paneljugarLayout.setVerticalGroup(
            paneljugarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(paneljugarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage7, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.add(paneljugar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 210, 280, 260));

        panelpreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelpreguntasMouseClicked(evt);
            }
        });

        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/question-mark_5726642.png"))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Questions", 3, 48)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 102, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("PREGUNTAS");

        javax.swing.GroupLayout panelpreguntasLayout = new javax.swing.GroupLayout(panelpreguntas);
        panelpreguntas.setLayout(panelpreguntasLayout);
        panelpreguntasLayout.setHorizontalGroup(
            panelpreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelpreguntasLayout.createSequentialGroup()
                .addGroup(panelpreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelpreguntasLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(rSLabelImage10, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelpreguntasLayout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(21, Short.MAX_VALUE))
        );
        panelpreguntasLayout.setVerticalGroup(
            panelpreguntasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelpreguntasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage10, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addContainerGap())
        );

        jPanel6.add(panelpreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 490, 280, 260));

        panelparticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelparticipantesMouseClicked(evt);
            }
        });

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/team_5065337.png"))); // NOI18N

        jLabel9.setFont(new java.awt.Font("Questions", 3, 48)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 102, 0));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("PARTICIPANTES");

        javax.swing.GroupLayout panelparticipantesLayout = new javax.swing.GroupLayout(panelparticipantes);
        panelparticipantes.setLayout(panelparticipantesLayout);
        panelparticipantesLayout.setHorizontalGroup(
            panelparticipantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelparticipantesLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelparticipantesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rSLabelImage9, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49))
        );
        panelparticipantesLayout.setVerticalGroup(
            panelparticipantesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelparticipantesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(rSLabelImage9, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9)
                .addContainerGap())
        );

        jPanel6.add(panelparticipantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 210, 280, 260));

        jLabel1.setFont(new java.awt.Font("Questions", 3, 36)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SOFTWARE DESARROLLADO POR");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 230, 330, -1));

        labelopciond1.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        labelopciond1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelopciond1.setText("<html><span style=\"text-aling:center\">DOCENTE ING. <br>YILBER JOSE TORO MANOSALVA</span></html>");
        jPanel6.add(labelopciond1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 300, 340, 80));

        labelopciond2.setFont(new java.awt.Font("Segoe UI", 2, 22)); // NOI18N
        labelopciond2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelopciond2.setText("<html><span style=\"text-aling:center\">DOCENTE ING. <br>LEIDY JOHANNA MEJIA CHOGO</span></html>");
        jPanel6.add(labelopciond2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 390, 340, 80));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro que deseas irte? :(", "Confirmar abandono", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
    }//GEN-LAST:event_boton1ActionPerformed

    private void paneljugarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_paneljugarMouseClicked
        cargarJuego objetoTorneo = new cargarJuego();
        // Ocultar el formulario de login al abrir la nueva ventana
        this.setVisible(false);  // Oculta el formlogin actual

        // Añadir un WindowListener a la nueva ventana para mostrar formlogin cuando se cierre
        objetoTorneo.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Mostrar de nuevo el formlogin cuando la ventana de equipos se cierra
                formlogin.getInstancia().setVisible(true);
            }
        });

        objetoTorneo.setVisible(true); // Mostrar la nueva ventana
    }//GEN-LAST:event_paneljugarMouseClicked

    private void panelparticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelparticipantesMouseClicked
        equipos objetoParticipantes = new equipos();
        // Ocultar el formulario de login al abrir la nueva ventana
        this.setVisible(false);  // Oculta el formlogin actual

        // Añadir un WindowListener a la nueva ventana para mostrar formlogin cuando se cierre
        objetoParticipantes.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Mostrar de nuevo el formlogin cuando la ventana de equipos se cierra
                formlogin.getInstancia().setVisible(true);
            }
        });

        objetoParticipantes.setVisible(true); // Mostrar la nueva ventana
    }//GEN-LAST:event_panelparticipantesMouseClicked

    private void panelasignaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelasignaturasMouseClicked
        vistaasignaturas objetoAsignaturas = new vistaasignaturas();
        // Ocultar el formulario de login al abrir la nueva ventana
        this.setVisible(false);  // Oculta el formlogin actual

        // Añadir un WindowListener a la nueva ventana para mostrar formlogin cuando se cierre
        objetoAsignaturas.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Mostrar de nuevo el formlogin cuando la ventana de equipos se cierra
                formlogin.getInstancia().setVisible(true);
            }
        });

        objetoAsignaturas.setVisible(true); // Mostrar la nueva ventana
    }//GEN-LAST:event_panelasignaturasMouseClicked

    private void panelpreguntasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelpreguntasMouseClicked
        vistapreguntas objetoPreguntas = new vistapreguntas();
        // Ocultar el formulario de login al abrir la nueva ventana
        this.setVisible(false);  // Oculta el formlogin actual

        // Añadir un WindowListener a la nueva ventana para mostrar formlogin cuando se cierre
        objetoPreguntas.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Mostrar de nuevo el formlogin cuando la ventana de equipos se cierra
                formlogin.getInstancia().setVisible(true);
            }
        });

        objetoPreguntas.setVisible(true); // Mostrar la nueva ventana
    }//GEN-LAST:event_panelpreguntasMouseClicked

    public void setImageLabel(JLabel labelName, String root) {
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }

    public static formlogin getInstancia() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new formlogin();
        }
        instancia.setVisible(true);
        return instancia;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton boton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel6;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private javax.swing.JLabel labelopciond;
    private javax.swing.JLabel labelopciond1;
    private javax.swing.JLabel labelopciond2;
    private javax.swing.JPanel panelasignaturas;
    private javax.swing.JPanel paneljugar;
    private javax.swing.JPanel panelparticipantes;
    private javax.swing.JPanel panelpreguntas;
    private rojerusan.RSComboMetroBeanInfo rSComboMetroBeanInfo1;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private rojerusan.RSMaterialButtonRectangleBeanInfo rSMaterialButtonRectangleBeanInfo1;
    // End of variables declaration//GEN-END:variables
}
