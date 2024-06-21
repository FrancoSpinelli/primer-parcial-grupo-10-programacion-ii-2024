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
        EntradaSalida.mostrarString("Menú de Cliente");
        EntradaSalida.mostrarString("1. Listar reservas pendientes");
        EntradaSalida.mostrarString("2. Aceptar reserva");
        EntradaSalida.mostrarString("3. Rechazar reserva");
        EntradaSalida.mostrarString("4. Entregar auto");
        EntradaSalida.mostrarString("0. Salir");   
}

public int seleccionar(){
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 0, 4);
        
        switch(seleccion){
            case 1:
            EntradaSalida.mostrarString("1. Listar reservas pendientes");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 2:
            EntradaSalida.mostrarString("2. Aceptar reserva");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 3:
            EntradaSalida.mostrarString("3. Rechazar reserva");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 4:
            EntradaSalida.mostrarString("4. Entregar auto");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
        }
        return seleccion;
    }
}
