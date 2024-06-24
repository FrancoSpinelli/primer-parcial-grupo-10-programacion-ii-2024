package CasaMatriz;

import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import Auto.Gasolina;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeListar.CapacidadDeListarStrategy;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenu;
import Oficina.Oficina;
import Personas.Admin;
import Personas.Cliente;
import Personas.Persona;
import Personas.Vendedor;
import Reserva.Reserva;
import enums.Color;
import enums.Marca;

public class CasaMatriz {
    private static ArrayList<Persona> personas;
    private static ArrayList<Auto> autos;
    private static ArrayList<Oficina> oficinas;

    // CONSTRUCTOR
    public CasaMatriz(ArrayList<Persona> personas, ArrayList<Auto> autos, ArrayList<Oficina> oficinas) {
        EntradaSalida.mostrarString("Bienvenido a la Casa Matriz");
        CasaMatriz.personas = personas;
        CasaMatriz.autos = autos;
        CasaMatriz.oficinas = oficinas;

        Admin admin1 = new Admin(1234, "admin", LocalDate.now(), "1234", "a", "1234");
        personas.add(admin1);
        Vendedor vendedor1 = new Vendedor(1234, "vendedor1", LocalDate.now(), "1234", "v", "1234");
        personas.add(vendedor1);
        Cliente cliente1 = new Cliente(1234, "cliente1", LocalDate.now(), "1234", "c", "1234");
        personas.add(cliente1);
        Vendedor vendedor2 = new Vendedor(1234, "vendedor2", LocalDate.now(), "1234", "vendedor2@vendedor.com", "1234");
        personas.add(vendedor2);

        Auto auto1 = new Auto("ABC123", "Corolla", 10000, Color.AZUL, Marca.CHEVROLET,
                new Gasolina(10000));
        autos.add(auto1);
        Auto auto2 = new Auto("DEF456", "Civic", 20000, Color.ROJO, Marca.FORD, new Gasolina(20000));
        autos.add(auto2);
        Auto auto3 = new Auto("GHI789", "Coupe", 30000, Color.BLANCO, Marca.BMW, new Gasolina(30000));
        autos.add(auto3);
        Auto auto4 = new Auto("JKL012", "Sedan", 40000, Color.NEGRO, Marca.FIAT, new Gasolina(40000));
        autos.add(auto4);

        Oficina oficina1 = new Oficina("Av. Siempre Viva 123", "123456789");
        oficinas.add(oficina1);
        Oficina oficina2 = new Oficina("Av. Siempre Viva 456", "987654321");
        oficinas.add(oficina2);

        admin1.asignarVendedorAOficina(vendedor1, oficina1);
        admin1.asignarVendedorAOficina(vendedor2, oficina2);
        for (Auto auto : autos) {
            if (autos.indexOf(auto) % 2 == 0) {
                admin1.asignarAutoAOficina(auto, oficina1);
            } else {
                admin1.asignarAutoAOficina(auto, oficina2);
            }
        }

        /*
         * oficina1.verListadoAutos();
         * oficina1.verListadoReservas();
         */

        admin1.configurarEstrategias(this);
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
        EntradaSalida.cualquierTeclaParaContinuar();
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

    private static int cantidadDeOficinas() {
        return oficinas.size();
    }

    static public void verListadoDeAutosPorOficina() {
        EntradaSalida.mostrarString("Listado de autos por oficina:");
        for (Oficina oficina : oficinas) {
            EntradaSalida.saltoDeLinea();
            oficina.verListadoAutos();
        }
        EntradaSalida.saltoDeLinea();
    }

    static public Oficina seleccionarOficina() {
        EntradaSalida.mostrarString("\nSeleccione una oficina: \n");
        for (Oficina oficina : oficinas) {
            EntradaSalida.mostrarString("\t" + oficina.toString() + "\n");
        }
        int seleccion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", 1, cantidadDeOficinas());
        EntradaSalida.saltoDeLinea();
        ;
        return seleccionarOficina(seleccion);
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

    public static Auto seleccionarAuto(Oficina oficina) {
        Auto autoSeleccionado = null;
        int id = EntradaSalida.leerEntero("\nIngrese el ID del auto que desea seleccionar: ");

        if (id == 0)
            return null;

        if (oficina != null) {
            autoSeleccionado = oficina.getAuto(id);
        } else {
            for (Auto auto : autos) {
                if (auto.getId() == id) {
                    autoSeleccionado = auto;
                    break;
                }
            }
        }
        EntradaSalida.saltoDeLinea();
        if (autoSeleccionado == null) {
            EntradaSalida.mostrarString("Auto no encontrado", true, true);
        } else {
            EntradaSalida.mostrarString(autoSeleccionado.verAuto() + " seleccionado.", true, true);
        }
        return autoSeleccionado;
    }

    public static int generarIdReserva() {
        int lastId = 0;
        for (Oficina oficina : oficinas) {
            ArrayList<Reserva> reservas = oficina.getReservas();
            if (reservas.isEmpty()) {
                continue;
            }
            if (reservas.getLast().getId() > lastId) {
                lastId = reservas.getLast().getId();
            }
        }
        return lastId + 1;
    }

    public static int generarIdAuto() {
        return autos.size() + 1;
    }

    public static int generarIdOficina() {
        return oficinas.size() + 1;
    }

    public static int generarIdPersona() {
        return personas.size() + 1;
    }
}
