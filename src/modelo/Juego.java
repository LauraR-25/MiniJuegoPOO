package modelo;

public class Juego {
    private Jugador jugador1; // Coca-Cola
    private Jugador jugador2; // Pepsi
    private boolean enJuego;
    private int distanciaTotal;
    private Jugador ganador;
    private int tiempo;
    private String dificultad; // "Fácil", "Normal", "Difícil"
    private Meta meta;

    public Juego(String nombre1, String nombre2, String dificultad) {
        this.jugador1 = new Jugador(nombre1, "Coca-Cola");
        this.jugador2 = new Jugador(nombre2, "Pepsi");
        this.enJuego = true;
        this.ganador = null;
        this.tiempo = 0;
        this.dificultad = dificultad;

        aplicarDificultad();

        // Crear la meta al final de la pista
        this.meta = new Meta(50 + distanciaTotal, 150, 10, 150, "META");
    }

    private void aplicarDificultad() {
        switch (dificultad) {
            case "Fácil":
                distanciaTotal = 600;
                break;
            case "Normal":
                distanciaTotal = 800;
                break;
            case "Difícil":
                distanciaTotal = 1000;
                break;
            default:
                distanciaTotal = 800;
        }
    }

    public void actualizar() {
        if (!enJuego) return;

        tiempo++;

        // Actualizar posición de los buses basado en progreso
        jugador1.getBus().setX(50 + (jugador1.getBus().getProgreso() * distanciaTotal / 100));
        jugador2.getBus().setX(50 + (jugador2.getBus().getProgreso() * distanciaTotal / 100));

        // Verificar si algún jugador llegó a la meta usando colisiones
        if (meta.verificarLlegada(jugador1.getBus())) {
            ganador = jugador1;
            enJuego = false;
            jugador1.incrementarVictorias();
            // Asegurar que el progreso sea 100%
            jugador1.getBus().setProgreso(100);
        } else if (meta.verificarLlegada(jugador2.getBus())) {
            ganador = jugador2;
            enJuego = false;
            jugador2.incrementarVictorias();
            // Asegurar que el progreso sea 100%
            jugador2.getBus().setProgreso(100);
        }

        // Verificación adicional por progreso (backup)
        if (jugador1.getBus().getProgreso() >= 100) {
            if (ganador == null) {
                ganador = jugador1;
                enJuego = false;
                jugador1.incrementarVictorias();
            }
        } else if (jugador2.getBus().getProgreso() >= 100) {
            if (ganador == null) {
                ganador = jugador2;
                enJuego = false;
                jugador2.incrementarVictorias();
            }
        }
    }

    public void reiniciarCarrera() {
        jugador1.getBus().setProgreso(0);
        jugador1.getBus().setX(50);
        jugador1.getBus().setPulsaciones(0);
        jugador1.getBus().resetVelocidad();

        jugador2.getBus().setProgreso(0);
        jugador2.getBus().setX(50);
        jugador2.getBus().setPulsaciones(0);
        jugador2.getBus().resetVelocidad();

        ganador = null;
        enJuego = true;
        tiempo = 0;
    }

    // Getters
    public Jugador getJugador1() { return jugador1; }
    public Jugador getJugador2() { return jugador2; }
    public boolean isEnJuego() { return enJuego; }
    public int getDistanciaTotal() { return distanciaTotal; }
    public Jugador getGanador() { return ganador; }
    public int getTiempo() { return tiempo; }
    public String getDificultad() { return dificultad; }
    public Meta getMeta() { return meta; }
}