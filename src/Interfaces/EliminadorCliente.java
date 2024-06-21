package Interfaces;

import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Personas.Persona;

public class EliminadorCliente implements EliminadorStrategy{
    
    @Override
    public void eliminar(){
        int dni;
        Persona personaEliminar;

        EntradaSalida.mostrarString("ELIMINAR");
        CasaMatriz.mostrarListadoPersonas(new ListadorClientes());
        dni = EntradaSalida.leerEntero("Ingrese el DNI a eliminar");
        personaEliminar = CasaMatriz.buscarPersona(dni);
        //VALIDAR
        CasaMatriz.eliminarPersona(personaEliminar);
    }
}
