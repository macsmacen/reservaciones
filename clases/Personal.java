package clases;

import patrones.ReservaRepository;

import java.util.Scanner;
import java.util.List;
import java.time.LocalDateTime;

public class Personal {
    private ReservaRepository reservaRepository;

    public Personal(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public void gestionarReservas() {
        // Código para que el personal gestione reservas
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú de gestión de reservas:");
            System.out.println("1. Ver reservas");
            System.out.println("2. Crear reserva");
            System.out.println("3. Editar reserva");
            System.out.println("4. Eliminar reserva");
            System.out.println("5. Salir");

            System.out.print("Seleccione una opción (1-5): ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    // Ver reservas
                    mostrarReservas(reservaRepository.obtenerReservas());
                    break;
                case 2:
                    // Crear reserva
                    realizarReservaComoPersonal();
                    break;
                case 3:
                    // Editar reserva (por simplicidad, asumiremos que se puede editar cualquier reserva)
                    System.out.print("Ingrese el índice de la reserva a editar: ");
                    int indiceEditar = scanner.nextInt();
                    if (indiceEditar >= 0 && indiceEditar < reservaRepository.obtenerReservas().size()) {
                        // Implementa la lógica de edición según tus necesidades
                        System.out.println("Editar reserva: No implementado en este ejemplo.");
                    } else {
                        System.out.println("Índice de reserva no válido.");
                    }
                    break;
                case 4:
                    // Eliminar reserva
                    System.out.print("Ingrese el índice de la reserva a eliminar: ");
                    int indiceEliminar = scanner.nextInt();
                    if (indiceEliminar >= 0 && indiceEliminar < reservaRepository.obtenerReservas().size()) {
                        // Implementa la lógica de eliminación utilizando el repositorio
                        Reserva reservaEliminar = reservaRepository.obtenerReservas().get(indiceEliminar);
                        reservaRepository.eliminarReserva(reservaEliminar);
                        System.out.println("Reserva eliminada con éxito.");
                    } else {
                        System.out.println("Índice de reserva no válido.");
                    }
                    break;
                case 5:
                    // Salir
                    System.out.println("Saliendo de la aplicación.");
                    System.exit(0);
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        }
    }

    private static void mostrarReservas(List<Reserva> reservas) {
        System.out.println("\nReservas existentes:");
        for (int i = 0; i < reservas.size(); i++) {
            Reserva r = reservas.get(i);
            System.out.println((i + 1) + ". Cliente: " + r.getCliente().getNombre() +
                    ", Mesa: " + r.getMesa().getNumero() +
                    ", Fecha y Hora: " + r.getFechaHora() +
                    ", Número de Comensales: " + r.getNumeroComensales());
        }
    }

    private void realizarReservaComoPersonal() {
        // Código para que el personal cree una reserva
        // (Puedes utilizar el código previo para esta funcionalidad)
        System.out.println("¡Hola, personal!");
        Scanner scanner = new Scanner(System.in);

        // Agregar la lógica para realizar la reserva utilizando el repositorio aquí
        System.out.println("Ingrese los detalles de la reserva:");

        // Obtener detalles de la reserva (por ejemplo)
        Cliente cliente = new Cliente("NombreCliente", "123456789");

        // Crear una nueva instancia de Mesa con los detalles proporcionados
        Mesa mesa = new Mesa(2,1);

        LocalDateTime fechaHora = LocalDateTime.now();
        int numeroComensales = 4;

        // Crear una nueva instancia de Reserva con los detalles proporcionados
        Reserva nuevaReserva = new Reserva(cliente, mesa, fechaHora, numeroComensales);

        // Agregar la reserva al repositorio
        reservaRepository.agregarReserva(nuevaReserva);
        System.out.println("Reserva creada con éxito.");
    }
}
