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

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int margen = 50;

        int anchoDisponible = anchoPanel - 2 * margen;
        if (anchoDisponible > 900) anchoDisponible = 900;

        // 1. DIBUJAR TÍTULO CENTRADO ARRIBA
        dibujarTituloCentrado(g2d, anchoPanel);

        // 2. DIBUJAR NOMBRES EN EXTREMOS SUPERIORES
        dibujarNombresJugadores(g2d, anchoPanel);

        // 3. DIBUJAR PISTA en el centro
        int yPista = altoPanel / 2 - 50;
        dibujarPista(g2d, margen, yPista, anchoDisponible, 150);

        // 4. DIBUJAR INFORMACIÓN justo debajo de la pista (centrado)
        int yInfo = yPista + 180;
        dibujarInfoCentrada(g2d, anchoPanel, yInfo);

        // NOTA: Quité el dibujarGanador() - ya no se muestra el anuncio negro
    }

    private void dibujarTituloCentrado(Graphics2D g2d, int anchoPanel) {
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 28));

        String titulo = "CARRERA COCA-COLA VS PEPSI";
        int anchoTitulo = g2d.getFontMetrics().stringWidth(titulo);
        int xTitulo = (anchoPanel - anchoTitulo) / 2;

        g2d.drawString(titulo, xTitulo, 40);
    }

    private void dibujarNombresJugadores(Graphics2D g2d, int anchoPanel) {
        if (juego == null) return;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 18));

        // Jugador 1 (Coca-Cola) - IZQUIERDA
        String nombre1 = juego.getJugador1().getNombre();
        String texto1;
        if (nombre1 == null || nombre1.trim().isEmpty()) {
            texto1 = "COCA-COLA";
        } else {
            texto1 = nombre1 + " (COCA-COLA)";
        }
        g2d.drawString(texto1, 50, 80);

        // Jugador 2 (Pepsi) - DERECHA
        String nombre2 = juego.getJugador2().getNombre();
        String texto2;
        if (nombre2 == null || nombre2.trim().isEmpty()) {
            texto2 = "PEPSI";
        } else {
            texto2 = nombre2 + " (PEPSI)";
        }
        int anchoTexto2 = g2d.getFontMetrics().stringWidth(texto2);
        g2d.drawString(texto2, anchoPanel - anchoTexto2 - 50, 80);
    }

    private void dibujarPista(Graphics2D g2d, int x, int y, int ancho, int alto) {
        if (juego == null) return;

        // DISTANCIA TOTAL - Ajustar para que 100% sea exactamente en la línea
        int distanciaTotal = Math.min(ancho, 900);
        int metaX = x + distanciaTotal;

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

        // Línea de META (roja punteada) - EN EL 100% EXACTO
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(4, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                10, new float[]{10}, 0));
        g2d.drawLine(metaX, y, metaX, y + alto);

        // BANDERA DE CUADROS en esquina superior derecha de la pista
        dibujarBanderaMeta(g2d, metaX, y);

        // Dibujar buses
        dibujarBuses(g2d, x, y, distanciaTotal, alto);
    }

    private void dibujarBanderaMeta(Graphics2D g2d, int metaX, int yPista) {
        // Texto "META" arriba a la derecha
        g2d.setColor(Color.RED);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString("META", metaX - 35, yPista - 5);

        // Asta de la bandera
        g2d.setColor(new Color(139, 69, 19)); // Color marrón para el asta
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(metaX - 20, yPista + 10, metaX - 20, yPista + 40);

        // Bandera de cuadros (negra y blanca)
        int banderaAncho = 25;
        int banderaAlto = 15;
        int cuadroSize = 3;

        // Dibujar cuadros negros y blancos
        for (int i = 0; i < banderaAncho / cuadroSize; i++) {
            for (int j = 0; j < banderaAlto / cuadroSize; j++) {
                if ((i + j) % 2 == 0) {
                    g2d.setColor(Color.BLACK);
                } else {
                    g2d.setColor(Color.WHITE);
                }
                g2d.fillRect(
                        metaX - 40 + i * cuadroSize,
                        yPista + 10 + j * cuadroSize,
                        cuadroSize,
                        cuadroSize
                );
            }
        }

        // Borde de la bandera
        g2d.setColor(Color.BLACK);
        g2d.drawRect(metaX - 40, yPista + 10, banderaAncho, banderaAlto);
    }

    private void dibujarBuses(Graphics2D g2d, int xInicio, int yInicio, int distanciaTotal, int alturaPista) {
        if (juego == null) return;

        Bus bus1 = juego.getJugador1().getBus();
        Bus bus2 = juego.getJugador2().getBus();

        // CALCULAR POSICIÓN EXACTA - Ajustar para que 100% toque la línea
        // Restamos el ancho del bus para que el FRENTE toque la línea en 100%
        int busAncho = 60;
        int metaX = xInicio + distanciaTotal;

        // Calcular posición: en 100%, el frente del bus debe tocar la línea
        int xBus1 = xInicio + (bus1.getProgreso() * (distanciaTotal - busAncho) / 100);
        int xBus2 = xInicio + (bus2.getProgreso() * (distanciaTotal - busAncho) / 100);

        // Asegurar que no se pase
        int maxX = metaX - busAncho;
        if (xBus1 > maxX) xBus1 = maxX;
        if (xBus2 > maxX) xBus2 = maxX;

        // Si el juego terminó, poner al ganador en la meta
        if (!juego.isEnJuego() && juego.getGanador() != null) {
            if (juego.getGanador().getBus() == bus1) {
                xBus1 = maxX;
                // Mostrar efecto de ganador
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString("¡GANADOR!", xBus1 - 30, yInicio - 10);
            } else {
                xBus2 = maxX;
                // Mostrar efecto de ganador
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial", Font.BOLD, 14));
                g2d.drawString("¡GANADOR!", xBus2 - 30, yInicio + 140);
            }
        }

        // Posiciones Y dentro de la pista
        int yBus1 = yInicio + 30;
        int yBus2 = yInicio + 100;

        // Actualizar posiciones
        bus1.setX(xBus1);
        bus1.setY(yBus1);
        bus2.setX(xBus2);
        bus2.setY(yBus2);

        // Dibujar buses
        dibujarBus(g2d, bus1, xBus1, yBus1, "COCA", Color.RED);
        dibujarBus(g2d, bus2, xBus2, yBus2, "PEPSI", Color.BLUE);
    }

    private void dibujarBus(Graphics2D g2d, Bus bus, int x, int y, String texto, Color color) {
        if (x < 50) x = 50;

        // Cuerpo del bus
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, 60, 35, 8, 8);

        // Ventanas
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x + 6, y + 5, 10, 12);
        g2d.fillRect(x + 24, y + 5, 10, 12);
        g2d.fillRect(x + 42, y + 5, 10, 12);

        // Logo
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 9));
        g2d.drawString(texto, x + 18, y + 25);

        // Llantas
        g2d.setColor(Color.BLACK);
        g2d.fillOval(x + 6, y + 25, 9, 9);
        g2d.fillOval(x + 45, y + 25, 9, 9);

        // Si es el ganador, dibujar estrella
        if (juego != null && !juego.isEnJuego() && juego.getGanador() != null) {
            if (juego.getGanador().getBus() == bus) {
                g2d.setColor(Color.YELLOW);
                g2d.setFont(new Font("Arial", Font.BOLD, 12));
                g2d.drawString("★", x + 25, y - 5);
            }
        }
    }

    private void dibujarInfoCentrada(Graphics2D g2d, int anchoPanel, int y) {
        if (juego == null) return;

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 20));

        // Tiempo centrado
        int segundos = juego.getTiempo() / 20;
        String tiempoTexto = "Tiempo: " + segundos + "s";
        int anchoTiempo = g2d.getFontMetrics().stringWidth(tiempoTexto);
        int xTiempo = (anchoPanel - anchoTiempo) / 2;
        g2d.drawString(tiempoTexto, xTiempo, y);

        // Pulsaciones centradas
        g2d.setFont(new Font("Arial", Font.BOLD, 18));

        String pulsaciones1 = "Coca-Cola: " + juego.getJugador1().getBus().getPulsaciones() + " pulsaciones";
        String pulsaciones2 = "Pepsi: " + juego.getJugador2().getBus().getPulsaciones() + " pulsaciones";

        int anchoPuls1 = g2d.getFontMetrics().stringWidth(pulsaciones1);
        int anchoPuls2 = g2d.getFontMetrics().stringWidth(pulsaciones2);

        int xPuls1 = (anchoPanel - anchoPuls1) / 2;
        int xPuls2 = (anchoPanel - anchoPuls2) / 2;

        g2d.drawString(pulsaciones1, xPuls1, y + 30);
        g2d.drawString(pulsaciones2, xPuls2, y + 60);

        // Controles centrados
        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
        String controles = "Controles: Coca-Cola (ESPACIO) | Pepsi (ENTER) | Menú (ESC)";
        int anchoControles = g2d.getFontMetrics().stringWidth(controles);
        int xControles = (anchoPanel - anchoControles) / 2;
        g2d.drawString(controles, xControles, y + 100);
    }
}