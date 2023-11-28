package Pantallas;

import Clases.Reserva;
import Clases.GestionReservasCSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PantallaPersonal extends JFrame {
    private JTextArea reservasTextArea;

    public PantallaPersonal() {
        super("Área de Personal");

        // Configuración de la ventana
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        reservasTextArea = new JTextArea();
        reservasTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(reservasTextArea);

        JButton editarButton = new JButton("Editar Reserva");
        JButton borrarButton = new JButton("Borrar Reserva");

        // Agregar componentes al panel
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(editarButton, BorderLayout.SOUTH);
        panel.add(borrarButton, BorderLayout.SOUTH);

        // Agregar panel a la ventana
        add(panel);

        // Acciones de los botones
        editarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarReserva();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarReserva();
            }
        });

        // Actualizar la lista de reservas al mostrar la pantalla
        actualizarListaReservas();
    }

    private void actualizarListaReservas() {
        List<Reserva> reservas = GestionReservasCSV.obtenerReservas();
        StringBuilder stringBuilder = new StringBuilder();

        for (Reserva reserva : reservas) {
            stringBuilder.append("Usuario: ").append(reserva.getNombreUsuario()).append(", ")
                    .append("Fecha: ").append(reserva.getFecha()).append(", ")
                    .append("Hora: ").append(reserva.getHora()).append(", ")
                    .append("Comensales: ").append(reserva.getNumComensales())
                    .append(System.lineSeparator());
        }

        reservasTextArea.setText(stringBuilder.toString());
    }

    private void editarReserva() {
        // Implementa la lógica para editar la reserva seleccionada
        // ...
    }

    private void borrarReserva() {
        // Implementa la lógica para borrar la reserva seleccionada
        // ...
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
