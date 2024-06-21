package Interfaces.CapazDeListar;

import java.util.ArrayList;
import Auto.Auto;

public class CapacidadDeListarAutos implements CapacidadDeListarStrategy<Auto>{
   
    @Override
    public ArrayList<Auto> listar(ArrayList<?> items){
        ArrayList<Auto> Autos = new ArrayList<>();
        for(Object item : items){
            Autos.add((Auto) item);
        }
        return Autos;
    }
}
