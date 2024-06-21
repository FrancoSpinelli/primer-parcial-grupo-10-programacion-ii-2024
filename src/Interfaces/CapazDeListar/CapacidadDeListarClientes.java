package Interfaces.CapazDeListar;

import java.util.ArrayList;
import Personas.Cliente;

public class CapacidadDeListarClientes implements CapacidadDeListarStrategy<Cliente> {
    
    @Override
    public ArrayList<Cliente> listar(ArrayList<?> items){
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(Object item : items){
            if(item instanceof Cliente){
            clientes.add((Cliente) item);
            }
        }
        return clientes;
    }
}
