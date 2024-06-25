package Personas;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.ArrayList;
import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuAdmin;
import Oficina.Oficina;

public class Admin extends Persona implements Serializable {

    public Admin(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email, String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        setMenu();
    }

    public void gestionarPersonas() {
        EntradaSalida.advertencia("Gestión de personas");
        mostarMenuGeneral();
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección (0 para volver): ");
        EntradaSalida.saltoDeLinea();

        switch (opcion) {
            case 1:
                listarPersonas();
                break;
            case 2:
                crearPersona();
                break;
            case 3:
                eliminarPersona();
                break;
            case 0:
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }

    private void eliminarPersona() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'eliminarPersona'");
    }

    private void listarPersonas() {
        CasaMatriz.listarPersonas();
    }

    public void gestionarAutos() {
        EntradaSalida.advertencia("Gestion de autos");
        mostarMenuGeneral();
        EntradaSalida.mostrarString("\t4. Asignar auto a oficina");
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección (0 para volver): ");
        EntradaSalida.saltoDeLinea();

        switch (opcion) {
            case 1:
                CasaMatriz.listarAutos();
                break;
            case 2:
                CasaMatriz.crearAuto();
                break;
            case 3:
                CasaMatriz.crearAuto();
                break;
            case 4:
                asignarAutoAOficina();
                break;
            case 5:
                trasladarAutosAOficinaDeOrigen();
                break;
            case 0:
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }

    private void trasladarAutosAOficinaDeOrigen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trasladarAutosAOficinaDeOrigen'");
    }

    public void gestionarOficinas() {
        EntradaSalida.advertencia("Gestion de oficinas");

        mostarMenuGeneral();
        EntradaSalida.mostrarString("\t4. Asignar vendedor");
        EntradaSalida.mostrarString("\t5. Trasladar autos a oficina de origen");
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección (0 para volver): ");
        EntradaSalida.saltoDeLinea();

        switch (opcion) {
            case 1:
                CasaMatriz.listarOficinas();
                break;
            case 2:
                CasaMatriz.crearOficina();
                break;
            case 3:
                CasaMatriz.eliminarOficina();
                break;
            case 4:
                asignarVendedorAOficina();
                break;
            case 0:
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }

    }

    public void gestionarReservas() {
        EntradaSalida.advertencia("Gestion de reservas");

        mostarMenuGeneral();
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección: ");
        EntradaSalida.saltoDeLinea(

        );
        switch (opcion) {
            case 1:
                CasaMatriz.listarReservas();
                break;
            case 2:
                CasaMatriz.crearReserva();
                break;
            case 3:
                CasaMatriz.eliminarReserva();
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }

    private void mostarMenuGeneral() {
        EntradaSalida.mostrarString("Qué deseas hacer?\n");
        EntradaSalida.mostrarString("\t1. Listar");
        EntradaSalida.mostrarString("\t2. Crear");
        EntradaSalida.mostrarString("\t3. Eliminar");
    }

    private void crearPersona() {
        int opcion = 0;
        EntradaSalida.mostrarString("Qué deseas crear?\n");
        EntradaSalida.mostrarString("\t1. Cliente");
        EntradaSalida.mostrarString("\t2. Vendedor");
        EntradaSalida.mostrarString("\t3. Admin");
        EntradaSalida.mostrarString("\n0. Volver\n");
        do {
            opcion = EntradaSalida.leerEntero("Ingrese su elección: ");
            switch (opcion) {
                case 1:
                    crearCliente();
                    return;
                case 2:
                    crearVendedor();
                    return;
                case 3:
                    crearAdmin();
                    return;
                case 0:
                    break;
                default:
                    EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }

    private void crearVendedor() {

    }

    private void crearCliente() {
        String nombre, telefono, email, contrasenia;
        int dni;
        LocalDate fechaNacimiento;

        dni = EntradaSalida.leerEnteroConLimites("Ingrese su DNI: ", 100000, 99999999);
        nombre = EntradaSalida.leerString("Ingrese su nombre: ");
        telefono = EntradaSalida.leerString("Ingrese su teléfono: ");
        email = EntradaSalida.leerString("Ingrese su email: ");
        fechaNacimiento = EntradaSalida.leerFechaAnteriorAHoy("Ingrese su fecha de nacimiento: ");
        contrasenia = Integer.toString(dni);

        Persona nuevoCliente = new Cliente(dni, nombre, fechaNacimiento, telefono, email,
        contrasenia);

        CasaMatriz.agregarPersona(nuevoCliente);
    }

    private void crearAdmin() {
        
    }

    private void asignarAutoAOficina() {

    }

    private void asignarVendedorAOficina() {
        EntradaSalida.advertencia("Asignar vendedor a oficina");

        EntradaSalida.advertencia("Solamente se pueden asignar vendedores sin oficina asignada");
        
        ArrayList<Persona> vendedores = CasaMatriz.getVendedoresSinOficina();
        if (vendedores.isEmpty())
        return;
        
        Persona vendedor = CasaMatriz.seleccionarPersona(vendedores);
        
        if (vendedor == null)
        return;
        
        if (!vendedor.getRol().equals("VENDEDOR")) {
            EntradaSalida.error("La persona seleccionada no es un vendedor");
            return;
        }
        ArrayList<Oficina> oficinasSinVendedor = CasaMatriz.getOficinasSinVendedor();
        Oficina oficina = CasaMatriz.seleccionarOficina(oficinasSinVendedor);
        if (oficina == null)
            return;

        if (oficina.getVendedor() != null) {
            EntradaSalida.error("La oficina seleccionada ya tiene un vendedor asignado");
            return;
        }

        asignarVendedorAOficina((Vendedor) vendedor, oficina);

    }

    public void asignarAutoAOficina(Auto auto, Oficina oficina) {
        oficina.agregarAuto(auto);
    }

    @Override
    public String getRol() {
        return "ADMIN";
    }

    public void asignarVendedorAOficina(Vendedor vendedor, Oficina oficina) {
        oficina.asignarVendedor(vendedor);
        vendedor.asignarOficina(oficina);
    }

    private void setMenu() {
        this.setMenu(new CapacidadDeVerMenuAdmin(this));
    }
}