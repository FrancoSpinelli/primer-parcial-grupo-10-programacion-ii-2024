package Interfaces;

import java.util.ArrayList;
import java.util.List;
import Personas.Vendedor;


public class ListadorVendedores implements ListadorStrategy<Vendedor> {
    
    @Override
    public List<Vendedor> listar(List<?> items){
        List<Vendedor> vendedores = new ArrayList<>();
        for (Object item : items){
            if (item instanceof Vendedor){
                vendedores.add((Vendedor) item);
            }
        }
        return vendedores;
    }
}