package vista;

import javax.swing.*;
import java.awt.*;

public class PanelControles extends JPanel {
    private JButton btnCocaCola;
    private JButton btnPepsi;
    private JButton btnTurbo1;
    private JButton btnTurbo2;
    private JButton btnReiniciar;
    private JButton btnMenu;

    public PanelControles() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(new Color(200, 200, 200));
        setPreferredSize(new Dimension(900, 80));

        // Botón Coca-Cola
        btnCocaCola = crearBoton("COCA-COLA", Color.RED, Color.WHITE);

        // Botón Pepsi
        btnPepsi = crearBoton("PEPSI", Color.BLUE, Color.WHITE);

        // Botones Turbo
        btnTurbo1 = crearBoton("TURBO COCA", Color.ORANGE, Color.BLACK);
        btnTurbo2 = crearBoton("TURBO PEPSI", Color.ORANGE, Color.BLACK);

        // Botones de control
        btnReiniciar = crearBoton("REINICIAR", Color.GREEN, Color.WHITE);
        btnMenu = crearBoton("MENÚ", Color.GRAY, Color.WHITE);

        // Añadir botones
        add(btnCocaCola);
        add(btnPepsi);
        add(btnTurbo1);
        add(btnTurbo2);
        add(btnReiniciar);
        add(btnMenu);
    }

    private JButton crearBoton(String texto, Color fondo, Color textoColor) {
        JButton boton = new JButton(texto);
        boton.setBackground(fondo);
        boton.setForeground(textoColor);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorder(BorderFactory.createRaisedBevelBorder());
        boton.setPreferredSize(new Dimension(120, 40));
        return boton;
    }

    // Getters
    public JButton getBtnCocaCola() { return btnCocaCola; }
    public JButton getBtnPepsi() { return btnPepsi; }
    public JButton getBtnTurbo1() { return btnTurbo1; }
    public JButton getBtnTurbo2() { return btnTurbo2; }
    public JButton getBtnReiniciar() { return btnReiniciar; }
    public JButton getBtnMenu() { return btnMenu; }
}