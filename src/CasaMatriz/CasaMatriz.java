package CasaMatriz;
import java.util.ArrayList;

import Auto.Auto;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeListar.CapacidadDeListarAutos;
import Interfaces.CapazDeListar.CapacidadDeListarOficinas;
import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenu;
import Interfaces.CapazDeListar.CapacidadDeListarPersonas;
import Oficina.Oficina;
import Personas.Admin;
import Personas.Persona;
import Personas.Vendedor;
import Personas.Cliente;

public class CasaMatriz {
    private static ArrayList<Persona> personas;
    private static ArrayList<Auto> autos;
    private static ArrayList<Oficina> oficinas;

    //CONSTRUCTOR
    public CasaMatriz(ArrayList<Persona> personas, ArrayList<Auto> autos, ArrayList<Oficina> oficinas) {
        EntradaSalida.mostrarString("Bienvenido a la Casa Matriz");
        CasaMatriz.personas = personas;
        CasaMatriz.autos = autos;
        CasaMatriz.oficinas = oficinas;

        Admin admin = (Admin) CasaMatriz.personas.get(0); 
        Vendedor vendedor1 = (Vendedor) CasaMatriz.personas.get(1);
        Oficina oficina = oficinas.get(0);

        CapacidadDeListarStrategy<Persona> listadorStrategyPersona = new CapacidadDeListarPersonas();
        CapacidadDeListarStrategy<Oficina> listadorStrategyOficina = new CapacidadDeListarOficinas();
        CapacidadDeListarStrategy<Auto> listadorStrategyAutos = new CapacidadDeListarAutos();

        admin.asignarVendedorAOficina(vendedor1,oficina);
        for (Auto auto : autos) {
            admin.asignarAutoAOficina(auto,oficina);
        }

        oficina.verListadoAutos();
        oficina.verListadoReservas();
    }

    public void login() {
        boolean continuar = false;
        int seleccion = 0;
        do{ //login credenciales
            String usuario = EntradaSalida.leerString("Usuario: ");
            String contrasenia = EntradaSalida.leerPassword("Contraseña: ");
            for(Persona persona : personas){ //login búsqueda base de datos
                if(persona.coincideUsuario(persona, usuario) && persona.coincideContrasenia(persona, contrasenia)){ //login autentificación
                    CapacidadDeVerMenu menu = persona.getMenuStrategy();
                    do{ //realiza operaciones del usuario
                        menu.verMenu();
                        seleccion = menu.seleccionar();
                    }while(seleccion != 0);
                    break;
                } else {
                    EntradaSalida.mostrarString("Error. Usuario o contraseña errónea.");
                    break;
                }
            }
        }while(continuar != false || seleccion != 0);
    }

    public void logout() {
        EntradaSalida.mostrarString("Adios");
    }

    //AGREGAR
    public static void agregarPersona(Persona persona){
        personas.add(persona);
    }

    //ENCONTRAR
    public static Persona buscarPersona(int dni){
        Persona personaEncontrada = null;
        for(Persona persona : personas){
            if(persona.coincideDni(persona, dni)) personaEncontrada = persona;
        }
        return personaEncontrada;
    }

    //BAJA
    public static void eliminarPersona(Persona persona){
        personas.remove(persona);
    }

    //LISTAR
    /*public <T> ArrayList<T> listarPersonas(CapacidadDeListarStrategy<T> strategy) {
        return strategy.listar(personas);
    }

    public <T> ArrayList<T> listarAutos(CapacidadDeListarStrategy<T> strategy) {
        return strategy.listar(autos);
    }

    public <T> ArrayList<T> listarOficinas(CapacidadDeListarStrategy<T> strategy) {
        return strategy.listar(oficinas);
    }*/

    public void mostrarListadoPersonas(CapacidadDeListarStrategy<Persona> strategy) {
        ArrayList<Persona> personasListadas = strategy.listar(personas);
        for (Persona persona : personasListadas) {
            System.out.println(persona.toString());
        }
    }

    public void mostrarListadoAutos(CapacidadDeListarStrategy<Auto> strategy) {
        ArrayList<Auto> autosListados = strategy.listar(autos);
        for (Auto auto : autosListados) {
            System.out.println(auto.toString());
        }
    }

    public void mostrarListadoOficina(CapacidadDeListarStrategy<Oficina> strategy) {
        ArrayList<Oficina> oficinasListadas = strategy.listar(oficinas);
        for (Oficina oficina : oficinasListadas) {
            System.out.println(oficina.toString());
        }
    }
}
