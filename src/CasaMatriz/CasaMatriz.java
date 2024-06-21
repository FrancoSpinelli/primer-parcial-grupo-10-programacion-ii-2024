package CasaMatriz;

import java.util.ArrayList;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeListar.CapacidadDeListarAutos;
import Interfaces.CapazDeListar.CapacidadDeListarClientes;
import Interfaces.CapazDeListar.CapacidadDeListarOficinas;
import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeListar.CapacidadDeListarVendedores;
import Interfaces.CapazDeListar.CapacidadDeListarAdmins;
import Oficina.Oficina;
import Personas.Admin;
import Personas.Persona;
import Personas.Vendedor;
import Personas.Cliente;

public class CasaMatriz {
    private static ArrayList<Persona> personas;
    private static ArrayList<Auto> autos;
    private static ArrayList<Oficina> oficinas;

    private CapacidadDeListarStrategy<?> listadorStrategy;

    // CONSTRUCTOR
    public CasaMatriz(ArrayList<Persona> personas, ArrayList<Auto> autos, ArrayList<Oficina> oficinas) {
        EntradaSalida.mostrarString("Bienvenido a la Casa Matriz");
        CasaMatriz.personas = personas;
        CasaMatriz.autos = autos;
        CasaMatriz.oficinas = oficinas;

        Admin admin = (Admin) this.personas.get(0);
        Vendedor vendedor1 = (Vendedor) this.personas.get(1);
        Vendedor vendedor2 = (Vendedor) this.personas.get(3);
        Oficina oficina1 = oficinas.get(0);
        Oficina oficina2 = oficinas.get(1);

        admin.asignarVendedorAOficina(vendedor1, oficina1);
        admin.asignarVendedorAOficina(vendedor2, oficina2);
        for (Auto auto : autos) {
            if (autos.indexOf(auto) % 2 == 0) {
                admin.asignarAutoAOficina(auto, oficina1);
            } else {
                admin.asignarAutoAOficina(auto, oficina2);
            }
        }

        /*
         * oficina1.verListadoAutos();
         * oficina1.verListadoReservas();
         */
    }

    public void setListadorClientes(CapacidadDeListarStrategy<Cliente> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }
    public void setListadorVendedores(CapacidadDeListarStrategy<Vendedor> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }
    public void setListadorAdmins(CapacidadDeListarStrategy<Admin> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }
    public void setListadorAutos(CapacidadDeListarStrategy<Auto> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }
    public void setListadorOficinas(CapacidadDeListarStrategy<Oficina> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }

    public void login() {

    }

    public void logout() {

    }

    // AGREGAR
    public static void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    // ENCONTRAR
    public static Persona buscarPersona(int dni) {
        Persona personaEncontrada = null;
        for (Persona persona : personas) {
            if (Persona.coincideDni(persona, dni))
                personaEncontrada = persona;
        }
        return personaEncontrada;
    }

    // BAJA
    public static void eliminarPersona(Persona persona) {
        personas.remove(persona);
    }

    //LISTAR
    public <T> ArrayList<T> listarPersonas(CapacidadDeListarStrategy<T> strategy) {
        return strategy.listar(personas);
    }

    public <T> ArrayList<T> listarAutos(CapacidadDeListarStrategy<T> strategy) {
        return strategy.listar(autos);
    }

    public <T> ArrayList<T> listarOficinas(CapacidadDeListarStrategy<T> strategy) {
        return strategy.listar(oficinas);
    }

    public void mostrarListadoPersonas(CapacidadDeListarStrategy<? extends Persona> strategy) {
        ArrayList<? extends Persona> personasListadas = listarPersonas(strategy);
        for (Persona persona : personasListadas) {
            System.out.println(persona.toString());
        }
    }

    public void mostrarListadoAutos(CapacidadDeListarStrategy<Auto> strategy) {
        ArrayList<Auto> autosListados = listarAutos(strategy);
        for (Auto auto : autosListados) {
            System.out.println(auto.toString());
        }
    }

    public void mostrarListadoOficina(CapacidadDeListarStrategy<Oficina> strategy) {
        ArrayList<Oficina> oficinasListadas = listarOficinas(strategy);
        for (Oficina oficina : oficinasListadas) {
            System.out.println(oficina.toString());
        }
    }
}
