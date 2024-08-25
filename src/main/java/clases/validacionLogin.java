/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import interfaces.formlogin;
import interfaces.vistalogin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author YILBER
 */
public class validacionLogin {
    public void validaUsuario(JTextField usuario, JPasswordField password, vistalogin loginForm){
        try {
            ResultSet rs =null;
            PreparedStatement ps = null;
            clases.conexionbd objetoConexion = new clases.conexionbd();
            
            String consulta="select * from usuarios u where u.usuario=(?) and u.password=(?);";
            ps=objetoConexion.establecerConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(password.getPassword());
            ps.setString(1, usuario.getText());
            ps.setString(2, contra);
            
            rs = ps.executeQuery();
            
            if (rs.next()) {
                JOptionPane.showMessageDialog(null, "Login exitoso");
                formlogin objetoMenu = new formlogin();
                
                loginForm.setVisible(false);
                objetoMenu.setVisible(true);
                
            }else{
                JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos");
            }
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Algo fallo"+e.toString());
        }
    }
}
