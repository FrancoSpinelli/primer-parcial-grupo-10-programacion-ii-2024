package Interfaces.CapazDeGestionarReserva;

import java.util.ArrayList;

import EntradaSalida.EntradaSalida;
import Oficina.Oficina;
import Personas.Cliente;
import Reserva.Reserva;
import enums.EstadoReserva;

public class CapacidadDeGestionarReservas implements CapazDeGestionarReservas {

    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public CapacidadDeGestionarReservas(ArrayList<Reserva> reservas) {
        this.reservas = reservas;
    }

    public Reserva seleccionarReserva(EstadoReserva estado, Cliente cliente, Oficina oficina) {
        if (!hayReservas(estado))
            return null;
        EntradaSalida.mostrarString("\nListado de reservas: ");
        verListadoReservasPorEstado(estado, oficina);

        int id = EntradaSalida.leerEntero("\nIngrese el ID de la reserva que desea seleccionar: ");
        EntradaSalida.saltoDeLinea();
        Reserva r = getReserva(id, cliente);
        if (r == null || (estado != null && r.getEstado() != estado)) {
            EntradaSalida.mostrarString("No se encontró la reserva", true, true);
            return null;
        }
        return r;
    }

    public Reserva getReserva(int id, Cliente c) {
        for (Reserva r : reservas) {
            if (r.getId() == id) {
                if (c != null && r.getCliente().equals(c)) {
                    return r;
                }
                return r;
            }
        }
        return null;
    }

    public boolean hayReservas(EstadoReserva estado) {
        if (estado == null) {
            if (reservas.isEmpty()) {
                EntradaSalida.mostrarString("No tenés reservas.", true, true);
                return false;
            }
            return true;
        } else {
            for (Reserva r : reservas) {
                if (r.getEstado() == estado) {
                    return true;
                }
            }
            EntradaSalida.mostrarString("No hay reservas en estado " + estado + ".", true, true);
            return false;
        }
    }

    public boolean hayReservas(EstadoReserva estado, Oficina oficina) {
        if (estado == null) {
            if (oficina.getReservas().isEmpty()) {
                EntradaSalida.mostrarString("No tenés reservas.", true, true);
                return false;
            }
            return true;
        } else {
            for (Reserva r : reservas) {
                if (r.getEstado() == estado && r.getOficina().equals(oficina)) {
                    return true;
                }
            }
            EntradaSalida.mostrarString("No hay reservas en estado " + estado + ".", true, true);
            return false;
        }
    }

    public void verReservas() {
        if (hayReservas(null)) {
            EntradaSalida.mostrarString("\nListado de reservas: ");
            verListadoReservasPorEstado(null);
        }
    }

    public void verReservas(Oficina oficina) {
        if (hayReservas(null)) {
            EntradaSalida.mostrarString("\nListado de reservas: ");
            verListadoReservasPorEstado(null, oficina);
        }
    }

    public void verListadoReservasPorEstado(EstadoReserva estado, Oficina oficina) {
        for (Reserva reserva : this.reservas) {
            if (estado == null) {
                if (oficina != null && reserva.getOficina().equals(oficina)) {
                    EntradaSalida.mostrarString(reserva.toString(), true, true);
                } else if (oficina == null) {
                    EntradaSalida.mostrarString(reserva.toString(), true, true);
                }
            } else if (reserva.getEstado() == estado) {
                if (oficina != null && reserva.getOficina().equals(oficina)) {
                    EntradaSalida.mostrarString(reserva.toString(), true, true);
                } else if (oficina == null) {
                    EntradaSalida.mostrarString(reserva.toString(), true, true);
                }
            }
        }
    }

    public void verListadoReservasPorEstado(EstadoReserva estado) {
        for (Reserva reserva : this.reservas) {
            if (estado == null) {
                EntradaSalida.mostrarString(reserva.toString(), true, true);
            } else if (reserva.getEstado() == estado) {
                EntradaSalida.mostrarString(reserva.toString(), true, true);
            }
        }
    }
}
