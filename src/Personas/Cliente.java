package Personas;

import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Fecha.Fecha;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuCliente;
import Oficina.Oficina;
import Reserva.Reserva;
import enums.EstadoReserva;

public class Cliente extends Persona {
    private ArrayList<Auto> favoritos = new ArrayList<Auto>();
    private ArrayList<Reserva> reservas = new ArrayList<Reserva>();

    public Cliente(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email,
            String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        this.setMenuStrategy(new CapacidadDeVerMenuCliente());
        this.favoritos = new ArrayList<Auto>();
    }

    public void agregarFavoritos(Auto a) {

    }

    public void sacarFavoritos(Auto a) {

    }

    public void crear(ArrayList<Oficina> oficinas) {
        int autoElegido = 0;
        int idOficina = 0;
        ArrayList<Auto> autosSeleccionados = new ArrayList<Auto>();

        EntradaSalida.mostrarString("Listado de autos disponibles: \n");
        for (Oficina oficina : oficinas) {
            EntradaSalida.mostrarString(oficina.toString(), true, true);
            for (Auto auto : oficina.getAutos()) {
                EntradaSalida.mostrarString(auto.toString(), true, true);
            }
        }

        idOficina = EntradaSalida.leerEnteroConLimites("Selecione una Oficina (" + 1 + " - " + oficinas.size() + "):",
                1, oficinas.size() + 1);

        Oficina oficina = oficinas.get(idOficina - 1);
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
                EntradaSalida.mostrarString(auto.toString() + " agregado a la reserva.", true, true);
            }
        } while (autoElegido != 0);

        Fecha fecha = new Fecha(EntradaSalida.leerFecha("\nIngrese la fecha de inicio del alquiler"),
                EntradaSalida.leerEnteroConLimites("Ingrese la cantidad de días que desea alquilar el auto: ", 1, 30));

        EntradaSalida.mostrarString("\nEstás por realizar la siguiente reserva: \n");

        EntradaSalida.mostrarString(fecha.toString());

        for (Auto auto : autosSeleccionados) {
            EntradaSalida.mostrarString(auto.toString(), true, true);
        }

        Boolean confirmar = EntradaSalida.leerBoolean("\n¿Desea confirmar la reserva?", "Si", "Cancelar");
        if (confirmar) {
            Reserva r = new Reserva(1, autosSeleccionados, this, oficina, fecha.getInicio(),
                    fecha.getCantDias());
            System.out.println("Reserva creada" + r.toString());
            reservas.add(r);
            oficina.agregarReserva(r);
            EntradaSalida.mostrarString("Reserva realizada con éxito.", true, true);
        } else {
            EntradaSalida.mostrarString("Reserva cancelada.", true, true);
        }

        EntradaSalida.mostrarString("Tus reservas: ");

    }

    public void cancelarReserva(Reserva r) {
        r.cancelarReserva();
    }

    public void verReservas() {
        for (Reserva r : reservas) {
            EntradaSalida.mostrarString(r.toString(), true, true);
        }
    }

    public void retirarAutos(Reserva r) {
        
    }

    public void devolverAuto(Reserva r, Oficina o) {

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

        EntradaSalida.mostrarString("No se encontró la reserva.", true, true);
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
}
