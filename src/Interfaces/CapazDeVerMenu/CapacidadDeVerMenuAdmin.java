package Interfaces.CapazDeVerMenu;

import EntradaSalida.EntradaSalida;
import Personas.Admin;

public class CapacidadDeVerMenuAdmin implements CapacidadDeVerMenu {

    private Admin admin;

    public CapacidadDeVerMenuAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public void verMenu() {
        String msgMenu = "Menú de " + admin.getNombre() + " (Admin)";

        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString(msgMenu, true, true);
        EntradaSalida.mostrarString("\t 1. Gestionar personas");
        EntradaSalida.mostrarString("\t 2. Gestionar autos");
        EntradaSalida.mostrarString("\t 3. Gestionar oficinas");
/*         EntradaSalida.mostrarString("\t 4. Gestionar reservas");
 */        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString("\t 9. Limpiar pantalla");
        EntradaSalida.mostrarString("\t 0. Salir ", false, true);
        EntradaSalida.saltoDeLinea();
    }

    @Override
    public int seleccionar() {
        int seleccion = EntradaSalida.leerEntero("Ingrese su elección: ");
        EntradaSalida.saltoDeLinea();

        switch (seleccion) {
            case 1:
                EntradaSalida.limpiarPantalla();
                admin.gestionarPersonas();
                break;
            case 2:
                EntradaSalida.limpiarPantalla();
                admin.gestionarAutos();
                break;
            case 3:
                EntradaSalida.limpiarPantalla();
                admin.gestionarOficinas();
                break;
/*             case 4:
                EntradaSalida.limpiarPantalla();
                admin.gestionarReservas();
                break; */
            case 9:
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
