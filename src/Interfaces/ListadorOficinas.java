package Interfaces;

import java.util.List;
import java.util.ArrayList;
import Oficina.Oficina;

public class ListadorOficinas implements ListadorStrategy<Oficina>{
    
    @Override
    public List<Oficina> listar(List<?> items){
        List<Oficina> oficinas = new ArrayList<>();
        for(Object item : items){
            if(item instanceof Oficina){
                oficinas.add((Oficina) item);
            }
        }
        return oficinas;
    }
}
