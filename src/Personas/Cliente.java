package Personas;

import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Fecha.Fecha;
import Interfaces.CapazDeEliminar.CapacidadDeSerEliminado;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuCliente;
import Oficina.Oficina;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Cliente extends Persona implements CapacidadDeSerEliminado {
    private ArrayList<Auto> favoritos = new ArrayList<Auto>();
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public Cliente(int id, int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(id, dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuCliente(this));
        this.favoritos = new ArrayList<Auto>();

    }

    public void agregarFavoritos(Auto a) {

    }

    public void sacarFavoritos(Auto a) {

    }

    public void crear() {
        int autoElegido = 0;
        int idOficina = 0;
        ArrayList<Auto> autosSeleccionados = new ArrayList<Auto>();
        ArrayList<Oficina> oficinas = CasaMatriz.getOficinas();

        CasaMatriz.verListadoDeAutosPorOficina();

        Oficina oficina = CasaMatriz.seleccionarOficina();
        oficina.verListadoAutos();

        do {
            autoElegido = EntradaSalida.leerEntero(
                    "\nIngrese el ID del auto que desea alquilar (0 para salir):");
            if (autoElegido != 0) {
                Auto auto = oficina.getAuto(autoElegido);
                if (auto == null)
                    continue;
                if (autoYaReservado(autosSeleccionados, auto))
                    continue;
                autosSeleccionados.add(auto);
                EntradaSalida.mostrarString(auto.verAuto() + " seleccionado con éxito.", true, true);
            }
        } while (autoElegido != 0);

        if (autosSeleccionados.size() == 0) {
            EntradaSalida.mostrarString("No se seleccionaron autos.", true, true);
            return;
        }

        Fecha fecha = new Fecha(EntradaSalida.leerFecha("\nIngrese la fecha de inicio del alquiler"),
                EntradaSalida.leerEnteroConLimites("Ingrese la cantidad de días que desea alquilar el auto: ", 1, 30));

        EntradaSalida.mostrarString("\nEstás por realizar la siguiente reserva: \n\n" + fecha.toString() + "",
                true, true);

        for (Auto auto : autosSeleccionados) {
            EntradaSalida.mostrarString(auto.verAuto(), true, true);
        }

        Boolean confirmar = EntradaSalida.leerBoolean("\n¿Desea confirmar la reserva?", "Si", "Cancelar");
        if (confirmar) {
            Reserva r = new Reserva(1, autosSeleccionados, this, oficina, fecha.getInicio(),
                    fecha.getCantDias());
            reservas.add(r);
            oficina.agregarReserva(r);
        } else {
            EntradaSalida.mostrarString("Reserva cancelada.", true, true);
        }
    }

    public void cancelarReserva() {
        verReservas();
        int id = EntradaSalida.leerEntero("Ingrese el ID de la reserva que desea cancelar: ");
        Reserva r = getReserva(id, this);
        if (r == null) {
            EntradaSalida.mostrarString("No se encontró la reserva.", true, true);
            return;
        }
        r.cancelarReserva();
    }

    public void verReservas() {

        if (reservas.size() == 0) {
            EntradaSalida.mostrarString("No tenés reservas.", true, true);
            return;
        }

        EntradaSalida.mostrarString("\nListado de reservas: ");
        for (Reserva r : reservas) {
            EntradaSalida.mostrarString(r.toString(), true, true);
        }
    }

    public void verReservasEntregado() {

        if (reservas.size() == 0) {
            EntradaSalida.mostrarString("No tenés reservas.", true, true);
            return;
        }

        EntradaSalida.mostrarString("\nListado de reservas: ");
        for (Reserva r : reservas) {
            if (r.getEstado() == EstadoReserva.ENTREGADO) {
                EntradaSalida.mostrarString(r.toString(), true, true);
            }
        }
    }

    public void retirarAutos() {
        verReservas();
        int id = EntradaSalida.leerEntero("Ingrese el ID de la reserva que desea retirar: ");
        Reserva r = getReserva(id, this);
        if (r == null) {
            EntradaSalida.mostrarString("No se encontró la reserva.", true, true);
            return;
        }
        r.retiarAutos();
    }

    public void devolverAutos() {

        verReservasEntregado();
        int id = EntradaSalida.leerEntero("Ingrese el ID de la reserva que desea devolver: ");
        Reserva r = getReserva(id, this);
        if (r == null) {
            EntradaSalida.mostrarString("No se puede devolver una reserva que no existe.", true, true);
            return;
        }

        Oficina oficina = CasaMatriz.seleccionarOficina();

        r.devolverAutos(oficina);
    }

    public void pagarReserva(Reserva r) {
        if (r == null) {
            EntradaSalida.mostrarString("No  puedes pagar una reserva que no existe.", true, true);
            return;
        }

        if (r.getEstado() != EstadoReserva.PENDIENTE_DE_PAGO) {
            EntradaSalida.mostrarString("No puedes pagar una reserva que no está pendiente de pago.", true, true);
            return;
        }
        r.pagarReserva();
    }

    public Reserva getReserva(int id, Cliente c) {
        for (Reserva r : reservas) {
            if (r.getId() == id && r.getCliente().equals(c)) {
                return r;
            }
        }
        return null;
    }

    private boolean autoYaReservado(ArrayList<Auto> autos, Auto auto) {
        for (Auto a : autos) {
            if (a.equals(auto)) {
                EntradaSalida.mostrarString("El auto ya fue seleccionado.", true, true);
                return true;
            }
        }
        return false;
    }

    @Override
    public void eliminar() {
        EntradaSalida.mostrarString("No puede eliminar");
    }

    @Override
    public boolean capacidadDeSerEliminado() {
        return true;
    }

}
