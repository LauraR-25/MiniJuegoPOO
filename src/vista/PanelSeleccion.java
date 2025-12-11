package vista;

import javax.swing.*;
import java.awt.*;

public class PanelSeleccion extends JPanel {
    private JTextField txtJugador1;
    private JTextField txtJugador2;
    private JButton btnIniciar;

    public PanelSeleccion() {
        setLayout(null); // Usamos layout absoluto para control exacto de posiciones
        setBackground(Color.WHITE);

        // Título principal - Centrado arriba
        JLabel lblTitulo = new JLabel("CARRERA COCA-COLA vs PEPSI");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(0, 30, 800, 40);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblTitulo);

        // Jugador Coca-cola
        JLabel lblJugador1 = new JLabel("Jugador Coca-cola");
        lblJugador1.setFont(new Font("Arial", Font.BOLD, 18));
        lblJugador1.setForeground(Color.BLACK);
        lblJugador1.setBounds(100, 100, 600, 30);
        lblJugador1.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblJugador1);

        txtJugador1 = new JTextField("Laura");
        txtJugador1.setFont(new Font("Arial", Font.PLAIN, 16));
        txtJugador1.setBounds(100, 130, 600, 35);
        txtJugador1.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(txtJugador1);

        // Jugador Pepsi
        JLabel lblJugador2 = new JLabel("Jugador Pepsi");
        lblJugador2.setFont(new Font("Arial", Font.BOLD, 18));
        lblJugador2.setForeground(Color.BLACK);
        lblJugador2.setBounds(100, 180, 600, 30);
        lblJugador2.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblJugador2);

        txtJugador2 = new JTextField("Samuel");
        txtJugador2.setFont(new Font("Arial", Font.PLAIN, 16));
        txtJugador2.setBounds(100, 210, 600, 35);
        txtJugador2.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
        add(txtJugador2);

        // Línea separadora
        JSeparator separador1 = new JSeparator();
        separador1.setBounds(50, 270, 700, 2);
        separador1.setForeground(Color.LIGHT_GRAY);
        add(separador1);

        // Instrucciones (título)
        JLabel lblInstrucciones = new JLabel("Instrucciones");
        lblInstrucciones.setFont(new Font("Arial", Font.BOLD, 16));
        lblInstrucciones.setForeground(Color.BLACK);
        lblInstrucciones.setBounds(100, 290, 600, 25);
        add(lblInstrucciones);

        // Texto de instrucciones
        JTextArea txtInstrucciones = new JTextArea(
                "1. Cada jugador controla un bus\n" +
                        "2. Presiona tu botón lo más rápido posible\n" +
                        "3. El primero en llegar a la meta, gana"
        );
        txtInstrucciones.setFont(new Font("Arial", Font.PLAIN, 14));
        txtInstrucciones.setForeground(Color.BLACK);
        txtInstrucciones.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        txtInstrucciones.setEditable(false);
        txtInstrucciones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtInstrucciones.setBounds(100, 320, 600, 80);
        add(txtInstrucciones);

        // Controles (título)
        JLabel lblControles = new JLabel("Controles");
        lblControles.setFont(new Font("Arial", Font.BOLD, 16));
        lblControles.setForeground(Color.BLACK);
        lblControles.setBounds(100, 420, 600, 25);
        add(lblControles);

        // Texto de controles
        JTextArea txtControles = new JTextArea("Coca-cola: Botón rojo\nPepsi: Botón azul");
        txtControles.setFont(new Font("Arial", Font.PLAIN, 14));
        txtControles.setForeground(Color.BLACK);
        txtControles.setBackground(new Color(240, 240, 240)); // Fondo gris claro
        txtControles.setEditable(false);
        txtControles.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txtControles.setBounds(100, 450, 600, 50);
        add(txtControles);

        // Línea separadora 2
        JSeparator separador2 = new JSeparator();
        separador2.setBounds(50, 520, 700, 2);
        separador2.setForeground(Color.LIGHT_GRAY);
        add(separador2);

        // Coca-cola - Barra espaciadora
        JLabel lblCocaColaTeclado = new JLabel("Coca-cola");
        lblCocaColaTeclado.setFont(new Font("Arial", Font.BOLD, 16));
        lblCocaColaTeclado.setForeground(Color.BLACK);
        lblCocaColaTeclado.setBounds(100, 540, 600, 25);
        add(lblCocaColaTeclado);

        JLabel lblCocaInst = new JLabel("Botón del teclado: \"Barra espaciadora\"");
        lblCocaInst.setFont(new Font("Arial", Font.PLAIN, 14));
        lblCocaInst.setForeground(Color.BLACK);
        lblCocaInst.setBounds(100, 570, 600, 25);
        add(lblCocaInst);

        // Línea separadora 3 (opcional, según tu diseño)
        JSeparator separador3 = new JSeparator();
        separador3.setBounds(50, 610, 700, 2);
        separador3.setForeground(Color.LIGHT_GRAY);
        add(separador3);

        // Iniciar Carrera
        JLabel lblIniciarCarrera = new JLabel("Iniciar Carrera");
        lblIniciarCarrera.setFont(new Font("Arial", Font.BOLD, 16));
        lblIniciarCarrera.setForeground(Color.BLACK);
        lblIniciarCarrera.setBounds(100, 630, 600, 25);
        add(lblIniciarCarrera);

        JLabel lblIniciarInst = new JLabel("Botón del teclado: \"Enter\"");
        lblIniciarInst.setFont(new Font("Arial", Font.PLAIN, 14));
        lblIniciarInst.setForeground(Color.BLACK);
        lblIniciarInst.setBounds(100, 660, 600, 25);
        add(lblIniciarInst);

        // Botón Iniciar Carrera - GRANDE y CENTRADO
        btnIniciar = new JButton("INICIAR CARRERA");
        btnIniciar.setFont(new Font("Arial", Font.BOLD, 20));
        btnIniciar.setBackground(new Color(0, 180, 0)); // Verde brillante
        btnIniciar.setForeground(Color.WHITE);
        btnIniciar.setFocusPainted(false);
        btnIniciar.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
        btnIniciar.setBounds(250, 700, 300, 60); // Centrado horizontalmente
        add(btnIniciar);

        // Ajustar tamaño del panel para que sea scrollable
        setPreferredSize(new Dimension(800, 800));
    }

    // Getters
    public String getNombreJugador1() {
        String text = txtJugador1.getText();
        return text.isEmpty() ? " " : text;
    }

    public String getNombreJugador2() {
        String text = txtJugador2.getText();
        return text.isEmpty() ? " " : text;
    }

    public JButton getBtnIniciar() { return btnIniciar; }
}