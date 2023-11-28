package Pantallas;

import Clases.Reserva;
import Clases.GestionReservasCSV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaReserva extends JFrame {
    private String nombreUsuario;
    private JTextField fechaField;
    private JTextField horaField;
    private JTextField numComensalesField;

    public PantallaReserva(String nombreUsuario) {
        super("Hacer Reserva");

        this.nombreUsuario = nombreUsuario;

        // Configuración de la ventana
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agregado un borde al panel

        // Título en la parte superior
        JLabel tituloLabel = new JLabel("Hacer Reserva");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(tituloLabel);

        // Panel para la fecha y hora
        JPanel fechaHoraPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        fechaHoraPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Agregado un borde al panel
        fechaField = new JTextField(15);
        horaField = new JTextField(15);

        fechaHoraPanel.add(new JLabel("Fecha de la reserva:"));
        fechaHoraPanel.add(fechaField);
        fechaHoraPanel.add(new JLabel("Hora de la reserva:"));
        fechaHoraPanel.add(horaField);

        panel.add(fechaHoraPanel);

        // Panel para el número de comensales
        JPanel numComensalesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numComensalesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Agregado un borde al panel
        numComensalesField = new JTextField(15);

        numComensalesPanel.add(new JLabel("Número de comensales:"));
        numComensalesPanel.add(numComensalesField);

        panel.add(numComensalesPanel);

        // Botón Hacer Reserva
        JButton hacerReservaButton = new JButton("Hacer Reserva");
        hacerReservaButton.setBackground(new Color(50, 205, 50)); // Color de fondo verde
        hacerReservaButton.setForeground(Color.WHITE); // Color de texto blanco
        hacerReservaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Agregado un borde al botón

        panel.add(hacerReservaButton);

        // Acciones de los botones
        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hacerReserva();
            }
        });

        // Agregar panel a la ventana
        add(panel);
    }

    private void hacerReserva() {
        // Lógica de reserva
        String fecha = fechaField.getText();
        String hora = horaField.getText();
        String numComensalesStr = numComensalesField.getText();

        try {
            int numComensales = Integer.parseInt(numComensalesStr);

            // Obtener el último ID de reserva y sumarle 1
            String ultimoId = GestionReservasCSV.obtenerUltimoIdReserva();
            int nuevoId = (ultimoId != null) ? Integer.parseInt(ultimoId) + 1 : 1;

            // Crear la nueva reserva con el nuevo ID
            Reserva reserva = new Reserva(String.valueOf(nuevoId), nombreUsuario, fecha, hora, numComensales);
            GestionReservasCSV.agregarReserva(reserva);

            JOptionPane.showMessageDialog(this, "Reserva realizada por " + nombreUsuario);

            // Opcional: Puedes cerrar la pantalla de reservas después de hacer la reserva
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para el número de comensales.");
        }
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