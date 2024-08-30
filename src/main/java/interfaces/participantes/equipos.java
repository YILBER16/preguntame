/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces.participantes;

import botones.boton;
import clases.participantes;
import interfaces.vistapreguntas;
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

/**
 *
 * @author YILBER
 */
public class equipos extends javax.swing.JFrame {
    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    private static equipos instancia;
    String Ruta = "";
    
    public equipos() {
        initComponents();
        clases.participantes objetoParticipantes = new clases.participantes();
        objetoParticipantes.mostrarParticipantes(tablaparticipantes);
        objetoParticipantes.llenarEquipos("Equipos", "equipo", idequipo);
       
        tablaparticipantes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tablaparticipantes.getTableHeader().setOpaque(false);
        tablaparticipantes.getTableHeader().setBackground(new Color(32,136,203));
        tablaparticipantes.getTableHeader().setForeground(new Color(255,255,255));
        tablaparticipantes.setRowHeight(80);
        
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
        jPanel3 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnguardar = new botones.boton();
        btnModificar = new botones.boton();
        btnEliminar = new botones.boton();
        txtnombres = new textfield.TextField();
        txtapellidos = new textfield.TextField();
        idequipo = new combo_suggestion.ComboBoxSuggestion();
        textid = new textfield.TextField();
        textlabel = new javax.swing.JLabel();
        btnexaminar = new botones.boton();
        jLabel4 = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaparticipantes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos participantes", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel1.setText("Nombres");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 70, -1));

        jLabel2.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel2.setText("Apellidos");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 70, -1));

        jLabel3.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel3.setText("Foto");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 80, -1));

        btnguardar.setBackground(new java.awt.Color(51, 153, 0));
        btnguardar.setForeground(new java.awt.Color(255, 255, 255));
        btnguardar.setText("Guardar");
        btnguardar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel1.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 510, 330, 50));

        btnModificar.setBackground(new java.awt.Color(255, 102, 0));
        btnModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        jPanel1.add(btnModificar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 330, 50));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 610, 330, 50));

        txtnombres.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtnombres.setShadowColor(new java.awt.Color(102, 102, 102));
        jPanel1.add(txtnombres, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 300, 50));

        txtapellidos.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        txtapellidos.setShadowColor(new java.awt.Color(102, 102, 102));
        jPanel1.add(txtapellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 300, 50));

        idequipo.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jPanel1.add(idequipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 230, 280, 40));

        textid.setEditable(false);
        textid.setEnabled(false);
        textid.setFocusable(false);
        textid.setShadowColor(new java.awt.Color(102, 102, 102));
        textid.setVerifyInputWhenFocusTarget(false);
        jPanel1.add(textid, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 300, 50));

        textlabel.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        textlabel.setText("Id");
        jPanel1.add(textlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, -1));

        btnexaminar.setText("Examinar");
        btnexaminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexaminarActionPerformed(evt);
            }
        });
        jPanel1.add(btnexaminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 280, -1));

        jLabel4.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel4.setText("Equipo");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 80, -1));
        jPanel1.add(lblImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 220, 160));

        jPanel3.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 420, 670));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla agregados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N

        tablaparticipantes.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        tablaparticipantes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaparticipantes.setGridColor(new java.awt.Color(255, 255, 255));
        tablaparticipantes.setRowHeight(40);
        tablaparticipantes.setRowMargin(5);
        tablaparticipantes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tablaparticipantes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaparticipantesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaparticipantes);

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

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 10, 700, 570));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 690));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaparticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaparticipantesMouseClicked
       participantes objetoParticipantes = new participantes();
       objetoParticipantes.seleccionarParticipante(tablaparticipantes, textid, txtnombres, txtapellidos, idequipo, lblImagen);
    }//GEN-LAST:event_tablaparticipantesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        participantes objetoParticipantes = new participantes();
        int fila = tablaparticipantes.getSelectedRow();
        if(fila >= 0){
            objetoParticipantes.eliminarParticipantes(textid);
            objetoParticipantes.mostrarParticipantes(tablaparticipantes);
        }else{
            JOptionPane.showMessageDialog(null, "Ningun registro seleccionado");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        participantes objetoParticipantes = new participantes();
        String rutaRelativa = null;  // Inicializamos rutaRelativa como null

        // Verificamos si hay una ruta cargada
        if (Ruta != null && !Ruta.isEmpty()) {
            byte[] imagenEnBytes = getImagen(Ruta);
            // Construir la ruta relativa si la imagen ha sido seleccionada
            rutaRelativa = "/fotos/" + new File(Ruta).getName();
        }
        objetoParticipantes.modificarParticipantes(textid, txtnombres, txtapellidos, idequipo, rutaRelativa);
        objetoParticipantes.mostrarParticipantes(tablaparticipantes);
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        participantes objetoParticipantes = new participantes();
         byte[] imagenEnBytes = getImagen(Ruta);
        // Construir la ruta relativa
        String rutaRelativa = "/preguntas/" + new File(Ruta).getName();
        objetoParticipantes.insertarParticipantes(txtnombres, txtapellidos, idequipo, rutaRelativa);
        objetoParticipantes.mostrarParticipantes(tablaparticipantes);
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnexaminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexaminarActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png", "gif");
        fileChooser.setFileFilter(extensionFilter);

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File imagenArchivo = fileChooser.getSelectedFile();
            Ruta = imagenArchivo.getAbsolutePath();

            // Definir la ruta relativa
            String rutaRelativa = "src/main/resources/fotos/" + imagenArchivo.getName();
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
                Logger.getLogger(equipos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnexaminarActionPerformed

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
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(equipos.class.getName()).log(Level.SEVERE, null, ex);
                }
                new equipos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton btnEliminar;
    private botones.boton btnModificar;
    private botones.boton btnexaminar;
    private botones.boton btnguardar;
    private combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private combo_suggestion.ComboBoxSuggestion idequipo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblImagen;
    private javax.swing.JTable tablaparticipantes;
    private textfield.TextField textid;
    private javax.swing.JLabel textlabel;
    private textfield.TextField txtapellidos;
    private textfield.TextField txtnombres;
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
    public static equipos getInstancia() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new equipos();
        }
        instancia.setVisible(true);
        return instancia;
    }
}
