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
import javax.sound.sampled.FloatControl;
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
    private reproducirSonidoInfinito reproductorFondo;
    private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private juegologica juego;
    
    public juegoencurso(int idTorneo) {
        iniciarComunicacion();
        reproductor = new reproducirSonido();
        reproductorFondo = new reproducirSonidoInfinito();
        
        reproductorFondo.cargarSonido("src/main/resources/sonidos/fondo.wav");
        reproductorFondo.reproducir();
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
        
        juego = new juegologica(labelpreguntas, labelopciona, labelopcionb, labelopcionc, labelopciond, btnsiguiente, labelAlerta, lblimgpregunta, idTorneo, this, grupoA, grupoB, grupoC, grupoD, panelClasificacion, nparticipantes1, nparticipantes2, nparticipantes3, nparticipantes4, imgpar1, imgpar2, imgpar3, imgpar4, npuntos1, npuntos2, npuntos3, npuntos4, n1, n2, n3, n4, txtTitulo, nactual, nglobal);
        vVolumen.addChangeListener(e -> {
            int valorSlider = vVolumen.getValue();
            float volumen = valorSlider / 100f; // Convertir de 0-100 a 0.0-1.0
            reproductorFondo.ajustarVolumen(volumen);
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        roboto1 = new efectos.Roboto();
        materialShadow1 = new efectos.MaterialShadow();
        rSButtonPaneBeanInfo1 = new rojerusan.RSButtonPaneBeanInfo();
        jPanel6 = new javax.swing.JPanel();
        panelClasificacion = new javax.swing.JPanel();
        rSLabelImage11 = new rojerusan.RSLabelImage();
        rSLabelImage12 = new rojerusan.RSLabelImage();
        btnsiguiente = new botones.boton();
        imgpar4 = new javax.swing.JLabel();
        npuntos4 = new javax.swing.JLabel();
        nparticipantes4 = new javax.swing.JLabel();
        n4 = new javax.swing.JLabel();
        rSLabelImage7 = new rojerusan.RSLabelImage();
        imgpar3 = new javax.swing.JLabel();
        npuntos3 = new javax.swing.JLabel();
        nparticipantes3 = new javax.swing.JLabel();
        n3 = new javax.swing.JLabel();
        rSLabelImage5 = new rojerusan.RSLabelImage();
        imgpar2 = new javax.swing.JLabel();
        npuntos2 = new javax.swing.JLabel();
        nparticipantes2 = new javax.swing.JLabel();
        n2 = new javax.swing.JLabel();
        rSLabelImage4 = new rojerusan.RSLabelImage();
        imgpar1 = new javax.swing.JLabel();
        npuntos1 = new javax.swing.JLabel();
        nparticipantes1 = new javax.swing.JLabel();
        n1 = new javax.swing.JLabel();
        rSLabelImage2 = new rojerusan.RSLabelImage();
        labelfondo1 = new rojerusan.RSLabelImage();
        fondoClasificacion = new javax.swing.JLabel();
        vVolumen = new javax.swing.JSlider();
        labelAlerta = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtTitulo = new javax.swing.JLabel();
        nglobal = new javax.swing.JLabel();
        nactual1 = new javax.swing.JLabel();
        nactual = new javax.swing.JLabel();
        rSLabelImage13 = new rojerusan.RSLabelImage();
        rSLabelImage10 = new rojerusan.RSLabelImage();
        rSLabelImage3 = new rojerusan.RSLabelImage();
        grupoD = new javax.swing.JPanel();
        labelopciond = new javax.swing.JLabel();
        labelD = new javax.swing.JLabel();
        rSLabelImage9 = new rojerusan.RSLabelImage();
        btnd = new rojerusan.RSLabelImage();
        grupoC = new javax.swing.JPanel();
        labelopcionc = new javax.swing.JLabel();
        labelC = new javax.swing.JLabel();
        rSLabelImage8 = new rojerusan.RSLabelImage();
        btnc = new rojerusan.RSLabelImage();
        grupoB = new javax.swing.JPanel();
        labelopcionb = new javax.swing.JLabel();
        labelB = new javax.swing.JLabel();
        rSLabelImage6 = new rojerusan.RSLabelImage();
        btnb = new rojerusan.RSLabelImage();
        grupoA = new javax.swing.JPanel();
        labelopciona = new javax.swing.JLabel();
        labelA = new javax.swing.JLabel();
        fondoLabelA = new rojerusan.RSLabelImage();
        btna = new rojerusan.RSLabelImage();
        labelpreguntas = new javax.swing.JLabel();
        lblimgpregunta = new rojerusan.RSLabelImage();
        rSLabelImage1 = new rojerusan.RSLabelImage();
        labelfondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelClasificacion.setOpaque(false);
        panelClasificacion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        rSLabelImage11.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelImage11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/COLCARMEN.png"))); // NOI18N
        panelClasificacion.add(rSLabelImage11, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 10, 200, 210));

        rSLabelImage12.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelImage12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/LOGO.jpg"))); // NOI18N
        panelClasificacion.add(rSLabelImage12, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 420, 170));

        btnsiguiente.setForeground(new java.awt.Color(255, 51, 51));
        btnsiguiente.setText("Siguiente pregunta");
        btnsiguiente.setToolTipText("");
        btnsiguiente.setFont(new java.awt.Font("Questions", 1, 36)); // NOI18N
        btnsiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsiguienteActionPerformed(evt);
            }
        });
        panelClasificacion.add(btnsiguiente, new org.netbeans.lib.awtextra.AbsoluteConstraints(1200, 730, 310, 60));
        panelClasificacion.add(imgpar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 690, 350, 80));

        npuntos4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(npuntos4, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 650, 90, 120));

        nparticipantes4.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(nparticipantes4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 650, 350, 40));

        n4.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n4.setForeground(new java.awt.Color(255, 51, 0));
        n4.setText("4");
        panelClasificacion.add(n4, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 680, 30, 60));

        rSLabelImage7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        panelClasificacion.add(rSLabelImage7, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 630, 530, 160));

        imgpar3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(imgpar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 540, 350, 80));

        npuntos3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(npuntos3, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 500, 90, 120));

        nparticipantes3.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(nparticipantes3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 500, 350, 40));

        n3.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n3.setForeground(new java.awt.Color(255, 51, 0));
        n3.setText("3");
        panelClasificacion.add(n3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 530, 30, 60));

        rSLabelImage5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        panelClasificacion.add(rSLabelImage5, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 480, 530, 160));

        imgpar2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(imgpar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 390, 350, 80));

        npuntos2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(npuntos2, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 350, 90, 120));

        nparticipantes2.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(nparticipantes2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 350, 350, 40));

        n2.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n2.setForeground(new java.awt.Color(255, 51, 0));
        n2.setText("2");
        panelClasificacion.add(n2, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 380, 30, 60));

        rSLabelImage4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        panelClasificacion.add(rSLabelImage4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 330, 530, 160));

        imgpar1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(imgpar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 240, 350, 80));

        npuntos1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(npuntos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 90, 110));

        nparticipantes1.setFont(new java.awt.Font("Arial Rounded MT Bold", 3, 12)); // NOI18N
        nparticipantes1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panelClasificacion.add(nparticipantes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 350, 40));

        n1.setFont(new java.awt.Font("Cooper Black", 0, 40)); // NOI18N
        n1.setForeground(new java.awt.Color(255, 51, 0));
        n1.setText("1");
        panelClasificacion.add(n1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 230, 30, 60));

        rSLabelImage2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/itemclasificacion_1.png"))); // NOI18N
        panelClasificacion.add(rSLabelImage2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, 530, 150));

        labelfondo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/clasificacion2_1.png"))); // NOI18N
        panelClasificacion.add(labelfondo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 0, 570, 800));

        fondoClasificacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/fondo-clasificacion2.gif"))); // NOI18N
        fondoClasificacion.setText("jLabel1");
        panelClasificacion.add(fondoClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        jPanel6.add(panelClasificacion, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        vVolumen.setForeground(new java.awt.Color(255, 51, 51));
        vVolumen.setToolTipText("");
        jPanel6.add(vVolumen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 760, 150, -1));
        jPanel6.add(labelAlerta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1540, 800));

        jLabel1.setFont(new java.awt.Font("Questions", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Volumen");
        jPanel6.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 750, 110, -1));

        txtTitulo.setFont(new java.awt.Font("Segoe UI", 3, 40)); // NOI18N
        txtTitulo.setForeground(new java.awt.Color(153, 153, 153));
        txtTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtTitulo.setText("rsInformacion.getString(\"nombre\")");
        jPanel6.add(txtTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 690, 1110, 60));

        nglobal.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        nglobal.setForeground(new java.awt.Color(255, 51, 51));
        nglobal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nglobal.setText("n");
        jPanel6.add(nglobal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1460, 20, 60, 30));

        nactual1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        nactual1.setForeground(new java.awt.Color(255, 51, 51));
        nactual1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nactual1.setText("/");
        jPanel6.add(nactual1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 20, 20, 30));

        nactual.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        nactual.setForeground(new java.awt.Color(255, 51, 51));
        nactual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nactual.setText("n");
        jPanel6.add(nactual, new org.netbeans.lib.awtextra.AbsoluteConstraints(1380, 20, 60, 30));

        rSLabelImage13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        jPanel6.add(rSLabelImage13, new org.netbeans.lib.awtextra.AbsoluteConstraints(1370, 10, 160, 50));

        rSLabelImage10.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelImage10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/COLCARMEN.png"))); // NOI18N
        jPanel6.add(rSLabelImage10, new org.netbeans.lib.awtextra.AbsoluteConstraints(1390, 660, 130, 130));

        rSLabelImage3.setForeground(new java.awt.Color(255, 255, 255));
        rSLabelImage3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/LOGO.jpg"))); // NOI18N
        jPanel6.add(rSLabelImage3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 690, 230, 100));

        grupoD.setOpaque(false);
        grupoD.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelopciond.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        grupoD.add(labelopciond, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 470, 180));

        labelD.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        labelD.setForeground(new java.awt.Color(255, 255, 255));
        labelD.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelD.setText("D");
        grupoD.add(labelD, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 60, 140));

        rSLabelImage9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber4.png"))); // NOI18N
        grupoD.add(rSLabelImage9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 200));

        btnd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        grupoD.add(btnd, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 560, 200));

        jPanel6.add(grupoD, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 480, 660, 200));

        grupoC.setOpaque(false);
        grupoC.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelopcionc.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        grupoC.add(labelopcionc, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 460, 180));

        labelC.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        labelC.setForeground(new java.awt.Color(255, 255, 255));
        labelC.setText("C");
        grupoC.add(labelC, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 60, 150));

        rSLabelImage8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber3.png"))); // NOI18N
        grupoC.add(rSLabelImage8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 150, 200));

        btnc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        grupoC.add(btnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 550, 200));

        jPanel6.add(grupoC, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 660, 200));

        grupoB.setOpaque(false);
        grupoB.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelopcionb.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        grupoB.add(labelopcionb, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 460, 180));

        labelB.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        labelB.setForeground(new java.awt.Color(255, 255, 255));
        labelB.setText("B");
        grupoB.add(labelB, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 60, 140));
        labelB.getAccessibleContext().setAccessibleParent(labelB);

        rSLabelImage6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber2.png"))); // NOI18N
        grupoB.add(rSLabelImage6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 200));

        btnb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        grupoB.add(btnb, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 0, 560, 200));

        jPanel6.add(grupoB, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 260, 660, 200));

        grupoA.setOpaque(false);
        grupoA.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labelopciona.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        labelopciona.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelopciona.setToolTipText("");
        labelopciona.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelopcionaMouseEntered(evt);
            }
        });
        grupoA.add(labelopciona, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 450, 180));

        labelA.setFont(new java.awt.Font("Questions", 2, 100)); // NOI18N
        labelA.setForeground(new java.awt.Color(255, 255, 255));
        labelA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelA.setText("A");
        grupoA.add(labelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 70, 120));

        fondoLabelA.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonnumber.png"))); // NOI18N
        grupoA.add(fondoLabelA, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 150, 200));

        btna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/buttonbase.png"))); // NOI18N
        btna.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnaMouseEntered(evt);
            }
        });
        grupoA.add(btna, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 560, 200));

        jPanel6.add(grupoA, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 250, 650, 220));

        labelpreguntas.setFont(new java.awt.Font("Segoe UI", 2, 48)); // NOI18N
        labelpreguntas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelpreguntas.setText("Cual es la longitud de la tierra?");
        jPanel6.add(labelpreguntas, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 70, 1130, 150));

        lblimgpregunta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/logo.png"))); // NOI18N
        lblimgpregunta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblimgpreguntaMouseClicked(evt);
            }
        });
        jPanel6.add(lblimgpregunta, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, -1, -1));

        rSLabelImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/panelquestion.png"))); // NOI18N
        jPanel6.add(rSLabelImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 1290, 170));

        labelfondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interfaces/gif3.gif"))); // NOI18N
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

    private void lblimgpreguntaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblimgpreguntaMouseClicked
    }//GEN-LAST:event_lblimgpreguntaMouseClicked

    private void btnsiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsiguienteActionPerformed
       
    }//GEN-LAST:event_btnsiguienteActionPerformed

    public void siguientePregunta(int id){
        juego.mostrarSiguientePregunta(id);
    }
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
                //new juegoencurso(20).setVisible(true); 
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSLabelImage btna;
    private rojerusan.RSLabelImage btnb;
    private rojerusan.RSLabelImage btnc;
    private rojerusan.RSLabelImage btnd;
    private botones.boton btnsiguiente;
    private javax.swing.JLabel fondoClasificacion;
    private rojerusan.RSLabelImage fondoLabelA;
    private javax.swing.JPanel grupoA;
    private javax.swing.JPanel grupoB;
    private javax.swing.JPanel grupoC;
    private javax.swing.JPanel grupoD;
    private javax.swing.JLabel imgpar1;
    private javax.swing.JLabel imgpar2;
    private javax.swing.JLabel imgpar3;
    private javax.swing.JLabel imgpar4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel labelA;
    private javax.swing.JLabel labelAlerta;
    private javax.swing.JLabel labelB;
    private javax.swing.JLabel labelC;
    private javax.swing.JLabel labelD;
    private javax.swing.JLabel labelfondo;
    private rojerusan.RSLabelImage labelfondo1;
    private javax.swing.JLabel labelopciona;
    private javax.swing.JLabel labelopcionb;
    private javax.swing.JLabel labelopcionc;
    private javax.swing.JLabel labelopciond;
    private javax.swing.JLabel labelpreguntas;
    private rojerusan.RSLabelImage lblimgpregunta;
    private efectos.MaterialShadow materialShadow1;
    private javax.swing.JLabel n1;
    private javax.swing.JLabel n2;
    private javax.swing.JLabel n3;
    private javax.swing.JLabel n4;
    private javax.swing.JLabel nactual;
    private javax.swing.JLabel nactual1;
    private javax.swing.JLabel nglobal;
    private javax.swing.JLabel nparticipantes1;
    private javax.swing.JLabel nparticipantes2;
    private javax.swing.JLabel nparticipantes3;
    private javax.swing.JLabel nparticipantes4;
    private javax.swing.JLabel npuntos1;
    private javax.swing.JLabel npuntos2;
    private javax.swing.JLabel npuntos3;
    private javax.swing.JLabel npuntos4;
    private javax.swing.JPanel panelClasificacion;
    private rojerusan.RSButtonPaneBeanInfo rSButtonPaneBeanInfo1;
    private rojerusan.RSLabelImage rSLabelImage1;
    private rojerusan.RSLabelImage rSLabelImage10;
    private rojerusan.RSLabelImage rSLabelImage11;
    private rojerusan.RSLabelImage rSLabelImage12;
    private rojerusan.RSLabelImage rSLabelImage13;
    private rojerusan.RSLabelImage rSLabelImage2;
    private rojerusan.RSLabelImage rSLabelImage3;
    private rojerusan.RSLabelImage rSLabelImage4;
    private rojerusan.RSLabelImage rSLabelImage5;
    private rojerusan.RSLabelImage rSLabelImage6;
    private rojerusan.RSLabelImage rSLabelImage7;
    private rojerusan.RSLabelImage rSLabelImage8;
    private rojerusan.RSLabelImage rSLabelImage9;
    private efectos.Roboto roboto1;
    private javax.swing.JLabel txtTitulo;
    private javax.swing.JSlider vVolumen;
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
class reproducirSonidoInfinito {
    private Clip clip;
    private FloatControl controlVolumen;

    // Método para cargar el sonido
    public void cargarSonido(String ruta) {
        try {
            File archivoSonido = new File(ruta);
            AudioInputStream audio = AudioSystem.getAudioInputStream(archivoSonido);
            clip = AudioSystem.getClip();
            clip.open(audio);

            // Obtener el control de volumen
            controlVolumen = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para reproducir el sonido infinitamente
    public void reproducir() {
        if (clip != null) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY); // Repetir infinitamente
        }
    }

    // Método para detener el sonido
    public void detener() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

    // Método para ajustar el volumen (nivel entre 0.0 y 1.0)
    public void ajustarVolumen(float nivel) {
        if (controlVolumen != null) {
            float min = controlVolumen.getMinimum();  // Valor mínimo (generalmente negativo)
            float max = controlVolumen.getMaximum();  // Valor máximo (cerca de 0)

            // Mapear el valor del slider (0.0 - 1.0) a un rango logarítmico más suave
            float nuevoNivel = min + (max - min) * (float) Math.pow(nivel, 2);  // Suaviza la curva de volumen

            controlVolumen.setValue(nuevoNivel);
        }
    }
}