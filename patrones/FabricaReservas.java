package patrones;

import clases.Cliente;
import clases.Mesa;
import clases.Reserva;

import java.time.LocalDateTime;

public class FabricaReservas {
    public Reserva crearReserva(Cliente cliente, Mesa mesa, LocalDateTime fechaHora, int numeroComensales) {
        if (mesaEstaDisponible(mesa, fechaHora)) {
            return new Reserva(cliente, mesa, fechaHora, numeroComensales);
        } else {
            throw new RuntimeException("La mesa no está disponible en la fecha y hora especificadas.");
        }
    }

    private boolean mesaEstaDisponible(Mesa mesa, LocalDateTime fechaHora) {
        // En una implementación más realista, verificarías las reservas existentes y disponibilidad de la mesa
        // Aquí, simplemente devolvemos true para simplificar
        return true;
    }
}
