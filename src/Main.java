import Clases.AdminMenu;
import Clases.ClienteMenu;
import Clases.GestionUsuariosCSV;
import Clases.Usuario;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema de reservas.");

        while (true) {
            System.out.println("1. Iniciar sesión");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea
            // TODO: handler para tomar una opcion solo "int"
            switch (opcion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    registrarse();
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private static void iniciarSesion() {
        System.out.println("Ingrese su nombre de usuario:");
        String usuario = scanner.nextLine();

        System.out.println("Ingrese su contraseña:");
        String contrasena = scanner.nextLine();

        List<Usuario> listaUsuarios = Usuario.obtenerUsuarios();
        String tipoUsuario = obtenerTipoUsuario(usuario, contrasena, listaUsuarios);

        if (tipoUsuario.equals("admin")) {
            AdminMenu adminMenu = new AdminMenu();
            adminMenu.realizarOperacionesCRUD();
        } else if (tipoUsuario.equals("cliente")) {
            ClienteMenu cliente = new ClienteMenu(usuario);
            cliente.crearReservaComoCliente();
        } else {
            System.out.println("Autenticación fallida. Verifique su nombre de usuario y contraseña.");
        }
    }

    private static void registrarse() {
        System.out.println("Ingrese su nombre de usuario:");
        String nuevoUsuario = scanner.nextLine();

        if (!Usuario.validarNombreUsuario(nuevoUsuario)) {
            System.out.println("El nombre de usuario ya está en uso. Por favor, elija otro.");
            return;
        }

        System.out.println("Ingrese su contraseña:");
        String nuevaContrasena = scanner.nextLine();

        // Se asume que los nuevos registros son siempre "clientes"
        Usuario usuarioNuevo = new Usuario(nuevoUsuario, "cliente", nuevaContrasena);
        Usuario.agregarUsuario(usuarioNuevo);

        System.out.println("Registro exitoso. Ahora puede iniciar sesión.");
    }

    private static String obtenerTipoUsuario(String nombreUsuario, String contrasena, List<Usuario> usuarios) {
        for (Usuario usuario : usuarios) {
            if (usuario.getNombre().equals(nombreUsuario) && usuario.getContrasena().equals(contrasena)) {
                return usuario.getTipo();
            }
        }
        return "";
    }
}
