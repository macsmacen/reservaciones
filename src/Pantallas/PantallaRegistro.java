package Pantallas;

import Clases.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaRegistro extends JFrame {
    private JTextField nuevoUsuarioField;
    private JPasswordField nuevaContrasenaField;
    private PantallaPrincipal pantallaPrincipal;

    public PantallaRegistro(PantallaPrincipal pantallaPrincipal) {
        super("Registro de Usuario");

        this.pantallaPrincipal = pantallaPrincipal;

        // Configuración de la ventana
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(pantallaPrincipal);

        Image icono = cargarIcono("src/img/rest.jpg");
        setIconImage(icono);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Registro", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        panel.add(titleLabel, BorderLayout.NORTH);

        // Panel de contenido
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel labelUsuario = new JLabel("Nuevo Usuario:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPanel.add(labelUsuario, gbc);

        nuevoUsuarioField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPanel.add(nuevoUsuarioField, gbc);

        JLabel labelContrasena = new JLabel("Nueva Contraseña:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPanel.add(labelContrasena, gbc);

        nuevaContrasenaField = new JPasswordField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(nuevaContrasenaField, gbc);

        JButton registerButton = new JButton("Registrarse");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        contentPanel.add(registerButton, gbc);

        // Agregar panel de contenido al panel principal
        panel.add(contentPanel, BorderLayout.CENTER);

        // Agregar panel principal a la ventana
        add(panel);

        // Acciones de los botones
        registerButton.addActionListener(e -> realizarRegistro());
    }

    public void mostrar() {
        SwingUtilities.invokeLater(() -> setVisible(true));
    }

    private void realizarRegistro() {
        String nuevoUsuario = nuevoUsuarioField.getText();
        String nuevaContrasena = new String(nuevaContrasenaField.getPassword());

        if (nuevoUsuario.trim().isEmpty() || nuevaContrasena.trim().isEmpty()) {
            mostrarMensajeError("Por favor, complete todos los campos.");
            return;
        }

        if (!Usuario.validarNombreUsuario(nuevoUsuario)) {
            mostrarMensajeError("El nombre de usuario ya está en uso. Por favor, elija otro.");
            return;
        }

        Usuario usuarioNuevo = new Usuario(nuevoUsuario, "cliente", nuevaContrasena);
        Usuario.agregarUsuario(usuarioNuevo);

        JOptionPane.showMessageDialog(this, "Registro exitoso. Ahora puede iniciar sesión.");

        // Cerrar la pantalla de registro y mostrar la pantalla principal
        dispose();
        pantallaPrincipal.mostrar();
    }

    private void mostrarMensajeError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private Image cargarIcono(String ruta) {
        ImageIcon icono = new ImageIcon(ruta);
        return icono.getImage();
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        PantallaPrincipal pantallaPrincipal = new PantallaPrincipal();
        PantallaRegistro pantallaRegistro = new PantallaRegistro(pantallaPrincipal);
        pantallaRegistro.mostrar();
    }
}