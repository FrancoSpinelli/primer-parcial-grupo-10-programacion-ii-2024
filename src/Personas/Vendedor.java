package Personas;
import java.time.LocalDate;

import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuVendedor;
import Reserva.Reserva;

public class Vendedor extends Persona {

    @Override
    public String toString() {
        return super.getNombre() + " (" + super.getEmail() + ")";
    }

    public Vendedor(int id, int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(id, dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuVendedor(this));
    }

    public void listarReservasPendientes() {
        EntradaSalida.mostrarString("Listado de reservas pendientes:");
    }

    public void aceptarReserva(Reserva r) {

    }

    public void rechazarReserva(Reserva r) {

    }

    public void entregarAuto(Reserva r) {

    }
}
