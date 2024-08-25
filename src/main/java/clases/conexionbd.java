/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author YILBER
 */
public class conexionbd {
    Connection conectar;
    String usuario = "root";
    String password = "";
    String bd = "preguntamebd";
    String servidor = "localhost";
    String puerto = "3306";
    
    String cadena = "jdbc:mysql://"+servidor+":"+puerto+"/"+bd;
    
    public Connection establecerConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,password);
            //JOptionPane.showMessageDialog(null, "Bienvenido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Problemas en la conexión"+ e.toString());
        }
        return conectar;
    }
}
