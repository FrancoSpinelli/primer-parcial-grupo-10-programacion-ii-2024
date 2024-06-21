package Interfaces.CapazDeListar;

import java.util.ArrayList;
import Personas.Admin;

public class CapacidadDeListarAdmins implements CapacidadDeListarStrategy<Admin>{
    
    @Override
    public ArrayList<Admin> listar(ArrayList<?> items){
        ArrayList<Admin> admins = new ArrayList<>();
        for(Object item : items){
            if(item instanceof Admin){
            admins.add((Admin) item);
            }
        }
        return admins;
    }
}
