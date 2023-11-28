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
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al presionar cerrar
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        fechaField = new JTextField(20);
        horaField = new JTextField(20);
        numComensalesField = new JTextField(20);

        JButton hacerReservaButton = new JButton("Hacer Reserva");
        JButton regresarButton = new JButton("Regresar");

        // Agregar componentes al panel
        panel.add(new JLabel("Área de Reservas"));
        panel.add(new JLabel("Fecha de la reserva:"));
        panel.add(fechaField);
        panel.add(new JLabel("Hora de la reserva:"));
        panel.add(horaField);
        panel.add(new JLabel("Número de comensales:"));
        panel.add(numComensalesField);
        panel.add(hacerReservaButton);
        panel.add(regresarButton, BorderLayout.SOUTH);

        // Agregar panel a la ventana
        add(panel);

        // Acciones de los botones
        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hacerReserva();
            }
        });
        regresarButton.addActionListener(new ActionListener() { //Vuelve a la pantalla de cliente.
            @Override
            public void actionPerformed(ActionEvent e) {
                regresarAPantallaPrincipal();
            }
        });
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
    private void regresarAPantallaPrincipal() { //Regresa a la pantalla de cliente
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        pantallaPrincipal.mostrar();
        dispose();
    }
}