package Pantallas;

import Clases.Reserva;
import Controladores.GestionReservasCSV;
import Repositorio.ReservaRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import javax.swing.text.MaskFormatter;

public class PantallaReserva extends JFrame {
    private String nombreUsuario;
    private JDateChooser fechaChooser;
    private JFormattedTextField horaFormattedTextField;
    private JTextField numComensalesField;

    public PantallaReserva(String nombreUsuario) {
        super("Hacer Reserva");
        this.nombreUsuario = nombreUsuario;
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel tituloLabel = new JLabel("Hacer Reserva");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(tituloLabel);

        JPanel fechaHoraPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        fechaHoraPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        fechaChooser = new JDateChooser();
        fechaChooser.setDate(new Date());
        JTextField editor = (JTextField) fechaChooser.getDateEditor();
        editor.setEditable(false);
        fechaHoraPanel.add(new JLabel("Fecha de la reserva:"));
        fechaHoraPanel.add(fechaChooser);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("##:##");
            horaFormattedTextField = new JFormattedTextField(maskFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fechaHoraPanel.add(new JLabel("Hora de la reserva:"));
        fechaHoraPanel.add(horaFormattedTextField);

        panel.add(fechaHoraPanel);

        JPanel numComensalesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numComensalesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        numComensalesField = new JTextField(15);

        numComensalesPanel.add(new JLabel("Número de comensales:"));
        numComensalesPanel.add(numComensalesField);

        panel.add(numComensalesPanel);

        JButton hacerReservaButton = new JButton("Hacer Reserva");
        hacerReservaButton.setBackground(new Color(50, 205, 50));
        hacerReservaButton.setForeground(Color.WHITE);
        hacerReservaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        panel.add(hacerReservaButton);

        hacerReservaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hacerReserva();
            }
        });

        add(panel);
    }

    private void hacerReserva() {
        Date fecha = fechaChooser.getDate();
        String horaString = horaFormattedTextField.getText();
        String numComensalesStr = numComensalesField.getText();

        try {

            String ultimoId = GestionReservasCSV.obtenerUltimoIdReserva();
            int nuevoId = (ultimoId != null) ? Integer.parseInt(ultimoId) + 1 : 1;
            String id = String.valueOf(nuevoId);

            int numComensales = Integer.parseInt(numComensalesStr);

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = dateFormat.format(fecha);
            String horaFormateada = horaString;


            Reserva reserva = new Reserva.Builder()
                    .id(id)
                    .nombreUsuario(nombreUsuario)
                    .fecha(fechaFormateada)
                    .hora(horaFormateada)
                    .numComensales(numComensales)
                    .build();

            ReservaRepository.agregarReserva(reserva);

            JOptionPane.showMessageDialog(this, "Reserva realizada por " + nombreUsuario);
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido para el número de comensales.");
        }
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }
}