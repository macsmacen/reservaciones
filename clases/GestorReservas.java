package clases;

import java.util.ArrayList;
import java.util.List;

public class GestorReservas {
    private static final GestorReservas instancia = new GestorReservas();
    private List<Reserva> reservas;

    private GestorReservas() {
        reservas = new ArrayList<>();
    }

    public static GestorReservas obtenerInstancia() {
        return instancia;
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public List<Reserva> obtenerReservas() {
        return reservas;
    }
}
