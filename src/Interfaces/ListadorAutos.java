package Interfaces;

import java.util.ArrayList;
import java.util.List;

import Auto.Auto;

public class ListadorAutos implements ListadorStrategy<Auto>{
   
    @Override
    public List<Auto> listar(List<?> items){
        List<Auto> Autos = new ArrayList<>();
        for(Object item : items){
            if(item instanceof Auto){
                Autos.add((Auto) item);
            }
        }
        return Autos;
    }
}
