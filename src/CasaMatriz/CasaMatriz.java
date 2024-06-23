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

    // CONSTRUCTOR
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

        CapacidadDeListarStrategy<?> listadorStrategy;

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

        admin.configurarEstrategias(this);
    }

    public void login() {

        EntradaSalida.mostrarString("\nBienvenido a RentACar!");
        EntradaSalida.mostrarString("Por favor, ingrese inicie sesión\n");

        boolean autenticado = false;
        while (!autenticado) {
            String usuario = EntradaSalida.leerString("Correo: ");
            String contrasenia = EntradaSalida.leerPassword("Contraseña: ");
            for (Persona persona : personas) {
                if (persona.coincideUsuarioYContrasenia(usuario, contrasenia)) {
                    autenticado = true;
                    CapacidadDeVerMenu menu = persona.getMenuStrategy();
                    int seleccion = -1;
                    do {
                        menu.verMenu();
                        seleccion = menu.seleccionar();
                    } while (seleccion != 0);
                    this.logout(persona);
                    autenticado = false;
                    login();
                }
            }
            if (!autenticado) {
                EntradaSalida.mostrarString("Error. Usuario o contraseña errónea.");
            }
        }
    }

    public void logout(Persona persona) {
        EntradaSalida.mostrarString("Adiós " + persona.getNombre(), true, true);
    }

    // AGREGAR
    public static void agregarPersona(Persona persona) {
        personas.add(persona);
    }

    // ENCONTRAR
    public static Persona buscarPersona(int dni) {
        Persona personaEncontrada = null;
        for (Persona persona : personas) {
            if (persona.coincideDni(dni))
                personaEncontrada = persona;
        }
        return personaEncontrada;
    }

    // BAJA
    public static void eliminarPersona(Persona persona) {
        personas.remove(persona);
    }

    // LISTAR
    /*
     * public <T> ArrayList<T> listarPersonas(CapacidadDeListarStrategy<T> strategy)
     * {
     * return strategy.listar(personas);
     * }
     * 
     * public <T> ArrayList<T> listarAutos(CapacidadDeListarStrategy<T> strategy) {
     * return strategy.listar(autos);
     * }
     * 
     * public <T> ArrayList<T> listarOficinas(CapacidadDeListarStrategy<T> strategy)
     * {
     * return strategy.listar(oficinas);
     * }
     */

    public void mostrarListadoPersonas(CapacidadDeListarStrategy<Persona> strategy) {
        ArrayList<Persona> personasListadas = strategy.listar(personas);
        for (Persona persona : personasListadas) {
            System.out.println(persona.toString());
        }
    }

    public void mostrarListadoAutos(CapacidadDeListarStrategy<Auto> strategy) {
        ArrayList<Auto> autosListados = strategy.listar(autos);
        for (Auto auto : autosListados) {
            System.out.println(auto.verAuto());
        }
    }

    public void mostrarListadoOficina(CapacidadDeListarStrategy<Oficina> strategy) {
        ArrayList<Oficina> oficinasListadas = strategy.listar(oficinas);
        for (Oficina oficina : oficinasListadas) {
            System.out.println(oficina.toString());
        }
    }

    static public ArrayList<Oficina> getOficinas() {
        return oficinas;
    }

    static public ArrayList<Auto> getAutos() {
        return autos;
    }

    static public void verListadoDeAutosPorOficina() {
        EntradaSalida.mostrarString("Listado de autos por oficina\n");
        for (Oficina oficina : oficinas) {
            oficina.verListadoAutos();
        }
        EntradaSalida.saltoDeLinea();;
    }

    static public Oficina seleccionarOficina() {
        EntradaSalida.mostrarString("Seleccione una oficina: \n");
        for (Oficina oficina : oficinas) {
            EntradaSalida.mostrarString("\t" + oficina.toString() + "\n");
        }
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 1, cantidadDeOficinas());
        EntradaSalida.saltoDeLinea();;
        return seleccionarOficina(seleccion);
    }

    private static int cantidadDeOficinas() {
        return oficinas.size();
    }

    private static Oficina seleccionarOficina(int id) {
        for (Oficina oficina : oficinas) {
            if (oficina.getId() == id) {
                return oficina;
            }
        }
        EntradaSalida.mostrarString("Oficina no encontrada", false, false);
        return null;
    }
}
