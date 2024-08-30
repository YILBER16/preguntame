package clases;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class juegologica {
    private List<Pregunta> listaPreguntas;
    private int indicePreguntaActual = 0;
    
    // Referencias a los componentes existentes en tu interfaz
    private JLabel labelpreguntas;
    private JLabel labelopciona, labelopcionb, labelopcionc, labelopciond;
    private JButton btnsiguiente;
    
    public juegologica(JLabel labelpreguntas, JLabel labelopciona, JLabel labelopcionb, JLabel labelopcionc, JLabel labelopciond, JButton btnsiguiente, int idTorneo) {
        this.labelpreguntas = labelpreguntas;
        this.labelopciona = labelopciona;
        this.labelopcionb = labelopcionb;
        this.labelopcionc = labelopcionc;
        this.labelopciond = labelopciond;
        this.btnsiguiente = btnsiguiente;

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
    }
    // Método para cargar las preguntas y sus opciones desde la base de datos
    private List<Pregunta> cargarPreguntasDesdeBD(int idTorneo) {
        List<Pregunta> preguntas = new ArrayList<>();

        // Consulta para obtener las preguntas
        String sqlPreguntas = "SELECT id, pregunta "
                            + "FROM preguntas p "
                            + "WHERE p.idgrado = ? AND p.idasignatura = ? "
                            + "AND NOT EXISTS (SELECT 1 FROM preguntas_respondidas pr "
                            + "WHERE pr.idtorneo = ? AND pr.idpregunta = p.id)";

        // Consulta para obtener las respuestas asociadas a una pregunta
        String sqlRespuestas = "SELECT idpregunta, respuesta, idtiporespuesta "
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

                // Obtener las respuestas para la pregunta actual
                PreparedStatement psRespuestas = objetoConexion.establecerConexion().prepareStatement(sqlRespuestas);
                psRespuestas.setInt(1, idPregunta);
                ResultSet rsRespuestas = psRespuestas.executeQuery();

                List<String> opciones = new ArrayList<>();
                while (rsRespuestas.next()) {
                    String respuesta = rsRespuestas.getString("respuesta");
                    opciones.add(respuesta);
                }

                // Asegurarse de tener exactamente 4 opciones, llenando con opciones vacías si es necesario
                while (opciones.size() < 4) {
                    opciones.add("");
                }

                // Crear el objeto Pregunta con las respuestas
                Pregunta pregunta = new Pregunta(idPregunta, textoPregunta, opciones.get(0), opciones.get(1), opciones.get(2), opciones.get(3));
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
            ajustarTextoLabel(labelopciona, preguntaActual.getOpcionA());
            ajustarTextoLabel(labelopcionb, preguntaActual.getOpcionB());
            ajustarTextoLabel(labelopcionc, preguntaActual.getOpcionC());
            ajustarTextoLabel(labelopciond, preguntaActual.getOpcionD());


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
}

class Pregunta {
    private int id;
    private String texto;
    private String opcionA, opcionB, opcionC, opcionD;

    public Pregunta(int id, String texto, String opcionA, String opcionB, String opcionC, String opcionD) {
        this.id = id;
        this.texto = texto;
        this.opcionA = opcionA;
        this.opcionB = opcionB;
        this.opcionC = opcionC;
        this.opcionD = opcionD;
    }

    public int getId() {
        return id;
    }

    public String getTexto() {
        return texto;
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
}
