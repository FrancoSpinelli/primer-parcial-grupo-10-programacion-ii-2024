package Personas;

import java.time.LocalDate;

import EntradaSalida.EntradaSalida;
import Oficina.Oficina;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuVendedor;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Vendedor extends Persona {

    private Oficina oficina;

    @Override
    public String toString() {
        return super.getNombre() + " (" + super.getEmail() + ")";
    }

    public Vendedor(int id, int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(id, dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuVendedor(this));
    }

    public void listarReservas() {
        oficina.verListadoReservas();
    }
    public void listarReservasPendientes() {
        oficina.verListadoReservasPendientes();
    }

    public void aceptarReserva() {

        if (!Oficina.hayReservasPendientes())
            return;

        Reserva r = oficina.seleccionarReserva(EstadoReserva.PENDIENTE);
        if (r == null) {
            EntradaSalida.mostrarString("No se puede rechazar una reserva sin autos");
            return;
        }
        r.aceptarReserva();
    }

    public void rechazarReserva() {

        if (!Oficina.hayReservasPendientes())
            return;

        Reserva r = oficina.seleccionarReserva(EstadoReserva.PENDIENTE);

        if (r == null) {
            EntradaSalida.mostrarString("No se puede rechazar una reserva sin autos");
            return;
        }

        r.rechazarReserva();
    }

    public void entregarAutos(Reserva r) {
        r.entregarAutos(r.getAutos());
    }

    public Boolean validoParaEntregarAutos(Reserva r) {
        return r.getEstado() == EstadoReserva.RESERVADO;
    }

    public void asignarOficina(Oficina oficina) {
        this.oficina = oficina;
        EntradaSalida.mostrarString("Se asignó la oficina " + oficina.toString() + " al vendedor " + this.toString());
    }

    public Oficina getOficina() {
        return this.oficina;
    }
}
