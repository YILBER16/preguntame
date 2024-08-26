/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import botones.boton;
import clases.conexionbd;
import clases.torneo;
import com.compudisenos.arduinoconexion.conexionarduino;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author YILBER
 */
public class creartorneo extends javax.swing.JFrame {

    
    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;
    private static creartorneo instancia;
    private DefaultTableModel modeloParticipantes;
    
//    PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
//    SerialPortEventListener listen = new SerialPortEventListener() {
//        @Override
//        public void serialEvent(SerialPortEvent spe) {
//            try {
//                if(ino.isMessageAvailable()){
//                    System.out.println(ino.printMessage());
//                }
//            } catch (SerialPortException ex) {
//                Logger.getLogger(conexionarduino.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ArduinoException ex) {
//                Logger.getLogger(conexionarduino.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    };
    
    public creartorneo() {
        initComponents();
        configurarTablaParticipantes();
        
        // Configuración del encabezado de la tabla
        tablaparticipantes.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 18));
        tablaparticipantes.getTableHeader().setOpaque(false);
        tablaparticipantes.getTableHeader().setBackground(new Color(32, 136, 203));
        tablaparticipantes.getTableHeader().setForeground(Color.WHITE);
        tablaparticipantes.getTableHeader().setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 1));

        // Configuración de la fila
        tablaparticipantes.setRowHeight(120);
        tablaparticipantes.setIntercellSpacing(new Dimension(0, 1)); // Espacio entre celdas
        tablaparticipantes.setShowGrid(true); // Mostrar rejilla
        tablaparticipantes.setGridColor(new Color(200, 200, 200)); // Color de la rejilla

        
        clases.torneo objetoCrearTorneo = new clases.torneo();
        objetoCrearTorneo.llenarComboboxTipoTorneo(idtipotorneo);
        objetoCrearTorneo.llenarComboboxColor(idcolor);
        //objetoCrearTorneo.llenarComboBoxAsignatura(idasignaturas);
        
         // Agregas el ActionListener al JComboBox idtipotorneo
        idtipotorneo.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Aquí llamas a tu método cuando cambia la selección
                clases.torneo objetoCrearTorneo = new clases.torneo();
                objetoCrearTorneo.llenarComboboxParticipantes("Participantes", idtipotorneo, idparticipantes);
            }
        });
 
        btnGo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
                // Crear el JComboBox que será llenado con los datos de la base de datos
                JComboBox<torneo> comboBoxAsignaturas = new JComboBox<>();
                int elementosTabla = tablaparticipantes.getRowCount();
                
                if(elementosTabla>=2){
                    // Realizar la consulta para obtener las asignaturas
                    String sql = "SELECT id, asignatura FROM asignaturas";
                    conexionbd con = new conexionbd();
                    Connection conexion = con.establecerConexion();

                    try (Statement st = conexion.createStatement(); 
                         ResultSet rs = st.executeQuery(sql)) {

                        // Agregar una opción inicial "Seleccione una opción"
                        comboBoxAsignaturas.addItem(new torneo(0, "Seleccione una opción", null));

                        // Llenar el JComboBox con las asignaturas de la base de datos
                        while (rs.next()) {
                            int id = rs.getInt("id");
                            String nombre = rs.getString("asignatura");
                            torneo asignatura = new torneo(id, nombre, null);
                            comboBoxAsignaturas.addItem(asignatura);
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Error al cargar asignaturas: " + ex.toString());
                        return;  // Si hay un error, detener la ejecución
                    }

                    // Mostrar el JOptionPane con el JComboBox lleno
                    int opcion = JOptionPane.showConfirmDialog(
                        null,  // El padre del JOptionPane, puede ser tu JFrame si lo necesitas
                        comboBoxAsignaturas,
                        "Seleccione una asignatura",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                    );

                    // Verificar si se presionó OK
                    if (opcion == JOptionPane.OK_OPTION) {
                        torneo seleccion = (torneo) comboBoxAsignaturas.getSelectedItem();
                        if (seleccion != null && seleccion.getId() != 0) {
                            objetoCrearTorneo.iniciarTorneo(tablaparticipantes, comboBoxAsignaturas);
                        } else {
                            JOptionPane.showMessageDialog(null, "No seleccionaste una asignatura válida.");
                        }
                    }                      
                }else{
                    JOptionPane.showMessageDialog(null, "Por favor agregue minimo 2 participantes");
                }
            }
        });

