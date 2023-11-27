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

    public void agregarReserva(Reserva reserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(reserva.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Reserva> obtenerReservas() {
        List<Reserva> reservas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    reservas.add(new Reserva(parts[0], parts[1], parts[2], Integer.parseInt(parts[3])));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reservas;
    }

    public void eliminarReserva(String nombreUsuario, String fecha) {
        List<Reserva> reservas = obtenerReservas();
        List<Reserva> nuevasReservas = new ArrayList<>();

        for (Reserva reserva : reservas) {
            if (!(reserva.getNombreUsuario().equals(nombreUsuario) && reserva.getFecha().equals(fecha))) {
                nuevasReservas.add(reserva);
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Reserva reserva : nuevasReservas) {
                writer.write(reserva.toString() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}