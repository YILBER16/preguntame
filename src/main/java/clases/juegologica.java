package clases;

import interfaces.clasificacionUi;
import interfaces.imagenespreguntas;
import interfaces.juegoencurso;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
public class juegologica {
    private List<Pregunta> listaPreguntas;
    private int indicePreguntaActual = 0;
    
    // Referencias a los componentes existentes en tu interfaz
    private JLabel labelpreguntas;
    private JLabel labelopciona, labelopcionb, labelopcionc, labelopciond, labelalerta, lblimgpregunta, nparticipantes1, nparticipantes2, nparticipantes3, nparticipantes4, imgpar1, imgpar2, imgpar3, imgpar4, npuntos1, npuntos2, npuntos3, npuntos4, n1, n2, n3, n4, txtTitulo, nactual, nglobal;
    private JButton btnsiguiente;
    private reproducirSonido reproductor;
    private String botonPresionado;
    private juegoencurso juegoencurso;
    private int torneoID;
    private JPanel grupoA, grupoB, grupoC, grupoD, panelClasificacion;
    private String nombreTorneo;
    private int nPreguntasTorneo;
    private int npreguntaRespondidas, idasignatura, idgrado;
    public void botonPresionar(String mensaje) {
        // Aquí puedes procesar el mensaje como quieras
        System.out.println("Mensaje recibido en logicaJuego: " + mensaje);
        this.botonPresionado = mensaje;
        // Lógica adicional con el mensaje
    }
    
