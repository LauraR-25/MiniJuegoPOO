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
                        // Enter para Pepsi (CORREGIDO)
                        if (juego != null && juego.isEnJuego()) {
                            pulsarPepsi();
                        } else {
                            // Si no hay juego en curso, iniciar uno
                            iniciarJuego();
                        }
                        break;
                    case KeyEvent.VK_R:
                        // Tecla R para reiniciar
                        if (juego != null && !juego.isEnJuego()) {
                            reiniciarJuego();
                        }
                        break;
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

    private void reiniciarJuego() {
        if (timer != null) {
            timer.stop();
        }
        if (juego != null) {
            juego.reiniciarCarrera();
            timer.start();
            vista.getPanelCarrera().repaint();
        }
    }

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
            int opcion = JOptionPane.showConfirmDialog(vista,
                    "¡GANADOR!\n\n" +
                            juego.getGanador().getNombre() + " con " +
                            juego.getGanador().getBus().getMarca() + "\n" +
                            "Pulsaciones: " + juego.getGanador().getBus().getPulsaciones() + "\n" +
                            "Tiempo: " + (juego.getTiempo() / 20) + " segundos\n\n" +
                            "¿Quieres jugar otra vez?",
                    "Carrera Terminada",
                    JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego();
            } else {
                volverMenu();
            }
        });
    }
}