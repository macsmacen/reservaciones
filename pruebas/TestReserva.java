package pruebas;

import static org.junit.Assert.*;

import clases.Cliente;
import clases.Mesa;
import clases.Reserva;
import org.junit.Test;

import java.time.LocalDateTime;

public class TestReserva {
    
    @Test
    public void testCrearReserva() {
        Cliente cliente = new Cliente("NombreCliente", "123456789");
        Mesa mesa = new Mesa(1, 4);
        LocalDateTime fechaHora = LocalDateTime.of(2023, 11, 25, 18, 30);
        
        Reserva reserva = new Reserva(cliente, mesa, fechaHora, 3);
        
        assertEquals(cliente, reserva.getCliente());
        assertEquals(mesa, reserva.getMesa());
        assertEquals(fechaHora, reserva.getFechaHora());
        assertEquals(3, reserva.getNumeroComensales());
    }

    // Otros tests según las funcionalidades de las clases
}
