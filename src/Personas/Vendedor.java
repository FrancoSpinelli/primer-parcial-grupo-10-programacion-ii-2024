package Personas;

import java.time.LocalDate;

import EntradaSalida.EntradaSalida;
import Interfaces.MenuVendedor;
import Oficina.Oficina;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Vendedor extends Persona {

    private Oficina oficina;

    @Override
    public String toString() {
        return super.getNombre() + " (" + super.getEmail() + ")";
    }

    public Vendedor(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new MenuVendedor());
    }

    public void listarReservasPendientes() {
        oficina.verListadoReservasPendientes();
    }

    public void aceptarReserva(Reserva r) {
        if (r == null) {
            EntradaSalida.mostrarString("No se puede aceptar una reserva sin autos");
            return;
        }

        if (r.getEstado() != EstadoReserva.PENDIENTE) {
            EntradaSalida.mostrarString("No se puede aceptar una reserva que no esté pendiente");
            return;
        }
        
        r.aceptarReserva();
    }

    public void rechazarReserva(Reserva r) {

    }

    public void entregarAuto(Reserva r) {

    }

    public void asignarOficina(Oficina oficina) {
        this.oficina = oficina;
        EntradaSalida.mostrarString("Se asignó la oficina " + oficina.toString() + " al vendedor " + this.toString());
    }

    public Oficina getOficina() {
        return this.oficina;
    }
}
