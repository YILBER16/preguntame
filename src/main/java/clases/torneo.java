/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import interfaces.juegoencurso;
import java.awt.Graphics;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.image.BufferedImage;
import java.sql.CallableStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author YILBER
 */
public class torneo {

    private int id;
    private String nombre;
    private String color;
    private String representacion;
    
    public torneo() {
        
    }
    
    public torneo(int id, String nombre, String representacion) {
        this.id = id;
        this.nombre = nombre;
        this.representacion = representacion;
    }
    
    @Override
    public String toString() {
        return this.nombre; // O el nombre que deseas mostrar en el JComboBox
    }
    
    public void iniciarTorneo(JTable tblParticipantes, JComboBox<torneo> comboAsignatura) {
        DefaultTableModel model = (DefaultTableModel) tblParticipantes.getModel();
        torneo asignaturaSeleccionada = (torneo) comboAsignatura.getSelectedItem();
        int idasignatura = asignaturaSeleccionada.getId();

        conexionbd objetoConexion = new conexionbd();
        String sqlTorneo = "INSERT INTO torneo(idasignatura, idestadostorneos) VALUES (?, ?);";
        int idTorneoGenerado = -1; 

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(sqlTorneo);
            cs.setInt(1, idasignatura);
            cs.setInt(2, 2);
            cs.execute();

            // Obtener las claves generadas (ID del torneo)
            ResultSet rs = cs.getGeneratedKeys();
            if (rs.next()) {
                idTorneoGenerado = rs.getInt(1);  // Obtener el ID generado
            }

            // Recorrer cada fila de la tabla
            for (int i = 0; i < model.getRowCount(); i++) {
                String idParticipante = (String) model.getValueAt(i, 0);

                String sqlConsulta = "Select idequipo from participantes where id="+idParticipante+"";
                Statement st;
                System.out.println("tipo participante "+idParticipante);
                st = objetoConexion.establecerConexion().createStatement();
                ResultSet rsConsulta = st.executeQuery(sqlConsulta);
                int idtipoparticipante = 0;
                int idequipo = 0;
                while (rsConsulta.next()) {
                    
                   if(rsConsulta.getInt(1) == 1){
                       idtipoparticipante = 1;
                   }else{
                       idtipoparticipante = 2;
                       idequipo = rsConsulta.getInt(1);
                   }
                }

                String sqlParticipantes = "INSERT INTO torneoparticipantes(idtorneo, idtipoparticipante, idparticipante, idequipo) VALUES (?, ?, ?, ?);";

                CallableStatement csParticipantes = objetoConexion.establecerConexion().prepareCall(sqlParticipantes);
                csParticipantes.setInt(1, idTorneoGenerado);
                csParticipantes.setInt(2, idtipoparticipante);
                if (idtipoparticipante == 1) {
                    csParticipantes.setString(3, idParticipante);
                    csParticipantes.setNull(4, java.sql.Types.INTEGER);
                } else {
                    csParticipantes.setNull(3, java.sql.Types.INTEGER); 
                    csParticipantes.setInt(4, idequipo);
                }
                csParticipantes.execute();
            }

            JOptionPane.showMessageDialog(null, "Se inició el torneo con éxito");
            juegoencurso vistaJuego = new juegoencurso(idTorneoGenerado); // Pasar el ID del torneo si es necesario
            vistaJuego.setVisible(true); // Mostrar la nueva ventana

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error en la creación del torneo, error: " + e.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error inesperado: " + e.toString());
        }
    }

    
    public void agregarParticipantes(JComboBox<torneo> comboParticipante, JComboBox<torneo> comboTipoTorneo, JTable tblParticipantes, DefaultTableModel modelo, JComboBox<torneo> comboColor) {
        torneo participanteSeleccionado = (torneo) comboParticipante.getSelectedItem();
        int idParticipanteSeleccionado = 0;
        if (participanteSeleccionado != null) {
            idParticipanteSeleccionado = participanteSeleccionado.getId();
        }

        torneo tipoTorneoSeleccionado = (torneo) comboTipoTorneo.getSelectedItem();
        int idTipoSeleccionado = tipoTorneoSeleccionado.getId();
        String idTipoTorneoSeleccionado = tipoTorneoSeleccionado.toString();

        torneo colorSeleccionado = (torneo) comboColor.getSelectedItem();
        int idColorSeleccionado = colorSeleccionado.getId();
        String representacionColorSeleccionado = colorSeleccionado.getColor();
        String nombreColorSeleccionado = colorSeleccionado.toString();

        // Verificar si el participante o equipo ya está en la tabla
        for (int i = 0; i < modelo.getRowCount(); i++) {
            Object idEnTablaObj = modelo.getValueAt(i, 0); // ID en la tabla
            Object colorEnTablaObj = modelo.getValueAt(i, 3); // Color en la tabla

            // Verificar el tipo del objeto y manejar la conversión
            int idEnTabla = 0;
            if (idEnTablaObj instanceof Integer) {
                idEnTabla = (Integer) idEnTablaObj;
            } else if (idEnTablaObj instanceof String) {
                try {
                    idEnTabla = Integer.parseInt((String) idEnTablaObj);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Error al convertir el ID en la tabla.");
                    e.printStackTrace();
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "ID en la tabla no es válido.");
                return;
            }

            // Extraer el color del HTML en la tabla
            String colorEnTabla = extraerColorDeHTML(colorEnTablaObj);
            if (colorEnTabla.equalsIgnoreCase(representacionColorSeleccionado)) {
                JOptionPane.showMessageDialog(null, "Ya hay un participante o equipo con el mismo color en la tabla.");
                return; // Salir del método si el color ya está en la tabla
            }

            if (idTipoTorneoSeleccionado.equals("Individual") && idEnTabla == idParticipanteSeleccionado) {
                JOptionPane.showMessageDialog(null, "El participante ya está agregado en la tabla.");
                return; // Salir del método si el participante ya está en la tabla
            }

            if (idTipoTorneoSeleccionado.equals("Equipos") && idEnTabla == idParticipanteSeleccionado) {
                JOptionPane.showMessageDialog(null, "El equipo ya está agregado en la tabla.");
                return; // Salir del método si el equipo ya está en la tabla
            }
        }

        String sql = "";
        if ((participanteSeleccionado != null || idParticipanteSeleccionado != 0) && idTipoSeleccionado != 0 && idColorSeleccionado != 0) {
            // Determinar el tipo de torneo y realizar la consulta correspondiente
            if (idTipoTorneoSeleccionado.equals("Individual")) {
                sql = "SELECT p.id, CONCAT(p.apellidos, ' ', p.nombres) AS participante, p.imagen " +
                      "FROM participantes p WHERE p.id = " + idParticipanteSeleccionado;
            } else if (idTipoTorneoSeleccionado.equals("Equipos")) {
                sql = "SELECT p.id, e.equipo, p.id, p.apellidos, p.nombres, p.imagen " +
                      "FROM equipos e " +
                      "JOIN participantes p ON e.id = p.idequipo " +
                      "WHERE e.id = " + idParticipanteSeleccionado;
            }

            // Conservar los datos anteriores en el modelo
            Object[] datos = new Object[4];
            conexionbd con = new conexionbd();
            Connection conexion = con.establecerConexion();

            try (PreparedStatement st = conexion.prepareStatement(sql)) {
                ResultSet rs = st.executeQuery();
                StringBuilder Color = new StringBuilder();
                ArrayList<ImageIcon> imagenesParticipantes = new ArrayList<>();
                if (idTipoTorneoSeleccionado.equals("Equipos")) {
                    StringBuilder equipoNombre = new StringBuilder();
                    int i = 1;
                    while (rs.next()) {
                        if (equipoNombre.length() == 0) {
                            equipoNombre.append("<html>");
                            equipoNombre.append("<div style='text-align:center; color:#" + representacionColorSeleccionado + "'>"); // Centrar el contenido
                            equipoNombre.append("<font size='6'>").append(rs.getString(2)).append("</font><br>");
                            equipoNombre.append("</div>");
                            equipoNombre.append("<br>");
                            datos[0] = rs.getString(1); // ID del equipo
                        }

                        equipoNombre.append("<font size='5'>").append(i).append(". ")
                                    .append(rs.getString(4)).append(" ")
                                    .append(rs.getString(5)).append("</font><br>");

                        String rutaImagen = rs.getString(6);
                        imagenesParticipantes.add(cargarImagenDesdeRuta("src/main/resources/" + rutaImagen, 150, 150));
                        i++;
                    }
                    equipoNombre.append("</html>");
                    datos[1] = equipoNombre.toString();

                    if (!imagenesParticipantes.isEmpty()) {
                        datos[2] = concatenarImagenes(imagenesParticipantes, 150, 150);
                    }
                    Color.append("<html>");
                    Color.append("<div style='text-align:center; color:#" + representacionColorSeleccionado + "'>").append(nombreColorSeleccionado);
                    Color.append("</div>");
                    Color.append("</html>");
                    datos[3] = Color.toString();

                    modelo.addRow(datos);
                } else {
                    while (rs.next()) {
                        datos[0] = rs.getString(1);  // ID del participante
                        datos[1] = rs.getString(2);  // Nombre del participante
                        String rutaImagen = rs.getString(3);  // Ruta de la imagen
                        imagenesParticipantes.add(cargarImagenDesdeRuta("src/main/resources/" + rutaImagen, 150, 150));
                        if (!imagenesParticipantes.isEmpty()) {
                            datos[2] = concatenarImagenes(imagenesParticipantes, 150, 150);
                        }
                        Color.append("<html>");
                        Color.append("<div style='text-align:center; color:#" + representacionColorSeleccionado + "'>").append(nombreColorSeleccionado);
                        Color.append("</div>");
                        Color.append("</html>");
                        datos[3] = Color.toString();
                        modelo.addRow(datos);
                    }
                }

                tblParticipantes.setModel(modelo);

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar los participantes: " + e.toString());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Faltan datos por seleccionar");
        }
    }
    // Método para cargar una imagen desde la ruta
    private ImageIcon cargarImagenDesdeRuta(String rutaCompleta, int ancho, int alto) {
        ImageIcon imageIcon = new ImageIcon(rutaCompleta);

        // Escalar la imagen al tamaño deseado
        Image imagen = imageIcon.getImage();
        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenEscalada);
    }
    // Método para concatenar múltiples imágenes en una sola
    private ImageIcon concatenarImagenes(ArrayList<ImageIcon> imagenes, int ancho, int alto) {
        int totalAncho = ancho * imagenes.size(); // Calcular el ancho total de todas las imágenes concatenadas
        int altura = alto; // La altura es la misma para todas las imágenes

        // Crear un nuevo buffer de imagen para almacenar todas las imágenes concatenadas
        BufferedImage imagenFinal = new BufferedImage(totalAncho, altura, BufferedImage.TYPE_INT_ARGB);
        Graphics g = imagenFinal.getGraphics();

        int x = 0;
        for (ImageIcon icono : imagenes) {
            g.drawImage(icono.getImage(), x, 0, ancho, alto, null);
            x += ancho; // Mover la posición para la siguiente imagen
        }

        g.dispose();
        return new ImageIcon(imagenFinal);
    }
    
    private String extraerColorDeHTML(Object colorObj) {
        if (colorObj instanceof String) {
            String colorHTML = (String) colorObj;
            Pattern pattern = Pattern.compile("color:#([a-fA-F0-9]{6})");
            Matcher matcher = pattern.matcher(colorHTML);
            if (matcher.find()) {
                return matcher.group(1); // Retorna el valor hexadecimal del color
            }
        }
        return "";
    }
    
    public void llenarComboboxTipoTorneo(JComboBox<torneo> comboTipo) {
        comboTipo.addItem(new torneo(0,"Seleccione una opción", null));
        comboTipo.addItem(new torneo(1,"Individual", null));
        comboTipo.addItem(new torneo(2,"Equipos", null));
    }
    
    public void llenarComboboxColor(JComboBox<torneo> comboColor) {
        String sql = "Select * from colores ORDER BY id";
        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();
        try (Statement st = conexion.createStatement(); 
            ResultSet rs = st.executeQuery(sql)) {
            comboColor.addItem(new torneo(0,"Seleccione un color", null));
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("color");
                String color = rs.getString("representacion");
                torneo tipoRespuesta = new torneo(id, nombre, color);
                comboColor.addItem(tipoRespuesta);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de respuesta: " + e.toString());
        }
    }
    
    public void llenarComboboxParticipantes(String tabla, JComboBox<torneo> comboTipo, JComboBox<torneo> comboParticipante) {
        comboParticipante.removeAllItems();
        torneo tipoSeleccionada = (torneo) comboTipo.getSelectedItem();
        int idTipoSeleccionada = tipoSeleccionada.getId();
        String sql = "";
        if(idTipoSeleccionada==1){
            sql = "SELECT p.id, CONCAT(p.apellidos,' ', p.nombres) as participante, p.idequipo, e.equipo  FROM participantes p join equipos e on (e.id = p.idequipo) where p.idequipo = 1";
        }else if(idTipoSeleccionada==2){
            sql = "SELECT id, equipo as participante FROM equipos where id != 1";
        }
        if(idTipoSeleccionada != 0){
            conexionbd con = new conexionbd();
            Connection conexion = con.establecerConexion();

            try (Statement st = conexion.createStatement(); 
                 ResultSet rs = st.executeQuery(sql)) {
                comboParticipante.addItem(new torneo(0,"Seleccione una opción", null));
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String nombre = rs.getString("participante");
                    torneo tipoRespuesta = new torneo(id, nombre, null);
                    comboParticipante.addItem(tipoRespuesta);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cargar tipos de respuesta: " + e.toString());
            }
        }


    }
    
    public void llenarComboBoxAsignatura(JComboBox<torneo> comboBoxAsignaturas) {
        String sql = "SELECT * FROM asignaturas";
        conexionbd con = new conexionbd();
        Connection conexion = con.establecerConexion();

        try (Statement st = conexion.createStatement(); 
            ResultSet rs = st.executeQuery(sql)) {
            comboBoxAsignaturas.addItem(new torneo(0,"Seleccione una opción", null));
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("asignatura");
                torneo asignaturas = new torneo(id, nombre, null);
                comboBoxAsignaturas.addItem(asignaturas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar tipos de respuesta: " + e.toString());
        }
    }
    
    public void eliminarFilaSeleccionada(JTable tblParticipantes) {
        DefaultTableModel modelo = (DefaultTableModel) tblParticipantes.getModel();
        int filaSeleccionada = tblParticipantes.getSelectedRow();

        if (filaSeleccionada != -1) {
            int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que quieres eliminar esta fila?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
            
            if (confirmacion == JOptionPane.YES_OPTION) {
                modelo.removeRow(filaSeleccionada);
                JOptionPane.showMessageDialog(null, "Fila eliminada exitosamente.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Por favor, selecciona una fila para eliminar.");
        }
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    
    public String getColor() {
        return representacion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
