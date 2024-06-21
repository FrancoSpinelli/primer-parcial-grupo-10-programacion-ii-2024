package Interfaces.CapazDeVerMenu;
import java.util.Scanner;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeCrear.CapacidadDeCrearCliente;
import Interfaces.CapazDeCrear.CapacidadDeCrearVendedor;
import Personas.Admin;

public class CapacidadDeVerMenuAdmin implements CapacidadDeVerMenuStrategy {
    
    private Admin admin;

    public CapacidadDeVerMenuAdmin(Admin admin) {
        this.admin = admin;
    }
    
    @Override
    public void verMenu(){
        EntradaSalida.leerString("Menú de Admin: ");
        EntradaSalida.leerString("1. Alta Vendedor: ");
        EntradaSalida.leerString("2. Baja Vendedor: ");
        EntradaSalida.leerString("3. Mostrar Vendedores: ");
        EntradaSalida.leerString("4. Alta Cliente: ");
        EntradaSalida.leerString("5. Baja Cliente: ");
        EntradaSalida.leerString("6. Mostrar Clientes: ");
        EntradaSalida.leerString("7. Salir");
    }

    @Override
    public void seleccionar(){
        Scanner scanner = new Scanner(System.in);
        int seleccion = scanner.nextInt(); //esto está mal, hay que pedir un string
        
        switch(seleccion){
            case 1:
            admin.setCreadorStrategy(new CapacidadDeCrearVendedor());
            admin.crear();
            break;
            case 2:
            break;
            case 3:
            break;
            case 4:
            admin.setCreadorStrategy(new CapacidadDeCrearCliente());
            admin.crear();
            break;
            case 5:
            break;
            case 6:
            break;
            case 7:
            break;
        }
    }
}

