package Oficina;

import java.util.ArrayList;
import java.util.Date;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Personas.Cliente;
import Personas.Vendedor;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Oficina {
    private int id;
    private String dirección;
    private String teléfono;
    private Vendedor vendedor;
    private ArrayList<Auto> autos;
    private ArrayList<Reserva> reservas;

    @Override
    public String toString() {
        return "#" + id + " (" + dirección + ")";
    }

    public Oficina(int id, String dirección, String teléfono) {
        this.id = id;
        this.dirección = dirección;
        this.teléfono = teléfono;

        this.autos = new ArrayList<Auto>();
        this.reservas = new ArrayList<Reserva>();

    }

    public void asignarVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        EntradaSalida.mostrarString("Se asignó el vendedor " + vendedor.toString() + " a la " + this.toString());
    }

    public void agregarAuto(Auto auto) {

        if (!this.tieneVendedor()) {
            EntradaSalida.mostrarString("No se puede agregar auto a oficina sin vendedor");
            return;
        }

        this.autos.add(auto);
        EntradaSalida.mostrarString(this.toString());
        EntradaSalida.mostrarString("Se agregó el auto " + auto.toString() + ", a la oficina " + this.toString(), true,
                true);
    }

    public void verListadoAutos() {
        if (this.autos.isEmpty()) {
            EntradaSalida.mostrarString("No hay autos en la " + this.toString(), false, true);
            return;
        }

        EntradaSalida.mostrarString("Listado de autos en la " + this.toString(), true, true);
        for (Auto auto : this.autos) {
            EntradaSalida.mostrarString(auto.toString(), false, true);
        }
    }

    public void verListadoReservas() {
        if (this.reservas.isEmpty()) {
            EntradaSalida.mostrarString("No hay reservas en la " + this.toString(), false, true);
            return;
        }

        EntradaSalida.mostrarString("Listado de reservas en la " + this.toString(), true, true);
        for (Reserva reserva : this.reservas) {
            EntradaSalida.mostrarString(reserva.toString(), false, true);
        }
    }

    public void verListadoReservasPendientes() {
        verListadoReservasPorEstado(EstadoReserva.PENDIENTE);
    }

    public void aceptarReserva(Reserva r) {
        r.aceptarReserva();
    }

    private void verListadoReservasPorEstado(EstadoReserva estado) {
        if (this.reservas.isEmpty()) {
            EntradaSalida.mostrarString("No hay reservas " + estado + " en la " + this.toString(), false, true);
            return;
        }

        EntradaSalida.mostrarString("Listado de reservas en la " + this.toString(), true, true);
        for (Reserva reserva : this.reservas) {
            if (reserva.getEstado() == estado) {
                EntradaSalida.mostrarString(reserva.toString(), false, true);
            }
        }
    }

    public ArrayList<Auto> getAutos() {
        return this.autos;
    }

    public Auto getAuto(int id) {
        for (Auto auto : autos) {
            if (auto.getId() == id) {
                return auto;
            }
        }
        EntradaSalida.mostrarString("Auto no encontrado", false, false);
        return null;
    }

    public ArrayList<Reserva> getReservas() {
        return this.reservas;
    }

    public Reserva getReserva(int id) {
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        EntradaSalida.mostrarString("Reserva no encontrada", false, false);
        return null;
    }

    private boolean tieneVendedor() {
        return this.vendedor != null;
    }

    private String generadorId() {
        return "RS" + this.id + "-000" + this.reservas.size() + 10;
    }

    public void agregarReserva(Reserva r) {
        this.reservas.add(r);
    }

}
