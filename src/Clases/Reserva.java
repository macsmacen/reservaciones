package Clases;

public class Reserva {
    private String usuario;
    private String fecha;
    private String hora;
    private int comensales;

    public Reserva(String usuario, String fecha, String hora, int comensales) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.comensales = comensales;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public int getComensales() {
        return comensales;
    }

    @Override
    public String toString() {
        return usuario + "," + fecha + "," + hora + "," + comensales;
    }
}
