package vista;

import modelo.*;
import javax.swing.*;
import java.awt.*;

public class PanelCarrera extends JPanel {
    private Juego juego;

    public PanelCarrera() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (juego == null) return;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Calcular dimensiones para que quepa en el panel
        int anchoPanel = getWidth();
        int altoPanel = getHeight();

        // Margen seguro
        int margen = 50;
        int anchoDisponible = anchoPanel - 2 * margen;
        int altoDisponible = altoPanel - 150; // Dejar espacio para información

        // Dibujar pista ajustada al tamaño del panel
        dibujarPista(g2d, margen, 100, anchoDisponible, 150);

        // Dibujar información en la parte inferior
        dibujarInfo(g2d, margen, 280, anchoDisponible);

        // Dibujar ganador si hay
        if (!juego.isEnJuego() && juego.getGanador() != null) {
            dibujarGanador(g2d, anchoPanel, altoPanel);
        }
    }

    private void dibujarPista(Graphics2D g2d, int x, int y, int ancho, int alto) {
        if (juego == null) return;

        // Ajustar la distancia total para que quepa en el ancho disponible
        int distanciaTotal = Math.min(ancho - 100, 800); // Máximo 800px

        // Actualizar distancia en el juego si es necesario
        if (distanciaTotal != juego.getDistanciaTotal()) {
            // Aquí podrías actualizar el juego si fuera necesario
        }

        // Pista principal
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(x, y, distanciaTotal, alto);

        // Líneas de carril
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(2));
        for (int i = 0; i < distanciaTotal; i += 40) {
            g2d.drawLine(x + i, y + alto/2, x + i + 20, y + alto/2);
        }

        // Bordes de la pista
        g2d.setColor(Color.YELLOW);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(x, y, x + distanciaTotal, y);
        g2d.drawLine(x, y + alto, x + distanciaTotal, y + alto);

        // Meta
        int metaX = x + distanciaTotal;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawLine(metaX, y, metaX, y + alto);

        // Dibujar buses ajustados a la nueva pista
        dibujarBuses(g2d, x, y, distanciaTotal, alto);
    }

    private void dibujarBuses(Graphics2D g2d, int xInicio, int yInicio, int distanciaTotal, int alturaPista) {
        if (juego == null) return;

        Bus bus1 = juego.getJugador1().getBus();
        Bus bus2 = juego.getJugador2().getBus();

        // Calcular posición X basada en el progreso
        int xBus1 = xInicio + (bus1.getProgreso() * distanciaTotal / 100);
        int xBus2 = xInicio + (bus2.getProgreso() * distanciaTotal / 100);

        // Posiciones Y dentro de la pista
        int yBus1 = yInicio + 20; // Coca-Cola (arriba)
        int yBus2 = yInicio + 80; // Pepsi (abajo)

        // Actualizar posiciones de los buses
        bus1.setX(xBus1);
        bus1.setY(yBus1);
        bus2.setX(xBus2);
        bus2.setY(yBus2);

        // Dibujar buses
        dibujarBus(g2d, bus1, xBus1, yBus1, "COCA-COLA", Color.RED);
        dibujarBus(g2d, bus2, xBus2, yBus2, "PEPSI", Color.BLUE);

        // Nombres de los jugadores
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 14));
        g2d.drawString(juego.getJugador1().getNombre(), xInicio - 40, yBus1 + 20);
        g2d.drawString(juego.getJugador2().getNombre(), xInicio - 40, yBus2 + 20);

        // Barras de progreso
        dibujarBarraProgreso(g2d, bus1, xInicio, yBus1 + 60, distanciaTotal, "Coca-Cola");
        dibujarBarraProgreso(g2d, bus2, xInicio, yBus2 + 60, distanciaTotal, "Pepsi");
    }

    private void dibujarBus(Graphics2D g2d, Bus bus, int x, int y, String texto, Color color) {
        // Asegurar que el bus no se salga de la pista
        if (x < 50) x = 50;

        // Cuerpo del bus (más pequeño para que quepa)
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, 80, 35, 8, 8);

        // Ventanas
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x + 8, y + 5, 15, 12);
        g2d.fillRect(x + 32, y + 5, 15, 12);
        g2d.fillRect(x + 56, y + 5, 15, 12);

        // Logo
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        g2d.drawString(texto, x + 10, y + 25);

        // Llantas
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + 8, y + 25, 12, 12);
        g2d.fillOval(x + 60, y + 25, 12, 12);
    }

    private void dibujarBarraProgreso(Graphics2D g2d, Bus bus, int x, int y, int anchoTotal, String nombre) {
        // Barra de fondo
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(x, y, anchoTotal, 8);

        // Progreso
        g2d.setColor(nombre.equals("Coca-Cola") ? Color.RED : Color.BLUE);
        int progresoAncho = bus.getProgreso() * anchoTotal / 100;
        g2d.fillRect(x, y, progresoAncho, 8);

        // Borde
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x, y, anchoTotal, 8);

        // Porcentaje
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString(bus.getProgreso() + "%", x + progresoAncho + 5, y + 7);
    }

    private void dibujarInfo(Graphics2D g2d, int x, int y, int ancho) {
        if (juego == null) return;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        // Título
        g2d.drawString("CARRERA COCA-COLA vs PEPSI", x + 50, y);

        // Tiempo
        int segundos = juego.getTiempo() / 20;
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("Tiempo: " + segundos + "s", x, y + 40);

        // Pulsaciones
        g2d.drawString("Coca-Cola: " + juego.getJugador1().getBus().getPulsaciones() + " pulsaciones",
                x, y + 70);
        g2d.drawString("Pepsi: " + juego.getJugador2().getBus().getPulsaciones() + " pulsaciones",
                x, y + 100);

        // Instrucciones durante la carrera
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        g2d.drawString("Controles: Coca-Cola (ESPACIO) | Pepsi (ENTER) | Reiniciar (R) | Menú (ESC)",
                x, y + 140);
    }

    private void dibujarGanador(Graphics2D g2d, int anchoPanel, int altoPanel) {
        // Fondo semi-transparente
        g2d.setColor(new Color(0, 0, 0, 200));
        g2d.fillRect(anchoPanel/2 - 250, altoPanel/2 - 100, 500, 200);

        // Texto del ganador
        g2d.setColor(Color.YELLOW);
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        g2d.drawString("¡GANADOR!", anchoPanel/2 - 80, altoPanel/2 - 40);

        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));

        String nombreGanador = juego.getGanador().getNombre();
        String marcaGanador = juego.getGanador().getBus().getMarca();
        g2d.drawString(nombreGanador + " - " + marcaGanador,
                anchoPanel/2 - 150, altoPanel/2);

        g2d.setFont(new Font("Arial", Font.PLAIN, 18));
        g2d.drawString("Pulsaciones: " + juego.getGanador().getBus().getPulsaciones(),
                anchoPanel/2 - 80, altoPanel/2 + 40);
        g2d.drawString("Tiempo: " + (juego.getTiempo() / 20) + " segundos",
                anchoPanel/2 - 80, altoPanel/2 + 70);
    }
}