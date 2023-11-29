package Pantallas;

import Clases.Reserva;
import Clases.GestionReservasCSV;

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

        // Configuración de la ventana
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Image icono = cargarIcono("src/img/rest.jpg");
        setIconImage(icono);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Título en la parte superior
        JLabel tituloLabel = new JLabel("Hacer Reserva");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(tituloLabel);

        // Panel para la fecha y hora
        JPanel fechaHoraPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        fechaHoraPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Usar JDateChooser para la fecha
        fechaChooser = new JDateChooser();
        fechaChooser.setDate(new Date()); // Establecer la fecha actual como valor inicial
        JTextField editor = (JTextField) fechaChooser.getDateEditor();
        editor.setEditable(false); // Hacer el JTextField no editable

        fechaHoraPanel.add(new JLabel("Fecha de la reserva:"));
        fechaHoraPanel.add(fechaChooser);

        // Usar JFormattedTextField para la hora
        try {
            MaskFormatter maskFormatter = new MaskFormatter("##:##");
            horaFormattedTextField = new JFormattedTextField(maskFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        fechaHoraPanel.add(new JLabel("Hora de la reserva:"));
        fechaHoraPanel.add(horaFormattedTextField);

        panel.add(fechaHoraPanel);

        // Panel para el número de comensales
        JPanel numComensalesPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        numComensalesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        numComensalesField = new JTextField(15);

        numComensalesPanel.add(new JLabel("Número de comensales:"));
        numComensalesPanel.add(numComensalesField);

        panel.add(numComensalesPanel);

        // Botón Hacer Reserva
        JButton hacerReservaButton = new JButton("Hacer Reserva");
        hacerReservaButton.setBackground(new Color(50, 205, 50));
        hacerReservaButton.setForeground(Color.WHITE);
        hacerReservaButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

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
        Date fecha = fechaChooser.getDate();
        String horaString = horaFormattedTextField.getText();
        String numComensalesStr = numComensalesField.getText();

        try {
            int numComensales = Integer.parseInt(numComensalesStr);

            // Obtener el último ID de reserva y sumarle 1
            String ultimoId = GestionReservasCSV.obtenerUltimoIdReserva();
            int nuevoId = (ultimoId != null) ? Integer.parseInt(ultimoId) + 1 : 1;

            // Formatear fecha y hora
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaFormateada = dateFormat.format(fecha);
            String horaFormateada = horaString;

            // Crear la nueva reserva con el nuevo ID
            Reserva reserva = new Reserva(String.valueOf(nuevoId), nombreUsuario, fechaFormateada, horaFormateada, numComensales);
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

    private Image cargarIcono(String ruta) {
        ImageIcon icono = new ImageIcon(ruta);
        return icono.getImage();
    }
}
