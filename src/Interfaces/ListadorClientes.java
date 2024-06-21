package Interfaces;

import java.util.ArrayList;
import java.util.List;
import Personas.Cliente;

public class ListadorClientes implements ListadorStrategy<Cliente> {
    
    @Override
    public List<Cliente> listar(List<?> items){
        List<Cliente> clientes = new ArrayList<>();
        for (Object item : items){
            if (item instanceof Cliente){
                clientes.add((Cliente) item);
            }
        }
        return clientes;
    }
}
