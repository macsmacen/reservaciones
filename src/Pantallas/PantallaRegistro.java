package Pantallas;

import Clases.Usuario;
import Repositorio.UsuarioRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaRegistro extends JFrame {
    private JTextField nombreUsuarioField;
    private JPasswordField contraseñaField;

    public PantallaRegistro() {
        super("Registro de Usuario");
        configurarVentana();
        inicializarComponentes();
    }

    private void configurarVentana() {
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JLabel nombreUsuarioLabel = new JLabel("Nombre de Usuario:");
        nombreUsuarioField = new JTextField();

        JLabel contraseñaLabel = new JLabel("Contraseña:");
        contraseñaField = new JPasswordField();

        JButton registrarButton = new JButton("Registrar");
        JButton volverButton = new JButton("Volver a Pantalla Principal");

        panel.add(nombreUsuarioLabel);
        panel.add(nombreUsuarioField);
        panel.add(contraseñaLabel);
        panel.add(contraseñaField);
        panel.add(registrarButton);
        panel.add(volverButton);

        add(panel);

        registrarButton.addActionListener(e -> registrarUsuario());
        volverButton.addActionListener(e -> volverAPantallaPrincipal());
    }

    private void registrarUsuario() {
        String nombreUsuario = nombreUsuarioField.getText();
        char[] contraseña = contraseñaField.getPassword();

        // Validaciones y lógica de registro de usuario
        // ...

        // Ejemplo: Guardar el usuario en el repositorio
        Usuario nuevoUsuario = new Usuario(nombreUsuario,"cliente", new String(contraseña));
        UsuarioRepository.agregarUsuario(nuevoUsuario);

        JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente", "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

        // Limpiar los campos después de registrar
        nombreUsuarioField.setText("");
        contraseñaField.setText("");
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
