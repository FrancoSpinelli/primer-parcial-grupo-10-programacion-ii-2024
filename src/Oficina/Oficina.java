package Oficina;

import java.io.Serializable;
import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Personas.Vendedor;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Oficina implements Serializable{
    private int id;
    private String dirección;
    private String teléfono;
    private Vendedor vendedor;
    private ArrayList<Auto> autos;
    private static ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    @Override
    public String toString() {
        return "#" + id + " - " + dirección + " - Contacto: " + teléfono;
    }

    public Oficina(String dirección, String teléfono) {
        this.id = CasaMatriz.generarIdOficina();
        this.dirección = dirección;
        this.teléfono = teléfono;

        this.autos = new ArrayList<Auto>();
        reservas = new ArrayList<Reserva>();

    }

    public void asignarVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public void agregarAuto(Auto auto) {

        if (!this.tieneVendedor()) {
            EntradaSalida.mostrarString("No se puede agregar auto a oficina sin vendedor");
            return;
        }
        auto.setOficinaOriginal(this);
        this.autos.add(auto);
        EntradaSalida.mostrarString(this.toString());
        EntradaSalida.mostrarString("Se agregó el auto " + auto.verAuto() + ", a la oficina " + this.toString(), true,
                true);
    }

    public boolean tieneAutos() {
        return !this.autos.isEmpty();
    }

    public void verListadoAutos() {
        if (!tieneAutos()) {
            EntradaSalida.mostrarString("No hay autos en la oficina " + this.toString(), true, true);
            return;
        }

        EntradaSalida.mostrarString("Oficina " + this.toString(), true, true);
        for (Auto auto : this.autos) {
            String msg = "\t" + auto.verAuto();
            if (auto.getOficinaOriginal() != this) {
                msg += " - En tránsito";
            }
            EntradaSalida.mostrarString(msg, false, true);
        }
    }

    public void verListadoReservas() {
        verListadoReservasPorEstado(null);
    }

    public void verListadoReservasPendientes() {
        if (!hayReservasPendientes())
            return;
        verListadoReservasPorEstado(EstadoReserva.PENDIENTE);
    }

    public void aceptarReserva(Reserva r) {
        r.aceptarReserva();
    }

    private void verListadoReservasPorEstado(EstadoReserva estado) {
        if (reservas.isEmpty()) {
            EntradaSalida.mostrarString("No hay reservas " + estado + " en la oficina " + this.toString(), false, true);
            return;
        }

        EntradaSalida.mostrarString("Listado de reservas en la " + this.toString(), true, true);
        for (Reserva reserva : reservas) {
            if (reserva.getEstado() == estado) {
                EntradaSalida.mostrarString("\t" + reserva.toString(), false, true);
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
        return null;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
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

    public void agregarReserva(Reserva r) {
        reservas.add(r);
    }

    public Vendedor getVendedor() {
        return this.vendedor;
    }

    public int getId() {
        return this.id;
    }

    public void recibirAutos(ArrayList<Auto> autos) {
        for (Auto auto : autos) {
            auto.setOficinaActual(this);
            if (!this.autos.contains(auto)) {
                this.autos.add(auto);
            }
        }
    }

    public Reserva seleccionarReserva(EstadoReserva estado) {
        if (reservas.isEmpty()) {
            EntradaSalida.mostrarString("No hay reservas en la oficina " + this.toString(), false, true);
            return null;
        }

        EntradaSalida.mostrarString("Seleccione una reserva");
        for (Reserva reserva : reservas) {
            if (estado != null) {
                EntradaSalida.mostrarString("\t" + reserva.toString(), true, true);
            } else if (reserva.getEstado() == estado) {
                EntradaSalida.mostrarString("\t" + reserva.toString(), true, true);
            }
        }

        int id = EntradaSalida.leerEntero("Ingrese el ID de la reserva: ");
        for (Reserva reserva : reservas) {
            if (reserva.getId() == id) {
                return reserva;
            }
        }
        EntradaSalida.mostrarString("\nReserva no encontrada", true, true);
        return null;
    }

    public static boolean hayReservasPendientes() {
        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.PENDIENTE) {
                return true;
            }
        }
        EntradaSalida.mostrarString("No hay reservas pendientes en esta oficina", true, true);
        return false;
    }

    public String verOficina() {
        return this.toString();
    }

}
