/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaces;

import clases.juegologica;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.sound.sampled.Clip;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
/**
 *
 * @author YILBER
 */
public class juegoencurso extends javax.swing.JFrame {

    Color miColorPersonalizado = new Color(0, 148, 172);
    private int idTorneo;
    private reproducirSonido reproductor;
    private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private juegologica juego;
    
    public juegoencurso(int idTorneo) {
        iniciarComunicacion();
        reproductor = new reproducirSonido();
        this.idTorneo = idTorneo;
        initComponents();
        labelAlerta.setOpaque(false);
       // Agregar un MouseListener para simular el comportamiento de un botón
        btna.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                btna.setIcon(new ImageIcon("src/main/resources/botones/buttonbase2.png")); // Color personalizado
                btna.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btna.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        btnb.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                btnb.setIcon(new ImageIcon("src/main/resources/botones/buttonbase3.png")); // Color personalizado
                btnb.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btnb.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        btnc.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                btnc.setIcon(new ImageIcon("src/main/resources/botones/buttonbase4.png")); // Color personalizado
                btnc.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btnc.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        btnd.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                btnd.setIcon(new ImageIcon("src/main/resources/botones/buttonbase5.png")); // Color personalizado
                btnd.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cambiar cursor a mano
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btnd.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
                labelopciond.setForeground(Color.BLACK);
            }
        });
        labelopciona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reproductor.cargarSonido("src/main/resources/sonidos/hover.wav");
                reproductor.reproducir();
                btna.setIcon(new ImageIcon("src/main/resources/botones/buttonbase2.png")); // Color personalizado
                btna.setCursor(new Cursor(Cursor.HAND_CURSOR));
                labelopciona.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btna.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        labelopcionb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                reproductor.cargarSonido("src/main/resources/sonidos/hover.wav");
                reproductor.reproducir();
                btnb.setIcon(new ImageIcon("src/main/resources/botones/buttonbase3.png")); // Color personalizado
                btnb.setCursor(new Cursor(Cursor.HAND_CURSOR));
                labelopcionb.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btnb.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        labelopcionc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                reproductor.cargarSonido("src/main/resources/sonidos/hover.wav");
                reproductor.reproducir();
                btnc.setIcon(new ImageIcon("src/main/resources/botones/buttonbase4.png")); // Color personalizado
                btnc.setCursor(new Cursor(Cursor.HAND_CURSOR));
                labelopcionc.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btnc.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        labelopciond.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                // Cambiar el color de fondo cuando el cursor entra
                reproductor.cargarSonido("src/main/resources/sonidos/hover.wav");
                reproductor.reproducir();
                btnd.setIcon(new ImageIcon("src/main/resources/botones/buttonbase5.png")); // Color personalizado
                btnd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                labelopciond.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                // Restaurar el color de fondo cuando el cursor sale
                btnd.setIcon(new ImageIcon("src/main/resources/botones/buttonbase.png"));
            }
        });
        btnsiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    enviarComandoReiniciar();
                } catch (SerialPortException ex) {
                    Logger.getLogger(juegoencurso.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        juego = new juegologica(labelpreguntas, labelopciona, labelopcionb, labelopcionc, labelopciond, btnsiguiente, labelAlerta, lblimgpregunta, 30, this);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roboto1 = new efectos.Roboto();
        materialShadow1 = new efectos.MaterialShadow();
        rSButtonPaneBeanInfo1 = new rojerusan.RSButtonPaneBeanInfo();
        jPanel6 = new javax.swing.JPanel();
        boton1 = new botones.boton();
        labelAlerta = new javax.swing.JLabel();
        btnsiguiente = new botones.boton();
        labelopciona = new javax.swing.JLabel();
        labelopcionb = new javax.swing.JLabel();
        labelopcionc = new javax.swing.JLabel();
        labelopciond = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        btna = new rojerusan.RSLabelImage();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        btnc = new rojerusan.RSLabelImage();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        btnd = new rojerusan.RSLabelImage();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        btnb = new rojerusan.RSLabelImage();
        labelpreguntas = new javax.swing.JLabel();
        lblimgpregunta = new rojerusan.RSLabelImage();
        rSLabelImage1 = new rojerusan.RSLabelImage();
        labelfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        boton1.setBackground(new java.awt.Color(255, 102, 0));
        boton1.setForeground(new java.awt.Color(255, 255, 255));
        boton1.setText("Clasificación");
        boton1.setToolTipText("");
        boton1.setFocusable(false);
        boton1.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        boton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boton1ActionPerformed(evt);
            }
        });
        jPanel6.add(boton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 10, 150, 50));

        labelAlerta.setText("jLabel1");
        jPanel6.add(labelAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        btnsiguiente.setText("Siguiente");
        btnsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguienteActionPerformed(evt);
            }
        });
        jPanel6.add(btnsiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 720, 240, 60));

        labelopciona.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        labelopciona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelopciona.setToolTipText("");
        labelopciona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelopcionaMouseEntered(evt);
            }
        });
        jPanel6.add(labelopciona, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 250, 440, 200));

        labelopcionb.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        jPanel6.add(labelopcionb, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 260, 440, 180));

        labelopcionc.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jPanel6.add(labelopcionc, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 500, 470, 180));

        labelopciond.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        jPanel6.add(labelopciond, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 500, 440, 180));

        jLabel6.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("C");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 530, 80, 140));

        jLabel5.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("D");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 530, 80, 140));

        jLabel4.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("B");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 290, 80, 140));

        jLabel3.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("A");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 110, 140));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber.png"))); // NOI18N
        jPanel6.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 160, 200));

        btna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        btna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnaMouseEntered(evt);
            }
        });
        jPanel6.add(btna, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 250, 550, 200));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber3.png"))); // NOI18N
        jPanel6.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, -1, 200));

        btnc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        jPanel6.add(btnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 490, 540, 200));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber4.png"))); // NOI18N
        jPanel6.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 490, -1, 200));

        btnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        jPanel6.add(btnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 490, 540, 200));

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber2.png"))); // NOI18N
        jPanel6.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 250, -1, 200));

        btnb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        jPanel6.add(btnb, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 250, 540, 200));

        labelpreguntas.setFont(new java.awt.Font("Segoe UI", 2, 48)); // NOI18N
        labelpreguntas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelpreguntas.setText("Cual es la longitud de la tierra?");
        jPanel6.add(labelpreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 60, 1130, 150));

        lblimgpregunta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/logo.png"))); // NOI18N
        lblimgpregunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimgpreguntaMouseClicked(evt);
            }
        });
        jPanel6.add(lblimgpregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, -1, -1));

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/panelquestion.png"))); // NOI18N
        jPanel6.add(rSLabelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 1290, 170));

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/gif3.gif"))); // NOI18N
        labelfondo.setText("jLabel1");
        jPanel6.add(labelfondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnaMouseEntered
        btna.setBackground(miColorPersonalizado);
    }//GEN-LAST:event_btnaMouseEntered

    private void labelopcionaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelopcionaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_labelopcionaMouseEntered

    private void btnsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnsiguienteActionPerformed

    private void lblimgpreguntaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimgpreguntaMouseClicked
    }//GEN-LAST:event_lblimgpreguntaMouseClicked

    private void boton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boton1ActionPerformed
        clasificacionUi objetoClasificacion = new clasificacionUi(idTorneo);
        objetoClasificacion.setVisible(true);
    }//GEN-LAST:event_boton1ActionPerformed

    public static void ajustarTextoLabel(JLabel label, String texto) {
        // Inicializar el tamaño de fuente
        int fontSize = 30;
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);
        label.setFont(font);

        // Aplicar HTML para centrar el texto
        label.setText("<html><div style='text-align:justify; vertical-align:middle;'>" + texto + "</div></html>");

        // Obtener el tamaño del JLabel
        int labelWidth = label.getWidth();
        int labelHeight = label.getHeight();

        // Ajustar el tamaño de la fuente basado en la longitud del texto y el tamaño del JLabel
        // Usar una fórmula simple para calcular el tamaño de la fuente basado en la longitud
        int numCaracteres = texto.length();
        
        if (numCaracteres <= 50) {
            fontSize = 44;
        } else if (numCaracteres <= 100) {
            fontSize = 32;
        } else if (numCaracteres <= 150) {
            fontSize = 27;
        } else if (numCaracteres <= 200) {
            fontSize = 24;
        }else if (numCaracteres <= 300) {
            fontSize = 20;
        }else {
            fontSize = 16;
        }
        // Aplicar el tamaño de fuente calculado
        font = new Font("SansSerif", Font.PLAIN, fontSize);
        label.setFont(font);
    }
    
    public static void ajustarTextoTitulo(JLabel label, String texto) {
        // Inicializar el tamaño de fuente
        int fontSize = 50;
        Font font = new Font("SansSerif", Font.PLAIN, fontSize);
        label.setFont(font);

        // Aplicar HTML para centrar el texto
        label.setText("<html><div style='text-align:center; vertical-align:middle;'>" + texto + "</div></html>");

        // Obtener el tamaño del JLabel
        int labelWidth = label.getWidth();
        int labelHeight = label.getHeight();

        // Ajustar el tamaño de la fuente basado en la longitud del texto y el tamaño del JLabel
        // Usar una fórmula simple para calcular el tamaño de la fuente basado en la longitud
        int numCaracteres = texto.length();
        
        if (numCaracteres <= 50) {
            fontSize = 60;
        } else if (numCaracteres <= 100) {
            fontSize = 42;
        } else if (numCaracteres <= 150) {
            fontSize = 34;
        } else if (numCaracteres <= 200) {
            fontSize = 28;
        }else if (numCaracteres <= 300) {
            fontSize = 24;
        }else {
            fontSize = 20;
        }
        // Aplicar el tamaño de fuente calculado
        font = new Font("SansSerif", Font.BOLD, fontSize);
        label.setFont(font);
    }
    
    public void iniciarComunicacion() {
        // Listener para manejar los eventos del puerto serial
        SerialPortEventListener listener = new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent spe) {
                try {
                    if (ino.isMessageAvailable()) {
                        // Recibir el mensaje desde Arduino
                        String mensaje = ino.printMessage();
//                        reproductor = new clases.reproducirSonido();
                        // Limpiar cualquier imagen previa antes de asignar el nuevo GIF
                        labelAlerta.setIcon(null);
                        labelAlerta.revalidate();
                        labelAlerta.repaint();

                        // Variable para almacenar el icono
                        ImageIcon iconoGif = null;
                        // Verificar si la respuesta es correcta (idtiporespuesta = 1)
                        if (mensaje.equals("1")) {
                            iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/alertas/alerta-azul.gif"));
                            reproductor.cargarSonido("src/main/resources/sonidos/soundnotificacion.wav");
                            reproductor.reproducir();
                        }else if (mensaje.equals("2")) {
                            iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/alertas/alerta-verde.gif"));
                            reproductor.cargarSonido("src/main/resources/sonidos/soundnotificacion.wav");
                            reproductor.reproducir();
                            
                        }else if (mensaje.equals("3")) {
                            iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/alertas/alerta-amarillo.gif"));
                            reproductor.cargarSonido("src/main/resources/sonidos/soundnotificacion.wav");
                            reproductor.reproducir();
                            
                        }else if (mensaje.equals("4")) {
                            iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/alertas/alerta-rojo.gif"));
                            reproductor.cargarSonido("src/main/resources/sonidos/soundnotificacion.wav");
                            reproductor.reproducir();
                        }
                        // Asignar el nuevo GIF al JLabel
                        labelAlerta.setIcon(iconoGif);
                        labelAlerta.revalidate();
                        labelAlerta.repaint();
                        juego.botonPresionar(mensaje);
                    }
                } catch (SerialPortException | ArduinoException ex) {
                    ex.printStackTrace();
                }
            }
        };

        try {
            // Inicializar la comunicación con Arduino (puerto y baud rate)
            ino.arduinoRXTX("COM6", 9600, listener);
        } catch (ArduinoException ex) {
            ex.printStackTrace();
        }
    }
    
    private void enviarComandoReiniciar() throws SerialPortException {
        try {
            if (ino != null) {
                ino.sendData("R");  // Envía el comando 'R' para reiniciar
            }
        } catch (ArduinoException ex) {
            Logger.getLogger(juegoencurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void enviarComandoIncorrecto() throws SerialPortException {
        try {
            if (ino != null) {
                ino.sendData("I");  // Envía el comando 'R' para reiniciar
            }
        } catch (ArduinoException ex) {
            Logger.getLogger(juegoencurso.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new juegoencurso(20).setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private botones.boton boton1;
    private rojerusan.RSLabelImage btna;
    private rojerusan.RSLabelImage btnb;
    private rojerusan.RSLabelImage btnc;
    private rojerusan.RSLabelImage btnd;
    private botones.boton btnsiguiente;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel labelAlerta;
    private javax.swing.JLabel labelfondo;
    private javax.swing.JLabel labelopciona;
    private javax.swing.JLabel labelopcionb;
    private javax.swing.JLabel labelopcionc;
    private javax.swing.JLabel labelopciond;
    private javax.swing.JLabel labelpreguntas;
    private rojerusan.RSLabelImage lblimgpregunta;
    private efectos.MaterialShadow materialShadow1;
    private rojerusan.RSButtonPaneBeanInfo rSButtonPaneBeanInfo1;
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private efectos.Roboto roboto1;
    // End of variables declaration//GEN-END:variables
}

class reproducirSonido{
    private Clip clip;
     // Método para reproducir el sonido MP3
    public void cargarSonido(String ruta) {
        try {
            File archivoSonido = new File(ruta);
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivoSonido);
            clip = AudioSystem.getClip();
            clip.open(audio);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void reproducir(){
        if(clip != null){
            clip.setFramePosition(0);
            clip.start();
        }
    }
    public void detener(){
        if(clip != null && clip.isRunning()){
            clip.stop();
        }
    }
}