package clases;

import interfaces.imagenespreguntas;
import interfaces.juegoencurso;
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
public class juegologica {
    private List<Pregunta> listaPreguntas;
    private int indicePreguntaActual = 0;
    
    // Referencias a los componentes existentes en tu interfaz
    private JLabel labelpreguntas;
    private JLabel labelopciona, labelopcionb, labelopcionc, labelopciond, labelalerta, lblimgpregunta;
    private JButton btnsiguiente;
    private reproducirSonido reproductor;
    private String botonPresionado;
    private juegoencurso juegoencurso;
    
    public void botonPresionar(String mensaje) {
        // Aquí puedes procesar el mensaje como quieras
        System.out.println("Mensaje recibido en logicaJuego: " + mensaje);
         this.botonPresionado = mensaje;
        // Lógica adicional con el mensaje
    }
    
    public juegologica(JLabel labelpreguntas, JLabel labelopciona, JLabel labelopcionb, JLabel labelopcionc, JLabel labelopciond, JButton btnsiguiente, JLabel labelalerta, JLabel lblimgpregunta, int idTorneo, juegoencurso juegoencurso) {
        
        this.labelpreguntas = labelpreguntas;
        this.labelopciona = labelopciona;
        this.labelopcionb = labelopcionb;
        this.labelopcionc = labelopcionc;
        this.labelopciond = labelopciond;
        this.labelalerta = labelalerta;
        this.lblimgpregunta = lblimgpregunta;
        this.btnsiguiente = btnsiguiente;
        this.botonPresionado = "0";
        this.juegoencurso = juegoencurso;
        // Inicializar la lista de preguntas desde la base de datos
        listaPreguntas = cargarPreguntasDesdeBD(idTorneo);
        // Mostrar la primera pregunta
        mostrarSiguientePregunta(idTorneo);

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
                    verificarRespuesta(labelopciona, labelalerta, idTorneo, botonPresionado);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        labelopcionb.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopcionb, labelalerta, idTorneo, botonPresionado);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        labelopcionc.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopcionc, labelalerta, idTorneo, botonPresionado);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        labelopciond.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (botonPresionado != "0") {
                    verificarRespuesta(labelopciond, labelalerta, idTorneo, botonPresionado);
                }else{
                    JOptionPane.showMessageDialog(null, "Ningún botón ha sido presionado.", "Alerta", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
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
        if (indicePreguntaActual < listaPreguntas.size()) {
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
            registrarPreguntaRespondida(idTorneo, preguntaActual.getId());

            // Incrementar el índice para la próxima pregunta
            indicePreguntaActual++;
        } else {
            JOptionPane.showMessageDialog(null, "No hay más preguntas disponibles.");
        }
    }

    // Método para registrar la pregunta respondida
    public void registrarPreguntaRespondida(int idTorneo, int idPregunta) {
        String sql = "INSERT INTO preguntas_respondidas (idtorneo, idpregunta) VALUES (?, ?)";

        try {
            conexionbd objetoConexion = new conexionbd();
            PreparedStatement ps = objetoConexion.establecerConexion().prepareStatement(sql);
            ps.setInt(1, idTorneo);
            ps.setInt(2, idPregunta);
            ps.executeUpdate();

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
    
    public void verificarRespuesta(JLabel labelSeleccionado, JLabel lblAlerta, int idTorneo, String idcolor) {
        // Obtener el idtiporespuesta almacenado en el JLabel clicado
        System.out.println("clases.juegologica.verificarRespuesta()");
        double puntajeActual = obtenerPuntajeActual(idTorneo, idcolor);
        Object idTipoRespuestaSeleccionada = labelSeleccionado.getClientProperty("idtiporespuesta");
        reproductor = new reproducirSonido();

        // Limpiar cualquier imagen previa antes de asignar el nuevo GIF
        lblAlerta.setIcon(null);
        lblAlerta.revalidate();
        lblAlerta.repaint();

        // Variable para almacenar el icono
        ImageIcon iconoGif = null;

        try {
            // Verificar si la respuesta es correcta (idtiporespuesta = 1)
            if (idTipoRespuestaSeleccionada != null && idTipoRespuestaSeleccionada.equals(1)) {
                // Forzar la lectura del GIF correcto desde el archivo
                double NuevoPuntaje = puntajeActual + 1.0f; 
                actualizarPuntaje(idTorneo, idcolor, NuevoPuntaje);
                iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/fondos/animacion_correcto.gif"));
                reproductor.cargarSonido("src/main/resources/sonidos/soundcorrecta.wav");
                reproductor.reproducir();
                this.botonPresionado = "0";
            } else {
                // Forzar la lectura del GIF incorrecto desde el archivo
                iconoGif = new ImageIcon(Toolkit.getDefaultToolkit().createImage("src/main/resources/fondos/animacion_incorrecto_3.gif"));
                double NuevoPuntaje = puntajeActual - 0.5f; 
                actualizarPuntaje(idTorneo, idcolor, NuevoPuntaje);
                reproductor.cargarSonido("src/main/resources/sonidos/soundincorrecta.wav");
                reproductor.reproducir();
                this.botonPresionado = "0";
                juegoencurso.enviarComandoIncorrecto();
            }

            // Asignar el nuevo GIF al JLabel
            lblAlerta.setIcon(iconoGif);
            lblAlerta.revalidate();
            lblAlerta.repaint();

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