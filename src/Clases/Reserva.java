package Clases;

public class Reserva {
    private String nombreUsuario;
    private String fecha;
    private String hora;
    private int numComensales;

    public Reserva(String nombreUsuario, String fecha, String hora, int numComensales) {
        this.nombreUsuario = nombreUsuario;
        this.fecha = fecha;
        this.hora = hora;
        this.numComensales = numComensales;
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

    @Override
    public String toString() {
        return nombreUsuario + "," + fecha + "," + hora + "," + numComensales;
    }
}
