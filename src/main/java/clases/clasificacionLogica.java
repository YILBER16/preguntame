/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.awt.Color;
import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author YILBER
 */
public class clasificacionLogica {
    
    public clasificacionLogica(){
    }


public void consultarClasificacion(int idtorneo, JLabel nparticipantes1, JLabel nparticipantes2, JLabel nparticipantes3, JLabel nparticipantes4, JLabel imgpar1, JLabel imgpar2, JLabel imgpar3, JLabel imgpar4, JLabel npuntos1, JLabel npuntos2, JLabel npuntos3, JLabel npuntos4, JLabel n1, JLabel n2, JLabel n3, JLabel n4) {
    // Consulta SQL para obtener participantes ordenados por puntaje
    String sql = "SELECT tp.idtipoparticipante, tp.idequipo, tp.idparticipante, tp.puntaje, c.representacion " +
                 "FROM torneoparticipantes tp " +
                 "JOIN colores c on (c.id = tp.idcolor) " +
                 "WHERE tp.idtorneo = ? " +
                 "ORDER BY tp.puntaje DESC";

    try (Connection conexion = new conexionbd().establecerConexion();
         PreparedStatement pst = conexion.prepareStatement(sql)) {

        pst.setInt(1, idtorneo);

        try (ResultSet rs = pst.executeQuery()) {
            int puesto = 1;
            while (rs.next() && puesto <= 4) {
                int idTipoParticipante = rs.getInt("idtipoparticipante");
                int idEquipo = rs.getInt("idequipo");
                int idParticipante = rs.getInt("idparticipante");
                double puntaje = rs.getDouble("puntaje");
                String color = rs.getString("representacion");

                if (idTipoParticipante == 2) { // Si es un equipo
                    // Consultar el nombre del equipo y la ruta de las imágenes
                    String sqlEquipo = "SELECT e.equipo, p.imagen " +
                                       "FROM participantes p " +
                                       "JOIN equipos e ON p.idequipo = e.id " +
                                       "WHERE e.id = ?";
                    StringBuilder htmlEquipo = new StringBuilder();
                    htmlEquipo.append("<html>");
                    String nombreEquipo = "";
                    
                    try (PreparedStatement pstEquipo = conexion.prepareStatement(sqlEquipo)) {
                        pstEquipo.setInt(1, idEquipo);
                        try (ResultSet rsEquipo = pstEquipo.executeQuery()) {
                            
                            htmlEquipo.append("<div style='display: flex; align-items: center; justify-content: center'>"); // Usar flexbox para alinear las imágenes

                            while (rsEquipo.next()) {
                                if (nombreEquipo.isEmpty()) {
                                    nombreEquipo = "<html><font color='#" + color + "' face='Arial' size='6'>"+rsEquipo.getString("equipo")+"</font></html>";
                                }
                                String rutaImagenMiembro = "src/main/resources/" + rsEquipo.getString("imagen");
                                
                                // Crear un ImageIcon para la imagen del equipo y redimensionarla
                                ImageIcon originalIcon = new ImageIcon(rutaImagenMiembro);
                                Image img = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                                ImageIcon iconoRedimensionado = new ImageIcon(img);
                                
                                htmlEquipo.append(String.format("<img src='file:%s' width='100' height='100' style='margin-right:5px;'/>", rutaImagenMiembro));
                            }
                            htmlEquipo.append(String.format("</div></html>"));
                        }
                    }
                    String puntajeText = "<html><div style='text-align: center;'>" +
                     "<font color='#" + color + "' face='Arial' size='6'>" +
                     puntaje + "<br> Puntos" +
                     "</font></div></html>";
                    // Asignar el HTML y la imagen a los JLabel correspondientes
                    switch (puesto) {
                        case 1:
                            n1.setForeground(Color.decode("#"+color));
                            nparticipantes1.setText(nombreEquipo);
                            imgpar1.setText(htmlEquipo.toString()); // Mostrar HTML en JLabel para imágenes del equipo
                            npuntos1.setText(puntajeText);
                            break;
                        case 2:
                            n2.setForeground(Color.decode("#"+color));
                            nparticipantes2.setText(nombreEquipo);
                            imgpar2.setText(htmlEquipo.toString());
                            npuntos2.setText(puntajeText);
                            break;
                        case 3:
                            n3.setForeground(Color.decode("#"+color));
                            nparticipantes3.setText(nombreEquipo);
                            imgpar3.setText(htmlEquipo.toString());
                            npuntos3.setText(puntajeText);
                            break;
                        case 4:
                            n4.setForeground(Color.decode("#"+color));
                            nparticipantes4.setText(nombreEquipo);
                            imgpar4.setText(htmlEquipo.toString());
                            npuntos4.setText(puntajeText);
                            break;
                    }
                } else { // Si es un participante individual
                    String sqlParticipante = "SELECT CONCAT(p.nombres, ' ', p.apellidos) as nombres, p.imagen " +
                                             "FROM participantes p " +
                                             "WHERE p.id = ?";
                    try (PreparedStatement pstParticipante = conexion.prepareStatement(sqlParticipante)) {
                        pstParticipante.setInt(1, idParticipante);
                        try (ResultSet rsParticipante = pstParticipante.executeQuery()) {
                            String nombre;
                            String rutaImagen;
                            ImageIcon imagenIcono = null;

                            if (rsParticipante.next()) {
                                nombre = rsParticipante.getString("nombres");
                                rutaImagen = "src/main/resources/" + rsParticipante.getString("imagen");
                                ImageIcon originalIcon = new ImageIcon(rutaImagen);
                                Image img = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                                imagenIcono = new ImageIcon(img);
                            } else {
                                nombre = "Participante Desconocido";
                                rutaImagen = "";
                            }

                            // Crear HTML para el participante individual
                            String htmlParticipante = String.format("<html><font color='#" + color + "' face='Arial' size='5'>%s</font><br></html>",
                                                                    nombre);
                            String puntajeText = "<html><div style='text-align: center;'>" +
                            "<font color='#" + color + "' face='Arial' size='6'>" +
                            puntaje + "<br> Puntos" +
                            "</font></div></html>";
                            // Asignar el HTML y la imagen a los JLabel correspondientes
                            switch (puesto) {
                                case 1:
                                    n1.setForeground(Color.decode("#"+color));
                                    nparticipantes1.setText(htmlParticipante);
                                    imgpar1.setIcon(imagenIcono);
                                    npuntos1.setText(puntajeText);
                                    break;
                                case 2:
                                    n2.setForeground(Color.decode("#"+color));
                                    nparticipantes2.setText(htmlParticipante);
                                    imgpar2.setIcon(imagenIcono);
                                    npuntos2.setText(puntajeText);
                                    break;
                                case 3:
                                    n3.setForeground(Color.decode("#"+color));
                                    nparticipantes3.setText(htmlParticipante);
                                    imgpar3.setIcon(imagenIcono);
                                    npuntos3.setText(puntajeText);
                                    break;
                                case 4:
                                    n4.setForeground(Color.decode("#"+color));
                                    nparticipantes4.setText(htmlParticipante);
                                    imgpar4.setIcon(imagenIcono);
                                    npuntos4.setText(puntajeText);
                                    break;
                            }
                        }
                    }
                }

                puesto++;
            }

            // Rellenar los JLabel restantes si hay menos de 4 participantes
            while (puesto <= 4) {
                JLabel labelTexto;
                JLabel labelImagen;

                switch (puesto) {
                    case 1:
                        labelTexto = nparticipantes1;
                        labelImagen = imgpar1;
                        break;
                    case 2:
                        labelTexto = nparticipantes2;
                        labelImagen = imgpar2;
                        break;
                    case 3:
                        labelTexto = nparticipantes3;
                        labelImagen = imgpar3;
                        break;
                    case 4:
                        labelTexto = nparticipantes4;
                        labelImagen = imgpar4;
                        break;
                    default:
                        labelTexto = null;
                        labelImagen = null;
                        break;
                }

                if (labelTexto != null) {
                    labelTexto.setText(String.format("<html><font color='#FF0000' face='Arial' size='4'>%d. - Sin participación</font></html>", puesto));
                    labelImagen.setIcon(null); // Sin imagen
                }

                puesto++;
            }

        }

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}





}