//        try {
//            ino.arduinoRX("COM6", 9600, listen);
//        } catch (ArduinoException ex) {
//            Logger.getLogger(conexionarduino.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (SerialPortException ex) {
//            Logger.getLogger(conexionarduino.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public JButton getBtnGuardar() {
        return btnagregar;
    }

    public void setBtnGuardar(JButton btnGuardar) {
        boton btnGuardar1 = this.btnagregar;
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new combo_suggestion.ComboSuggestionUI();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idtipotorneo = new combo_suggestion.ComboBoxSuggestion();
        jLabel3 = new javax.swing.JLabel();
        idparticipantes = new combo_suggestion.ComboBoxSuggestion();
        jLabel4 = new javax.swing.JLabel();
        idcolor = new combo_suggestion.ComboBoxSuggestion();
        btnagregar = new botones.boton();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaparticipantes = new javax.swing.JTable();
        btnGo = new botones.boton();
        btnEliminar = new botones.boton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel1.setText("Tipo torneo");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 100, -1));

        idtipotorneo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        idtipotorneo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                idtipotorneoMouseClicked(evt);
            }
        });
        idtipotorneo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                idtipotorneoActionPerformed(evt);
            }
        });
        jPanel1.add(idtipotorneo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 250, 40));

        jLabel3.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel3.setText("Participante");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 70, 100, -1));

        idparticipantes.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel1.add(idparticipantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 360, 40));

        jLabel4.setFont(new java.awt.Font("Questions", 1, 24)); // NOI18N
        jLabel4.setText("Color");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 70, 50, -1));

        idcolor.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jPanel1.add(idcolor, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 60, 140, 40));

        btnagregar.setBackground(new java.awt.Color(51, 51, 255));
        btnagregar.setForeground(new java.awt.Color(255, 255, 255));
        btnagregar.setText("+ agregar participante");
        btnagregar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnagregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnagregarActionPerformed(evt);
            }
        });
        jPanel1.add(btnagregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 120, 290, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 1120, 170));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tabla guardadas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Questions", 3, 36), new java.awt.Color(255, 102, 0))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 66, 1098, 430));

        btnGo.setBackground(new java.awt.Color(0, 153, 0));
        btnGo.setForeground(new java.awt.Color(255, 255, 255));
        btnGo.setText("Iniciar torneo");
        btnGo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnGo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGoActionPerformed(evt);
            }
        });
        jPanel2.add(btnGo, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 500, 300, 50));

        btnEliminar.setBackground(new java.awt.Color(255, 0, 0));
        btnEliminar.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 143, 38));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 550));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1160, 760));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tablaparticipantesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaparticipantesMouseClicked
     
    }//GEN-LAST:event_tablaparticipantesMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       torneo objetoTorneo = new torneo();
       objetoTorneo.eliminarFilaSeleccionada(tablaparticipantes);
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnagregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnagregarActionPerformed
       torneo objetoTorneo = new torneo();
       objetoTorneo.agregarParticipantes(idparticipantes, idtipotorneo, tablaparticipantes,modeloParticipantes, idcolor);
    }//GEN-LAST:event_btnagregarActionPerformed

    private void idtipotorneoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_idtipotorneoMouseClicked
      
    }//GEN-LAST:event_idtipotorneoMouseClicked

    private void btnGoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGoActionPerformed

    }//GEN-LAST:event_btnGoActionPerformed

    private void idtipotorneoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_idtipotorneoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_idtipotorneoActionPerformed

    private void configurarTablaParticipantes() {
        modeloParticipantes = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) { // Columna de imágenes
                    return ImageIcon.class;
                }
                return String.class;
            }
        };

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modeloParticipantes);
        tablaparticipantes.setRowSorter(ordenarTabla);

        modeloParticipantes.addColumn("id");
        modeloParticipantes.addColumn("Participante / Equipo");
        modeloParticipantes.addColumn("Imágenes");
        modeloParticipantes.addColumn("Color");

        tablaparticipantes.setModel(modeloParticipantes);
        //tablaparticipantes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        TableColumnModel columnModel = tablaparticipantes.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(55);
        columnModel.getColumn(1).setPreferredWidth(400);
        columnModel.getColumn(2).setPreferredWidth(500);
        columnModel.getColumn(3).setPreferredWidth(140);

        DefaultTableCellRenderer centroRenderer = new DefaultTableCellRenderer();
        centroRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        columnModel.getColumn(1).setCellRenderer(centroRenderer);
        columnModel.getColumn(3).setCellRenderer(centroRenderer);
        ocultarColumna(tablaparticipantes, 0);
    }
    // Método para ocultar la columna del JTable
    public void ocultarColumna(JTable tabla, int indiceColumna) {
        TableColumnModel columnModel = tabla.getColumnModel();
        TableColumn columna = columnModel.getColumn(indiceColumna);

        // Ajustar el ancho de la columna a 0
        columna.setMinWidth(0);
        columna.setMaxWidth(0);
        columna.setPreferredWidth(0);

        // Desactivar el redimensionamiento de la columna
        columna.setResizable(false);
    }
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UIManager.setLookAndFeel(new MetalLookAndFeel());
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(creartorneo.class.getName()).log(Level.SEVERE, null, ex);
                }
                new creartorneo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton btnEliminar;
    private botones.boton btnGo;
    private botones.boton btnagregar;
    private combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private combo_suggestion.ComboBoxSuggestion idcolor;
    private combo_suggestion.ComboBoxSuggestion idparticipantes;
    private combo_suggestion.ComboBoxSuggestion idtipotorneo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaparticipantes;
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
    public static creartorneo getInstancia() {
        if (instancia == null || !instancia.isDisplayable()) {
            instancia = new creartorneo();
        }
        instancia.setVisible(true);
        return instancia;
    }
}
