package Pantallas;

import Clases.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PantallaPrincipal extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public PantallaPrincipal() {
        super("Sistema de Reservas");

        // Configuración de la ventana
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Creación de componentes
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Título
        JLabel titleLabel = new JLabel("Sistema de Reservas");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titleLabel, gbc);

        // Nombre de Usuario
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(new JLabel("Nombre de Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);

        // Contraseña
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);

        // Botones
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton loginButton = new JButton("Iniciar sesión");
        panel.add(loginButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        JButton registerButton = new JButton("Registrarse");
        panel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        JButton exitButton = new JButton("Salir");
        panel.add(exitButton, gbc);

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

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        // Agregar panel a la ventana
        add(panel);
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
