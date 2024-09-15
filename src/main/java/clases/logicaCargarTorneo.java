/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author YILBER
 */
public class logicaCargarTorneo {
    
    public void mostrarTorneos(JTable paramTablaTorneo) {
        conexionbd objetoConexion = new conexionbd();
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaTorneo.setRowSorter(ordenarTabla);

        modelo.addColumn("id");
        modelo.addColumn("Asignatura");
        modelo.addColumn("Grado");
        modelo.addColumn("Estado");
        paramTablaTorneo.setModel(modelo);

        TableColumnModel columnModel = paramTablaTorneo.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(20);  // Ancho para la columna "id"
        columnModel.getColumn(1).setPreferredWidth(100); // Ancho para la columna "Nombres"
        columnModel.getColumn(2).setPreferredWidth(100); // Ancho para la columna "Apellidos"
        columnModel.getColumn(3).setPreferredWidth(100);  // Ancho para la columna "Equipo"

        String sql = "select p.id, a.asignatura, g.grado, et.estado, et.color from torneo p join asignaturas a on(a.id = p.idasignatura) join grados g on(g.id = p.idgrado) join estadostorneos et on(et.id = p.idestadostorneos)";
        Object[] datos = new Object[4]; // Cambiado a Object[] para manejar la imagen
        Statement st;

        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                String estado = "<html><span class='background:black'>"+rs.getString(4)+"</span></html>";
                datos[0] = rs.getString(1);  // ID del participante
                datos[1] = rs.getString(2);  // Asignatura
                datos[2] = rs.getString(3);  // Grado
                datos[3] = estado;  // Grado

                modelo.addRow(datos);
            }
            paramTablaTorneo.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros " + e.toString());
        }
    }
    
    public void eliminarTorneos(JTable tablaTorneos) {
        try {
            int fila = tablaTorneos.getSelectedRow();
            if (fila >= 0) {
                String idFila = tablaTorneos.getValueAt(fila, 0).toString();   

                // Mostrar un cuadro de confirmación
                int confirmacion = JOptionPane.showConfirmDialog(null, "¿Está seguro de que desea eliminar este torneo?", 
                                                                 "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                // Solo eliminar si el usuario confirma
                if (confirmacion == JOptionPane.YES_OPTION) {
                    conexionbd objetoConexion = new conexionbd();
                    String sql = "DELETE FROM torneo WHERE id=?;";
                    CallableStatement cs = objetoConexion.establecerConexion().prepareCall(sql);
                    cs.setString(1, idFila);
                    cs.execute();

                    JOptionPane.showMessageDialog(null, "Se eliminó correctamente el torneo.");
                } else {
                    JOptionPane.showMessageDialog(null, "Eliminación cancelada.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila para eliminar.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.toString());
        }
    }
}
