package controlador;

import javax.swing.*;
import java.awt.event.*;

public class ControladorBotones extends KeyAdapter {
    private JButton btnCocaCola;
    private JButton btnPepsi;

    public ControladorBotones(JButton btnCocaCola, JButton btnPepsi) {
        this.btnCocaCola = btnCocaCola;
        this.btnPepsi = btnPepsi;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_A: // Tecla A para Coca-Cola
                btnCocaCola.doClick();
                break;
            case KeyEvent.VK_L: // Tecla L para Pepsi
                btnPepsi.doClick();
                break;
            case KeyEvent.VK_1: // Tecla 1 para Turbo Coca
                // Buscar y hacer clic en botón turbo
                break;
            case KeyEvent.VK_2: // Tecla 2 para Turbo Pepsi
                // Buscar y hacer clic en botón turbo
                break;
        }
    }
}