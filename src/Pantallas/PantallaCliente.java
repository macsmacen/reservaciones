package Pantallas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaCliente extends JFrame {
    private String nombreUsuario;

    public PantallaCliente(String nombreUsuario) {
        super("Pantalla del Cliente");
        this.nombreUsuario = nombreUsuario;

        // Configuración de la ventana
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Bienvenida al Cliente
        JLabel welcomeLabel = new JLabel("¡Bienvenido, " + nombreUsuario + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(welcomeLabel, gbc);

        // Funciones del Cliente
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JTextArea functionsTextArea = new JTextArea("Aquí se mostrarían las funciones para el Cliente.\nEjemplo: Ver Reservas, Realizar Reserva, etc.");
        functionsTextArea.setEditable(false);
        panel.add(functionsTextArea, gbc);

        // Botón para Crear Reservación
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton crearReservacionButton = new JButton("Crear Reservación");
        panel.add(crearReservacionButton, gbc);

        // Acción del botón para Crear Reservación
        crearReservacionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPantallaReserva();
            }
        });

        // Botón para Cerrar Sesión
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        JButton logoutButton = new JButton("Cerrar Sesión");
        panel.add(logoutButton, gbc);

        // Acción del botón para Cerrar Sesión
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        // Agregar panel a la ventana
        add(panel);
    }

    private void abrirPantallaReserva() {
        PantallaReserva pantallaReserva = new PantallaReserva(nombreUsuario);
        pantallaReserva.mostrar();
    }

    private void cerrarSesion() {
        PantallaPrincipal pantallaPrincipal = PantallaPrincipal.obtenerInstancia();
        pantallaPrincipal.mostrar();
        dispose();
    }

    public void mostrar() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }
}