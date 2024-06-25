package Personas;

import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import CasaMatriz.Const;
import EntradaSalida.EntradaSalida;
import Fecha.Fecha;
import Interfaces.CapazDeGestionarReserva.CapacidadDeGestionarReservas;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuCliente;
import Oficina.Oficina;
import Reserva.Reserva;
import enums.EstadoReserva;
import enums.Rol;

public class Cliente extends Persona {
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();
    private CapacidadDeGestionarReservas gReservas;

    public Cliente(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenu(new CapacidadDeVerMenuCliente(this));
        this.gReservas = new CapacidadDeGestionarReservas(reservas);
    }

    public void generarReserva() {
        Auto autoSeleccionado = null;
        ArrayList<Auto> autosSeleccionados = new ArrayList<Auto>();

        EntradaSalida.mostrarString("Creación de reserva", true, true);
        EntradaSalida.saltoDeLinea();
        CasaMatriz.verListadoDeAutosPorOficina();

        Oficina oficina = CasaMatriz.seleccionarOficina();
        oficina.verListadoAutos();

        if (!oficina.tieneAutos())
            return;
        do {
            autoSeleccionado = CasaMatriz.seleccionarAuto(oficina);
            if (!validoParaAgregarAlCarrito(autosSeleccionados, autoSeleccionado, oficina))
                continue;
            autosSeleccionados.add(autoSeleccionado);

        } while (EntradaSalida.leerBoolean("\n¿Desea seleccionar otro auto?", "Si", "No"));

        if (autosSeleccionados.isEmpty()) {
            EntradaSalida.mostrarString("No se seleccionaron autos.", true, true);
            return;
        }

        Fecha fecha = new Fecha(EntradaSalida.leerFechaPosteriorAHoy("\nIngrese la fecha de inicio del alquiler\n"),
                EntradaSalida.leerEnteroConLimites("Ingrese la cantidad de días que desea alquilar el auto (1 - 30): ",
                        Const.LIMITE_INFERIOR_DEFAULT, Const.MAX_CANTIDAD_DIAS));

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
        if (r == null)
            return;

        r.pagarReserva();
    }

    public void retirarAutos() {
        if (!gReservas.hayReservas(EstadoReserva.RESERVADO))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.RESERVADO, this, null);
        if (r == null)
            return;

        r.retiarAutos();
    }

    public void devolverAutos() {
        if (!gReservas.hayReservas(EstadoReserva.ENTREGADO))
            return;

        Reserva r = gReservas.seleccionarReserva(EstadoReserva.ENTREGADO, this, null);
        if (r == null)
            return;

        EntradaSalida.advertencia("Podés devolver los autos en cualquier oficina.");

        Oficina oficina = CasaMatriz.seleccionarOficina();

        r.devolverAutos(oficina);
    }



    @Override
    public Rol getRol() {
        return Rol.CLIENTE;
    }

    public ArrayList<Reserva> getReservas() {
        return reservas;
    }

    private boolean validoParaAgregarAlCarrito(ArrayList<Auto> autos, Auto auto, Oficina oficina) {

        if (auto == null)
            return false;

        if (auto.getOficinaOriginal() != oficina) {
            EntradaSalida.error("El auto seleccionado no es de esta oficina.");
            return false;
        }

        for (Auto a : autos) {
            if (a.equals(auto)) {
                EntradaSalida.advertencia("El auto ya se encuentra en el carrito.");
                return false;
            }

        }
        EntradaSalida.mostrarString("Auto agregado al carrito exitosamente.", true, true);
        return true;
    }
}
