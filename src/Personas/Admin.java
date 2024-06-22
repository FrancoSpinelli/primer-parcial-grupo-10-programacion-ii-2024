package Personas;
import java.time.LocalDate;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeCrear.CapacidadDeCrearStrategy;
import Interfaces.CapazDeEliminar.CapacidadDeSerEliminado;
import Interfaces.CapazDeListar.CapacidadDeListarPersonas;
import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuAdmin;
import Oficina.Oficina;
import CasaMatriz.CasaMatriz;

public class Admin extends Persona implements CapacidadDeSerEliminado{
    private CapacidadDeListarStrategy<?> listadorStrategy;
    private CapacidadDeCrearStrategy creadorStrategy;

    public Admin(int id, int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email, String contrasenia) {
        super(id, dni, nombre, fechaNacimiento, telefono, email, contrasenia);
    }

    public void setCreadorStrategy(CapacidadDeCrearStrategy creadorStrategy){
        this.creadorStrategy = creadorStrategy;
    }

    public void configurarEstrategias(CasaMatriz casaMatriz) {
        this.setListadorStrategy(new CapacidadDeListarPersonas());
        this.setMenuStrategy(new CapacidadDeVerMenuAdmin(this, casaMatriz));
    }

    public void crear() {
        creadorStrategy.crear();
    }

    @Override
    public void eliminar(){
        /*int id;
        Object objeto;
        
        EntradaSalida.mostrarString("ELIMINAR");
        casaMatriz.mostrarListadoPersonas(new CapacidadDeListarClientes());
        id = EntradaSalida.leerEntero("Ingrese el ID a eliminar");
        objeto = CasaMatriz.buscarPersona(dni);
        //VALIDAR
        CasaMatriz.eliminarPersona(personaEliminar);

        object.capacidadDeSerEliminado ?

        :
        System.out.println("No se puede eliminar.");*/
    }

    @Override
    public boolean capacidadDeSerEliminado(){
        return false;
    }

    public void asignarAutoAOficina(Auto auto, Oficina oficina) {
        oficina.agregarAuto(auto);
    }

    public void asignarVendedorAOficina(Vendedor vendedor, Oficina oficina) {
        oficina.asignarVendedor(vendedor);
        vendedor.asignarOficina(oficina);
    }
}