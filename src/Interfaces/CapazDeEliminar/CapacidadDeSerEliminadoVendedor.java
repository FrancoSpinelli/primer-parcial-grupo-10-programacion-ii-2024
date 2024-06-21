package Interfaces.CapazDeEliminar;

import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeListar.CapacidadDeListarClientes;
import Personas.Persona;

public class CapacidadDeSerEliminadoCliente implements CapacidadDeEliminarStrategy{
    
    CasaMatriz casaMatriz;

    @Override
    public void eliminar(){
        int dni;
        Persona personaEliminar;

        EntradaSalida.mostrarString("ELIMINAR");
        casaMatriz.mostrarListadoPersonas(new CapacidadDeListarClientes());
        dni = EntradaSalida.leerEntero("Ingrese el DNI a eliminar");
        personaEliminar = CasaMatriz.buscarPersona(dni);
        //VALIDAR
        CasaMatriz.eliminarPersona(personaEliminar);
    }
}
