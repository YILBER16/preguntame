/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import botones.boton;
import clases.asignaturas;
import clases.participantes;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

/**
 *
 * @author YILBER
 */
public class cargarJuego extends javax.swing.JFrame {

    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    private static cargarJuego instancia;
    
    public cargarJuego() {
        initComponents();
        clases.logicaCargarTorneo objetologica = new clases.logicaCargarTorneo();
        objetologica.mostrarTorneos(tablatorneos);
       
        tablatorneos.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tablatorneos.getTableHeader().setOpaque(false);
        tablatorneos.getTableHeader().setBackground(new Color(32,136,203));
        tablatorneos.getTableHeader().setForeground(new Color(255,255,255));
        tablatorneos.setRowHeight(25);
    }

    public JButton getBtnGuardar() {
        return btnCargar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        boton btnGuardar1 = this.btnCargar;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new combo_suggestion.ComboSuggestionUI();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablatorneos = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        btnCargar = new botones.boton();
        btnEliminar = new botones.boton();
        btnCrear = new botones.boton();
        btnRegresar = new botones.boton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Torneos Creados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N

        tablatorneos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablatorneos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablatorneos.setGridColor(new java.awt.Color(255, 255, 255));
        tablatorneos.setRowHeight(40);
        tablatorneos.setRowMargin(5);
        tablatorneos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablatorneos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablatorneosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablatorneos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1118, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(9, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1140, 510));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCargar.setBackground(new java.awt.Color(51, 153, 0));
        btnCargar.setForeground(new java.awt.Color(255, 255, 255));
        btnCargar.setText("Cargar");
        btnCargar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 610, 300, 50));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 610, 300, 50));

        btnCrear.setBackground(new java.awt.Color(51, 153, 0));
        btnCrear.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear.setText("Crear torneo +");
        btnCrear.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnCrear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearActionPerformed(evt);
            }
        });
        jPanel3.add(btnCrear, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 300, 50));

        btnRegresar.setBackground(new java.awt.Color(0, 102, 255));
        btnRegresar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegresar.setText("Menu");
        btnRegresar.setActionCommand("Cerrar sesión :(");
        btnRegresar.setFocusable(false);
        btnRegresar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        jPanel3.add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 20, 150, 50));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablatorneosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablatorneosMouseClicked
  
    }//GEN-LAST:event_tablatorneosMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        int fila = tablatorneos.getSelectedRow();
        clases.logicaCargarTorneo objetologica = new clases.logicaCargarTorneo();
        if(fila >= 0){
            objetologica.eliminarTorneos(tablatorneos);
        }else{
            JOptionPane.showMessageDialog(null, "Ningun registro seleccionado");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        int fila = tablatorneos.getSelectedRow();
        if(fila >= 0){
            String idFilaStr = (String) tablatorneos.getValueAt(fila, 0);   
            int idFila = Integer.parseInt(idFilaStr); // Convertir el String a int 
            System.out.println("IdTorneo: "+idFila);
            // Mostrar un cuadro de confirmación
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea cargar este torneo?", 
                                                             "Confirmar cagué", JOptionPane.YES_NO_OPTION);
            
            juegoencurso objetoJuegoEnCurso = new juegoencurso(idFila);
            this.setVisible(false);  // Oculta el formlogin actual
            // Solo eliminar si el usuario confirma
            if (confirmacion == JOptionPane.YES_OPTION) {
                // Añadir un WindowListener a la nueva ventana para mostrar formlogin cuando se cierre
                objetoJuegoEnCurso.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        // Mostrar de nuevo el formlogin cuando la ventana de equipos se cierra
                        cargarJuego.getInstancia().setVisible(true);
                    }
                });
                objetoJuegoEnCurso.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Carga cancelada.");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Ningun registro seleccionado");
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void btnCrearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearActionPerformed
        creartorneo objetoCrearTorneo = new creartorneo();
        // Ocultar el formulario de login al abrir la nueva ventana
        this.setVisible(false);  // Oculta el formlogin actual

        // Añadir un WindowListener a la nueva ventana para mostrar formlogin cuando se cierre
        objetoCrearTorneo.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                // Mostrar de nuevo el formlogin cuando la ventana de equipos se cierra
                cargarJuego.getInstancia().setVisible(true);
            }
        });
        objetoCrearTorneo.setVisible(true); // Mostrar la nueva ventana
    }//GEN-LAST:event_btnCrearActionPerformed

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        this.setVisible(false);
        formlogin objetoForm = new formlogin();
        objetoForm.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    public void refrescarTablaTorneos() {
        clases.logicaCargarTorneo objetologica = new clases.logicaCargarTorneo();
        objetologica.mostrarTorneos(tablatorneos); // Recargar los torneos en la tabla
    }
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(cargarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(cargarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(cargarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(cargarJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(cargarJuego.class.getName()).log(Level.SEVERE, null, ex);
                }
                new cargarJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton btnCargar;
    private botones.boton btnCrear;
    private botones.boton btnEliminar;
    private botones.boton btnRegresar;
    private combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablatorneos;
    // End of variables declaration//GEN-END:variables

    public boolean isOver() {
        return over;
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
    
    @Override
    public void paintComponents(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D)grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
        g2.setColor(getBackground());
        g2.fillRoundRect(2, 2, getWidth()-4, getHeight()-4, radius, radius);
        super.paintComponents(grphcs); 
    }
    public static cargarJuego getInstancia() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new cargarJuego();
        }
        instancia.setVisible(true);
        return instancia;
    }
}
