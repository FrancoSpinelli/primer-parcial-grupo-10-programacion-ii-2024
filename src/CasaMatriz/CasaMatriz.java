package CasaMatriz;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import Auto.Gasolina;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenu;
import Oficina.Oficina;
import Personas.Admin;
import Personas.Cliente;
import Personas.Persona;
import Personas.Vendedor;
import Reserva.Reserva;
import enums.Color;
import enums.Marca;

public class CasaMatriz implements Serializable{
    private static ArrayList<Persona> personas = new ArrayList<Persona>();
    private static ArrayList<Auto> autos = new ArrayList<Auto>();
    private static ArrayList<Oficina> oficinas = new ArrayList<Oficina>();

    // CONSTRUCTOR
    public CasaMatriz(){
        personas = new ArrayList<Persona>();
        autos = new ArrayList<Auto>();
        oficinas = new ArrayList<Oficina>();
    }

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
        // admin1.asignarVendedorAOficina(vendedor2, oficina2);
        for (Auto auto : autos) {
            if (autos.indexOf(auto) % 2 == 0) {
                admin1.asignarAutoAOficina(auto, oficina1);
            }
            /*
             * else {
             * admin1.asignarAutoAOficina(auto, oficina2);
             * }
             */
        }

    }

    public void login() {

        EntradaSalida.advertencia("Bienvenido a RentACar");

        EntradaSalida.mostrarString("Por favor, ingrese inicie sesión para continuar\n");

        boolean autenticado = false;
        while (!autenticado) {
            String usuario = EntradaSalida.leerString("Correo: ");
            String contrasenia = EntradaSalida.leerPassword("Contraseña: ");
            for (Persona p : personas) {
                if (p.coincideUsuarioYContrasenia(usuario, contrasenia)) {
                    autenticado = true;
                    EntradaSalida.limpiarPantalla();
                    EntradaSalida.advertencia("Bienvenido " + p.getNombre() + " a RentACar");
                    CapacidadDeVerMenu menu = p.getMenu();
                    int seleccion = -1;
                    do {
                        menu.verMenu();
                        seleccion = menu.seleccionar();
                    } while (seleccion != 0);
                    this.logout(p);
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

    public CasaMatriz deserializar(String archivo) throws IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(archivo);
        ObjectInputStream o = new ObjectInputStream(f);
        CasaMatriz s = (CasaMatriz) o.readObject();
        o.close();
        f.close();
        return s;
    }

    public void serializar(String archivo) throws IOException {
        FileOutputStream f = new FileOutputStream(archivo);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this);
        o.close();
        f.close();
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

    public static ArrayList<Persona> getPersonas() {
        return personas;
    }

    static public ArrayList<Oficina> getOficinas() {
        return oficinas;
    }

    static public ArrayList<Auto> getAutos() {
        return autos;
    }

    static public ArrayList<Reserva> getReservas() {
        ArrayList<Reserva> reservas = new ArrayList<Reserva>();

        for (Oficina oficina : getOficinas()) {
            ArrayList<Reserva> reservasOficina = oficina.getReservas();

            for (Reserva reserva : reservasOficina) {
                if (!reservas.contains(reserva)) {
                    reservas.add(reserva);
                }
            }

        }
        return reservas;
    }

    static public Persona seleccionarPersona(ArrayList<Persona> personas) {
        EntradaSalida.mostrarString("\nSeleccione una persona: \n");
        for (Persona p : personas) {
            EntradaSalida.mostrarString(p.verPersona(), true, true);
        }

        int id = EntradaSalida.leerEnteroConLimites("\nIngrese el ID de la persona: ", 1, CasaMatriz.personas.size());
        EntradaSalida.saltoDeLinea();

        Persona p = seleccionarPersona(id);

        if (!personas.contains(p)) {
            EntradaSalida.error("Persona no válida");
            return null;
        }

        return p;
    }

    private static Persona seleccionarPersona(int id) {
        Persona p = getPersona(id);

        if (p == null) {
            EntradaSalida.mostrarString("Persona no encontrada", true, true);
        } else {
            EntradaSalida.mostrarString(p.verPersona() + " seleccionada.", true, true);
        }

        return p;
    }

    private static Persona getPersona(int id) {
        for (Persona persona : personas) {
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

    static public ArrayList<Persona> getVendedores() {
        ArrayList<Persona> vendedores = new ArrayList<>();

        for (Persona p : CasaMatriz.getPersonas()) {
            if (p.getRol().equals("VENDEDOR")) {
                vendedores.add(p);
            }
        }

        return vendedores;
    }

    static public ArrayList<Persona> getVendedoresSinOficina() {
        ArrayList<Persona> vendedores = getVendedores();

        for (Persona v : getVendedores()) {
            if (((Vendedor) v).getOficina() != null) {
                vendedores.remove(v);
            }
        }

        return vendedores;
    }

    static public ArrayList<Oficina> getOficinasSinVendedor() {

        ArrayList<Oficina> oficinasSinVendedor = new ArrayList<Oficina>();
        for (Oficina o : getOficinas()) {
            if (o.getVendedor() == null) {
                oficinasSinVendedor.add(o);
            }
        }

        return oficinasSinVendedor;
    }

    static public void listarVendedores() {
        ArrayList<Persona> vendedores = getVendedores();
        if (vendedores.isEmpty()) {
            EntradaSalida.mostrarString("No hay vendedores disponibles", true, true);
            return;
        }
        for (Persona v : vendedores) {
            EntradaSalida.mostrarString(v.verPersona(), true, true);
        }
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
        int seleccion = EntradaSalida.leerEntero("Ingrese su elección: ");
        EntradaSalida.saltoDeLinea();
        return seleccionarOficina(seleccion);
    }

    static public Oficina seleccionarOficina(ArrayList<Oficina> ofis) {
        EntradaSalida.mostrarString("\nSeleccione una oficina: \n");

        for (Oficina oficina : ofis) {
            EntradaSalida.mostrarString("\t" + oficina.toString() + "\n");
        }
        int seleccion = EntradaSalida.leerEntero("Ingrese su elección: ");
        EntradaSalida.saltoDeLinea();
        Oficina o = seleccionarOficina(seleccion);

        if (o == null) {
            return null;
        }

        for (Oficina oficina : oficinas) {
            if (oficina.getId() == o.getId()) {
                return oficina;
            }
        }

        EntradaSalida.mostrarString("Oficina no encontrada", true, true);

        return null;
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

    // PERSONAS

    static public void listarPersonas() {
        EntradaSalida.mostrarString("\nListado de personas:\n");
        for (Persona p : getPersonas()) {
            EntradaSalida.mostrarString(p.verPersona(), true, true);
        }
        EntradaSalida.saltoDeLinea();
    }

    // AUTOS
    public static void listarAutos() {
        EntradaSalida.mostrarString("\nListado de autos:\n");
        for (Auto auto : getAutos()) {
            EntradaSalida.mostrarString(auto.verAuto(), true, true);
        }
        EntradaSalida.saltoDeLinea();
    }

    // OFICINAS

    public static void listarOficinas() {
        EntradaSalida.mostrarString("\nListado de oficinas:\n");
        for (Oficina oficina : getOficinas()) {
            String msg = oficina.toString();
            if (oficina.getVendedor() != null) {
                msg += " - Vendedor: " + oficina.getVendedor().getNombre();
            } else {
                msg += " - Sin vendedor asignado";
            }
            EntradaSalida.mostrarString(msg, true, true);
        }
        EntradaSalida.saltoDeLinea();
    }

    // RESERVAS

    public static void listarReservas() {
        ArrayList<Reserva> reservas = getReservas();

        if (reservas.isEmpty()) {
            EntradaSalida.mostrarString("No hay reservas", true, true);
            return;
        }

        EntradaSalida.mostrarString("\nListado de reservas:\n");
        for (Reserva reserva : reservas) {
            EntradaSalida.mostrarString(reserva.verReserva(), true, true);
        }
        EntradaSalida.saltoDeLinea();
    }

    public static void crearAuto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearAuto'");
    }

    public static void crearOficina() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearOficina'");
    }

    public static void eliminarOficina() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarOficina'");
    }

    public static void crearReserva() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'crearReserva'");
    }

    public static void eliminarReserva() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarReserva'");
    }
}
