package clases;

import interfaces.clasificacionUi;
import interfaces.imagenespreguntas;
import interfaces.juegoencurso;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
    private int npreguntaRespondidas;
    public void botonPresionar(String mensaje) {
        // Aquí puedes procesar el mensaje como quieras
        System.out.println("Mensaje recibido en logicaJuego: " + mensaje);
        this.botonPresionado = mensaje;
        // Lógica adicional con el mensaje
    }
    
    public juegologica(JLabel labelpreguntas, JLabel labelopciona, JLabel labelopcionb, JLabel labelopcionc, JLabel labelopciond, JButton btnsiguiente, JLabel labelalerta, JLabel lblimgpregunta, int idTorneo, juegoencurso juegoencurso, JPanel grupoA, JPanel grupoB, JPanel grupoC, JPanel grupoD, JPanel panelClasificacion, JLabel nparticipantes1, JLabel nparticipantes2, JLabel nparticipantes3, JLabel nparticipantes4, JLabel imgpar1, JLabel imgpar2, JLabel imgpar3, JLabel imgpar4, JLabel npuntos1, JLabel npuntos2, JLabel npuntos3, JLabel npuntos4, JLabel n1, JLabel n2, JLabel n3, JLabel n4, JLabel txtTitulo, JLabel nactual, JLabel nglobal) {
        
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
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopciona, labelalerta, idTorneo, botonPresionado, grupoA, panelClasificacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        labelopcionb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopcionb, labelalerta, idTorneo, botonPresionado, grupoB, panelClasificacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        labelopcionc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopcionc, labelalerta, idTorneo, botonPresionado, grupoC, panelClasificacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        labelopciond.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopciond, labelalerta, idTorneo, botonPresionado, grupoD, panelClasificacion);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
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

        // Consulta para obtener las preguntas
        String sqlPreguntas = "SELECT id, pregunta, imagen "
                            + "FROM preguntas p "
                            + "WHERE p.idgrado = ? AND p.idasignatura = ? "
                            + "AND NOT EXISTS (SELECT 1 FROM preguntas_respondidas pr "
                            + "WHERE pr.idtorneo = ? AND pr.idpregunta = p.id)";

        // Consulta para obtener las respuestas asociadas a una pregunta
        String sqlRespuestas = "SELECT idpregunta, respuesta, idtiporespuesta, imagen "
                             + "FROM respuestas "
                             + "WHERE idpregunta = ?";

        try {
            conexionbd objetoConexion = new conexionbd();
            PreparedStatement psPreguntas = objetoConexion.establecerConexion().prepareStatement(sqlPreguntas);
            psPreguntas.setInt(1, 2); // Parámetro del grado
            psPreguntas.setInt(2, 3); // Parámetro de la asignatura
            psPreguntas.setInt(3, idTorneo); // Excluir las preguntas ya respondidas en este torneo
            ResultSet rsPreguntas = psPreguntas.executeQuery();

            while (rsPreguntas.next()) {
                int idPregunta = rsPreguntas.getInt("id");
                String textoPregunta = rsPreguntas.getString("pregunta");
                String rutaImagenPregunta = rsPreguntas.getString("imagen");
                // Obtener las respuestas para la pregunta actual
                PreparedStatement psRespuestas = objetoConexion.establecerConexion().prepareStatement(sqlRespuestas);
                psRespuestas.setInt(1, idPregunta);
                ResultSet rsRespuestas = psRespuestas.executeQuery();

                List<String> opciones = new ArrayList<>();
                List<Integer> idTipoRespuestas = new ArrayList<>();
                String vimagen = "";
                while (rsRespuestas.next()) {
                    String respuesta;
                    String vRespuesta = rsRespuestas.getString("respuesta");
                    if(!vRespuesta.trim().isEmpty()){
                        respuesta = rsRespuestas.getString("respuesta");
                        vimagen = "0";
                    }else{
                        respuesta = rsRespuestas.getString("imagen");
                        vimagen = "1";
                    }
                    opciones.add(respuesta);
                     // Agregar el idtiporespuesta a la lista
                    int idTipoRespuesta = rsRespuestas.getInt("idtiporespuesta");
                    idTipoRespuestas.add(idTipoRespuesta);
                }
                opciones.add(vimagen);
                // Asegurarse de tener exactamente 4 opciones, llenando con opciones vacías si es necesario
                while (opciones.size() < 4) {
                    opciones.add("");
                     idTipoRespuestas.add(0);
                }

                // Crear el objeto Pregunta con las respuestas
                Pregunta pregunta = new Pregunta(idPregunta, textoPregunta, rutaImagenPregunta, opciones.get(0), opciones.get(1), opciones.get(2), opciones.get(3), opciones.get(4), idTipoRespuestas.get(0), idTipoRespuestas.get(1), idTipoRespuestas.get(2), idTipoRespuestas.get(3));
                preguntas.add(pregunta);
            }

            if (preguntas.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay más preguntas disponibles.");
            } else {
                // Barajar las preguntas (opcional)
                Collections.shuffle(preguntas);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar las preguntas: " + e.getMessage());
        }

        return preguntas;
    }

    // Método para mostrar la siguiente pregunta
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
                System.out.println(npreguntaRespondidas + " "+nPreguntasTorneo);
                if(npreguntaRespondidas<nPreguntasTorneo){
                    Pregunta preguntaActual = listaPreguntas.get(indicePreguntaActual);
                    // Mostrar la pregunta y las opciones en los JLabels
                    labelpreguntas.setText(preguntaActual.getTexto());
                    labelopciona.setText(preguntaActual.getOpcionA());
                    labelopcionb.setText(preguntaActual.getOpcionB());
                    labelopcionc.setText(preguntaActual.getOpcionC());
                    labelopciond.setText(preguntaActual.getOpcionD());
                    ajustarTextoTitulo(labelpreguntas, preguntaActual.getTexto());
                    String rutaImagenPregunta = "";
                    ImageIcon iconoPregunta = null;
                    if(!preguntaActual.getImagenPregunta().isEmpty()){
                        rutaImagenPregunta = "src/main/resources"+preguntaActual.getImagenPregunta();
                        iconoPregunta = new ImageIcon(rutaImagenPregunta);
                    }else{
                        rutaImagenPregunta = "src/main/resources/interfaces/logo.png";
                        iconoPregunta = new ImageIcon(rutaImagenPregunta);
                    }
                    lblimgpregunta.setIcon(iconoPregunta);
                    System.out.println("Imagen pregunta: "+preguntaActual.getImagenPregunta());
                     // Eliminar MouseListeners anteriores
                    for (MouseListener ml : lblimgpregunta.getMouseListeners()) {
                        lblimgpregunta.removeMouseListener(ml);
                    }
                    lblimgpregunta.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            // Crear una instancia de imagenespreguntas con la ruta de la imagen
                            String rutaImagenPregunta = "src/main/resources" + preguntaActual.getImagenPregunta();
                            imagenespreguntas ventanaImagen = new imagenespreguntas(rutaImagenPregunta);
                            ventanaImagen.setVisible(true); // Mostrar la ventana
                        }
                    });
                    // Limpiar los textos
                    labelopciona.setText(null);
                    labelopcionb.setText(null);
                    labelopcionc.setText(null);
                    labelopciond.setText(null);

                    labelopciona.setIcon(null);
                    labelopcionb.setIcon(null);
                    labelopcionc.setIcon(null);
                    labelopciond.setIcon(null);

                    if(preguntaActual.getVImagen().equals("0")) {

                        ajustarTextoLabel(labelopciona, preguntaActual.getOpcionA());
                        ajustarTextoLabel(labelopcionb, preguntaActual.getOpcionB());
                        ajustarTextoLabel(labelopcionc, preguntaActual.getOpcionC());
                        ajustarTextoLabel(labelopciond, preguntaActual.getOpcionD());

                    }else{
                        // Caso con imágenes
                        ajustarImagenEnLabel(labelopciona, "src/main/resources" + preguntaActual.getOpcionA());
                        ajustarImagenEnLabel(labelopcionb, "src/main/resources" + preguntaActual.getOpcionB());
                        ajustarImagenEnLabel(labelopcionc, "src/main/resources" + preguntaActual.getOpcionC());
                        ajustarImagenEnLabel(labelopciond, "src/main/resources" + preguntaActual.getOpcionD());

                    }
                    labelopciona.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaA());
                    labelopcionb.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaB());
                    labelopcionc.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaC());
                    labelopciond.putClientProperty("idtiporespuesta", preguntaActual.getIdTipoRespuestaD());
                    // Registrar la pregunta como respondida en la tabla preguntas_respondidas
                    //registrarPreguntaRespondida(idTorneo, preguntaActual.getId());

                    // Incrementar el índice para la próxima pregunta
                    nactual.setText("" + (npreguntaRespondidas + 1));
                    npreguntaRespondidas++;
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

            if (idTipoRespuestaSeleccionada != null && idTipoRespuestaSeleccionada.equals(1)) {
                // Respuesta correcta
                intentosIncorrectos = 0; // Reiniciar los intentos si la respuesta es correcta
                double NuevoPuntaje = puntajeActual + 1.0f; 
                actualizarPuntaje(idTorneo, idcolor, NuevoPuntaje);
                iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/fondos/animacion_correcto.gif"));
                reproductor.cargarSonido("src/main/resources/sonidos/soundcorrecta.wav");
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
                iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/fondos/animacion_incorrecto_3.gif"));
                reproductor.cargarSonido("src/main/resources/sonidos/soundincorrecta.wav");
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
                panelClasificacion.setVisible(true);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

}

class Pregunta {
    private int id;
    private String texto, imagenPregunta;
    private String opcionA, opcionB, opcionC, opcionD, vimagen;
    private int idTipoRespuestaA;
    private int idTipoRespuestaB;
    private int idTipoRespuestaC;
    private int idTipoRespuestaD;

    // Constructor de la clase
    public Pregunta(int id, String texto, String imagenPregunta, String opcionA, String opcionB, String opcionC, String opcionD, String vimagen, 
                    int idTipoRespuestaA, int idTipoRespuestaB, int idTipoRespuestaC, int idTipoRespuestaD) {
        this.id = id;
        this.texto = texto;
        this.imagenPregunta = imagenPregunta;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
        this.vimagen = vimagen;
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
    
    public String getImagenPregunta() {
        return imagenPregunta;
    }

    public String getOpcionA() {
        return opcionA;
    }

    public String getOpcionB() {
        return opcionB;
    }

    public String getOpcionC() {
        return opcionC;
    }

    public String getOpcionD() {
        return opcionD;
    }

    public String getVImagen() {
        return vimagen;
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