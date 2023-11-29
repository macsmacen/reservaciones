package Pantallas;

import Clases.Reserva;
import Controladores.GestionReservasCSV;

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
        JPanel panel = new JPanel(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("Id");
        tableModel.addColumn("Usuario");
        tableModel.addColumn("Fecha");
        tableModel.addColumn("Hora");
        tableModel.addColumn("Comensales");

        reservasTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(reservasTable);

        JButton borrarButton = new JButton("Borrar Reserva");
        JButton guardarCambiosButton = new JButton("Editar Reserva");
        JButton volverButton = new JButton("Volver a Pantalla Principal");

        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 0, 10));
        buttonPanel.add(borrarButton);
        buttonPanel.add(guardarCambiosButton);
        buttonPanel.add(volverButton);

        panel.add(buttonPanel, BorderLayout.WEST);

        add(panel);

        borrarButton.addActionListener(e -> borrarReserva());
        guardarCambiosButton.addActionListener(e -> guardarCambiosReserva());
        volverButton.addActionListener(e -> volverAPantallaPrincipal());

        actualizarListaReservas();
    }

    public void actualizarListaReservas() {
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

    private void borrarReserva() {
        int filaSeleccionada = reservasTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva para borrar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idReserva = tableModel.getValueAt(filaSeleccionada, 0).toString();

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de borrar la reserva?", "Confirmación", JOptionPane.YES_NO_OPTION);

        if (confirmacion == JOptionPane.YES_OPTION) {
            GestionReservasCSV.borrarReservaPorId(idReserva);
            actualizarListaReservas();
            JOptionPane.showMessageDialog(this, "Reserva borrada correctamente");
        }
    }

    private void guardarCambiosReserva() {
        int filaSeleccionada = reservasTable.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una reserva para editar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String idReserva = tableModel.getValueAt(filaSeleccionada, 0).toString();
        String nuevoUsuario = JOptionPane.showInputDialog(this, "Nuevo nombre de usuario:");
        String nuevaFecha = JOptionPane.showInputDialog(this, "Nueva fecha:");
        String nuevaHora = JOptionPane.showInputDialog(this, "Nueva hora:");
        String nuevoComensales = JOptionPane.showInputDialog(this, "Nuevo número de comensales:");

        try {
            int numComensales = Integer.parseInt(nuevoComensales);
            GestionReservasCSV.actualizarReserva(idReserva, nuevoUsuario, nuevaFecha, nuevaHora, numComensales);
            actualizarListaReservas();
            JOptionPane.showMessageDialog(this, "Cambios guardados correctamente");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para el número de comensales.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void volverAPantallaPrincipal() {
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        pantallaPrincipal.mostrar();
        dispose();
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}
