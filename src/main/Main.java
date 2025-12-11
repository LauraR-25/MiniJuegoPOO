package main;

import vista.VentanaPrincipal;
import controlador.ControladorJuego;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Establecer look and feel del sistema
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            VentanaPrincipal ventana = new VentanaPrincipal();
            new ControladorJuego(ventana);
            ventana.setVisible(true);
        });
    }
}