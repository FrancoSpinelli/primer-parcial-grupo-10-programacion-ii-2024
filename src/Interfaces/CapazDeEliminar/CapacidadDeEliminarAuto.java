package Interfaces.CapazDeEliminar;

import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeListar.CapacidadDeListarClientes;
import Personas.Persona;

public class CapacidadDeEliminarCliente implements CapazDeEliminar{
    
    CasaMatriz casaMatriz;

    @Override
    public void eliminar() implements capazDeSerEliminado{
        int dni;
        Persona personaEliminar;

        EntradaSalida.mostrarString("ELIMINAR");
        casaMatriz.mostrarListadoPersonas(new CapacidadDeListarClientes());
        dni = EntradaSalida.leerEntero("Ingrese el DNI a eliminar");
        personaEliminar = CasaMatriz.buscarPersona(dni);
        //VALIDAR
        CasaMatriz.eliminarPersona(personaEliminar);
    }

    public boolean capazDeSerEliminado(){
        return true;
    }
}
