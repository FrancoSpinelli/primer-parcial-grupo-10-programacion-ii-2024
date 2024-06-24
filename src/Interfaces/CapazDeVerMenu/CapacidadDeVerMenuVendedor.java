package Interfaces.CapazDeVerMenu;

import EntradaSalida.EntradaSalida;
import Personas.Vendedor;

public class CapacidadDeVerMenuVendedor implements CapacidadDeVerMenu {

    private Vendedor vendedor;

    public CapacidadDeVerMenuVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public void verMenu() {

        String msgMenu = "Menú de " + vendedor.getNombre() + " (Vendedor)";
        if (vendedor.getOficina() != null) {
            msgMenu += " - Oficina #" + vendedor.getOficina().getId();
        } else {
            msgMenu += " - Sin oficina asignada";
        }

        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString(msgMenu, true, true);
        EntradaSalida.mostrarString("\t 1. Listar reservas");
        EntradaSalida.mostrarString("\t 2. Listar reservas pendientes");
        EntradaSalida.mostrarString("\t 3. Aceptar reserva");
        EntradaSalida.mostrarString("\t 4. Rechazar reserva");
        // EntradaSalida.mostrarString("\t 4. Entregar auto");
        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString("\t 9. Limpiar pantalla");
        EntradaSalida.mostrarString("\t 0. Salir", false, true);
        EntradaSalida.saltoDeLinea();
    }

    public int seleccionar() {
        int seleccion = EntradaSalida.leerEntero("Ingrese su elección: ");
        EntradaSalida.saltoDeLinea();
        switch (seleccion) {
            case 1:
                EntradaSalida.limpiarPantalla();
                vendedor.listarReservas();
                break;
            case 2:
                EntradaSalida.limpiarPantalla();
                vendedor.listarReservasPendientes();
                break;
            case 3:
                EntradaSalida.limpiarPantalla();
                vendedor.aceptarReserva();
                break;
            case 4:
                EntradaSalida.limpiarPantalla();
                vendedor.rechazarReserva();
                break;
            case 9:
                EntradaSalida.limpiarPantalla();
                break;
            case 0:
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
        return seleccion;
    }
}
