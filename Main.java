import clases.*;
import patrones.FabricaReservas;
import patrones.GestorMesas;
import patrones.ReservaRepository;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenido a la aplicación de reservas.");
        System.out.print("Ingresa 'cliente' o 'personal': ");
        String tipoUsuario = scanner.nextLine().toLowerCase();

        if (tipoUsuario.equals("cliente")) {
            // Código para cliente
            System.out.println("¡Hola, cliente!");
            Cliente cliente = new Cliente("NombreCliente", "123456789");
            cliente.realizarReserva();
        } else if (tipoUsuario.equals("personal")) {
            // Código para personal
            System.out.println("¡Hola, personal!");
            ReservaRepository reservaRepository = ReservaRepository.obtenerInstancia();
            Personal personal = new Personal(reservaRepository);
            personal.gestionarReservas();
        } else {
            System.out.println("Tipo de usuario no reconocido. Saliendo de la aplicación.");
        }
    }
}
