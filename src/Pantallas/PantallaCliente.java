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
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel superior con mensaje de bienvenida
        JPanel panelSuperior = new JPanel();
        JLabel bienvenidaLabel = new JLabel("Bienvenido, " + nombreUsuario + "!");
        bienvenidaLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Tamaño y estilo del texto del título
        panelSuperior.add(bienvenidaLabel);

        // Panel central con el botón de reserva
        JPanel panelCentral = new JPanel(new GridBagLayout()); // Utilizo GridBagLayout para centrar verticalmente
        JButton reservaButton = new JButton("Hacer Reserva");
        reservaButton.setFont(new Font("Arial", Font.PLAIN, 16)); // Tamaño y estilo del texto del botón

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 0, 0); // Espacio superior para centrar verticalmente

        panelCentral.add(reservaButton, gbc);

        // Agregar componentes al panel principal
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);
        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Agregar panel principal a la ventana
        add(panelPrincipal);

        // Acciones de los botones
        reservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPantallaReserva();
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

    public static void main(String[] args) {
        // Ejemplo de uso
        PantallaCliente pantallaCliente = new PantallaCliente("NombreUsuario");
        pantallaCliente.mostrar();
    }
}