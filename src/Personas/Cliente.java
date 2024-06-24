package Personas;

import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Fecha.Fecha;
import Interfaces.CapazDeEliminar.CapacidadDeSerEliminado;
import Interfaces.CapazDeGestionarReserva.CapacidadDeGestionarReservas;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuCliente;
import Oficina.Oficina;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Cliente extends Persona implements CapacidadDeSerEliminado {
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private CapacidadDeGestionarReservas gReservas;

    public Cliente(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuCliente(this));
        this.gReservas = new CapacidadDeGestionarReservas(reservas);
    }

    public void crear() {
        Auto autoSeleccionado = null;
        ArrayList<Auto> autosSeleccionados = new ArrayList<Auto>();

        EntradaSalida.mostrarString("Creación de reserva", true, true);
        EntradaSalida.saltoDeLinea();
        CasaMatriz.verListadoDeAutosPorOficina();

        Oficina oficina = CasaMatriz.seleccionarOficina();
        oficina.verListadoAutos();

        do {
            autoSeleccionado = CasaMatriz.seleccionarAuto(oficina);
            if (invalidoParaAgregarAlCarrito(autosSeleccionados, autoSeleccionado))
                continue;
            autosSeleccionados.add(autoSeleccionado);

        } while (EntradaSalida.leerBoolean("\n¿Desea seleccionar otro auto?", "Si", "No"));

        if (autosSeleccionados.isEmpty()) {
            EntradaSalida.mostrarString("No se seleccionaron autos.", true, true);
            return;
        }

        Fecha fecha = new Fecha(EntradaSalida.leerFechaPosteriorAHoy("\nIngrese la fecha de inicio del alquiler\n"),
                EntradaSalida.leerEnteroConLimites("Ingrese la cantidad de días que desea alquilar el auto (1 - 30): ",
                        1, 30));

        EntradaSalida.limpiarPantalla();
        EntradaSalida.mostrarString("\nEstás por realizar la siguiente reserva: \n\n" + fecha.toString() + "\n");

        for (Auto auto : autosSeleccionados) {
            EntradaSalida.mostrarString(auto.verAuto(), true, true);
        }

        Boolean confirmar = EntradaSalida.leerBoolean("\n¿Desea confirmar la reserva?", "Si", "Cancelar");
        if (confirmar) {
            Reserva r = new Reserva(autosSeleccionados, this, oficina, fecha.getInicio(),
                    fecha.getCantDias());
            reservas.add(r);
            oficina.agregarReserva(r);

            EntradaSalida.mostrarString("\nReserva generada con éxito.");
            EntradaSalida.advertencia("El vendedor debe confirmarla antes de realizar el pago.");
        } else {
            EntradaSalida.mostrarString("Reserva cancelada.", true, true);
        }
    }

    public void verReservas() {
        gReservas.verReservas();
    }

    public void cancelarReserva() {
        if (!gReservas.hayReservas(EstadoReserva.PENDIENTE) && !gReservas.hayReservas(EstadoReserva.PENDIENTE_DE_PAGO))
            return;
        EntradaSalida.mostrarString("\nListado de reservas: ");
        gReservas.verListadoReservasPorEstado(null);

        EntradaSalida.advertencia("Solamente se pueden cancelar reservas en estados: " + EstadoReserva.PENDIENTE + " | "
                + EstadoReserva.PENDIENTE_DE_PAGO);

        int id = EntradaSalida.leerEntero("Ingrese el ID de la reserva que desea cancelar (0: volver): ");
        if (id == 0)
            return;

        Reserva r = gReservas.getReserva(id, this);
        if (r == null) {
            EntradaSalida.mostrarString("No se encontró la reserva.", true, true);
            return;
        }
        r.cancelarReserva();
    }

    public void pagarReserva() {
        if (!gReservas.hayReservas(EstadoReserva.PENDIENTE_DE_PAGO))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.PENDIENTE_DE_PAGO, this, null);
        if (r == null) {
            EntradaSalida.mostrarString("No se encontró la reserva", true, true);
            return;
        }

        r.pagarReserva();
    }

    public void retirarAutos() {
        if (!gReservas.hayReservas(EstadoReserva.RESERVADO))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.RESERVADO, this, null);
        if (r == null) {
            EntradaSalida.mostrarString("No se encontró la reserva.", true, true);
            return;
        }

        r.retiarAutos();
    }

    public void devolverAutos() {
        if (!gReservas.hayReservas(EstadoReserva.ENTREGADO))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.ENTREGADO, this, null);
        if (r == null) {
            EntradaSalida.mostrarString("No se encontró la reserva.", true, true);
            return;
        }

        EntradaSalida.advertencia("Podés devolver los autos en cualquier oficina.");

        Oficina oficina = CasaMatriz.seleccionarOficina();

        r.devolverAutos(oficina);
    }

    private boolean invalidoParaAgregarAlCarrito(ArrayList<Auto> autos, Auto auto) {

        if (auto == null)
            return true;

        for (Auto a : autos) {
            if (a.equals(auto)) {
                EntradaSalida.mostrarString("El auto ya se encuentra en el carrito.", true, true);
                return true;
            }
        }
        EntradaSalida.mostrarString("Auto agregado al carrito exitosamente.", true, true);
        return false;
    }

    /*
     * public void verListadoReservasPorEstado(EstadoReserva estado) {
     * for (Reserva reserva : this.reservas) {
     * if (estado == null) {
     * EntradaSalida.mostrarString(reserva.toString(), true, true);
     * } else if (reserva.getEstado() == estado) {
     * EntradaSalida.mostrarString(reserva.toString(), true, true);
     * }
     * }
     * }
     * 
     * public Reserva seleccionarReserva(EstadoReserva estado), null {
     * if (!hayReservas(estado))
     * return null;
     * EntradaSalida.mostrarString("\nListado de reservas: ");
     * verListadoReservasPorEstado(estado);
     * 
     * int id = EntradaSalida.
     * leerEntero("\nIngrese el ID de la reserva que desea seleccionar: ");
     * EntradaSalida.saltoDeLinea();
     * return getReserva(id, this);
     * }
     * 
     * 
     * public Reserva getReserva(int id, Cliente c) {
     * for (Reserva r : reservas) {
     * if (r.getId() == id && r.getCliente().equals(c)) {
     * return r;
     * }
     * }
     * return null;
     * }
     * 
     * 
     * 
     * private boolean hayReservas(EstadoReserva estado) {
     * if (estado == null) {
     * if (reservas.isEmpty()) {
     * EntradaSalida.mostrarString("No tenés reservas.", true, true);
     * return false;
     * }
     * return true;
     * } else {
     * for (Reserva r : reservas) {
     * if (r.getEstado() == estado) {
     * return true;
     * }
     * }
     * EntradaSalida.mostrarString("No hay reservas en estado " + estado + ".",
     * true, true);
     * return false;
     * }
     * }
     */
    @Override
    public void eliminar() {
        EntradaSalida.mostrarString("No puede eliminar");
    }

    @Override
    public boolean capacidadDeSerEliminado() {
        return true;
    }

}
