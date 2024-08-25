package clases;

import combo_suggestion.ComboBoxSuggestion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import textarea.TextArea;

public class preguntas {
    int id;
    int idasignatura;
    int idgrado;
    private String pregunta;
    private String asignatura;

    public preguntas() {
        
    }
    
    public preguntas(int id, int idasignatura, int idgrado, String asignatura) {
        this.id = id;
        this.idasignatura = idasignatura;
        this.idgrado = idgrado;
        this.asignatura = asignatura;
    }
    
    public int getId() {
        return id;
    }
    public int getIdAsignatura() {
        return idasignatura;
    }
    public int getIdGrado() {
        return idgrado;
    }
    public String getPregunta() {
        return pregunta;
    }

    public void setIdAsignatura(int asignatura) {
        this.idasignatura = idasignatura;
    }

     public void setIdGrado(int grado) {
        this.idgrado = idgrado;
    }
     
    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }
    
    @Override
    public String toString() {
        return this.asignatura; // O el nombre que deseas mostrar en el JComboBox
    }

    public void insertarPregunta(TextArea paraPregunta, JComboBox<preguntas> paraAsignatura, JComboBox<preguntas> paraGrado) {
        preguntas asignaturaSeleccionada = (preguntas) paraAsignatura.getSelectedItem();
        int idAsignaturaSeleccionada = asignaturaSeleccionada.getId();

        preguntas gradoSeleccionado = (preguntas) paraGrado.getSelectedItem();
        int idGradoSeleccionado = gradoSeleccionado.getId();
        
        if(!paraPregunta.getText().isEmpty() && idAsignaturaSeleccionada != 0 && idGradoSeleccionado != 0){
            setPregunta(paraPregunta.getText());
            conexionbd objetoConexion = new conexionbd();
            String consulta = "insert into preguntas(pregunta, idasignatura, idgrado) values (?, ?, ?);";

            try {
                CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
                cs.setString(1, getPregunta());
                cs.setInt(2, idAsignaturaSeleccionada);
                cs.setInt(3, idGradoSeleccionado);
                cs.execute();

                paraPregunta.setText("");
                paraAsignatura.setSelectedIndex(0);
                paraGrado.setSelectedIndex(0);
                JOptionPane.showMessageDialog(null, "Se insertó correctamente la pregunta");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la inserción, error: " + e.toString());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Faltan datos necesarios en los campos.");
        }
    }

    public void mostrarPreguntas(JTable paramTablaPreguntas) {
        conexionbd objetoConexion = new conexionbd();
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // Solo permitir edición en columnas diferentes de "id"
            }
        };

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaPreguntas.setRowSorter(ordenarTabla);

        modelo.addColumn("id");
        modelo.addColumn("Asignatura");
        modelo.addColumn("Grado");
        modelo.addColumn("Pregunta");

        paramTablaPreguntas.setModel(modelo);

        // Aquí se llena la tabla con los datos incluyendo los nombres de asignatura y grado
        String sql = "select p.id, a.asignatura, g.grado, p.pregunta, p.idasignatura, p.idgrado " +
                     "from preguntas p " +
                     "join asignaturas a on (p.idasignatura = a.id) " +
                     "join grados g on (p.idgrado = g.id)";
        String[] datos = new String[6];
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5); // idasignatura
                datos[5] = rs.getString(6); // idgrado

                modelo.addRow(datos);
            }
            paramTablaPreguntas.setModel(modelo);

            TableColumnModel columnModel = paramTablaPreguntas.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(50);  // Columna 0, ID
            columnModel.getColumn(1).setPreferredWidth(200); // Columna 1, Asignatura
            columnModel.getColumn(2).setPreferredWidth(100); // Columna 2, Grado
            columnModel.getColumn(3).setPreferredWidth(450); // Columna 3, Pregunta
            // Asignar ComboBox como editor para las columnas de asignatura y grado
            JComboBox<String> comboAsignatura = new JComboBox<>();
            JComboBox<String> comboGrado = new JComboBox<>();

            // Llenar ComboBox con valores de asignaturas y grados
            llenarComboBoxTabla("asignaturas", "asignatura", comboAsignatura);
            llenarComboBoxTabla("grados", "grado", comboGrado);

            DefaultCellEditor asignaturaEditor = new DefaultCellEditor(comboAsignatura);
            DefaultCellEditor gradoEditor = new DefaultCellEditor(comboGrado);

            paramTablaPreguntas.getColumnModel().getColumn(1).setCellEditor(asignaturaEditor);
            paramTablaPreguntas.getColumnModel().getColumn(2).setCellEditor(gradoEditor);

            // Añadir listeners a los editores
            asignaturaEditor.addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    actualizarCelda(paramTablaPreguntas, modelo);
                }

                @Override
                public void editingCanceled(ChangeEvent e) {
                    // No hacer nada si se cancela la edición
                }
            });

            gradoEditor.addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    actualizarCelda(paramTablaPreguntas, modelo);
                }

                @Override
                public void editingCanceled(ChangeEvent e) {
                    // No hacer nada si se cancela la edición
                }
            });

            paramTablaPreguntas.getDefaultEditor(String.class).addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    actualizarCelda(paramTablaPreguntas, modelo);
                }

                @Override
                public void editingCanceled(ChangeEvent e) {
                    // No hacer nada si se cancela la edición
                }
            });

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros " + e.toString());
        }
    }

    private void actualizarCelda(JTable paramTablaPreguntas, DefaultTableModel modelo) {
        int row = paramTablaPreguntas.getSelectedRow();
        int col = paramTablaPreguntas.getSelectedColumn();

        if (col == 1 || col == 2) { // Si es la columna de asignatura o grado
            String newValue = paramTablaPreguntas.getValueAt(row, col).toString();
            String id = paramTablaPreguntas.getValueAt(row, 0).toString(); // Obtener el ID de la fila

            // Obtener id correspondiente al valor seleccionado
            int foreignKeyId = obtenerIdClaveForanea(col == 1 ? "asignaturas" : "grados", newValue);

            String columnName = col == 1 ? "idasignatura" : "idgrado";

            // Llamar método para actualizar la base de datos
            actualizarRegistro(id, columnName, String.valueOf(foreignKeyId));
        } else {
            String newValue = paramTablaPreguntas.getValueAt(row, col).toString();
            String id = paramTablaPreguntas.getValueAt(row, 0).toString();
            String columnName = modelo.getColumnName(col);
            actualizarRegistro(id, columnName, newValue);
        }
    }

    private void actualizarRegistro(String id, String columnName, String newValue) {
        conexionbd objetoConexion = new conexionbd();
        String sql = "UPDATE preguntas SET " + columnName + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = objetoConexion.establecerConexion().prepareStatement(sql);
            ps.setString(1, newValue);
            ps.setString(2, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro: " + e.toString());
        }
    }

    public void eliminarPreguntas(String paraId) {
        conexionbd objetoConexion = new conexionbd();
        String sql = "DELETE FROM preguntas WHERE id=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(sql);
            cs.setString(1, paraId);
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se elimino correctamente la pregunta");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar, error: " + e.toString());
        }
    }

    public void llenarCombobox(String tabla, JComboBox<preguntas> combo, String campo) {
        String sql = "select * from " + tabla;
        Statement st;
        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println(rs);
            combo.addItem(new preguntas(0,0,0,"Seleccione una opción"));
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString(campo);
                combo.addItem(new preguntas(id,id,id,nombre));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
    private void llenarComboBoxTabla(String tabla, String columna, JComboBox<String> combo) {
        conexionbd objetoConexion = new conexionbd();
        String sql = "SELECT " + columna + " FROM " + tabla;
        try {
            Statement st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                combo.addItem(rs.getString(1));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al llenar ComboBox: " + e.toString());
        }
    }

    private int obtenerIdClaveForanea(String tabla, String valor) {
        conexionbd objetoConexion = new conexionbd();
        String sql = "SELECT id FROM " + tabla + " WHERE " + tabla.substring(0, tabla.length() - 1) + " = ?";
        try {
            PreparedStatement ps = objetoConexion.establecerConexion().prepareStatement(sql);
            ps.setString(1, valor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener ID de clave foránea: " + e.toString());
        }
        return -1;
    }
}
