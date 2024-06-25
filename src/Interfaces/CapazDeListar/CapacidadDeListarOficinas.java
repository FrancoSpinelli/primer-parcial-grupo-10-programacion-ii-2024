package Interfaces.CapazDeListar;

import java.util.ArrayList;
import Oficina.Oficina;

public class CapacidadDeListarOficinas implements CapacidadDeListarStrategy<Oficina>{
    
    @Override
    public ArrayList<Oficina> listar(ArrayList<?> items){
        ArrayList<Oficina> oficinas = new ArrayList<>();
        for(Object item : items){
            oficinas.add((Oficina) item);
        }
        return oficinas;
    }
}
