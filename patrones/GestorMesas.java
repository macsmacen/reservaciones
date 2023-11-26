package patrones;

import clases.Mesa;

import java.util.ArrayList;
import java.util.List;

public class GestorMesas {
    private static final GestorMesas instancia = new GestorMesas();
    private List<Mesa> mesasDisponibles;

    private GestorMesas() {
        mesasDisponibles = new ArrayList<>();
        mesasDisponibles.add(new Mesa(1, 4));
        mesasDisponibles.add(new Mesa(2, 6));
        // Puedes agregar más mesas según sea necesario
    }

    public static GestorMesas obtenerInstancia() {
        return instancia;
    }

    public List<Mesa> obtenerMesasDisponibles() {
        return mesasDisponibles;
    }

    // Otros métodos para gestionar mesas
}
