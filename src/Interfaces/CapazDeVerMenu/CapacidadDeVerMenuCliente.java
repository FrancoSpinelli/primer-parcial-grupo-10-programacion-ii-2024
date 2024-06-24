package Interfaces.CapazDeVerMenu;

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
        String msgMenu = "Menú de " + cliente.getNombre() + " (Cliente)";

        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString(msgMenu, true, true);
        EntradaSalida.mostrarString("\t 1. Ver Autos");
        EntradaSalida.mostrarString("\t 2. Ver Reservas");
        EntradaSalida.mostrarString("\t 3. Crear reserva");
        EntradaSalida.mostrarString("\t 4. Cancelar reserva");
        EntradaSalida.mostrarString("\t 5. Abonar reserva");
        EntradaSalida.mostrarString("\t 6. Retirar autos");
        EntradaSalida.mostrarString("\t 7. Devolver autos");
        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString("\t 9. Limpiar pantalla");
        EntradaSalida.mostrarString("\t 0. Salir ", false, true);
        EntradaSalida.saltoDeLinea();
    }

    public int seleccionar() {
        int seleccion = EntradaSalida.leerEntero("Ingrese su elección: ");
        EntradaSalida.saltoDeLinea();
        switch (seleccion) {
            case 1:
                EntradaSalida.limpiarPantalla();
                CasaMatriz.verListadoDeAutosPorOficina();
                break;
            case 2:
                EntradaSalida.limpiarPantalla();
                cliente.verReservas();
                break;
            case 3:
                EntradaSalida.limpiarPantalla();
                cliente.crear();
                break;
            case 4:
                EntradaSalida.limpiarPantalla();
                cliente.cancelarReserva();
                break;
            case 5:
                EntradaSalida.limpiarPantalla();
                cliente.pagarReserva();
                break;
            case 6:
                EntradaSalida.limpiarPantalla();
                cliente.retirarAutos();
                break;
            case 7:
                EntradaSalida.limpiarPantalla();
                cliente.devolverAutos();
                break;
            case 9:
                EntradaSalida.limpiarPantalla();
                EntradaSalida.limpiarPantalla();
                break;
            case 0:
                EntradaSalida.limpiarPantalla();
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
        return seleccion;
    }
}
