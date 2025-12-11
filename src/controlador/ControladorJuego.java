package controlador;

import modelo.Juego;
import vista.VentanaPrincipal;
import javax.swing.*;
import java.awt.event.*;

public class ControladorJuego {
    private Juego juego;
    private VentanaPrincipal vista;
    private Timer timer;

    public ControladorJuego(VentanaPrincipal vista) {
        this.vista = vista;

        configurarAcciones();
        configurarTeclado();
    }

    private void configurarAcciones() {
        vista.getPanelSeleccion().getBtnIniciar().addActionListener(e -> iniciarJuego());
    }

    private void configurarTeclado() {
        vista.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        // Barra espaciadora para Coca-Cola
                        pulsarCocaCola();
                        break;
                    case KeyEvent.VK_ENTER:
                        // Enter para Pepsi
                        if (juego != null && juego.isEnJuego()) {
                            pulsarPepsi();
                        } else {
                            // Si no hay juego en curso, iniciar uno
                            iniciarJuego();
                        }
                        break;
                    // ELIMINADO: case KeyEvent.VK_R: // Quitado Reiniciar
                    case KeyEvent.VK_ESCAPE:
                        // Escape para volver al menú
                        volverMenu();
                        break;
                }
            }
        });

        vista.setFocusable(true);
        vista.requestFocus();
    }

    private void pulsarCocaCola() {
        if (juego != null && juego.isEnJuego()) {
            juego.getJugador1().pulsarBoton();
            vista.getPanelCarrera().repaint();
        }
    }

    private void pulsarPepsi() {
        if (juego != null && juego.isEnJuego()) {
            juego.getJugador2().pulsarBoton();
            vista.getPanelCarrera().repaint();
        }
    }

    private void iniciarJuego() {
        String nombre1 = vista.getPanelSeleccion().getNombreJugador1();
        String nombre2 = vista.getPanelSeleccion().getNombreJugador2();

        // Permitir nombres vacíos - el usuario decide si poner nombre o no
        // No forzamos "Laura" y "Samuel" por defecto

        juego = new Juego(nombre1, nombre2, "Normal");
        vista.getPanelCarrera().setJuego(juego);

        vista.mostrarCarrera();
        vista.requestFocus();

        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (juego != null && juego.isEnJuego()) {
                    juego.actualizar();
                    vista.getPanelCarrera().repaint();

                    if (!juego.isEnJuego()) {
                        timer.stop();
                        mostrarGanador();
                    }
                }
            }
        });
        timer.start();
    }

    // ELIMINADO: método reiniciarJuego() - Ya no se usa

    private void volverMenu() {
        if (timer != null) {
            timer.stop();
        }
        juego = null;
        vista.mostrarSeleccion();
        vista.requestFocus();
    }

    private void mostrarGanador() {
        SwingUtilities.invokeLater(() -> {
            String nombreGanador = juego.getGanador().getNombre();
            String marcaGanador = juego.getGanador().getBus().getMarca();

            // Si no hay nombre, mostrar solo la marca
            String displayGanador = (nombreGanador == null || nombreGanador.trim().isEmpty()) ?
                    marcaGanador : nombreGanador + " con " + marcaGanador;

            String mensaje = "<html><div style='text-align: center;'>" +
                    "<h2 style='color: " + (marcaGanador.equals("Coca-Cola") ? "red" : "blue") + ";'>¡GANADOR!</h2>" +
                    "<p><b>" + displayGanador + "</b></p>" +
                    "<p>Pulsaciones: " + juego.getGanador().getBus().getPulsaciones() + "</p>" +
                    "<p>Tiempo: " + (juego.getTiempo() / 20) + " segundos</p>" +
                    "</div></html>";

            int opcion = JOptionPane.showConfirmDialog(vista,
                    mensaje,
                    "Carrera Terminada",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.INFORMATION_MESSAGE);

            if (opcion == JOptionPane.YES_OPTION) {
                // En lugar de reiniciar, volver al menú para nueva carrera
                volverMenu();
            } else {
                volverMenu();
            }
        });
    }
}