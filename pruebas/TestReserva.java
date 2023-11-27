package pruebas;

import org.junit.*;

import clases.Cliente;
import clases.Mesa;
import clases.Reserva;

import java.time.LocalDateTime;

public class TestReserva {

    private Cliente cliente;
    private Mesa mesa;
    private LocalDateTime fechaHora;
    private Reserva reserva;
    
    @Before
    public void setUp(){
        cliente = new Cliente("Pepe","6627287584");
        mesa = new Mesa(1, 4);
        fechaHora = LocalDateTime.of(2023, 11, 25, 18, 30);
        reserva = new Reserva(cliente, mesa, fechaHora, 3);
    }

    @Test
    public void testCrearReserva() {
        Assert.assertEquals(cliente, reserva.getCliente());
        Assert.assertEquals(mesa, reserva.getMesa());
        Assert.assertEquals(fechaHora, reserva.getFechaHora());
        Assert.assertEquals(3, reserva.getNumeroComensales());
    }

    // Otros tests según las funcionalidades de las clases
}
