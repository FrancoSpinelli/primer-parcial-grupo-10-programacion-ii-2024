package Interfaces.CapazDeVerMenu;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeCrear.CapacidadDeCrearAdmin;
import Interfaces.CapazDeCrear.CapacidadDeCrearCliente;
import Interfaces.CapazDeCrear.CapacidadDeCrearVendedor;
import Personas.Admin;

public class CapacidadDeVerMenuAdmin implements CapacidadDeVerMenu {
    
    private Admin admin;

    public CapacidadDeVerMenuAdmin(Admin admin) {
        this.admin = admin;
    }
    
    @Override
    public void verMenu(){
        EntradaSalida.mostrarString("Menú de Admin");
        EntradaSalida.mostrarString("1. Alta Vendedor");
        EntradaSalida.mostrarString("2. Baja Vendedor");
        EntradaSalida.mostrarString("3. Alta Cliente");
        EntradaSalida.mostrarString("4. Baja Cliente");
        EntradaSalida.mostrarString("5. Alta Admin");
        EntradaSalida.mostrarString("6. Baja Admin");
        EntradaSalida.mostrarString("7. Mostrar Usuarios");
        EntradaSalida.mostrarString("0. Salir");
    }

    @Override
    public int seleccionar(){
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 0, 7);
        
        switch(seleccion){
            case 1:
            admin.setCreadorStrategy(new CapacidadDeCrearVendedor());
            admin.crear();
            break;
            case 2:
            EntradaSalida.mostrarString("Baja Vendedor");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 3:
            admin.setCreadorStrategy(new CapacidadDeCrearCliente());
            admin.crear();
            break;
            case 4:
            EntradaSalida.mostrarString("Baja Cliente");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 5:
            admin.setCreadorStrategy(new CapacidadDeCrearAdmin());
            admin.crear();
            break;
            case 6:
            EntradaSalida.mostrarString("Baja Admin");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 7:
            EntradaSalida.mostrarString("Mostrar todo");
            EntradaSalida.mostrarString("NO IMPLEMENTADO");
            break;
            case 8:
            EntradaSalida.mostrarString("Salir");
            break;
        }
        return seleccion;
    }
}

