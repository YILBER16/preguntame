package clases;

import combo_suggestion.ComboBoxSuggestion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class asignaturas {
    int id;
    private String asignatura;

    public asignaturas() {
        
    }
    
    public asignaturas(int id, String asignatura) {
        this.id = id;
        this.asignatura = asignatura;
    }

     public int getId() {
        return id;
    }
     
    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }
    
    public void insertarAsignatura(JTextField paraAsignatura) {
        if(!paraAsignatura.getText().isEmpty()){
            setAsignatura(paraAsignatura.getText());

            conexionbd objetoConexion = new conexionbd();
            String consulta = "insert into asignaturas(asignatura) values (?);";

            try {
                CallableStatement cs = objetoConexion.establecerConexion().prepareCall(consulta);
                cs.setString(1, getAsignatura());
                cs.execute();

                JOptionPane.showMessageDialog(null, "Se insertó correctamente la asignatura");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error en la inserción, error: " + e.toString());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Faltan datos necesarios en los campos.");
        }
    }

    public void mostrarAsignaturas(JTable paramTablaAsignaturas) {
        conexionbd objetoConexion = new conexionbd();
        DefaultTableModel modelo = new DefaultTableModel();

        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTablaAsignaturas.setRowSorter(ordenarTabla);

        modelo.addColumn("id");
        modelo.addColumn("Asignatura");

        paramTablaAsignaturas.setModel(modelo);

        String sql = "select id, asignatura from asignaturas";
        String[] datos = new String[2];
        Statement st;
        
        try {
            st = objetoConexion.establecerConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                modelo.addRow(datos);
            }
            paramTablaAsignaturas.setModel(modelo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo mostrar los registros " + e.toString());
        }
    }

    public void seleccionarAsignaturas(JTable paramTablaAsignaturas, JTextField paramId, JTextField paramAsignatura){
        try {
            int fila = paramTablaAsignaturas.getSelectedRow();
            if(fila >= 0){
                String idFila = paramTablaAsignaturas.getValueAt(fila, 0).toString();
                String sql = "select * from asignaturas where id="+idFila;
                Statement st;
                conexionbd con = new conexionbd();
                Connection conexion = con.establecerConexion();
                st = conexion.createStatement();
                ResultSet rs = st.executeQuery(sql);
                
                while (rs.next()) {
                    paramId.setText(rs.getString("id"));
                    paramAsignatura.setText(rs.getString("asignatura"));
                }
            }else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: "+e.toString());
        }
    }
    
    public void modificarAsignaturas(JTextField paraId, JTextField paraAsignaturas) {
        String idModificar = paraId.getText();
        String nombresModificar = paraAsignaturas.getText();
        
        conexionbd objetoConexion = new conexionbd();
        String sql = "UPDATE asignaturas SET asignatura= ? WHERE id = ?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(sql);
            cs.setString(1, nombresModificar);
            cs.setString(2, idModificar);
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se actualizo correctamente la asignatura");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en la actualización, error: " + e.toString());
        }
    }
    
    public void eliminarAsignaturas(JTextField paraId) {
        String idEliminar = paraId.getText();
        
        conexionbd objetoConexion = new conexionbd();
        String sql = "DELETE FROM asignaturas WHERE id=?;";

        try {
            CallableStatement cs = objetoConexion.establecerConexion().prepareCall(sql);
            cs.setString(1, idEliminar);
            cs.execute();

            JOptionPane.showMessageDialog(null, "Se elimino correctamente la asignatura");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar, error: " + e.toString());
        }
    }
}
