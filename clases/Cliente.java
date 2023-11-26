package clases;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Cliente {
    private String nombre;
    private String telefono;

    public Cliente(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void realizarReserva() {
        // Código para que el cliente realice una reserva
        // (Puedes utilizar el código previo para esta funcionalidad)
        System.out.println("¡Hola, cliente!");
        Scanner scanner = new Scanner(System.in);

        // Agregar la lógica para realizar la reserva aquí
    }
}