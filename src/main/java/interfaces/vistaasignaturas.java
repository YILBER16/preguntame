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
public class vistaasignaturas extends javax.swing.JFrame {

    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    private static vistaasignaturas instancia;
    
    public vistaasignaturas() {
        initComponents();
        clases.asignaturas objetoAsignaturas = new clases.asignaturas();
        objetoAsignaturas.mostrarAsignaturas(tablaasignaturas);
       
        tablaasignaturas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tablaasignaturas.getTableHeader().setOpaque(false);
        tablaasignaturas.getTableHeader().setBackground(new Color(32,136,203));
        tablaasignaturas.getTableHeader().setForeground(new Color(255,255,255));
        tablaasignaturas.setRowHeight(25);
    }

    public JButton getBtnGuardar() {
        return btnguardar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        boton btnGuardar1 = this.btnguardar;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new combo_suggestion.ComboSuggestionUI();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaasignaturas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnguardar = new botones.boton();
        btnModificar = new botones.boton();
        btnEliminar = new botones.boton();
        txtasignatura = new textfield.TextField();
        textid = new textfield.TextField();
        textlabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla guardadas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N

        tablaasignaturas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablaasignaturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaasignaturas.setGridColor(new java.awt.Color(255, 255, 255));
        tablaasignaturas.setRowHeight(40);
        tablaasignaturas.setRowMargin(5);
        tablaasignaturas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaasignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaasignaturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaasignaturas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(7, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 700, 570));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos asignaturas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel1.setText("Asignatura");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 90, -1));

        btnguardar.setBackground(new java.awt.Color(51, 153, 0));
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Guardar");
        btnguardar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, 300, 50));

        btnModificar.setBackground(new java.awt.Color(255, 102, 0));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 300, 50));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 310, 300, 50));

        txtasignatura.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtasignatura.setShadowColor(new java.awt.Color(102, 102, 102));
        jPanel1.add(txtasignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 300, 50));

        textid.setEditable(false);
        textid.setEnabled(false);
        textid.setFocusable(false);
        textid.setShadowColor(new java.awt.Color(102, 102, 102));
        textid.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(textid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 300, 50));

        textlabel.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        textlabel.setText("Id");
        jPanel1.add(textlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, -1));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 420, 670));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaasignaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaasignaturasMouseClicked
        asignaturas objetoAsignaturas = new asignaturas();
       objetoAsignaturas.seleccionarAsignaturas(tablaasignaturas, textid, txtasignatura);
    }//GEN-LAST:event_tablaasignaturasMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        asignaturas objetoAsignaturas = new asignaturas();
        int fila = tablaasignaturas.getSelectedRow();
        if(fila >= 0){
            objetoAsignaturas.eliminarAsignaturas(textid);
            objetoAsignaturas.mostrarAsignaturas(tablaasignaturas);
        }else{
            JOptionPane.showMessageDialog(null, "Ningun registro seleccionado");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        asignaturas objetoAsignaturas = new asignaturas();
        int fila = tablaasignaturas.getSelectedRow();
        if(fila >= 0){
            objetoAsignaturas.modificarAsignaturas(textid, txtasignatura);
            objetoAsignaturas.mostrarAsignaturas(tablaasignaturas);
        }else{
            JOptionPane.showMessageDialog(null, "Ningun registro seleccionado");
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        asignaturas objetoAsignaturas = new asignaturas();
        objetoAsignaturas.insertarAsignatura(txtasignatura);
        objetoAsignaturas.mostrarAsignaturas(tablaasignaturas);
    }//GEN-LAST:event_btnguardarActionPerformed

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
            java.util.logging.Logger.getLogger(vistaasignaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaasignaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaasignaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaasignaturas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(vistaasignaturas.class.getName()).log(Level.SEVERE, null, ex);
                }
                new vistaasignaturas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton btnEliminar;
    private botones.boton btnModificar;
    private botones.boton btnguardar;
    private combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaasignaturas;
    private textfield.TextField textid;
    private javax.swing.JLabel textlabel;
    private textfield.TextField txtasignatura;
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
    public static vistaasignaturas getInstancia() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new vistaasignaturas();
        }
        instancia.setVisible(true);
        return instancia;
    }
}
