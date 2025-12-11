package vista;

import javax.swing.*;
import java.awt.*;

public class VentanaPrincipal extends JFrame {
    private PanelSeleccion panelSeleccion;
    private PanelCarrera panelCarrera;
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    public VentanaPrincipal() {
        setTitle("Carrera Coca-Cola vs Pepsi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(850, 850); // Un poco más grande
        setLocationRelativeTo(null);
        setResizable(true);

        // Usar CardLayout para cambiar entre paneles
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Crear paneles
        panelSeleccion = new PanelSeleccion();
        panelCarrera = new PanelCarrera();

        // Hacer que PanelSeleccion sea scrollable
        JScrollPane scrollSeleccion = new JScrollPane(panelSeleccion);
        scrollSeleccion.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollSeleccion.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollSeleccion.getVerticalScrollBar().setUnitIncrement(16);
        scrollSeleccion.setBorder(BorderFactory.createEmptyBorder());

        // Añadir paneles al CardLayout
        panelPrincipal.add(scrollSeleccion, "Seleccion");
        panelPrincipal.add(panelCarrera, "Carrera");

        add(panelPrincipal, BorderLayout.CENTER);

        // Mostrar primero el panel de selección
        cardLayout.show(panelPrincipal, "Seleccion");
    }

    public void mostrarCarrera() {
        cardLayout.show(panelPrincipal, "Carrera");
    }

    public void mostrarSeleccion() {
        cardLayout.show(panelPrincipal, "Seleccion");
    }

    // Getters
    public PanelSeleccion getPanelSeleccion() { return panelSeleccion; }
    public PanelCarrera getPanelCarrera() { return panelCarrera; }
}