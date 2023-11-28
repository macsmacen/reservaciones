package Pantallas;

import Clases.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PantallaPrincipal extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public PantallaPrincipal() {
        super("Sistema de Reservas");

        // Configuración de la ventana
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Iniciar sesión");
        JButton registerButton = new JButton("Registrarse");

        // Agregar componentes al panel
        panel.add(new JLabel("Nombre de Usuario:"));
        panel.add(usernameField);
        panel.add(new JLabel("Contraseña:"));
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        // Agregar panel a la ventana
        add(panel);

        // Acciones de los botones
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirPantallaRegistro();
            }
        });
    }

    private void iniciarSesion() {
        String nombreUsuario = usernameField.getText();
        String contrasena = new String(passwordField.getPassword());

        List<Usuario> usuarios = Usuario.obtenerUsuarios();
        String tipoUsuario = obtenerTipoUsuario(nombreUsuario, contrasena, usuarios);

        if (tipoUsuario.equals("admin")) {
            // Lógica para administradores
            JOptionPane.showMessageDialog(this, "Inicio de sesión como administrador");
            abrirPantallaPersonal();
        } else if (tipoUsuario.equals("cliente")) {
            // Lógica para clientes
            JOptionPane.showMessageDialog(this, "Inicio de sesión como cliente");
            abrirPantallaCliente(nombreUsuario);
        } else {
            JOptionPane.showMessageDialog(this, "Autenticación fallida. Verifique su nombre de usuario y contraseña.");
        }
    }

    private void abrirPantallaPersonal() {
        PantallaPersonal pantallaPersonal = new PantallaPersonal();
        pantallaPersonal.mostrar();
        dispose();
    }

    private void abrirPantallaRegistro() {
        PantallaRegistro pantallaRegistro = new PantallaRegistro(this);
        pantallaRegistro.mostrar();
    }

    public void mostrar() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                setVisible(true);
            }
        });
    }

    private String obtenerTipoUsuario(String nombreUsuario, String contrasena, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return usuario.getTipo();
            }
        }
        return "";
    }

    private void abrirPantallaCliente(String nombreUsuario) {
        PantallaCliente pantallaCliente = new PantallaCliente(nombreUsuario);
        pantallaCliente.mostrar();
        dispose();
    }
}