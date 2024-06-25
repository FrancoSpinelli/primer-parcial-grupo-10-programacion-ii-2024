package Validador;

import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Personas.Persona;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Validador {

    public static boolean validarRango(int valor, int min, int max) {
        return valor >= min && valor <= max;
    }

    public static boolean validarString(String valor) {
        return valor != null && !valor.isEmpty();
    }

    public static boolean validarEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }

    public static boolean validarContrasenia(String contrasenia) {
        return contrasenia != null && contrasenia.length() >= 4;
    }

    public static boolean validoParaCrearPersona(int dni, String email) {
        return dniDisponible(dni) && correoDisponible(email);
    }

    public static boolean patenteValida(String patente) {
        if (patente != null && patente.length() != 6) {
            EntradaSalida.error("La patente debe tener 6 caracteres.");
            return false;
        }
        for (Auto a : CasaMatriz.getAutos()) {
            if (a.getPatente().equals(patente)) {
                EntradaSalida.error("La patente ya está en uso.");
                return false;
            }
        }
        return true;
    }

    public static boolean telefonoValido(String telefono) {
        if (telefono != null && telefono.length() >= 6) {
            return true;
        }
        EntradaSalida.error("El teléfono debe tener al menos 6 caracteres.");
        return false;
    }

    public static boolean validarReservasParaEliminar(ArrayList<Reserva> reservas) {
        if (reservas.isEmpty()) {
            return true;
        }

        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.RESERVADO || r.getEstado() == EstadoReserva.ENTREGADO) {
                EntradaSalida.advertencia("Existen reservas en estado RESERVADO o ENTREGADO.");
                EntradaSalida.error("No se pueden eliminar reservas en estos estados.");
                return false;
            }
        }

        return true;
    }

    private static boolean dniDisponible(int dni) {
        for (Persona p : CasaMatriz.getPersonasStatic()) {
            if (p.getDni() == dni) {
                return false;
            }
        }
        return true;
    }

    private static boolean correoDisponible(String email) {
        for (Persona p : CasaMatriz.getPersonasStatic()) {
            if (p.getEmail().equals(email)) {
                return false;
            }
        }
        return true;
    }

}
