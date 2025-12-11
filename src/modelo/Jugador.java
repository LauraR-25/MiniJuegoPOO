package modelo;

import java.awt.Color;

public class Jugador {
    private String nombre;
    private Bus bus;
    private int victorias;

    public Jugador(String nombre, String marcaBus) {
        this.nombre = nombre;
        this.victorias = 0;

        Color colorBus = marcaBus.equals("Coca-Cola") ?
                new Color(255, 0, 0) : // Rojo Coca-Cola
                new Color(0, 100, 255); // Azul Pepsi

        this.bus = new Bus(marcaBus, colorBus, 50, 0);
    }

    public void incrementarVictorias() {
        this.victorias++;
    }

    public void pulsarBoton() {
        bus.avanzar();
    }

    public void activarTurbo() {
        bus.aplicarTurbo();
    }

    // Getters
    public String getNombre() { return nombre; }
    public Bus getBus() { return bus; }
    public int getVictorias() { return victorias; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
}