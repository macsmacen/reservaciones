package Clases;

import java.util.List;
import java.util.Scanner;

public class AdminMenu {
    private final GestionReservasCSV gestionReservasCSV;
    private final Scanner scanner;
    private String usuario = "";
    private String fecha;
    private boolean reservaExistente;

    public AdminMenu() {
        this.gestionReservasCSV = new GestionReservasCSV();
        this.scanner = new Scanner(System.in);
    }

    public void realizarOperacionesCRUD() {
        while (true) {
            System.out.println("1. Ver todas las reservas");
            System.out.println("2. Actualizar reserva");
            System.out.println("3. Eliminar reserva");
            System.out.println("4. Cerrar sesión");

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
                    System.out.println("\nVolviendo al menú principal...\n");
                    return;
                default:
                    System.out.println("\nOpción no válida. Intentalo de nuevo.\n");
            }
        }
    }

    private void verTodasLasReservas() {
        List<Reserva> reservas = gestionReservasCSV.obtenerReservas();
        //En caso de no haber reservas
        if(reservas.isEmpty()){ System.out.println("\nNo se encontraron reservas\n"); }

        System.out.println();
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
        System.out.println();
    }

    private void actualizarReserva() {
        // Para cada operacion se tiene que resetear las siguientes variables
        reservaExistente = false;
        usuario = "";

        while (!reservaExistente) {
            // Verificar si la reserva existe antes de actualizar con el metodo -> verificarReserva()
            // Opcion si se desea retroceder
            if (usuario.equals("s")) { break; }
            
            if (verificarReserva() && !usuario.equals("s")) {

                System.out.println("Ingrese la nueva fecha de la reserva (formato yyyy-MM-dd):");
                String nuevaFecha = scanner.nextLine();

                System.out.println("Ingrese la nueva hora de la reserva (formato HH:mm):");
                String nuevaHora = scanner.nextLine();

                System.out.println("Ingrese el nuevo número de comensales:");
                int nuevoNumComensales = scanner.nextInt();
                scanner.nextLine(); // Consumir la nueva línea

                Reserva nuevaReserva = new Reserva(usuario,nuevaFecha, nuevaHora, nuevoNumComensales);
                gestionReservasCSV.eliminarReserva(usuario,fecha);
                gestionReservasCSV.agregarReserva(nuevaReserva);

                System.out.println("\nReserva actualizada exitosamente.\n");
                reservaExistente = true;
            }
        }
    }

    private void eliminarReserva() {
        // Para cada operacion se tiene que resetear las siguientes variables
        reservaExistente = false;
        usuario = "";

        while (!reservaExistente) {
            // Opcion si se desea retroceder
            if (usuario.equals("s")) { break; }
            if (verificarReserva() && !usuario.equals("s")) {
                gestionReservasCSV.eliminarReserva(usuario, fecha);
                System.out.println("Reserva eliminada exitosamente.");
                reservaExistente = true;
            }
        }
    }

    private boolean verificarReserva(){
        System.out.println("Ingrese el nombre de usuario del cliente:\n\t[s] para salir");
        usuario = scanner.nextLine().toLowerCase().strip();
        //caso para salir
        if (usuario.equals("s")) { return true; }

        if (!verificarAtributo(usuario, "usuario")) { 
            System.out.println("\nLa reserva de "+usuario+" no existe...\n");
            return false;
        }

        System.out.println("Ingrese la fecha de la reserva para poder continuar (formato yyyy-MM-dd):");
        fecha = scanner.nextLine();
        if (!verificarAtributo(fecha, "fecha")) { 
            System.out.println("\nLa reserva de "+usuario+" para el dia "+fecha+" no existe...\n");
            return false;
        }

        return true;
    }

    private boolean verificarAtributo(String atributo, String key){
        List<Reserva> reservas = gestionReservasCSV.obtenerReservas();
        switch (key) {
            case "usuario":
                for (Reserva reserva : reservas) {
                    if (reserva.getUsuario().equals(atributo)) {
                        return true;
                    }
                }
                break;
            case "fecha":
                for (Reserva reserva : reservas) {
                    if (reserva.getFecha().equals(atributo)) {
                        return true;
                    }
                }
                break;
        }
        return false;
    }
}
