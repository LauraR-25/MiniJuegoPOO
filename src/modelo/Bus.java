package modelo;

import java.awt.Color;
import java.awt.Rectangle;

public class Bus {
    private String marca; // "Coca-Cola" o "Pepsi"
    private int x, y;
    private int velocidad;
    private int progreso; // 0-100%
    private Color color;
    private int pulsaciones; // Contador de pulsaciones
    private boolean turboActivo;

    public Bus(String marca, Color color, int xInicial, int yInicial) {
        this.marca = marca;
        this.color = color;
        this.x = xInicial;
        this.y = yInicial;
        this.velocidad = 1;
        this.progreso = 0;
        this.pulsaciones = 0;
        this.turboActivo = false;
    }

    public void avanzar() {
        this.progreso += velocidad;
        this.pulsaciones++;

        // Actualizar posición en pantalla (basado en progreso)
        // La posición X se actualiza en el controlador

        // Limitar progreso a 100%
        if (progreso > 100) {
            progreso = 100;
        }
    }

    public void aplicarTurbo() {
        this.velocidad = 3; // Velocidad aumentada
        this.turboActivo = true;
    }

    public void resetVelocidad() {
        this.velocidad = 1;
        this.turboActivo = false;
    }

    // Getters y setters
    public String getMarca() { return marca; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getVelocidad() { return velocidad; }
    public int getProgreso() { return progreso; }
    public Color getColor() { return color; }
    public int getPulsaciones() { return pulsaciones; }
    public boolean isTurboActivo() { return turboActivo; }

    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
    public void setVelocidad(int velocidad) { this.velocidad = velocidad; }
    public void setProgreso(int progreso) { this.progreso = progreso; }
    public void setPulsaciones(int pulsaciones) { this.pulsaciones = pulsaciones; }
}