/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import clases.TipoRespuestas;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author YILBER
 */
public class respuestas {
    private int id;
    private String respuesta;
    private int idtiporespuesta;
    private String tipo;
    private List<TipoRespuestas> tipoRespuestaList = new ArrayList<>();
    
    public respuestas() {

    }
    public respuestas(int id, String respuesta, int idtiporespuesta, String tipo) {
        this.id = id;
        this.respuesta = respuesta;
        this.idtiporespuesta = idtiporespuesta;
        this.tipo = tipo;
    }
     
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public int getIdtiporespuesta() {
        return idtiporespuesta;
    }

    public void setIdtiporespuesta(int idtiporespuesta) {
        this.idtiporespuesta = idtiporespuesta;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
       
    @Override
    public String toString() {
        return tipo; // Esto permite que el JComboBox muestre el nombre
    }
    
    public void seleccionarPregunta(JTable paramTablaPreguntas, JTable tablaRespuestas) {
        try {
            int fila = paramTablaPreguntas.getSelectedRow();
            if (fila >= 0) {
                String idFila = paramTablaPreguntas.getValueAt(fila, 0).toString();
                cargarRespuestas(idFila, tablaRespuestas, paramTablaPreguntas);
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
    // Método para cargar la tabla con las respuestas y el JComboBox dinámico
    private void cargarRespuestas(String idPregunta, JTable tablaRespuestas, JTable tablaPreguntas) {
        String sql = "SELECT r.id, r.respuesta, r.idtiporespuesta, tr.tipo " +
                     "FROM respuestas r " +
                     "JOIN tiporespuesta tr ON r.idtiporespuesta = tr.id " +
                     "WHERE idpregunta = ?";

        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();
        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("id");
        modelo.addColumn("Respuesta");
        modelo.addColumn("Respuesta correcta");

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, idPregunta);
            ResultSet rs = st.executeQuery();

            // Agrega filas al modelo
            while (rs.next()) {
                String id = rs.getString("id");
                String respuesta = rs.getString("respuesta");
                String nombreTipoRespuesta = rs.getString("tipo"); // Nombre del tipo de respuesta

                modelo.addRow(new Object[] { id, respuesta, nombreTipoRespuesta });
            }

            // Asigna el modelo a la tabla antes de modificar columnas
            tablaRespuestas.setModel(modelo);

            // Crear y cargar el JComboBox después de haber asignado el modelo
            JComboBox<TipoRespuestas> comboBoxTipoRespuesta = new JComboBox<>();
            cargarComboBoxTipoRespuesta(comboBoxTipoRespuesta); // Método que llena el JComboBox

            // Asignar el JComboBox como editor de la columna
            TableColumn tipoColumna = tablaRespuestas.getColumnModel().getColumn(2);
            tipoColumna.setCellEditor(new DefaultCellEditor(comboBoxTipoRespuesta));

            // Ocultar la columna "id"
            tablaRespuestas.getColumnModel().getColumn(0).setMinWidth(0);
            tablaRespuestas.getColumnModel().getColumn(0).setMaxWidth(0);
            tablaRespuestas.getColumnModel().getColumn(0).setPreferredWidth(0);

            // Permitir al usuario agregar nuevas respuestas en la tabla
            modelo.addRow(new Object[] {null, "", ""}); // Fila vacía para nueva respuesta

              // Aquí es donde colocas el código para configurar la tabla con el listener
            DefaultTableModel modeloTablaRespuestas = (DefaultTableModel) tablaRespuestas.getModel();
            configurarTablaRespuestasConActualizaciones(tablaRespuestas, modeloTablaRespuestas, tablaPreguntas);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar respuestas: " + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.toString());
        }
    }

    private Map<String, Integer> tipoRespuestaMap = new HashMap<>();

    private void cargarComboBoxTipoRespuesta(JComboBox<TipoRespuestas> comboBoxTipoRespuesta) {
        String sql = "SELECT id, tipo FROM tiporespuesta";
        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();

        try (Statement st = conexion.createStatement(); 
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("tipo");
                TipoRespuestas tipoRespuesta = new TipoRespuestas(id, nombre);
                comboBoxTipoRespuesta.addItem(tipoRespuesta);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de respuesta: " + e.toString());
        }
    }
    
    private TipoRespuestas obtenerTipoRespuestasPorNombre(String nombreTipoRespuesta) {
        for (TipoRespuestas tipo : tipoRespuestaList) {
            if (tipo.getTipo().equals(nombreTipoRespuesta)) {
                return tipo;
            }
        }
        return null;
    }
    
    public void guardarNuevasRespuestas(JTable tablaRespuestas, JTable tablaPreguntas) {
        DefaultTableModel modelo = (DefaultTableModel) tablaRespuestas.getModel();
        int filaSeleccionada = tablaPreguntas.getSelectedRow();

        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
            return;
        }

        String idPregunta = tablaPreguntas.getValueAt(filaSeleccionada, 0).toString();
        System.out.println("ID de la pregunta: " + idPregunta);

        if (idPregunta == null || idPregunta.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID de la pregunta no válido.");
            return;
        }

        String selectSql = "SELECT COUNT(*) FROM respuestas WHERE idpregunta = ? AND respuesta = ?";
        String insertSql = "INSERT INTO respuestas (idpregunta, respuesta, idtiporespuesta) VALUES (?, ?, ?)";
        String sqlNRespuestasCorrectas = "SELECT COUNT(r.id) AS nrespuestascorrectas " +
                                         "FROM preguntas p JOIN respuestas r ON (r.idpregunta = p.id) " +
                                         "WHERE p.id = " + idPregunta + " AND r.idtiporespuesta = 1";

        conexionbd objetoConexion = new conexionbd();
        int filasGuardadas = 0; // Variable para contar el número de filas guardadas

        try {
            Connection conexion = objetoConexion.establecerConexion();
            PreparedStatement selectStmt = conexion.prepareStatement(selectSql);
            PreparedStatement selectsqlNRespuestasCorrectas = conexion.prepareStatement(sqlNRespuestasCorrectas);
            PreparedStatement insertStmt = conexion.prepareStatement(insertSql);

            // Ejecutar la consulta para contar respuestas correctas existentes
            ResultSet rsNRespuestasCorrectas = selectsqlNRespuestasCorrectas.executeQuery();
            int nRespuestasCorrectasExistentes = 0;
            if (rsNRespuestasCorrectas.next()) {
                nRespuestasCorrectasExistentes = rsNRespuestasCorrectas.getInt(1);
            }

            // Contar cuántas respuestas correctas se están intentando guardar
            int nRespuestasCorrectasEnTabla = 0;
            for (int i = 0; i < modelo.getRowCount(); i++) {
                Object tipoRespuestaObj = modelo.getValueAt(i, 2);

                TipoRespuestas tipoRespuestaSeleccionado = null;

                // Convertir el valor de la columna a TipoRespuestas
                if (tipoRespuestaObj instanceof TipoRespuestas) {
                    tipoRespuestaSeleccionado = (TipoRespuestas) tipoRespuestaObj;
                } else if (tipoRespuestaObj instanceof String) {
                    tipoRespuestaSeleccionado = obtenerTipoRespuestasPorNombre((String) tipoRespuestaObj);
                }

                if (tipoRespuestaSeleccionado != null && tipoRespuestaSeleccionado.getId() == 1) {
                    nRespuestasCorrectasEnTabla++;
                }
            }

            // Validar que no haya más de una respuesta correcta
            if (nRespuestasCorrectasExistentes + nRespuestasCorrectasEnTabla > 1) {
                JOptionPane.showMessageDialog(null, "No es posible guardar múltiples respuestas correctas para la misma pregunta.");
                return;
            }

            // Guardar las respuestas si la validación fue exitosa
            for (int i = 0; i < modelo.getRowCount(); i++) {
                String idRespuesta = (String) modelo.getValueAt(i, 0);
                String respuesta = (String) modelo.getValueAt(i, 1);
                Object tipoRespuestaObj = modelo.getValueAt(i, 2);

                TipoRespuestas tipoRespuestaSeleccionado = null;

                // Convertir el valor de la columna a TipoRespuestas
                if (tipoRespuestaObj instanceof TipoRespuestas) {
                    tipoRespuestaSeleccionado = (TipoRespuestas) tipoRespuestaObj;
                } else if (tipoRespuestaObj instanceof String) {
                    tipoRespuestaSeleccionado = obtenerTipoRespuestasPorNombre((String) tipoRespuestaObj);
                }

                Integer tipoRespuestaId = tipoRespuestaSeleccionado != null ? tipoRespuestaSeleccionado.getId() : null;

                if (idRespuesta == null) {
                    idRespuesta = ""; // Asegúrate de que idRespuesta no sea null
                }

                if (!respuesta.trim().isEmpty() && tipoRespuestaId != null) {
                    if (idRespuesta.trim().isEmpty()) {
                        // Nueva respuesta
                        insertStmt.setString(1, idPregunta);
                        insertStmt.setString(2, respuesta);
                        insertStmt.setInt(3, tipoRespuestaId);
                        int filasAfectadas = insertStmt.executeUpdate();
                        if (filasAfectadas > 0) {
                            filasGuardadas++; // Incrementa el contador si se insertó una fila
                        }
                    } else {
                        // Respuesta existente, opcionalmente podrías actualizarla si es necesario
                    }
                }
            }

            if (filasGuardadas > 0) {
                cargarRespuestas(idPregunta, tablaRespuestas, tablaPreguntas);
                JOptionPane.showMessageDialog(null, "Respuestas guardadas correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se guardaron nuevas respuestas.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar respuestas: " + e.toString());
        }
    }

    
    public void eliminarRespuestaDesdeBaseDeDatos(String idRespuesta) {
            String sql = "DELETE FROM respuestas WHERE id = ?";
            conexionbd objetoConexion = new conexionbd();

            try (Connection conexion = objetoConexion.establecerConexion();
                PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setString(1, idRespuesta);
                ps.executeUpdate();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al eliminar la respuesta: " + e.toString());
            }
        }
    
    private void configurarTablaRespuestasConActualizaciones(JTable tablaRespuestas, DefaultTableModel modelo, JTable tablaPreguntas) {
        // Configurar el listener
        int filaSeleccionada = tablaPreguntas.getSelectedRow();
        String idPregunta = tablaPreguntas.getValueAt(filaSeleccionada, 0).toString();
        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int row = e.getFirstRow();
                    int column = e.getColumn();

                    // Llamar al método para actualizar la celda en la base de datos
                    actualizarCeldaRespuestas(tablaRespuestas, modelo, row, column, idPregunta, tablaPreguntas);
                }
            }
        });
    }

    private void actualizarCeldaRespuestas(JTable tablaRespuestas, DefaultTableModel modelo, int row, int col, String idPregunta, JTable tablaPreguntas) {
        // Obtener el ID de la respuesta en la fila actual
        String idRespuesta = (String) modelo.getValueAt(row, 0);

        if (idRespuesta == null || idRespuesta.trim().isEmpty()) {
            return; // No actualizar si el ID es nulo o vacío
        }

        // Manejar las actualizaciones según la columna
        String newValue;
        String columnName;

        if (col == 1) { // Columna "respuesta"
            newValue = (String) modelo.getValueAt(row, col);
            columnName = "respuesta";
            actualizarRegistroRespuestas(idRespuesta, columnName, newValue);
        } else if (col == 2) { // Columna "idtiporespuesta"
            TipoRespuestas tipoRespuestaSeleccionado = (TipoRespuestas) modelo.getValueAt(row, col);
            if (tipoRespuestaSeleccionado != null) {
                Statement st;
                int tipoRespuestaId = tipoRespuestaSeleccionado.getId();
                columnName = "idtiporespuesta";

                conexionbd conexion = new conexionbd();
                String sql = "Select COUNT(r.id) as nrespuestascorrectas from preguntas p join respuestas r on (r.idpregunta = p.id) WHERE p.id = "+idPregunta+" and r.idtiporespuesta = 1";
                
                try {
                    st = conexion.establecerConexion().createStatement();
                    ResultSet rs = st.executeQuery(sql);
                    int nRespuetasCorrectas = 0;
                    while (rs.next()) {
                       nRespuetasCorrectas = rs.getInt(1);
                    }
                    if(nRespuetasCorrectas == 0){
                        actualizarRegistroRespuestas(idRespuesta, columnName, String.valueOf(tipoRespuestaId));
                    }else{
                        JOptionPane.showMessageDialog(null, "No es posible tener 2 respuestas correctas en la misma pregunta.");
                        cargarRespuestas(idPregunta, tablaRespuestas, tablaPreguntas);
                    }
                    
                } catch (SQLException ex) {
                    Logger.getLogger(respuestas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void actualizarRegistroRespuestas(String idRespuesta, String columnName, String newValue) {
        conexionbd objetoConexion = new conexionbd();
        String sql = "UPDATE respuestas SET " + columnName + " = ? WHERE id = ?";
        try {
            PreparedStatement ps = objetoConexion.establecerConexion().prepareStatement(sql);
            ps.setString(1, newValue);
            ps.setString(2, idRespuesta);
            ps.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el registro en respuestas: " + e.toString());
        }
    }

}
