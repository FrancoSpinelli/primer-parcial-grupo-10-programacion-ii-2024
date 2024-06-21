package Interfaces.CapazDeListar;

import java.util.ArrayList;
import Personas.Persona;

public class CapacidadDeListarPersonas implements CapacidadDeListarStrategy<Persona>{
    
    @Override
    public ArrayList<Persona> listar(ArrayList<?> items){
        ArrayList<Persona> personas = new ArrayList<>();
        for(Object item : items){
            personas.add((Persona)item);
        }
        return personas;
    }
}
