package patrones;

import clases.Reserva;

import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {
    private static final ReservaRepository instancia = new ReservaRepository();
    private List<Reserva> reservas;

    private ReservaRepository() {
        reservas = new ArrayList<>();
    }

    public static ReservaRepository obtenerInstancia() {
        return instancia;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> obtenerReservas() {
        return reservas;
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }
}
