package clases;

import java.time.LocalDateTime;
import java.util.List;

// Reserva
public class Reserva {
    private Cliente cliente;
    private Mesa mesa;
    private LocalDateTime fechaHora;
    private int numeroComensales;

    public Reserva(Cliente cliente, Mesa mesa, LocalDateTime fechaHora, int numeroComensales) {
        this.cliente = cliente;
        this.mesa = mesa;
        this.fechaHora = fechaHora;
        this.numeroComensales = numeroComensales;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public int getNumeroComensales() {
        return numeroComensales;
    }
}
