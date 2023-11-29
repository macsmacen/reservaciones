package Repositorio;

import Clases.Reserva;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReservaRepository {
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
                    reservas.add(new Reserva.Builder()
                            .nombreUsuario(parts[1])
                            .fecha(parts[2])
                            .hora(parts[3])
                            .numComensales(Integer.parseInt(parts[4]))
                            .build());                }
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

    public void borrarReservaPorId(String id) {
        List<Reserva> reservas = obtenerReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                reservas.remove(reserva);
                actualizarArchivoCSV(reservas);
                return; // Salir del método después de borrar la reserva
            }
        }
        throw new IllegalArgumentException("No se encontró la reserva con el ID: " + id);
    }

    private void actualizarArchivoCSV(List<Reserva> reservas) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Reserva reserva : reservas) {
                writer.write(reserva.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al actualizar el archivo CSV", e);
        }
    }
}

