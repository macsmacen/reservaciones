package Pantallas;

import Clases.Reserva;
import Clases.GestionReservasCSV;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PantallaPersonal extends JFrame {
    private JTable reservasTable;
    private DefaultTableModel tableModel;

    public PantallaPersonal() {
        super("Área de Personal");
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Usuario");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Hora");
        tableModel.addColumn("Comensales");

        reservasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reservasTable);

        JButton borrarButton = new JButton("Borrar Reserva");
        JButton volverButton = new JButton("Volver a Pantalla Principal");

        // Estilos adicionales
        Font buttonFont = new Font("Arial", Font.PLAIN, 14);
        borrarButton.setFont(buttonFont);
        volverButton.setFont(buttonFont);

        borrarButton.setBackground(Color.RED);
        borrarButton.setForeground(Color.WHITE);

        volverButton.setBackground(Color.GRAY);
        volverButton.setForeground(Color.WHITE);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(borrarButton, BorderLayout.WEST);
        panel.add(volverButton, BorderLayout.SOUTH);

        // Dentro de tu método inicializarComponentes()
        JButton guardarCambiosButton = new JButton("Guardar Cambios");
        guardarCambiosButton.setFont(buttonFont);
        guardarCambiosButton.setBackground(Color.GREEN);
        guardarCambiosButton.setForeground(Color.WHITE);
        panel.add(guardarCambiosButton, BorderLayout.EAST);

        guardarCambiosButton.addActionListener(e -> guardarCambiosReserva());

        add(panel);

        borrarButton.addActionListener(e -> borrarReserva());
        volverButton.addActionListener(e -> volverAPantallaPrincipal());

        actualizarListaReservas();
    }

    private void actualizarListaReservas() {
        try {
            List<Reserva> reservas = GestionReservasCSV.obtenerReservas();
            tableModel.setRowCount(0);

            for (Reserva reserva : reservas) {
                Object[] rowData = {reserva.getId(), reserva.getNombreUsuario(), reserva.getFecha(), reserva.getHora(), reserva.getNumComensales()};
                tableModel.addRow(rowData);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar las reservas", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarCambiosReserva() {
        int selectedRow = reservasTable.getSelectedRow();
        if (selectedRow != -1) {
            // Obtener el ID de la reserva seleccionada
            String selectedReservaId = reservasTable.getValueAt(selectedRow, 0).toString();

            try {
                // Obtener los datos de la fila seleccionada
                String nombreUsuario = reservasTable.getValueAt(selectedRow, 1).toString();
                String fecha = reservasTable.getValueAt(selectedRow, 2).toString();
                String hora = reservasTable.getValueAt(selectedRow, 3).toString();
                int numComensales = Integer.parseInt(reservasTable.getValueAt(selectedRow, 4).toString());

                // Llamar al método en GestionReservasCSV para guardar los cambios
                GestionReservasCSV.guardarCambiosReserva(selectedReservaId, nombreUsuario, fecha, hora, numComensales);

                // Actualizar la tabla después de guardar los cambios
                actualizarListaReservas();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar los cambios", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una reserva para guardar los cambios", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void borrarReserva() {
        int selectedRow = reservasTable.getSelectedRow();
        if (selectedRow != -1) {
            // Obtener el ID de la reserva seleccionada
            String selectedReservaId = reservasTable.getValueAt(selectedRow, 0).toString();

            try {
                // Llamar al método en GestionReservasCSV para borrar la reserva por ID
                GestionReservasCSV.borrarReservaPorId(selectedReservaId);

                // Actualizar la tabla después de la eliminación
                actualizarListaReservas();
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona una reserva para borrar", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    private void volverAPantallaPrincipal() {
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        pantallaPrincipal.mostrar();
        dispose();
    }
}
