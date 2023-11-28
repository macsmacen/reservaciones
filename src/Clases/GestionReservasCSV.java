package Clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GestionReservasCSV {
    private static final String FILE_PATH = "reservas.csv";

    public static void agregarReserva(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(reserva.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    reservas.add(new Reserva(parts[0], parts[1], parts[2],parts[3], Integer.parseInt(parts[4])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public static String obtenerUltimoIdReserva() {
        List<Reserva> reservas = obtenerReservas();
        if (!reservas.isEmpty()) {
            return reservas.get(reservas.size() - 1).getId();
        }
        return "0"; // Devuelve "0" si no hay reservas
    }

    public static void borrarReservaPorId(String id) {
        List<Reserva> reservas = obtenerReservas();

        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                reservas.remove(reserva);
                actualizarArchivoCSV(reservas);
                return; // Salir del método después de borrar la reserva
            }
        }

        // Si llegamos aquí, no se encontró la reserva con el ID correspondiente
        throw new IllegalArgumentException("No se encontró la reserva con el ID: " + id);
    }

    public static void actualizarArchivoCSV(List<Reserva> reservas) {
        // Actualizar el archivo CSV con la nueva lista de reservas
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Puedes manejar el error según tus necesidades
            throw new RuntimeException("Error al actualizar el archivo CSV", e);
        }
    }

    public static void guardarCambiosReserva(String id, String nombreUsuario, String fecha, String hora, int numComensales) {
        borrarReservaPorId(id);

        // Crear una nueva reserva con los cambios
        Reserva nuevaReserva = new Reserva(id, nombreUsuario, fecha, hora, numComensales);

        // Agregar la nueva reserva
        agregarReserva(nuevaReserva);
    }


}