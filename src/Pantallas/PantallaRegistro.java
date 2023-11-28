package Pantallas;

import Clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaRegistro extends JFrame {
    private JTextField nuevoUsuarioField;
    private JPasswordField nuevaContrasenaField;
    private PantallaPrincipal pantallaPrincipal;

    public PantallaRegistro(PantallaPrincipal pantallaPrincipal) {
        super("Registro");

        this.pantallaPrincipal = pantallaPrincipal;

        // Configuración de la ventana
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo esta ventana al presionar cerrar
        setLocationRelativeTo(pantallaPrincipal);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        nuevoUsuarioField = new JTextField(20);
        nuevaContrasenaField = new JPasswordField(20);

        JButton registerButton = new JButton("Registrarse");

        // Agregar componentes al panel
        panel.add(new JLabel("Nuevo Usuario:"));
        panel.add(nuevoUsuarioField);
        panel.add(new JLabel("Nueva Contraseña:"));
        panel.add(nuevaContrasenaField);
        panel.add(registerButton);

        // Agregar panel a la ventana
        add(panel);

        // Acciones de los botones
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarRegistro();
            }
        });
    }

    public void mostrar() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }

    private void realizarRegistro() {
        String nuevoUsuario = nuevoUsuarioField.getText();
        String nuevaContrasena = new String(nuevaContrasenaField.getPassword());

        if (nuevoUsuario.trim().isEmpty() || nuevaContrasena.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
            return;
        }

        if (!Usuario.validarNombreUsuario(nuevoUsuario)) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario ya está en uso. Por favor, elija otro.");
            return;
        }

        Usuario usuarioNuevo = new Usuario(nuevoUsuario, "cliente", nuevaContrasena);
        Usuario.agregarUsuario(usuarioNuevo);

        JOptionPane.showMessageDialog(this, "Registro exitoso. Ahora puede iniciar sesión.");

        // Cerrar la pantalla de registro y mostrar la pantalla principal
        dispose();
        pantallaPrincipal.mostrar();
    }
}
