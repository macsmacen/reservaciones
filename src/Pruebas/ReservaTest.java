package Pruebas;

import java.util.List;
import org.junit.*;
import Clases.GestionReservasCSV;
import Clases.Reserva;

public class ReservaTest {

    private GestionReservasCSV gestionReservasCSV;
    private List<Reserva> reservas;
    private Reserva reserva;

    @Before
    public void setUp(){
        gestionReservasCSV = new GestionReservasCSV();
        reservas = gestionReservasCSV.obtenerReservas();
        reserva = new Reserva(null, "pedro", "2023-11-28", "12:00", 1);
    }

    @Test
    public void crearReservaTest(){
        Assert.assertEquals("pedro", reserva.getNombreUsuario());
        Assert.assertEquals("2023-11-28", reserva.getFecha());
        Assert.assertEquals("12:00", reserva.getHora());
        Assert.assertEquals(1, reserva.getNumComensales());
    }

    @Test
    public void registrosEnReservaTest(){
        //Verificamos que el archivo "reservas.csv" tenga contenido
        Assert.assertFalse(reservas.isEmpty());
        //Verificamos la cantidad de reservas: 2
        Assert.assertEquals(1, reservas.size());
    }
}
