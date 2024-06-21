package Reserva;

import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Fecha.Fecha;
import Oficina.Oficina;
import Personas.Cliente;
import enums.EstadoReserva;

public class Reserva {
    private int id;
    private float precioFinal;
    private Fecha fechas;
    private ArrayList<Auto> autos;
    private EstadoReserva estado;
    private Boolean pagado;
    private Oficina oficina;
    private Cliente cliente;

    @Override
    public String toString() {
        return "Reserva #" + this.id + " - " + this.estado + " - " + this.fechas.toString() + " - Total: $"
                + ((int) this.precioFinal);
    }

    public Reserva(int id, ArrayList<Auto> autos, Cliente cliente, Oficina oficina, LocalDate fechaInicio,
            int cantDias) {
        this.id = id;
        this.autos = autos;
        this.fechas = new Fecha(fechaInicio, cantDias);
        this.precioFinal = calcularPrecioFinal();
        this.estado = EstadoReserva.PENDIENTE;
        this.pagado = false;
        this.cliente = cliente;
        this.oficina = oficina;

        EntradaSalida.mostrarString(this.toString(), true, true);
    }

    public void crearReserva() {
        this.estado = EstadoReserva.RESERVADO;
    }

    public void rechazarReserva() {
        this.estado = EstadoReserva.RECHAZADO;
    }

    public void entregarAutos() {
        if (this.estado != EstadoReserva.RESERVADO) {
            EntradaSalida.mostrarString("No se puede entregar un auto sin reservar");
            return;
        }

        if (!this.pagado) {
            EntradaSalida.mostrarString("No se puede entregar un auto sin pagar");
            return;
        }

        this.estado = EstadoReserva.ENTREGADO;
    }

    public void devolverAutos() {
        this.estado = EstadoReserva.DEVUELTO;
    }

    public void cancelarReserva() {
        this.estado = EstadoReserva.CANCELADO;
    }

    public void finalizarReserva() {
        this.estado = EstadoReserva.FINALIZADO;
    }

    public void pagarReserva() {
        this.pagado = true;
        this.estado = EstadoReserva.RESERVADO;
        EntradaSalida.mostrarString(
                "Reserva #" + this.id + ": pagaste $" + (int) this.precioFinal
                        + ". Ya podes retirar los autos reservados.");
    }

    public String verReserva() {
        return "Reserva #" + this.id + " - " + this.estado + " - " + this.fechas.toString();
    }

    public EstadoReserva getEstado() {
        return this.estado;
    }

    private float calcularPrecioFinal() {
        float precioFinal = 0;
        for (Auto auto : autos) {
            precioFinal += auto.getPrecioPorDia() * fechas.getCantDias();
        }
        return precioFinal;
    }

    public void aceptarReserva() {
        this.estado = EstadoReserva.PENDIENTE_DE_PAGO;

        EntradaSalida.mostrarString("Reserva #" + this.id + " aceptada. Precio final: $" + this.precioFinal
                + "Estado de la reserva: " + this.estado);
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public int getId() {
        return this.id;
    }

}
