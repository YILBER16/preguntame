package clases;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import textarea.TextArea;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class preguntas {
    int id;
    int idasignatura;
    int idgrado;
    private String pregunta;
    private String asignatura;
    private String rutaImagen;

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

    public void insertarPregunta(TextArea paraPregunta, JComboBox<preguntas> paraAsignatura, JComboBox<preguntas> paraGrado, byte[] imagenEnBytes, JLabel lblImagen) {
        preguntas asignaturaSeleccionada = (preguntas) paraAsignatura.getSelectedItem();
        int idAsignaturaSeleccionada = asignaturaSeleccionada.getId();

        preguntas gradoSeleccionado = (preguntas) paraGrado.getSelectedItem();
        int idGradoSeleccionado = gradoSeleccionado.getId();
        
        if(!paraPregunta.getText().isEmpty() && idAsignaturaSeleccionada != 0 && idGradoSeleccionado != 0){
            setPregunta(paraPregunta.getText());
            conexionbd objetoConexion = new conexionbd();
            String consulta = "insert into preguntas(pregunta, idasignatura, idgrado, imagen) values (?, ?, ?, ?);";

            try {
                CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
                cs.setString(1, getPregunta());
                cs.setInt(2, idAsignaturaSeleccionada);
                cs.setInt(3, idGradoSeleccionado);
                if (imagenEnBytes != null) {
                    cs.setBytes(4, imagenEnBytes);
                } else {
                    cs.setNull(4, java.sql.Types.BLOB);
                }
                cs.execute();

                paraPregunta.setText("");
                paraAsignatura.setSelectedIndex(0);
                paraGrado.setSelectedIndex(0);
                lblImagen.setIcon(null);
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
            public Class<?> getColumnClass(int column) {
                if (column == 4) { // La columna de imágenes
                    return ImageIcon.class;
                }
                return String.class;
            }
        };

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaPreguntas.setRowSorter(ordenarTabla);

        modelo.addColumn("id");
        modelo.addColumn("Asignatura");
        modelo.addColumn("Grado");
        modelo.addColumn("Pregunta");
        modelo.addColumn("Imagen");
        modelo.addColumn("RutaImagen");

        paramTablaPreguntas.setModel(modelo);

        // Aquí se llena la tabla con los datos incluyendo los nombres de asignatura y grado
        String sql = "select p.id, a.asignatura, g.grado, p.pregunta, p.imagen, p.idasignatura, p.idgrado " +
                     "from preguntas p " +
                     "join asignaturas a on (p.idasignatura = a.id) " +
                     "join grados g on (p.idgrado = g.id)";
        Object[] datos = new Object[8];
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                // Obtener el BLOB de la imagen desde la base de datos
                Blob blob = rs.getBlob(5);
                ImageIcon imagenIcon = null;
                if (blob != null) {
                    InputStream inputStream = blob.getBinaryStream();
                    try {
                        BufferedImage bufferedImage = ImageIO.read(inputStream);
                        if (bufferedImage != null) {
                            Image imagen = bufferedImage.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Escalar imagen
                            imagenIcon = new ImageIcon(imagen);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        inputStream.close();
                    }
                }
                datos[4] = imagenIcon; // Añadir el ImageIcon a los datos
                datos[5] = rutaImagen;
                
                datos[6] = rs.getString(6); // idasignatura
                datos[7] = rs.getString(7); // idgrado

                modelo.addRow(datos);
            }
            paramTablaPreguntas.setModel(modelo);

            TableColumnModel columnModel = paramTablaPreguntas.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(20);  // Columna 0, ID
            columnModel.getColumn(1).setPreferredWidth(200); // Columna 1, Asignatura
            columnModel.getColumn(2).setPreferredWidth(50); // Columna 2, Grado
            columnModel.getColumn(3).setPreferredWidth(300); // Columna 3, Pregunta
            columnModel.getColumn(4).setPreferredWidth(300);
            
            paramTablaPreguntas.getColumnModel().getColumn(5).setMinWidth(0);
            paramTablaPreguntas.getColumnModel().getColumn(5).setMaxWidth(0);
            paramTablaPreguntas.getColumnModel().getColumn(5).setWidth(0);
            paramTablaPreguntas.getColumnModel().getColumn(5).setPreferredWidth(0);
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
            
            paramTablaPreguntas.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int row = paramTablaPreguntas.rowAtPoint(e.getPoint());
                    int col = paramTablaPreguntas.columnAtPoint(e.getPoint());

                    if (col == 4 && e.getClickCount() == 1) { // Doble clic en la columna de imagen
                        JFileChooser fileChooser = new JFileChooser();
                        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                        fileChooser.setDialogTitle("Seleccionar imagen");
                        int result = fileChooser.showOpenDialog(null);

                        if (result == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fileChooser.getSelectedFile();

                            try {
                                // Convertir la imagen seleccionada a un arreglo de bytes
                                byte[] imagenBytes = convertirImagenABytes(selectedFile);

                                // Actualizar la imagen en la base de datos
                                String idPregunta = paramTablaPreguntas.getValueAt(row, 0).toString();
                                actualizarImagenEnBD(idPregunta, imagenBytes);

                                // Mostrar la nueva imagen en la tabla
                                ImageIcon nuevaImagen = cargarImagenDesdeBytes(imagenBytes, 100, 100);
                                paramTablaPreguntas.setValueAt(nuevaImagen, row, col);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            });


        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros " + e.toString());
        }
    }
    
    private byte[] convertirImagenABytes(File imagen) throws IOException {
        BufferedImage bufferedImage = ImageIO.read(imagen);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufferedImage, "png", baos);
        return baos.toByteArray();
    }
    private void actualizarImagenEnBD(String idPregunta, byte[] imagenBytes) {
        conexionbd objetoConexion = new conexionbd();
        String sql = "UPDATE preguntas SET imagen = ? WHERE id = ?";
        try (PreparedStatement ps = objetoConexion.establecerConexion().prepareStatement(sql)) {
            ps.setBytes(1, imagenBytes);
            ps.setString(2, idPregunta);
            ps.executeUpdate();
            System.out.println("Imagen actualizada en la base de datos.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private ImageIcon cargarImagenDesdeBytes(byte[] imagenBytes, int ancho, int alto) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(imagenBytes);
        BufferedImage bufferedImage = ImageIO.read(bais);
        Image imagen = bufferedImage.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagen);
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
    
    public void llenarComboboxCorrecta(JComboBox<preguntas> combo) {
        combo.addItem(new preguntas(0,0,0,"Seleccione una opción"));
        combo.addItem(new preguntas(1,0,0,"Si"));
        combo.addItem(new preguntas(2,0,0,"No"));
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
    
    private ImageIcon cargarImagenDesdeRuta(String rutaRelativa, int ancho, int alto) {
         String rutaCompleta = "src/main/resources" + rutaRelativa; // Ruta completa basada en la ruta relativa
         ImageIcon imageIcon = new ImageIcon(rutaCompleta);

         // Escalar la imagen al tamaño deseado
         Image imagen = imageIcon.getImage();
         Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
         return new ImageIcon(imagenEscalada);
     }
        // Método para cargar y mostrar la imagen en el JLabel
    private void cargarImagenEnLabel(JLabel label, String rutaCompleta) {
         ImageIcon imageIcon = new ImageIcon(rutaCompleta);
         Image imagen = imageIcon.getImage();
         Image imagenEscalada = imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
         label.setIcon(new ImageIcon(imagenEscalada));
     }
     
    private String copiarImagenARutaRelativa(String rutaAbsolutaImagen) {
        // Carpeta donde guardarás las imágenes en la carpeta de recursos
        String carpetaDestino = "src/main/resources/preguntas/";

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
        return "/preguntas/" + nombreArchivo;
    }

}