    public juegologica(JLabel labelpreguntas, JLabel labelopciona, JLabel labelopcionb, JLabel labelopcionc, JLabel labelopciond, JButton btnsiguiente, JLabel labelalerta, JLabel lblimgpregunta, int idTorneo, juegoencurso juegoencurso, JPanel grupoA, JPanel grupoB, JPanel grupoC, JPanel grupoD, JPanel panelClasificacion, JLabel nparticipantes1, JLabel nparticipantes2, JLabel nparticipantes3, JLabel nparticipantes4, JLabel imgpar1, JLabel imgpar2, JLabel imgpar3, JLabel imgpar4, JLabel npuntos1, JLabel npuntos2, JLabel npuntos3, JLabel npuntos4, JLabel n1, JLabel n2, JLabel n3, JLabel n4, JLabel txtTitulo, JLabel nactual, JLabel nglobal, int idasignatura,int idgrado) {
        
        this.labelpreguntas = labelpreguntas;
        this.labelopciona = labelopciona;
        this.labelopcionb = labelopcionb;
        this.labelopcionc = labelopcionc;
        this.labelopciond = labelopciond;
        this.labelalerta = labelalerta;
        this.lblimgpregunta = lblimgpregunta;
        this.btnsiguiente = btnsiguiente;
        this.grupoA = grupoA;
        this.grupoB = grupoB;
        this.grupoC = grupoC;
        this.grupoD = grupoD;
        this.panelClasificacion = panelClasificacion;
        this.nparticipantes1 = nparticipantes1;
        this.nparticipantes2 = nparticipantes2;
        this.nparticipantes3 = nparticipantes3;
        this.nparticipantes4 = nparticipantes4;
        this.imgpar1 = imgpar1;
        this.imgpar2 = imgpar2;
        this.imgpar3 = imgpar3;
        this.imgpar4 = imgpar4;
        this.npuntos1 = npuntos1;
        this.npuntos2 = npuntos2;
        this.npuntos3 = npuntos3;
        this.npuntos4 = npuntos4;
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
        this.nactual = nactual;
        this.nglobal = nglobal;
        this.txtTitulo = txtTitulo;
        this.torneoID = idTorneo;
        this.idasignatura = idasignatura;
        this.idgrado = idgrado;
        this.botonPresionado = "0";
        this.juegoencurso = juegoencurso;
        panelClasificacion.setVisible(false);

        // Asignar acción al botón Siguiente
        btnsiguiente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarSiguientePregunta(idTorneo);
            }
        });
        // Asignar acción a los JLabel que actúan como botones de opción
        
        labelopciona.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas seleccionar esta respuesta?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (!"0".equals(botonPresionado)) { // Usar equals() para comparar cadenas
                        verificarRespuesta(labelopciona, labelalerta, idTorneo, botonPresionado, grupoA, panelClasificacion);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        labelopcionb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas seleccionar esta respuesta?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (!"0".equals(botonPresionado)) {
                        verificarRespuesta(labelopcionb, labelalerta, idTorneo, botonPresionado, grupoB, panelClasificacion);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        labelopcionc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas seleccionar esta respuesta?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (!"0".equals(botonPresionado)) {
                        verificarRespuesta(labelopcionc, labelalerta, idTorneo, botonPresionado, grupoC, panelClasificacion);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        labelopciond.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "¿Estás seguro de que deseas seleccionar esta respuesta?", "Confirmación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    if (!"0".equals(botonPresionado)) {
                        verificarRespuesta(labelopciond, labelalerta, idTorneo, botonPresionado, grupoD, panelClasificacion);
                    } else {
                        JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        
        String sqlInformacion = "SELECT * "
                            + "FROM torneo t "
                            + "WHERE t.id = ?";
        try {
            conexionbd objetoConexion = new conexionbd();
            PreparedStatement psInformacion = objetoConexion.establecerConexion().prepareStatement(sqlInformacion);
            psInformacion.setInt(1, torneoID); // Parámetro del grado
            ResultSet rsInformacion = psInformacion.executeQuery();

            while (rsInformacion.next()) {
                txtTitulo.setText(rsInformacion.getString("nombre"));
                nPreguntasTorneo = rsInformacion.getInt("npreguntas");
                nglobal.setText(rsInformacion.getString("npreguntas"));
            }
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las preguntas: " + e.getMessage());
        }
        // Inicializar la lista de preguntas desde la base de datos
        listaPreguntas = cargarPreguntasDesdeBD(idTorneo);
        // Mostrar la primera pregunta
        mostrarSiguientePregunta(idTorneo);
    }
    // Método para cargar las preguntas y sus opciones desde la base de datos
    private List<Pregunta> cargarPreguntasDesdeBD(int idTorneo) {
        List<Pregunta> preguntas = new ArrayList<>();

        String sqlPreguntas = "SELECT id, pregunta, imagen FROM preguntas p "
                              + "WHERE p.idgrado = ? AND p.idasignatura = ? "
                              + "AND NOT EXISTS (SELECT 1 FROM preguntas_respondidas pr "
                              + "WHERE pr.idtorneo = ? AND pr.idpregunta = p.id)";

        String sqlRespuestas = "SELECT idpregunta, respuesta, idtiporespuesta, imagen "
                               + "FROM respuestas "
                               + "WHERE idpregunta = ?";

        try {
            conexionbd objetoConexion = new conexionbd();
            Connection conexion = objetoConexion.establecerConexion();

            PreparedStatement psPreguntas = conexion.prepareStatement(sqlPreguntas);
            psPreguntas.setInt(1, idgrado);
            psPreguntas.setInt(2, idasignatura);
            psPreguntas.setInt(3, idTorneo);
            ResultSet rsPreguntas = psPreguntas.executeQuery();

            while (rsPreguntas.next()) {
                int idPregunta = rsPreguntas.getInt("id");
                String textoPregunta = rsPreguntas.getString("pregunta");

                Blob blobImagenPregunta = rsPreguntas.getBlob("imagen");
                byte[] imagenPregunta = null;
                if (blobImagenPregunta != null) {
                    imagenPregunta = blobImagenPregunta.getBytes(1, (int) blobImagenPregunta.length());
                }

                // Obtener las respuestas para la pregunta actual
                PreparedStatement psRespuestas = conexion.prepareStatement(sqlRespuestas);
                psRespuestas.setInt(1, idPregunta);
                ResultSet rsRespuestas = psRespuestas.executeQuery();

                // Crear una lista para almacenar respuestas
                List<Respuesta> respuestas = new ArrayList<>();
                List<Integer> idTipoRespuestas = new ArrayList<>();
                while (rsRespuestas.next()) {
                    String textoRespuesta = rsRespuestas.getString("respuesta");
                    byte[] imagenRespuesta = rsRespuestas.getBytes("imagen");

                    if (imagenRespuesta != null && imagenRespuesta.length > 0) {
                        respuestas.add(new Respuesta(null, imagenRespuesta));
                    } else {
                        respuestas.add(new Respuesta(textoRespuesta, null));
                    }
                    int idTipoRespuesta = rsRespuestas.getInt("idtiporespuesta");
                    idTipoRespuestas.add(idTipoRespuesta);
                }

                // Asegurarse de que la lista tenga exactamente 4 respuestas
                while (respuestas.size() < 4) {
                    respuestas.add(new Respuesta("", null));
                }

                Pregunta pregunta = new Pregunta(
                    idPregunta, 
                    textoPregunta, 
                    respuestas, 
                    imagenPregunta,
                    idTipoRespuestas.get(0), idTipoRespuestas.get(1), idTipoRespuestas.get(2), idTipoRespuestas.get(3)
                );
                preguntas.add(pregunta);
            }

            if (preguntas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay más preguntas disponibles.");
            } else {
                Collections.shuffle(preguntas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las preguntas: " + e.getMessage());
        }

        return preguntas;
    }


    public void mostrarSiguientePregunta(int idTorneo) {
        grupoA.setVisible(true);
        grupoB.setVisible(true);
        grupoC.setVisible(true);
        grupoD.setVisible(true);

        String sqlPreguntasRespondidas = "SELECT COUNT(*) FROM preguntas_respondidas WHERE idtorneo = ?";
        try {
            conexionbd objetoConexion = new conexionbd();
            PreparedStatement psPreguntasRespondidas = objetoConexion.establecerConexion().prepareStatement(sqlPreguntasRespondidas);
            psPreguntasRespondidas.setInt(1, idTorneo);
            ResultSet rsPreguntasRespondidas = psPreguntasRespondidas.executeQuery();

            if (rsPreguntasRespondidas.next()) {
                npreguntaRespondidas = rsPreguntasRespondidas.getInt(1);
            }
            if (indicePreguntaActual < listaPreguntas.size()) {
                if(npreguntaRespondidas<nPreguntasTorneo){
                    Pregunta preguntaActual = listaPreguntas.get(indicePreguntaActual);

                    // Limpiar los JLabel y otros componentes
                    labelpreguntas.setText("");
                    lblimgpregunta.setIcon(null);
                    labelopciona.setIcon(null);
                    labelopcionb.setIcon(null);
                    labelopcionc.setIcon(null);
                    labelopciond.setIcon(null);
                    labelopciona.setText("");
                    labelopcionb.setText("");
                    labelopcionc.setText("");
                    labelopciond.setText("");

                    // Asegurarse de que no haya listeners de mouse anteriores
                    for (MouseListener ml : lblimgpregunta.getMouseListeners()) {
                        lblimgpregunta.removeMouseListener(ml);
                    }

                    // Mostrar la pregunta
                    labelpreguntas.setText(preguntaActual.getTexto());
                    ajustarTextoTitulo(labelpreguntas, preguntaActual.getTexto());

                    // Cargar y mostrar la imagen de la pregunta si existe
                    byte[] imagenPregunta = preguntaActual.getImagenPregunta();
                    mostrarImagen(lblimgpregunta, imagenPregunta, "/interfaces/logo.png");

                    // Configurar el listener para la imagen de la pregunta
                    lblimgpregunta.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // Crear una instancia de imagenespreguntas con el byte[] de la imagen
                            byte[] imagenPregunta = preguntaActual.getImagenPregunta();
                            imagenespreguntas ventanaImagen = new imagenespreguntas(imagenPregunta);
                            ventanaImagen.setVisible(true); // Mostrar la ventana
                        }
                    });

                    // Mostrar las opciones según si son imágenes o texto
                    List<Respuesta> respuestas = preguntaActual.getRespuestas();
                    mostrarOpcion(labelopciona, respuestas.size() > 0 ? respuestas.get(0) : null);
                    mostrarOpcion(labelopcionb, respuestas.size() > 1 ? respuestas.get(1) : null);
                    mostrarOpcion(labelopcionc, respuestas.size() > 2 ? respuestas.get(2) : null);
                    mostrarOpcion(labelopciond, respuestas.size() > 3 ? respuestas.get(3) : null);
                    labelopciona.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaA());
                    labelopcionb.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaB());
                    labelopcionc.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaC());
                    labelopciond.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaD());

                    nactual.setText("" + (npreguntaRespondidas + 1));
                    npreguntaRespondidas++;
                    // Incrementar el índice para la próxima pregunta
                    indicePreguntaActual++;
                    panelClasificacion.setVisible(false);
                }else {
                    JOptionPane.showMessageDialog(null, "Torneo terminado.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "No hay más preguntas disponibles.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al contar las preguntas respondidas: " + e.getMessage());
        }
    }


    private void mostrarOpcion(JLabel label, Respuesta respuesta) {
        if (respuesta == null) {
            label.setText("");
            label.setIcon(null);
        } else if (respuesta.getImagen() != null && respuesta.getImagen().length > 0) {
            mostrarImagen(label, respuesta.getImagen(), "/interfaces/logo.png");
        } else {
            ajustarTextoLabel(label, respuesta.getTexto());
        }
    }


    private void mostrarImagen(JLabel label, byte[] imagen, String imagenPorDefecto) {
        ImageIcon icono = null;
        if (imagen != null && imagen.length > 0) {
            try {
                InputStream inputStream = new ByteArrayInputStream(imagen);
                BufferedImage bufferedImage = ImageIO.read(inputStream);
                icono = new ImageIcon(bufferedImage);
            } catch (IOException e) {
                icono = new ImageIcon(getClass().getResource(imagenPorDefecto));
            }
        } else {
            icono = new ImageIcon(getClass().getResource(imagenPorDefecto));
        }
        label.setIcon(icono);
    }


    // Método para registrar la pregunta respondida
    public void registrarPreguntaRespondida(int idTorneo, int idPregunta) {
        // Verificar si la pregunta ya fue respondida en este torneo
        String sqlVerificar = "SELECT COUNT(*) FROM preguntas_respondidas WHERE idtorneo = ? AND idpregunta = ?";

        try {
            conexionbd objetoConexion = new conexionbd();
            PreparedStatement psVerificar = objetoConexion.establecerConexion().prepareStatement(sqlVerificar);
            psVerificar.setInt(1, idTorneo);
            psVerificar.setInt(2, idPregunta);
            ResultSet rsVerificar = psVerificar.executeQuery();

            // Si ya existe un registro, no se hace nada
            if (rsVerificar.next() && rsVerificar.getInt(1) > 0) {
                System.out.println("La pregunta ya fue respondida en este torneo, no se registrará nuevamente.");
            } else {
                // Si no existe, se inserta el registro
                String sqlInsertar = "INSERT INTO preguntas_respondidas (idtorneo, idpregunta) VALUES (?, ?)";
                PreparedStatement psInsertar = objetoConexion.establecerConexion().prepareStatement(sqlInsertar);
                psInsertar.setInt(1, idTorneo);
                psInsertar.setInt(2, idPregunta);
                psInsertar.executeUpdate();
                System.out.println("Pregunta registrada como respondida.");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al registrar la pregunta respondida: " + e.getMessage());
        }
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
    
    private void ajustarImagenEnLabel(JLabel label, String rutaImagen) {
        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        Image imagen = iconoOriginal.getImage();

        // Obtener las dimensiones del JLabel
        int labelAncho = label.getWidth();
        int labelAlto = label.getHeight();

        // Obtener las dimensiones de la imagen
        int imagenAncho = iconoOriginal.getIconWidth();
        int imagenAlto = iconoOriginal.getIconHeight();

        // Solo redimensionar si la imagen es más grande que el JLabel
        if (imagenAncho > labelAncho || imagenAlto > labelAlto) {
            // Escalar la imagen proporcionalmente
            float relacionAncho = (float) labelAncho / imagenAncho;
            float relacionAlto = (float) labelAlto / imagenAlto;
            float escala = Math.min(relacionAncho, relacionAlto);

            int nuevoAncho = Math.round(imagenAncho * escala);
            int nuevoAlto = Math.round(imagenAlto * escala);

            // Redimensionar la imagen
            Image imagenEscalada = imagen.getScaledInstance(nuevoAncho, nuevoAlto, Image.SCALE_SMOOTH);
            label.setIcon(new ImageIcon(imagenEscalada));
        } else {
            // Si la imagen es más pequeña que el JLabel, usar el tamaño original
            label.setIcon(iconoOriginal);
        }
    }
    
    // Definir un contador de intentos incorrectos y el ID de la pregunta actual
    private int intentosIncorrectos = 0;
    private int idPreguntaActual = -1; // -1 indica que no hay pregunta cargada aún

    public void verificarRespuesta(JLabel labelSeleccionado, JLabel lblAlerta, int idTorneo, String idcolor, JPanel grupo, JPanel panelClasificacion) {
        double puntajeActual = obtenerPuntajeActual(idTorneo, idcolor);
        Object idTipoRespuestaSeleccionada = labelSeleccionado.getClientProperty("idtiporespuesta");
        reproductor = new reproducirSonido();

        lblAlerta.setIcon(null);
        lblAlerta.revalidate();
        lblAlerta.repaint();

        ImageIcon iconoGif = null;
        Pregunta preguntaActual = listaPreguntas.get(indicePreguntaActual - 1); // Pregunta actual

        try {
            // Verificar si es una nueva pregunta
            if (idPreguntaActual != preguntaActual.getId()) {
                // Es una nueva pregunta, reiniciar los intentos
                idPreguntaActual = preguntaActual.getId();
                intentosIncorrectos = 0;
            }
            System.out.println("Respuesta: "+idTipoRespuestaSeleccionada);
            if (idTipoRespuestaSeleccionada != null && idTipoRespuestaSeleccionada.equals(1)) {
                // Respuesta correcta
                intentosIncorrectos = 0; // Reiniciar los intentos si la respuesta es correcta
                double NuevoPuntaje = puntajeActual + 1.0f; 
                actualizarPuntaje(idTorneo, idcolor, NuevoPuntaje);
                iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/fondos/animacion_correcto.gif")));
                
                reproductor.cargarSonido("/sonidos/soundcorrecta.wav");
                reproductor.reproducir();
                this.botonPresionado = "0";

                Timer timer = new Timer(4000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        consultarClasificacion(idTorneo, nparticipantes1, nparticipantes2, nparticipantes3, nparticipantes4, imgpar1, imgpar2, imgpar3, imgpar4, npuntos1, npuntos2, npuntos3, npuntos4, n1, n2, n3, n4);
                    }
                });
                timer.setRepeats(false); // Ejecutar solo una vez
                timer.start(); // Iniciar el temporizador

            } else {
                // Respuesta incorrecta
                intentosIncorrectos++; // Incrementar los intentos incorrectos
                double NuevoPuntaje = puntajeActual - 0.5f; 
                actualizarPuntaje(idTorneo, idcolor, NuevoPuntaje);
                iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage(getClass().getResource("/fondos/animacion_incorrecto_3.gif")));
                reproductor.cargarSonido("/sonidos/soundincorrecta.wav");
                reproductor.reproducir();
                this.botonPresionado = "0";
                juegoencurso.enviarComandoIncorrecto();

                // Si la pregunta ha sido respondida incorrectamente dos veces
                if (intentosIncorrectos >= 2) {
                    // Mostrar el panel de clasificación
                    Timer timer = new Timer(4000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            consultarClasificacion(idTorneo, nparticipantes1, nparticipantes2, nparticipantes3, nparticipantes4, imgpar1, imgpar2, imgpar3, imgpar4, npuntos1, npuntos2, npuntos3, npuntos4, n1, n2, n3, n4);
                        }
                    });
                    timer.setRepeats(false); // Ejecutar solo una vez
                    timer.start(); // Iniciar el temporizador
                }

                // Crear un Timer para ejecutar la acción después de 2 segundos
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        grupo.setVisible(false);
                    }
                });
                timer.setRepeats(false); // Ejecutar solo una vez
                timer.start(); // Iniciar el temporizador
            }

            // Mostrar la animación correspondiente
            lblAlerta.setIcon(iconoGif);
            lblAlerta.revalidate();
            lblAlerta.repaint();

            // Registrar la pregunta como respondida
            registrarPreguntaRespondida(idTorneo, preguntaActual.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

 
    public void actualizarPuntaje(int idTorneo, String idcolor, double nuevoPuntaje) {
        conexionbd objetoConexion = new conexionbd(); // Crear el objeto de la clase que maneja la conexión
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // Obtener la conexión desde el método de conexionbd
            conn = objetoConexion.establecerConexion();

            // Sentencia SQL para actualizar el puntaje
            String sql = "UPDATE torneoparticipantes SET puntaje = ? WHERE idtorneo = ? AND idcolor = ?";
            pstmt = conn.prepareStatement(sql);

            // Asignar los valores a la consulta
            pstmt.setDouble(1, nuevoPuntaje); // nuevo puntaje
            pstmt.setInt(2, idTorneo);     // idTorneo
            pstmt.setString(3, idcolor);   // idColor

            // Ejecutar la actualización
            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Puntaje actualizado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el PreparedStatement y la conexión
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    
    public double obtenerPuntajeActual(int idTorneo, String idcolor) {
        conexionbd objetoConexion = new conexionbd(); // Crear el objeto de la clase que maneja la conexión
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        double puntaje = 0.0;

        try {
            // Obtener la conexión desde el método de conexionbd
            conn = objetoConexion.establecerConexion();

            // Sentencia SQL para obtener el puntaje
            String sql = "SELECT puntaje FROM torneoparticipantes WHERE idtorneo = ? AND idcolor = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idTorneo);
            pstmt.setString(2, idcolor);

            // Ejecutar la consulta
            rs = pstmt.executeQuery();
            if (rs.next()) {
                puntaje = rs.getDouble("puntaje");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return puntaje;
    }
    
    public void consultarClasificacion(int idtorneo, JLabel nparticipantes1, JLabel nparticipantes2, JLabel nparticipantes3, JLabel nparticipantes4, JLabel imgpar1, JLabel imgpar2, JLabel imgpar3, JLabel imgpar4, JLabel npuntos1, JLabel npuntos2, JLabel npuntos3, JLabel npuntos4, JLabel n1, JLabel n2, JLabel n3, JLabel n4) {
        String sql = "SELECT tp.idtipoparticipante, tp.idequipo, tp.idparticipante, tp.puntaje, c.representacion " +
                     "FROM torneoparticipantes tp " +
                     "JOIN colores c ON c.id = tp.idcolor " +
                     "WHERE tp.idtorneo = ? " +
                     "ORDER BY tp.puntaje DESC";
        String color = "000000";
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
                    color = rs.getString("representacion");

                    if (idTipoParticipante == 2) { // Si es un equipo
                        String sqlEquipo = "SELECT e.equipo, p.foto " +
                                            "FROM participantes p " +
                                            "JOIN equipos e ON p.idequipo = e.id " +
                                            "WHERE e.id = ?";
                        String nombre = "";
                        try (PreparedStatement pstEquipo = conexion.prepareStatement(sqlEquipo)) {
                            pstEquipo.setInt(1, idEquipo);

                            try (ResultSet rsEquipo = pstEquipo.executeQuery()) {
                                List<Blob> blobs = new ArrayList<>();
                                while (rsEquipo.next()) {
                                    Blob blobImagen = rsEquipo.getBlob("foto");
                                    nombre = rsEquipo.getString("equipo");
                                    blobs.add(blobImagen);
                                }

                                ImageIcon imagenIcono = combineImages(blobs.toArray(new Blob[0]), 80, 80); // Redimensionar a 200x200px
                                if (imagenIcono == null) {
                                    System.out.println("Imágenes del equipo no se pudieron combinar.");
                                }

                                String nombreEquipo = "<html><font color='#" + color + "' face='Arial' size='6'>" + nombre + "</font></html>";
                                String puntajeText = "<html><div style='text-align: center;'>" +
                                                     "<font color='#" + color + "' face='Arial' size='6'>" +
                                                     puntaje + "<br> Puntos" +
                                                     "</font></div></html>";

                                switch (puesto) {
                                    case 1:
                                        n1.setForeground(Color.decode("#" + color));
                                        nparticipantes1.setText(nombreEquipo);
                                        imgpar1.setIcon(imagenIcono); // Mostrar imagen combinada en JLabel
                                        npuntos1.setText(puntajeText);
                                        break;
                                    case 2:
                                        n2.setForeground(Color.decode("#" + color));
                                        nparticipantes2.setText(nombreEquipo);
                                        imgpar2.setIcon(imagenIcono);
                                        npuntos2.setText(puntajeText);
                                        break;
                                    case 3:
                                        n3.setForeground(Color.decode("#" + color));
                                        nparticipantes3.setText(nombreEquipo);
                                        imgpar3.setIcon(imagenIcono);
                                        npuntos3.setText(puntajeText);
                                        break;
                                    case 4:
                                        n4.setForeground(Color.decode("#" + color));
                                        nparticipantes4.setText(nombreEquipo);
                                        imgpar4.setIcon(imagenIcono);
                                        npuntos4.setText(puntajeText);
                                        break;
                                }
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }else { // Si es un participante individual
                        String sqlParticipante = "SELECT CONCAT(p.nombres, ' ', p.apellidos) AS nombres, p.foto " +
                                                 "FROM participantes p " +
                                                 "WHERE p.id = ?";
                        try (PreparedStatement pstParticipante = conexion.prepareStatement(sqlParticipante)) {
                            pstParticipante.setInt(1, idParticipante);
                            try (ResultSet rsParticipante = pstParticipante.executeQuery()) {
                                String nombre;
                                ImageIcon imagenIcono = null;

                                if (rsParticipante.next()) {
                                    nombre = rsParticipante.getString("nombres");
                                    Blob blobImagen = rsParticipante.getBlob("foto");
                                    imagenIcono = blobToImageIcon(blobImagen, 80); // Redimensionar imagen a 80px de ancho
                                } else {
                                    nombre = "Participante Desconocido";
                                }

                                String htmlParticipante = String.format("<html><font color='#" + color + "' face='Arial' size='5'>%s</font><br></html>", nombre);
                                String puntajeText = "<html><div style='text-align: center;'>" +
                                                     "<font color='#" + color + "' face='Arial' size='6'>" +
                                                     puntaje + "<br> Puntos" +
                                                     "</font></div></html>";

                                switch (puesto) {
                                    case 1:
                                        n1.setForeground(Color.decode("#" + color));
                                        nparticipantes1.setText(htmlParticipante);
                                        imgpar1.setIcon(imagenIcono);
                                        npuntos1.setText(puntajeText);
                                        break;
                                    case 2:
                                        n2.setForeground(Color.decode("#" + color));
                                        nparticipantes2.setText(htmlParticipante);
                                        imgpar2.setIcon(imagenIcono);
                                        npuntos2.setText(puntajeText);
                                        break;
                                    case 3:
                                        n3.setForeground(Color.decode("#" + color));
                                        nparticipantes3.setText(htmlParticipante);
                                        imgpar3.setIcon(imagenIcono);
                                        npuntos3.setText(puntajeText);
                                        break;
                                    case 4:
                                        n4.setForeground(Color.decode("#" + color));
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
                        labelTexto.setText("<html><font color='#" + color + "' face='Arial' size='6'>---</font></html>");
                        if (labelImagen != null) {
                            labelImagen.setIcon(null); // Limpiar la imagen
                        }
                    }

                    puesto++;
                }
                if(nPreguntasTorneo == npreguntaRespondidas){
                    reproductor = new reproducirSonido();
                    reproductor.cargarSonido("/sonidos/victoria.wav");
                    reproductor.reproducir();
                    btnsiguiente.setVisible(false);
                }
                panelClasificacion.setVisible(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ImageIcon combineImages(Blob[] images, int imageWidth, int imageHeight) {
        try {
            BufferedImage[] resizedImages = new BufferedImage[images.length];
            int totalWidth = 0;
            int maxHeight = imageHeight; // La altura será constante

            // Redimensionar cada imagen y calcular el ancho total
            for (int i = 0; i < images.length; i++) {
                InputStream inputStream = images[i].getBinaryStream();
                BufferedImage originalImage = ImageIO.read(inputStream);
                BufferedImage resizedImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g2d = resizedImage.createGraphics();
                g2d.drawImage(originalImage.getScaledInstance(imageWidth, imageHeight, Image.SCALE_SMOOTH), 0, 0, null);
                g2d.dispose();
                resizedImages[i] = resizedImage;
                totalWidth += resizedImage.getWidth();
            }

            // Crear una imagen de salida
            BufferedImage combinedImage = new BufferedImage(totalWidth, maxHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = combinedImage.createGraphics();

            // Dibujar cada imagen en la imagen de salida de manera horizontal
            int xOffset = 0;
            for (BufferedImage img : resizedImages) {
                g.drawImage(img, xOffset, 0, null);
                xOffset += img.getWidth();
            }
            g.dispose();

            // Convertir BufferedImage a ImageIcon
            return new ImageIcon(combinedImage);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // Método auxiliar para convertir un Blob en ImageIcon y redimensionar la imagen
    private ImageIcon blobToImageIcon(Blob blob, int ancho) {
        if (blob == null) {
            return null;
        }
        try {
            InputStream inputStream = blob.getBinaryStream();
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            if (bufferedImage == null) {
                return null;
            }
            BufferedImage imagenRedimensionada = redimensionarImagen(bufferedImage, ancho);
            return new ImageIcon(imagenRedimensionada);
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }


    // Método para redimensionar la imagen manteniendo la proporción
    private BufferedImage redimensionarImagen(BufferedImage imagen, int ancho) {
        int alto = (int) Math.round(((double) ancho / imagen.getWidth()) * imagen.getHeight());
        Image imagenEscalada = imagen.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        BufferedImage imagenRedimensionada = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = imagenRedimensionada.createGraphics();
        // Configurar RenderingHints para mejorar la calidad del escalado
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(imagenEscalada, 0, 0, null);
        g2d.dispose();

        return imagenRedimensionada;
    }


    private String encodeToBase64(ImageIcon imageIcon) {
        if (imageIcon == null) {
            return "";
        }
        BufferedImage bufferedImage = (BufferedImage) imageIcon.getImage();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            return Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    
    


}

class Pregunta {
    private int id;
    private String texto;
    private List<Respuesta> respuestas;
    private byte[] imagen; // Imagen en formato byte[]
    private int idTipoRespuestaA;
    private int idTipoRespuestaB;
    private int idTipoRespuestaC;
    private int idTipoRespuestaD;
    
    // Constructor de la clase
    public Pregunta(int id, String texto, List<Respuesta> respuestas, byte[] imagen, int idTipoRespuestaA, int idTipoRespuestaB, int idTipoRespuestaC, int idTipoRespuestaD) {
        this.id = id;
        this.texto = texto;
        this.respuestas = respuestas;
        this.imagen = imagen;
        this.idTipoRespuestaA = idTipoRespuestaA;
        this.idTipoRespuestaB = idTipoRespuestaB;
        this.idTipoRespuestaC = idTipoRespuestaC;
        this.idTipoRespuestaD = idTipoRespuestaD;
    }

    // Métodos getter
    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
    }

    public List<Respuesta> getRespuestas() {
        return respuestas;
    }

    public byte[] getImagenPregunta() {
        return imagen;
    }
    public int getIdTipoRespuestaA() {
        return idTipoRespuestaA;
    }

    public int getIdTipoRespuestaB() {
        return idTipoRespuestaB;
    }

    public int getIdTipoRespuestaC() {
        return idTipoRespuestaC;
    }

    public int getIdTipoRespuestaD() {
        return idTipoRespuestaD;
    }
}

class Respuesta {
    private String texto;
    private byte[] imagen; // Imagen en formato byte[]

    // Constructor para respuestas con texto
    public Respuesta(String texto, byte[] imagen) {
        this.texto = texto;
        this.imagen = imagen;
    }

    // Métodos getter
    public String getTexto() {
        return texto;
    }

    public byte[] getImagen() {
        return imagen;
    }
}

class reproducirSonido{
    private Clip clip;
     // Método para reproducir el sonido MP3
    public void cargarSonido(String ruta) {
        try {
            // Usa getResource como URL, que funciona dentro de un .jar
            URL url = getClass().getResource(ruta);
            if (url == null) {
                throw new FileNotFoundException("No se encontró el recurso: " + ruta);
            }

            // Crear el AudioInputStream desde la URL
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            System.out.println("Sonido cargado correctamente.");
        } catch (UnsupportedAudioFileException e) {
            System.err.println("Formato de audio no soportado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (LineUnavailableException e) {
            System.err.println("Línea de audio no disponible: " + e.getMessage());
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