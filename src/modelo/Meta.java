package modelo;

import java.awt.*;

public class Meta {
    private int x, y;
    private int ancho, alto;
    private String texto;

    public Meta(int x, int y, int ancho, int alto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.texto = "META";
    }

    public Meta(int x, int y, int ancho, int alto, String texto) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
        this.texto = texto;
    }

    public boolean verificarLlegada(Bus bus) {
        Rectangle rectMeta = new Rectangle(x, y, ancho, alto);
        Rectangle rectBus = new Rectangle(bus.getX(), bus.getY(), 100, 40);
        return rectMeta.intersects(rectBus);
    }

    public void dibujar(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // Dibujar bandera de meta
        g2d.setColor(Color.CYAN);
        g2d.fillRect(x + 5, y + 20, 30, 20);

        // Palo de la bandera
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x + 35, y + 20, 5, 50);

        // Texto "META"
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 16));
        g2d.drawString(texto, x - 40, y - 10);

        // Línea de meta
        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(Color.BLACK);
        g2d.drawLine(x, y, x, y + alto);
    }

    // Getters y setters
    public int getX() { return x; }
    public int getY() { return y; }
    public int getAncho() { return ancho; }
    public int getAlto() { return alto; }
    public String getTexto() { return texto; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setAncho(int ancho) { this.ancho = ancho; }
    public void setAlto(int alto) { this.alto = alto; }
    public void setTexto(String texto) { this.texto = texto; }
}