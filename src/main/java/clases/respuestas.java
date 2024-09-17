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
import interfaces.vistapreguntas;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
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
    public void cargarRespuestas(String idPregunta, JTable tablaRespuestas, JTable tablaPreguntas) {
        String sql = "SELECT r.id, r.respuesta, r.idtiporespuesta, tr.tipo, r.imagen2 " +
                     "FROM respuestas r " +
                     "JOIN tiporespuesta tr ON r.idtiporespuesta = tr.id " +
                     "WHERE idpregunta = ?";

        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 3) { // La columna de imágenes
                    return ImageIcon.class;
                }
                return String.class;
            }
        };
        modelo.addColumn("id");
        modelo.addColumn("Respuesta");
        modelo.addColumn("Respuesta correcta");
        modelo.addColumn("Imagen");
        modelo.addColumn("Ruta imagen");

        try (PreparedStatement st = conexion.prepareStatement(sql)) {
            st.setString(1, idPregunta);
            ResultSet rs = st.executeQuery();
            Object[] datos = new Object[5];

            // Agrega filas al modelo
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(4);

                // Obtener el BLOB de la imagen
                Blob blob = rs.getBlob(5);
                ImageIcon imagenIcono = null;
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    BufferedImage bufferedImage = ImageIO.read(inputStream);
                    if (bufferedImage != null) {
                        imagenIcono = new ImageIcon(bufferedImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH));
                    }
                }
                datos[3] = imagenIcono;
                datos[4] = ""; // Ruta de la imagen no es necesaria si estás manejando BLOB

                modelo.addRow(datos);
            }
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

            tablaRespuestas.getColumnModel().getColumn(4).setMinWidth(0);
            tablaRespuestas.getColumnModel().getColumn(4).setMaxWidth(0);
            tablaRespuestas.getColumnModel().getColumn(4).setWidth(0);
            tablaRespuestas.getColumnModel().getColumn(4).setPreferredWidth(0);

            // Permitir al usuario agregar nuevas respuestas en la tabla
            modelo.addRow(new Object[] {null, "", "", null, ""}); // Fila vacía para nueva respuesta

            // Configurar la tabla con el listener
            DefaultTableModel modeloTablaRespuestas = (DefaultTableModel) tablaRespuestas.getModel();
            configurarTablaRespuestasConActualizaciones(tablaRespuestas, modeloTablaRespuestas, tablaPreguntas);

            tablaRespuestas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = tablaRespuestas.rowAtPoint(e.getPoint());
                    int col = tablaRespuestas.columnAtPoint(e.getPoint());

                    if (col == 3 && e.getClickCount() == 1) { // Doble clic en la columna de imagen
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        fileChooser.setDialogTitle("Seleccionar imagen");
                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();
                            String rutaAbsolutaImagen = selectedFile.getAbsolutePath();

                            // Obtener el nuevo BLOB de la imagen
                            try (InputStream inputStream = new FileInputStream(selectedFile)) {
                                BufferedImage bufferedImage = ImageIO.read(inputStream);
                                ImageIcon nuevaImagen = new ImageIcon(bufferedImage.getScaledInstance(60, 60, Image.SCALE_SMOOTH));

                                // Actualizar la tabla con la nueva imagen
                                tablaRespuestas.setValueAt(nuevaImagen, row, col);

                                // Actualizar la base de datos con la nueva imagen
                                String idRespuesta = tablaRespuestas.getValueAt(row, 0).toString();
                                actualizarRegistroRespuestas(idRespuesta, "imagen2", new FileInputStream(selectedFile));

                            } catch (IOException ex) {
                                JOptionPane.showMessageDialog(null, "Error al leer la imagen: " + ex.toString());
                            }
                        }
                    }
                }
            });

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar respuestas: " + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.toString());
        }
    }

    
    private void actualizarRegistroRespuestas(String idRespuesta, String columna, InputStream imagenStream) {
        String sql = "UPDATE respuestas SET " + columna + " = ? WHERE id = ?";

        try (Connection conexion = new conexionbd().establecerConexion();
             PreparedStatement st = conexion.prepareStatement(sql)) {

            // Configura el parámetro del BLOB
            st.setBlob(1, imagenStream);
            // Configura el ID de la respuesta
            st.setString(2, idRespuesta);

            // Ejecuta la actualización
            int filasAfectadas = st.executeUpdate();
            if (filasAfectadas > 0) {
                System.out.println("Imagen actualizada correctamente.");
            } else {
                System.out.println("No se encontró el registro con ID: " + idRespuesta);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la imagen: " + e.toString());
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
    
    public void guardarRespuestas(JTable tablaPreguntas, JTextField respuesta, JComboBox<preguntas> correcta, byte[] imagenEnBytes, JLabel lblimgrespuesta) {
        DefaultTableModel modelo = (DefaultTableModel) tablaPreguntas.getModel();
        int filaSeleccionada = tablaPreguntas.getSelectedRow();
        // Verificar si se seleccionó una fila
        if (filaSeleccionada < 0) {
            JOptionPane.showMessageDialog(null, "No se ha seleccionado ninguna fila.");
            return;
        }

        // Obtener el ID de la pregunta desde la tabla
        Object idFilaObj = tablaPreguntas.getValueAt(filaSeleccionada, 0);
        if (idFilaObj == null || idFilaObj.toString().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID de la pregunta no válido.");
            return;
        }
        String idFila = idFilaObj.toString();

        // Verificar si 'respuesta' no es null antes de obtener su texto
        String respuestaText = (respuesta != null) ? respuesta.getText().trim() : "";
        if (respuestaText == null) {
            respuestaText = "";
        }

        // Verificar si 'correcta' no es null y obtener la opción correcta seleccionada
        preguntas correctaSeleccionada = (correcta != null) ? (preguntas) correcta.getSelectedItem() : null;
        int idCorrectaSeleccionada = (correctaSeleccionada != null) ? correctaSeleccionada.getId() : 0;

        // Verificar si 'imagenEnBytes' no es null y tiene longitud
        boolean imagenCompleta = (imagenEnBytes != null && imagenEnBytes.length > 0);

        // Validar que se tenga texto o imagen, pero no ambos ni ninguno
        boolean textoCompleto = !respuestaText.isEmpty();
        if ((textoCompleto && imagenCompleta) || (!textoCompleto && !imagenCompleta)) {
            JOptionPane.showMessageDialog(null, "Debe ingresar texto o cargar una imagen, pero no ambos ni ninguno.");
            return;
        }

        // Verificar si ya existe una respuesta correcta para la misma pregunta
        String sqlVerificarRespuestaCorrecta = "SELECT COUNT(*) FROM respuestas WHERE idpregunta = ? AND idtiporespuesta = 1";
        String sqlInsertarRespuesta = "INSERT INTO respuestas (idpregunta, respuesta, idtiporespuesta, imagen2) VALUES (?, ?, ?, ?)";

        try (Connection conexion = new conexionbd().establecerConexion();
             PreparedStatement psVerificar = conexion.prepareStatement(sqlVerificarRespuestaCorrecta);
             PreparedStatement psInsertar = conexion.prepareStatement(sqlInsertarRespuesta)) {

            // Verificar respuestas correctas existentes
            psVerificar.setString(1, idFila);
            try (ResultSet rs = psVerificar.executeQuery()) {
                if (rs.next()) {
                    int numeroRespuestasCorrectas = rs.getInt(1);
                    // Validar que no haya más de una respuesta correcta
                    if (numeroRespuestasCorrectas >= 1 && idCorrectaSeleccionada == 1) {
                        JOptionPane.showMessageDialog(null, "Ya existe una respuesta correcta para esta pregunta.");
                        return;
                    }
                }
            }

            // Configurar los parámetros del PreparedStatement para la inserción
            psInsertar.setString(1, idFila);
            psInsertar.setString(2, respuestaText);
            psInsertar.setInt(3, idCorrectaSeleccionada);
            if (imagenCompleta) {
                psInsertar.setBytes(4, imagenEnBytes);
            } else {
                psInsertar.setNull(4, java.sql.Types.BLOB);
            }

            // Ejecutar la consulta de inserción
            psInsertar.executeUpdate();

            // Limpiar los campos y actualizar la interfaz de usuario
            if (respuesta != null) {
                respuesta.setText("");
            }
            if (correcta != null) {
                correcta.setSelectedIndex(0);
            }
            if (lblimgrespuesta != null) {
                lblimgrespuesta.setIcon(null);
            }
            JOptionPane.showMessageDialog(null, "Se insertó correctamente la respuesta");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la inserción, error: " + e.toString());
        }
    }

   
    private byte[] convertirImagenABytes(File imagen) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            BufferedImage bufferedImage = ImageIO.read(imagen);

            // Convertir la imagen a un formato adecuado, como JPG o PNG
            ImageIO.write(bufferedImage, "jpg", baos);
            return baos.toByteArray();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al convertir la imagen: " + e.getMessage());
            return null;
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

    private ImageIcon cargarImagenDesdeRuta(String rutaRelativa, int ancho, int alto) {
         String rutaCompleta = "src/main/resources" + rutaRelativa; // Ruta completa basada en la ruta relativa
         ImageIcon imageIcon = new ImageIcon(rutaCompleta);

         // Escalar la imagen al tamaño deseado
         Image imagen = imageIcon.getImage();
         Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
         return new ImageIcon(imagenEscalada);
     }
    
    private String copiarImagenARutaRelativa(String rutaAbsolutaImagen) {
        // Carpeta donde guardarás las imágenes en la carpeta de recursos
        String carpetaDestino = "src/main/resources/respuestas/";

        // Obtener el nombre del archivo de la imagen seleccionada
        File archivoOrigen = new File(rutaAbsolutaImagen);
        String nombreArchivo = archivoOrigen.getName();

        // Ruta de destino en la carpeta de recursos
        String rutaDestino = carpetaDestino + nombreArchivo;

        // Copiar el archivo a la carpeta de recursos
        try {
            File archivoDestino = new File(rutaDestino);
            Files.copy(archivoOrigen.toPath(), archivoDestino.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al copiar la imagen: " + e.getMessage());
        }

        // Devolver la ruta relativa para guardar en la base de datos
        return "/respuestas/" + nombreArchivo;
    }
    
}
