import java.util.ArrayList;
import java.util.Date;

import enums.EstadoReserva;

public class Reserva {
    private float precioFinal;
    private Fecha fechas;
    private ArrayList<Auto> autos;
    private EstadoReserva estado;

    public Reserva(Auto auto, Cliente cliente, Date fechaInicio, Date fechaFin) {
        this.autos = new ArrayList<Auto>();
        this.autos.add(auto);
        this.fechas = new Fecha(fechaInicio, fechaFin);
        this.estado = EstadoReserva.PENDIENTE;
    }

    public void crearReserva() {

    }

    public void rechazarReserva() {

    }

    public void aceptarReserva() {

    }

}
