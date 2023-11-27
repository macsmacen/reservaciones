package Clases;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final GestionReservasCSV gestionReservasCSV;
    private final Scanner scanner;

    public AdminMenu() {
        this.gestionReservasCSV = new GestionReservasCSV();
        this.scanner = new Scanner(System.in);
    }

    public void realizarOperacionesCRUD() {
        while (true) {
            System.out.println("1. Ver todas las reservas");
            System.out.println("2. Actualizar reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Volver al menú principal");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    verTodasLasReservas();
                    break;
                case 2:
                    actualizarReserva();
                    break;
                case 3:
                    eliminarReserva();
                    break;
                case 4:
                    System.out.println("Volviendo al menú principal.");
                    return;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        }
    }

    private void verTodasLasReservas() {
        List<Reserva> reservas = gestionReservasCSV.obtenerReservas();
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private void actualizarReserva() {
        System.out.println("Ingrese el nombre de usuario del cliente:");
        String nombreUsuario = scanner.nextLine();

        System.out.println("Ingrese la fecha de la reserva que desea actualizar (formato yyyy-MM-dd):");
        String fecha = scanner.nextLine();

        // Verificar si la reserva existe antes de actualizar
        List<Reserva> reservas = gestionReservasCSV.obtenerReservas();
        boolean reservaExistente = false;
        for (Reserva reserva : reservas) {
            if (reserva.getNombreUsuario().equals(nombreUsuario) && reserva.getFecha().equals(fecha)) {
                reservaExistente = true;
                break;
            }
        }

        if (reservaExistente) {

            System.out.println("Ingrese la nueva fecha de la reserva (formato yyyy-MM-dd):");
            String nuevaFecha = scanner.nextLine();

            System.out.println("Ingrese la nueva hora de la reserva (formato HH:mm):");
            String nuevaHora = scanner.nextLine();

            System.out.println("Ingrese el nuevo número de comensales:");
            int nuevoNumComensales = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            Reserva nuevaReserva = new Reserva(nombreUsuario,nuevaFecha, nuevaHora, nuevoNumComensales);
            gestionReservasCSV.eliminarReserva(nombreUsuario,fecha);
            gestionReservasCSV.agregarReserva(nuevaReserva);

            System.out.println("Reserva actualizada exitosamente.");
        } else {
            System.out.println("La reserva no existe.");
        }
    }

    private void eliminarReserva() {
        System.out.println("Ingrese el nombre de usuario del cliente:");
        String nombreUsuario = scanner.nextLine();

        System.out.println("Ingrese la fecha de la reserva que desea eliminar (formato yyyy-MM-dd):");
        String fecha = scanner.nextLine();

        // Verificar si la reserva existe antes de eliminar
        List<Reserva> reservas = gestionReservasCSV.obtenerReservas();
        boolean reservaExistente = false;
        for (Reserva reserva : reservas) {
            if (reserva.getNombreUsuario().equals(nombreUsuario) && reserva.getFecha().equals(fecha)) {
                reservaExistente = true;
                break;
            }
        }

        if (reservaExistente) {
            gestionReservasCSV.eliminarReserva(nombreUsuario, fecha);
            System.out.println("Reserva eliminada exitosamente.");
        } else {
            System.out.println("La reserva no existe.");
        }
    }
}
