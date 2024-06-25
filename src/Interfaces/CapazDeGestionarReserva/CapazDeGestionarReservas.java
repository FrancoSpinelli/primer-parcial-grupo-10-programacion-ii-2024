package Interfaces.CapazDeGestionarReserva;

import Oficina.Oficina;
import Personas.Cliente;
import Reserva.Reserva;
import enums.EstadoReserva;

public interface CapazDeGestionarReservas {
    void verListadoReservasPorEstado(EstadoReserva estado);
    Reserva seleccionarReserva(EstadoReserva estado, Cliente cliente, Oficina oficina);
    Reserva getReserva(int id, Cliente c);
}