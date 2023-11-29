package Clases;

public class Reserva {
    private String id;
    private String nombreUsuario;
    private String fecha;
    private String hora;
    private int numComensales;

    private Reserva(Builder builder) {
        this.id = builder.id;
        this.nombreUsuario = builder.nombreUsuario;
        this.fecha = builder.fecha;
        this.hora = builder.hora;
        this.numComensales = builder.numComensales;
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

    // Métodos setters para permitir la modificación de los campos
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

    public static class Builder {
        private String id;
        private String nombreUsuario;
        private String fecha;
        private String hora;
        private int numComensales;

        public Builder nombreUsuario(String nombreUsuario) {
            this.nombreUsuario = nombreUsuario;
            return this;
        }

        public Builder fecha(String fecha) {
            this.fecha = fecha;
            return this;
        }

        public Builder hora(String hora) {
            this.hora = hora;
            return this;
        }

        public Builder numComensales(int numComensales) {
            this.numComensales = numComensales;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Reserva build() {
            return new Reserva(this);
        }
    }

    @Override
    public String toString() {
        return id + "," + nombreUsuario + "," + fecha + "," + hora + "," + numComensales;
    }
}