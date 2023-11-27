package Clases;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private static final String FILE_PATH = "usuarios.csv";

    private String nombre;
    private String tipo;
    private String contrasena;

    public Usuario(String nombre, String tipo, String contrasena) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.contrasena = contrasena;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public static List<Usuario> obtenerUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    usuarios.add(new Usuario(parts[0], parts[1], parts[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public static void agregarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(usuario.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean validarNombreUsuario(String nombreUsuario) {
        List<Usuario> usuarios = obtenerUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario)) {
                return false; // El nombre de usuario ya está en uso
            }
        }
        return true; // El nombre de usuario está disponible
    }

    @Override
    public String toString() {
        return nombre + "," + tipo + "," + contrasena;
    }
}
