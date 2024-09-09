/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import botones.boton;
import clases.asignaturas;
import clases.participantes;
import clases.preguntas;
import clases.respuestas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author YILBER
 */
public class vistapreguntas extends javax.swing.JFrame {

    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    private static vistapreguntas instancia;
    String Ruta = "";
    
    public vistapreguntas() {
        initComponents();
        clases.preguntas objetoAsignaturas = new clases.preguntas();
        objetoAsignaturas.mostrarPreguntas(tablapreguntas);
        objetoAsignaturas.llenarCombobox("asignaturas", idasignatura, "asignatura");
        objetoAsignaturas.llenarCombobox("grados", idgrado, "grado");
       
        tablapreguntas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tablapreguntas.getTableHeader().setOpaque(false);
        tablapreguntas.getTableHeader().setBackground(new Color(32,136,203));
        tablapreguntas.getTableHeader().setForeground(new Color(255,255,255));
        tablapreguntas.setRowHeight(100);
        
        tablarespuestas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tablarespuestas.getTableHeader().setOpaque(false);
        tablarespuestas.getTableHeader().setBackground(new Color(32,136,203));
        tablarespuestas.getTableHeader().setForeground(new Color(255,255,255));
        tablarespuestas.setRowHeight(100);
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

        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnguardar = new botones.boton();
        btnEliminar = new botones.boton();
        textAreaScroll1 = new textarea.TextAreaScroll();
        txtpregunta = new textarea.TextArea();
        idasignatura = new combo_suggestion.ComboBoxSuggestion();
        ASIGNATURA = new javax.swing.JLabel();
        Imagen = new javax.swing.JLabel();
        idgrado = new combo_suggestion.ComboBoxSuggestion();
        btnexaminar = new botones.boton();
        ASIGNATURA2 = new javax.swing.JLabel();
        lblImagen = new rojerusan.RSLabelImage();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablarespuestas = new javax.swing.JTable();
        btnsaverespuesta = new botones.boton();
        btnadd = new botones.boton();
        btndeleterespuesta = new botones.boton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablapreguntas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos preguntas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel1.setText("PREGUNTA");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 90, -1));

        btnguardar.setBackground(new java.awt.Color(51, 153, 0));
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Guardar");
        btnguardar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 200, 200, 50));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 190, 50));

        textAreaScroll1.setFont(new java.awt.Font("Questions", 0, 24)); // NOI18N
        textAreaScroll1.setLabelText("Redacta la pregunta");
        textAreaScroll1.setLineColor(new java.awt.Color(255, 102, 0));

        txtpregunta.setColumns(20);
        txtpregunta.setRows(3);
        txtpregunta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        textAreaScroll1.setViewportView(txtpregunta);

        jPanel1.add(textAreaScroll1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, 850, 80));

        idasignatura.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel1.add(idasignatura, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 400, 40));

        ASIGNATURA.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        ASIGNATURA.setText("ASIGNATURA");
        jPanel1.add(ASIGNATURA, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, -1));

        Imagen.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        Imagen.setText("Imagen");
        jPanel1.add(Imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 60, 70, -1));

        idgrado.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel1.add(idgrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 50, 350, 40));

        btnexaminar.setText("Examinar");
        btnexaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexaminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnexaminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1020, 140, 130, -1));

        ASIGNATURA2.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        ASIGNATURA2.setText("grado");
        jPanel1.add(ASIGNATURA2, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 50, -1));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 40, 260, 200));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1510, 270));
        jPanel1.getAccessibleContext().setAccessibleDescription("");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Respuestas ligadas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N

        tablarespuestas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablarespuestas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablarespuestas.setGridColor(new java.awt.Color(255, 255, 255));
        tablarespuestas.setRowHeight(40);
        tablarespuestas.setRowMargin(5);
        tablarespuestas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablarespuestas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablarespuestasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablarespuestas);

        btnsaverespuesta.setBackground(new java.awt.Color(0, 153, 0));
        btnsaverespuesta.setForeground(new java.awt.Color(255, 255, 255));
        btnsaverespuesta.setText("Guardar respuesta");
        btnsaverespuesta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnsaverespuestaMouseClicked(evt);
            }
        });
        btnsaverespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaverespuestaActionPerformed(evt);
            }
        });

        btnadd.setBackground(new java.awt.Color(0, 102, 255));
        btnadd.setForeground(new java.awt.Color(255, 255, 255));
        btnadd.setText("Añadir fila");
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });

        btndeleterespuesta.setBackground(new java.awt.Color(255, 0, 0));
        btndeleterespuesta.setForeground(new java.awt.Color(255, 255, 255));
        btndeleterespuesta.setText("Eliminar respuesta");
        btndeleterespuesta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleterespuestaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnsaverespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btndeleterespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnsaverespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndeleterespuesta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 290, 740, 510));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preguntas guardadas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N

        tablapreguntas.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablapreguntas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablapreguntas.setGridColor(new java.awt.Color(255, 255, 255));
        tablapreguntas.setRowHeight(40);
        tablapreguntas.setRowMargin(5);
        tablapreguntas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablapreguntas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablapreguntasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablapreguntas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 740, 510));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1530, 810));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablarespuestasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablarespuestasMouseClicked
       //preguntas objetoPreguntas = new preguntas();
       //objetoPreguntas.seleccionarPreguntas(tablapreguntas, textid, idasignatura, idgrado, txtpregunta);
    }//GEN-LAST:event_tablarespuestasMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        preguntas objetoPregunta = new preguntas();
        int fila = tablapreguntas.getSelectedRow();
        String idFila = tablapreguntas.getValueAt(fila, 0).toString();
        if(fila >= 0){
            objetoPregunta.eliminarPreguntas(idFila);
            objetoPregunta.mostrarPreguntas(tablapreguntas);
        }else{
            JOptionPane.showMessageDialog(null, "Ningun registro seleccionado");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        preguntas objetoPreguntas = new preguntas();
        byte[] imagenEnBytes = getImagen(Ruta);
        // Construir la ruta relativa
        String rutaRelativa = "";
        if (Ruta != null && !Ruta.isEmpty()) {
          rutaRelativa = "/preguntas/" + new File(Ruta).getName();
        }
        objetoPreguntas.insertarPregunta(txtpregunta, idasignatura, idgrado, rutaRelativa, lblImagen);
        objetoPreguntas.mostrarPreguntas(tablapreguntas);
        Ruta = "";
    }//GEN-LAST:event_btnguardarActionPerformed

    private void tablapreguntasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablapreguntasMouseClicked
       respuestas objetoRespuestas = new respuestas();
       objetoRespuestas.seleccionarPregunta(tablapreguntas, tablarespuestas);
    }//GEN-LAST:event_tablapreguntasMouseClicked

    private void btnsaverespuestaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnsaverespuestaMouseClicked
        respuestas objetoRespuestas = new respuestas();
       objetoRespuestas.guardarNuevasRespuestas(tablarespuestas, tablapreguntas);
    }//GEN-LAST:event_btnsaverespuestaMouseClicked

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tablarespuestas.getModel();
        // Agregar una nueva fila vacía
        modelo.addRow(new Object[]{null, "", ""});
    }//GEN-LAST:event_btnaddActionPerformed

    private void btndeleterespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleterespuestaActionPerformed
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tablarespuestas.getModel();

        // Obtener la fila seleccionada
        int filaSeleccionada = tablarespuestas.getSelectedRow();

        if (filaSeleccionada >= 0) { // Verificar que haya una fila seleccionada
            // Obtener el ID de la respuesta desde la tabla
            String idRespuesta = (String) modelo.getValueAt(filaSeleccionada, 0);

            // Verificar si la respuesta tiene un ID (es decir, no es una nueva fila)
            if (idRespuesta != null && !idRespuesta.trim().isEmpty()) {
                respuestas objetoRespuestas = new respuestas();
                objetoRespuestas.eliminarRespuestaDesdeBaseDeDatos(idRespuesta);
            }

            // Eliminar la fila del modelo de la tabla
            modelo.removeRow(filaSeleccionada);

            JOptionPane.showMessageDialog(null, "Fila eliminada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una fila para eliminar.");
        }
    }//GEN-LAST:event_btndeleterespuestaActionPerformed

    private void btnexaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexaminarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File imagenArchivo = fileChooser.getSelectedFile();
            Ruta = imagenArchivo.getAbsolutePath();

            // Definir la ruta relativa
            String rutaRelativa = "src/main/resources/preguntas/" + imagenArchivo.getName();
            File destino = new File(rutaRelativa);

            try {
                // Copiar el archivo al directorio de destino
                Files.copy(imagenArchivo.toPath(), destino.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Cargar la imagen y mostrarla en el JLabel
                ImageIcon imageIcon = new ImageIcon(destino.getAbsolutePath());
                Image imagen = imageIcon.getImage(); // Convertir a Image
                Image imagenEscalada = imagen.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(), Image.SCALE_SMOOTH); // Escalar la imagen
                imageIcon = new ImageIcon(imagenEscalada); // Convertir a ImageIcon

                lblImagen.setIcon(imageIcon); // Establecer el icono en el JLabel
            } catch (IOException ex) {
                Logger.getLogger(vistapreguntas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnexaminarActionPerformed

    private void btnsaverespuestaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaverespuestaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsaverespuestaActionPerformed

    private byte[] getImagen(String Ruta) {
        File imagen = new File(Ruta);
        try {
            byte[] icono = new byte[(int) imagen.length()];
            InputStream input = new FileInputStream(imagen);
            input.read(icono);
            return icono;
        } catch (Exception ex) {
            return null;
        }
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
            java.util.logging.Logger.getLogger(vistapreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistapreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistapreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistapreguntas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(vistapreguntas.class.getName()).log(Level.SEVERE, null, ex);
                }
                new vistapreguntas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ASIGNATURA;
    private javax.swing.JLabel ASIGNATURA2;
    private javax.swing.JLabel Imagen;
    private botones.boton btnEliminar;
    private botones.boton btnadd;
    private botones.boton btndeleterespuesta;
    private botones.boton btnexaminar;
    private botones.boton btnguardar;
    private botones.boton btnsaverespuesta;
    private combo_suggestion.ComboBoxSuggestion idasignatura;
    private combo_suggestion.ComboBoxSuggestion idgrado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private rojerusan.RSLabelImage lblImagen;
    private javax.swing.JTable tablapreguntas;
    private javax.swing.JTable tablarespuestas;
    private textarea.TextAreaScroll textAreaScroll1;
    private textarea.TextArea txtpregunta;
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
    public static vistapreguntas getInstancia() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new vistapreguntas();
        }
        instancia.setVisible(true);
        return instancia;
    }
}
