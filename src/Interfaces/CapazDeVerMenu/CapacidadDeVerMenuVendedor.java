package Interfaces.CapazDeVerMenu;

import EntradaSalida.EntradaSalida;
import Personas.Vendedor;

public class CapacidadDeVerMenuVendedor implements CapacidadDeVerMenu {
    
    private Vendedor vendedor;

    public CapacidadDeVerMenuVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    @Override
    public void verMenu(){
        EntradaSalida.saltoDeLinea();
        EntradaSalida.mostrarString("\nMenú de Vendedor\n", true, true);
        EntradaSalida.mostrarString("\t 1. Listar reservas");
        EntradaSalida.mostrarString("\t 2. Listar reservas pendientes");
        EntradaSalida.mostrarString("\t 3. Aceptar reserva");
        EntradaSalida.mostrarString("\t 4. Rechazar reserva");
        // EntradaSalida.mostrarString("\t 4. Entregar auto");
        EntradaSalida.mostrarString("\t 0. Salir", false, true);
        EntradaSalida.saltoDeLinea();   
}

public int seleccionar(){
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 0, 4);
        EntradaSalida.saltoDeLinea();
        switch(seleccion){
            case 1:
            vendedor.listarReservas();
            break;
            case 2:
            vendedor.listarReservasPendientes();
            break;
            case 3:
            vendedor.aceptarReserva();
            break;
            case 4:
            vendedor.rechazarReserva();
            break;
/*             case 5:
            EntradaSalida.mostrarString("4. Entregar auto");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break; */
        }
        return seleccion;
    }
}
