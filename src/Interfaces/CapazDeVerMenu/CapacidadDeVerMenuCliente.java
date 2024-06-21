package Interfaces.CapazDeVerMenu;
import EntradaSalida.EntradaSalida;
import Personas.Cliente;

public class CapacidadDeVerMenuCliente implements CapacidadDeVerMenu {

    private Cliente cliente;

    public CapacidadDeVerMenuCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void verMenu(){
        EntradaSalida.mostrarString("Menú de Cliente");
        EntradaSalida.mostrarString("1. Agregar a favoritos");
        EntradaSalida.mostrarString("2. Sacar de favoritos");
        EntradaSalida.mostrarString("3. Crear reserva");
        EntradaSalida.mostrarString("4. Cancelar reserva");
        EntradaSalida.mostrarString("5. Retirar autos de la oficina");
        EntradaSalida.mostrarString("6. devolver autos");
        EntradaSalida.mostrarString("0. Salir");
    }
    public int seleccionar(){
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 0, 6);
        
        switch(seleccion){
            case 1:
            EntradaSalida.mostrarString("Agregar a favoritos");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 2:
            EntradaSalida.mostrarString("Sacar de favoritos");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 3:
            EntradaSalida.mostrarString("Crear reserva");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 4:
            EntradaSalida.mostrarString("Cancelar reserva");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 5:
            EntradaSalida.mostrarString("Retirar autos de la oficina");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 6:
            EntradaSalida.mostrarString("Devolver autos");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 7:
            EntradaSalida.mostrarString("Salir");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
        }
        return seleccion;
    }
}
