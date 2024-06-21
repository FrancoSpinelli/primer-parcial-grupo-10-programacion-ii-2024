package CasaMatriz;
import java.util.ArrayList;
import java.util.List;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Interfaces.ListadorAutos;
import Interfaces.ListadorClientes;
import Interfaces.ListadorStrategy;
import Oficina.Oficina;
import Personas.Admin;
import Personas.Persona;
import Personas.Vendedor;

public class CasaMatriz {
    public static ArrayList<Persona> personas;
    private static ArrayList<Auto> autos;
    private static ArrayList<Oficina> oficinas;

    private ListadorStrategy<?> listadorStrategy;

    //CONSTRUCTOR
    public CasaMatriz(ArrayList<Persona> personas, ArrayList<Auto> autos, ArrayList<Oficina> oficinas) {
        EntradaSalida.mostrarString("Bienvenido a la Casa Matriz");
        this.personas = personas;
        this.autos = autos;
        this.oficinas = oficinas;

        Admin admin = (Admin) this.personas.get(0); 
        Vendedor vendedor1 = (Vendedor) this.personas.get(1);
        Oficina oficina = oficinas.get(0);

        admin.asignarVendedorAOficina(vendedor1,oficina);
        for (Auto auto : autos) {
            admin.asignarAutoAOficina(auto,oficina);
        }

        oficina.verListadoAutos();
        oficina.verListadoReservas();
    }

    public void setListadorStrategy(ListadorStrategy<?> listadorStrategy){
        this.listadorStrategy = listadorStrategy;
    }

    public void login() {

    }

    public void logout() {

    }

    //AGREGAR
    public static void agregarPersona(Persona persona){
        personas.add(persona);
    }

    //ENCONTRAR
    public static Persona buscarPersona(int dni){
        Persona personaEncontrada = null;
        for(Persona persona : personas){
            if(Persona.coincideDni(persona, dni)) personaEncontrada = persona;
        }
        return personaEncontrada;
    }

    //BAJA
    public static void eliminarPersona(Persona persona){
        personas.remove(persona);
    }

    //LISTAR
    public <T> List<T> listarPersonas(ListadorStrategy<T> strategy) {
        return strategy.listar(personas);
    }

    public <T> List<T> listarAutos(ListadorStrategy<T> strategy) {
        return strategy.listar(autos);
    }

    public <T> List<T> listarOficinas(ListadorStrategy<T> strategy) {
        return strategy.listar(oficinas);
    }

    public void mostrarListadoPersonas(ListadorStrategy<? extends Persona> strategy) {
        List<? extends Persona> personasListadas = listarPersonas(strategy);
        for (Persona persona : personasListadas) {
            System.out.println(persona.toString());
        }
    }

    public void mostrarListadoAutos(ListadorStrategy<Auto> strategy) {
        List<Auto> autosListados = listarAutos(strategy);
        for (Auto auto : autosListados) {
            //auto.toString();
        }
    }

    public void mostrarListadoOficina(ListadorStrategy<Oficina> strategy) {
        List<Oficina> oficinasListadas = listarOficinas(strategy);
        for (Oficina oficina : oficinasListadas) {
            //oficina.toString();
        }
    }
}
