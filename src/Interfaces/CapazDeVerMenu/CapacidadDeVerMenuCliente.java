package Interfaces.CapazDeVerMenu;

import java.util.ArrayList;

import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Personas.Cliente;

public class CapacidadDeVerMenuCliente implements CapacidadDeVerMenu {

    private Cliente cliente;

    public CapacidadDeVerMenuCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void verMenu() {
        EntradaSalida.mostrarString("\nMenú de Cliente\n");

        EntradaSalida.mostrarString("1. Ver Autos");
        // EntradaSalida.mostrarString("1. Agregar a favoritos");
        // EntradaSalida.mostrarString("2. Sacar de favoritos");
        EntradaSalida.mostrarString("2. Ver Reservas");
        EntradaSalida.mostrarString("3. Crear reserva");
        EntradaSalida.mostrarString("4. Cancelar reserva");
        EntradaSalida.mostrarString("5. Retirar autos");
        EntradaSalida.mostrarString("6. Devolver autos");
        EntradaSalida.mostrarString("0. Salir\n ");
    }

    public int seleccionar() {
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 0, 6);

        switch (seleccion) {
            case 1:
                CasaMatriz.verListadoDeAutosPorOficina();
                break;
            case 2:
                cliente.verReservas();
                break;
            case 3:
                cliente.crear();
                break;
            case 4:
                cliente.cancelarReserva();
                break;
            case 5:
                cliente.retirarAutos();
                break;
            case 6:
                cliente.devolverAutos();
                break;
            case 7:
                EntradaSalida.mostrarString("Salir");
                EntradaSalida.mostrarString("NO IMPLEMENTADO");
                break;
        }
        return seleccion;
    }
}
