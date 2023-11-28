package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaCliente extends JFrame {
    private String nombreUsuario;

    public PantallaCliente(String nombreUsuario) {
        super("Área del Cliente");

        this.nombreUsuario = nombreUsuario;

        // Configuración de la ventana
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JButton reservaButton = new JButton("Hacer Reserva");
        JButton volverButton = new JButton("Volver a Pantalla Principal");

        // Agregar componentes al panel
        panel.add(new JLabel("Bienvenido, " + nombreUsuario + "!"));
        panel.add(reservaButton);
        panel.add(volverButton, BorderLayout.SOUTH);

        // Agregar panel a la ventana
        add(panel);

        // Acciones de los botones
        reservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPantallaReserva();
            }
        });
        volverButton.addActionListener(new ActionListener() { //Vuelve a la pantalla principal.
            @Override
            public void actionPerformed(ActionEvent e) {
                volverAPantallaPrincipal();
            }
        });
    }

    private void abrirPantallaReserva() {
        PantallaReserva pantallaReserva = new PantallaReserva(nombreUsuario);
        pantallaReserva.mostrar();
        // Opcional: Puedes cerrar la pantalla actual si es necesario
        // dispose();
    }

    public void mostrar() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
    private void volverAPantallaPrincipal() {
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        pantallaPrincipal.mostrar();
        dispose();
    }
}
