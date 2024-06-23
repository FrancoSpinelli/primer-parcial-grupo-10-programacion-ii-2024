package Reserva;

import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.RowFilter.Entry;

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

    public void entregarAutos(ArrayList<Auto> autos) {
        if (this.estado != EstadoReserva.RESERVADO) {
            EntradaSalida.mostrarString("No se puede entregar autos de esta reserva. Estado: " + this.estado);
            return;
        }

        if (!this.pagado) {
            EntradaSalida.mostrarString("No se puede entregar un auto sin pagar");
            return;
        }

        this.estado = EstadoReserva.ENTREGADO;

        for (Auto auto : autos) {
            auto.consumirGasolina(this.fechas.getCantDias());
            EntradaSalida
                    .mostrarString("\nSe entregaron los siguientes autos de la oficina " + this.oficina.toString() + ":");
            EntradaSalida.mostrarString(auto.verAuto(), true, true);
        }

    }

    public void devolverAutos(Oficina oficina) {

        if (oficina == null) {
            EntradaSalida.mostrarString("Oficina inválida");
            return;
        }

        if (validoParaDevolver()) {
            EntradaSalida.mostrarString("\nSe devolvieron los autos de la reserva #" + this.id);
            this.estado = EstadoReserva.DEVUELTO;
        }

        oficina.recibirAutos(this.autos);
    }

    private boolean validoParaDevolver() {

        boolean valido = true;
        if (this.estado != EstadoReserva.ENTREGADO) {
            EntradaSalida.mostrarString("No se puede devolver autos de esta reserva. Estado: " + this.estado);
            return false;
        }

        for (Auto auto : autos) {
            auto.consultarGasolina();
            if (!auto.tieneTanqueLleno()) {
                valido = false;
            }
        }

        if (!valido) {
            EntradaSalida.mostrarString(
                    "No se puede devolver autos de esta reserva. Todos los autos se deben devolver con el tanque lleno.");

            boolean cargar = EntradaSalida.leerBoolean("Deseas cargar los tanques", "Si", "No");
            if (cargar) {
                for (Auto auto : autos) {
                    auto.recargarGasolina();
                }
                valido = true;
                EntradaSalida.mostrarString("Se cargaron los tanques de los autos.");
            }
        }

        return valido;
    }

    public void cancelarReserva() {
        if (!validoParaCancelar()) {
            EntradaSalida.mostrarString("No se puede cancelar la reserva ya que su estado es: " + this.estado);
            return;
        }
        this.estado = EstadoReserva.CANCELADO;
        EntradaSalida.mostrarString("Reserva #" + this.id + " cancelada.", true, true);
    }

    private Boolean validoParaCancelar() {
        return this.estado == EstadoReserva.PENDIENTE || this.estado == EstadoReserva.PENDIENTE_DE_PAGO
                || this.estado == EstadoReserva.RESERVADO;
    }

    public Oficina getOficina() {
        return this.oficina;
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
        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString("Reserva #" + this.id + " aceptada. Precio final: $" + this.precioFinal
                + " Estado de la reserva: " + this.estado, true, true);
    }

    public void rechazarReserva() {
        this.estado = EstadoReserva.RECHAZADO;
        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString("Reserva #" + this.id + " rechazada.", true, true);
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public int getId() {
        return this.id;
    }

    public void retiarAutos() {
        oficina.getVendedor().entregarAutos(this);
    }

    public ArrayList<Auto> getAutos() {
        return this.autos;
    }

}
