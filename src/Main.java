import Pantallas.PantallaPrincipal;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        // Supongamos que tienes una clase PantallaPrincipal que extiende JFrame
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();

        // Muestra la pantalla principal
        SwingUtilities.invokeLater(() -> pantallaPrincipal.mostrar());
    }
}
