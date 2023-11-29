package Controladores;

import Clases.Reserva;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GestionReservasCSV {
    private static final String FILE_PATH = "reservas.csv";

    public void agregarReserva(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(reserva.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método obtenerReservas en GestionReservasCSV
    public static List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    reservas.add(new Reserva.Builder()
                            .id(parts[0])
                            .nombreUsuario(parts[1])
                            .fecha(parts[2])
                            .hora(parts[3])
                            .numComensales(Integer.parseInt(parts[4]))
                            .build());
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
    public static void actualizarReserva(String id, String nuevoUsuario, String nuevaFecha, String nuevaHora, int nuevoNumComensales) {
        List<Reserva> reservas = obtenerReservas();

        for (Reserva reserva : reservas) {
            if (reserva.getId().equals(id)) {
                reserva.setNombreUsuario(nuevoUsuario);
                reserva.setFecha(nuevaFecha);
                reserva.setHora(nuevaHora);
                reserva.setNumComensales(nuevoNumComensales);
                actualizarArchivoCSV(reservas);
                return; // Salir del método después de actualizar la reserva
            }
        }

        // Si llegamos aquí, no se encontró la reserva con el ID correspondiente
        throw new IllegalArgumentException("No se encontró la reserva con el ID: " + id);
    }
    public static void borrarReservaPorId(String id) {
        List<Reserva> reservas = obtenerReservas();

        // Utilizar un iterador para evitar ConcurrentModificationException
        Iterator<Reserva> iterator = reservas.iterator();
        while (iterator.hasNext()) {
            Reserva reserva = iterator.next();
            if (reserva.getId().equals(id)) {
                iterator.remove(); // Utilizar el método remove del iterador
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
}