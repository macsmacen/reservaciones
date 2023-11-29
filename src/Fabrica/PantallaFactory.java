package Fabrica;

import Pantallas.*;

public class PantallaFactory {
    public static PantallaPrincipal crearPantallaPrincipal() {
        return new PantallaPrincipal();
    }

    public static PantallaCliente crearPantallaCliente(String nombreUsuario) {
        return new PantallaCliente(nombreUsuario);
    }

    public static PantallaPersonal crearPantallaPersonal() {
        return new PantallaPersonal();
    }

    public static PantallaRegistro crearPantallaRegistro() {
        PantallaPrincipal pantallaPrincipal = PantallaPrincipal.obtenerInstancia();
        return new PantallaRegistro();
    }

    public static PantallaReserva crearPantallaReserva(String nombreUsuario) {
        return new PantallaReserva(nombreUsuario);
    }
}
