package Personas;

import java.time.LocalDate;

import EntradaSalida.EntradaSalida;
import Oficina.Oficina;
import Interfaces.CapazDeGestionarReserva.CapacidadDeGestionarReservas;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuVendedor;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Vendedor extends Persona {

    private Oficina oficina;
    private CapacidadDeGestionarReservas gReservas;

    @Override
    public String toString() {
        return super.getNombre() + " (" + super.getEmail() + ")";
    }

    public Vendedor(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuVendedor(this));
    }

    public void listarReservas() {
        if (gReservas == null) {
            EntradaSalida.mostrarString("No se puede listar reservas sin una oficina asignada");
            return;
        }

        if (!gReservas.hayReservas(null, oficina))
            return;

        gReservas.verListadoReservasPorEstado(null, this.oficina);
    }

    public void listarReservasPendientes() {
        if (gReservas == null) {
            EntradaSalida.mostrarString("No se puede listar reservas sin una oficina asignada");
            return;
        }

        if (!gReservas.hayReservas(EstadoReserva.PENDIENTE, oficina))
            return;

        gReservas.verListadoReservasPorEstado(EstadoReserva.PENDIENTE, this.oficina);
    }

    public void aceptarReserva() {

        if (gReservas == null) {
            EntradaSalida.mostrarString("No se puede aceptar reservas sin una oficina asignada");
            return;
        }

        if (!gReservas.hayReservas(EstadoReserva.PENDIENTE, oficina))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.PENDIENTE, null, oficina);
        if (r == null || r.getOficina() != this.oficina) {
            EntradaSalida.mostrarString("No se encontró la reserva", true, true);
            return;
        }

        r.aceptarReserva();
    }

    public void rechazarReserva() {

        if (gReservas == null) {
            EntradaSalida.mostrarString("No se puede rechazar reservas sin una oficina asignada");
            return;
        }

        if (!gReservas.hayReservas(EstadoReserva.PENDIENTE, oficina))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.PENDIENTE, null, oficina);
        if (r == null || r.getOficina() != this.oficina) {
            EntradaSalida.mostrarString("No se encontró la reserva", true, true);
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
        this.gReservas = new CapacidadDeGestionarReservas(oficina.getReservas());
    }

    public Oficina getOficina() {
        return this.oficina;
    }
}
