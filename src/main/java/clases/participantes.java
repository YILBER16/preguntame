package clases;

import combo_suggestion.ComboBoxSuggestion;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class participantes {
    int id;
    private String nombres;
    private String apellidos;
    private int idEquipo;
    private String equipo;
    private String rutaImagen;

    public participantes() {
        
    }
    
    public participantes(int id, String nombres, String apellidos, int idEquipo, String equipo) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.idEquipo = idEquipo;
        this.equipo = equipo;
    }

     public int getId() {
        return id;
    }
     
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getIdEquipo() {
        return idEquipo;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    
    @Override
    public String toString() {
        return this.equipo; // O el nombre que deseas mostrar en el JComboBox
    }

    public void insertarParticipantes(JTextField paraNombres, JTextField paraApellidos, JComboBox<participantes> paraEquipo, String rutaImagen) {
        if (!paraNombres.getText().isEmpty() && !paraApellidos.getText().isEmpty()) {
            setNombres(paraNombres.getText());
            setApellidos(paraApellidos.getText());
            participantes equipoSeleccionado = (participantes) paraEquipo.getSelectedItem();
            int idEquipoSeleccionado = equipoSeleccionado.getId();
            conexionbd objetoConexion = new conexionbd();
            String consulta = "INSERT INTO participantes(nombres, apellidos, idequipo, imagen) VALUES (?, ?, ?, ?);";

            try {
                CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
                cs.setString(1, getNombres());
                cs.setString(2, getApellidos());
                cs.setInt(3, idEquipoSeleccionado); // Usar el ID del equipo seleccionado
                if(!rutaImagen.isEmpty() || rutaImagen != null || rutaImagen != ""){
                  cs.setString(4, rutaImagen); // Guardar la ruta de la imagen
                }
                cs.execute();

                JOptionPane.showMessageDialog(null, "Se insertó correctamente el participante");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la inserción, error: " + e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faltan datos necesarios en los campos.");
        }
    }


    public void mostrarParticipantes(JTable paramTablaParticipantes) {
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
        paramTablaParticipantes.setRowSorter(ordenarTabla);

        modelo.addColumn("id");
        modelo.addColumn("Nombres");
        modelo.addColumn("Apellidos");
        modelo.addColumn("Equipo");
        modelo.addColumn("Imagen"); // Nueva columna para la imagen

        paramTablaParticipantes.setModel(modelo);

        TableColumnModel columnModel = paramTablaParticipantes.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(20);  // Ancho para la columna "id"
        columnModel.getColumn(1).setPreferredWidth(100); // Ancho para la columna "Nombres"
        columnModel.getColumn(2).setPreferredWidth(100); // Ancho para la columna "Apellidos"
        columnModel.getColumn(3).setPreferredWidth(50);  // Ancho para la columna "Equipo"
        columnModel.getColumn(4).setPreferredWidth(150); // Ancho para la columna "Imagen"

        String sql = "select p.id, p.nombres, p.apellidos, e.equipo, p.imagen from participantes p join equipos e on(p.idequipo = e.id)";
        Object[] datos = new Object[5]; // Cambiado a Object[] para manejar la imagen
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);  // ID del participante
                datos[1] = rs.getString(2);  // Nombres
                datos[2] = rs.getString(3);  // Apellidos
                datos[3] = rs.getString(4);  // Equipo

                // Obtener la ruta relativa de la imagen desde la base de datos
                String rutaImagen = rs.getString(5);
                datos[4] = cargarImagenDesdeRuta(rutaImagen, 60, 60); // Cargar la imagen desde la ruta y escalarla

                modelo.addRow(datos);
            }
            paramTablaParticipantes.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros " + e.toString());
        }
    }


    public void seleccionarParticipante(JTable paramTablaParticipantes, JTextField paramId, JTextField paramNombres, JTextField paramApellidos, JComboBox<participantes> paramEquipo, JLabel lblImagen) {
        try {
            int fila = paramTablaParticipantes.getSelectedRow();
            if (fila >= 0) {
                String idFila = paramTablaParticipantes.getValueAt(fila, 0).toString();
                String sql = "select * from participantes where id=" + idFila;
                Statement st;
                conexionbd con = new conexionbd();
                Connection conexion = con.establecerConexion();
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {
                    int equipoActual = rs.getInt("idequipo");

                    paramId.setText(rs.getString("id"));
                    paramNombres.setText(rs.getString("nombres"));
                    paramApellidos.setText(rs.getString("apellidos"));

                    // Buscar y seleccionar el equipo en el JComboBox
                    for (int i = 0; i < paramEquipo.getItemCount(); i++) {
                        participantes equipo = paramEquipo.getItemAt(i);
                        if (equipo.getIdEquipo() == equipoActual) {
                            paramEquipo.setSelectedIndex(i);
                            break;
                        }
                    }

                    // Cargar la imagen desde la base de datos
                    rutaImagen = rs.getString("imagen");  // Obtén la ruta de la imagen
                    if (rutaImagen != null && !rutaImagen.isEmpty()) {
                        cargarImagenEnLabel(lblImagen, "src/main/resources" + rutaImagen);  // Cargar la imagen y mostrarla en el JLabel
                    } else {
                        lblImagen.setIcon(null);  // Si no hay imagen, dejar el JLabel vacío
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
    
    public void modificarParticipantes(JTextField paraId, JTextField paraNombres, JTextField paraApellidos, JComboBox<participantes> paraEquipo, String rutaImagenNueva) {
        String idModificar = paraId.getText();
        String nombresModificar = paraNombres.getText();
        String apellidosModificar = paraApellidos.getText();
        participantes equipoSeleccionado = (participantes) paraEquipo.getSelectedItem();
        int idEquipoSeleccionado = equipoSeleccionado.getId();

        conexionbd objetoConexion = new conexionbd();

        // Obtener la ruta de la imagen actual antes de actualizar
        String sqlSelect = "SELECT imagen FROM participantes WHERE id = ?;";
        String rutaImagenActual = null;

        try {
            CallableStatement csSelect = objetoConexion.establecerConexion().prepareCall(sqlSelect);
            csSelect.setString(1, idModificar);
            ResultSet rs = csSelect.executeQuery();
            if (rs.next()) {
                rutaImagenActual = rs.getString("imagen");  // Obtener la ruta de la imagen actual
            }

            // Si se va a cargar una nueva imagen, eliminamos la imagen anterior
            if (rutaImagenNueva != null && !rutaImagenNueva.isEmpty() && rutaImagenActual != null && !rutaImagenActual.isEmpty()) {
                File archivoImagenActual = new File("src/main/resources" + rutaImagenActual);  // Ruta completa de la imagen anterior
                if (archivoImagenActual.exists()) {
                    archivoImagenActual.delete();  // Eliminar la imagen anterior
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al obtener la imagen actual: " + e.toString());
            return;  // Detener si hay un error
        }

        // Procedemos con la actualización
        String sqlUpdate = "UPDATE participantes SET nombres = ?, apellidos = ?, idequipo = ?, imagen = ? WHERE id = ?;";

        try {
            CallableStatement csUpdate = objetoConexion.establecerConexion().prepareCall(sqlUpdate);
            csUpdate.setString(1, nombresModificar);
            csUpdate.setString(2, apellidosModificar);
            csUpdate.setInt(3, idEquipoSeleccionado);

            // Verificar si hay una nueva imagen cargada
            if (rutaImagenNueva != null && !rutaImagenNueva.isEmpty()) {
                csUpdate.setString(4, rutaImagenNueva);  // Guardar la nueva ruta de la imagen
            } else {
                csUpdate.setString(4, rutaImagenActual);  // Mantener la imagen anterior si no se ha cargado una nueva
            }

            csUpdate.setString(5, idModificar);
            csUpdate.executeUpdate();

            JOptionPane.showMessageDialog(null, "Se actualizó correctamente el participante");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización, error: " + e.toString());
        }
    }
    
    public void eliminarParticipantes(JTextField paraId) {
        String idEliminar = paraId.getText();
        
        conexionbd objetoConexion = new conexionbd();
        String sql = "DELETE FROM participantes WHERE id=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(sql);
            cs.setString(1, idEliminar);
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se elimino correctamente el participante");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar, error: " + e.toString());
        }
    }
    
    public void llenarEquipos(String tabla, String valor, JComboBox<participantes> combo) {
        String sql = "select * from " + tabla;
        Statement st;
        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("equipo");
                combo.addItem(new participantes(id, "", "", id, nombre));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
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
        // Método para cargar y mostrar la imagen en el JLabel
     private void cargarImagenEnLabel(JLabel label, String rutaCompleta) {
         ImageIcon imageIcon = new ImageIcon(rutaCompleta);
         Image imagen = imageIcon.getImage();
         Image imagenEscalada = imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
         label.setIcon(new ImageIcon(imagenEscalada));
     }

}
