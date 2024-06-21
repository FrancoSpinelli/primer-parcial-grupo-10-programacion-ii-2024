package Reserva;
import java.util.ArrayList;
import java.util.Date;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Fecha.Fecha;
import Oficina.Oficina;
import Personas.Cliente;
import enums.EstadoReserva;

public class Reserva {
    private String id;
    private float precioFinal;
    private Fecha fechas;
    private ArrayList<Auto> autos;
    private EstadoReserva estado;
    private Boolean pagado;
    private Oficina oficina;
    private Cliente cliente;

    public Reserva(String id, ArrayList<Auto> autos, Cliente cliente, Oficina oficina, Date fechaInicio, Date fechaFin) {
        this.id = id;
        this.autos = autos;
        this.fechas = new Fecha(fechaInicio, fechaFin);
        this.estado = EstadoReserva.PENDIENTE;
        this.pagado = false;
        this.cliente = cliente;
        this.oficina = oficina;

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

    public void finalizarReserva() {
        this.estado = EstadoReserva.FINALIZADO;
    }

    public void pagarReserva() {
        this.pagado = true;
    }

    public String verReserva () {
        return "Reserva #" + this.id + " - " + this.estado + " - " + this.fechas.toString();
    }

    private void calcularPrecioFinal() {
    }

}
