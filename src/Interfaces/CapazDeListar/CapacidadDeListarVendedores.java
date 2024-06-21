package Interfaces.CapazDeListar;

import java.util.ArrayList;
import Personas.Vendedor;


public class CapacidadDeListarVendedores implements CapacidadDeListarStrategy<Vendedor> {
    
    @Override
    public ArrayList<Vendedor> listar(ArrayList<?> items){
        ArrayList<Vendedor> vendedores = new ArrayList<>();
        for (Object item : items){
            if(item instanceof Vendedor){
                vendedores.add((Vendedor) item);
            }
        }
        return vendedores;
    }
}