package Clases;

import java.util.UUID;

public class Reserva {
    private String id;
    private String nombreUsuario;
    private String fecha;
    private String hora;
    private int numComensales;

    // Modificado el constructor para recibir el ID como parámetro
    public Reserva(String id, String nombreUsuario, String fecha, String hora, int numComensales) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.numComensales = numComensales;
    }

    public String getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getNumComensales() {
        return numComensales;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void setNumComensales(int numComensales) {
        this.numComensales = numComensales;
    }

    @Override
    public String toString() {
        return id + "," + nombreUsuario + "," + fecha + "," + hora + "," + numComensales;
    }
}
