package Clases;

import java.util.Scanner;

public class Cliente {
    private final String nombreUsuario;
    private final GestionReservasCSV gestionReservasCSV;
    private final Scanner scanner;

    public Cliente(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.gestionReservasCSV = new GestionReservasCSV();
        this.scanner = new Scanner(System.in);
    }

    public void crearReservaComoCliente() {
        System.out.println("Ingrese la fecha de la reserva (formato yyyy-MM-dd):");
        String fecha = scanner.nextLine();

        System.out.println("Ingrese la hora de la reserva (formato HH:mm):");
        String hora = scanner.nextLine();

        System.out.println("Ingrese el número de comensales:");
        int numComensales = scanner.nextInt();
        scanner.nextLine(); // Consumir la nueva línea

        Reserva reserva = new Reserva(nombreUsuario, fecha, hora, numComensales);
        gestionReservasCSV.agregarReserva(reserva);

        System.out.println("Reserva creada exitosamente.");
    }
}