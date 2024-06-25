package Personas;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Interfaces.CapazDeVerMenu.CapacidadDeVerMenuAdmin;
import Oficina.Oficina;
import enums.Rol;

public class Admin extends Persona implements Serializable {

    public Admin(int dni, String nombre, LocalDate fechaNacimiento, String telefono, String email, String contrasenia) {
        super(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        setMenu();
    }

    public void gestionarPersonas() {
        EntradaSalida.advertencia("Gestión de personas");
        mostarMenuGeneral();
        EntradaSalida.mostrarString("\n\t0 Volver");
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección: ");
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


    public void gestionarAutos() {
        EntradaSalida.advertencia("Gestión de autos");
        mostarMenuGeneral();
        EntradaSalida.mostrarString("\t4. Asignar auto a oficina");
        EntradaSalida.mostrarString("\n\t0 Volver");
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección: ");
        EntradaSalida.saltoDeLinea();

        switch (opcion) {
            case 1:
                CasaMatriz.listarAutos();
                break;
            case 2:
                CasaMatriz.crearAuto();
                break;
            case 3:
                CasaMatriz.eliminarAuto();
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



    public void gestionarOficinas() {
        EntradaSalida.advertencia("Gestión de oficinas");

        mostarMenuGeneral();
        EntradaSalida.mostrarString("\t4. Asignar vendedor");
        EntradaSalida.mostrarString("\t5. Trasladar autos a oficina de origen");
        EntradaSalida.mostrarString("\n\t0 Volver");
        int opcion = EntradaSalida.leerEntero("\nIngrese su elección: ");
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
        EntradaSalida.advertencia("Gestión de reservas");

        EntradaSalida.mostrarString("Qué deseas hacer?\n");
        EntradaSalida.mostrarString("\t1. Listar");
        EntradaSalida.mostrarString("\t2. Aceptar");
        EntradaSalida.mostrarString("\t3. Rechazar");
        EntradaSalida.mostrarString("\n\t0. Volver\n");

        int opcion = EntradaSalida.leerEntero("\nIngrese su elección: ");
        EntradaSalida.saltoDeLinea(

        );
        switch (opcion) {
            case 1:
                CasaMatriz.listarReservas();
                break;
            case 2:
                CasaMatriz.aceptarReserva();
                break;
            case 3:
                CasaMatriz.rechazarReserva();
                break;
            case 0:
                break;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                break;
        }
    }




    public void asignarAutoAOficina() {
        // oficina.agregarAuto(auto);
    }

    public void asignarAutoAOficina(Auto auto, Oficina oficina) {
        oficina.agregarAuto(auto);
    }

    @Override
    public Rol getRol() {
        return Rol.ADMIN;
    }

    public void asignarVendedorAOficina(Vendedor vendedor, Oficina oficina) {
        oficina.asignarVendedor(vendedor);
        vendedor.asignarOficina(oficina);
    }

    private void setMenu() {
        this.setMenu(new CapacidadDeVerMenuAdmin(this));
    }

    private void listarPersonas() {
        CasaMatriz.listarPersonas();
    }


    private void crearVendedor() {
        CasaMatriz.crearVendedor();
    }

    private void crearCliente() {
        CasaMatriz.crearCliente();
    }

    private void crearAdmin() {
        CasaMatriz.crearAdmin();
    }

    private void eliminarPersona() {
        CasaMatriz.eliminarPersona();
    }

    private void asignarAutoAOficial() {

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
        EntradaSalida.mostrarString("\n\t0. Volver\n");
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

    private void asignarVendedorAOficina() {
        EntradaSalida.advertencia("Asignar vendedor a oficina");

        EntradaSalida.advertencia("Solamente se pueden asignar vendedores sin oficina asignada");

        ArrayList<Persona> vendedores = CasaMatriz.getVendedoresSinOficina();
        if (vendedores.isEmpty())
            return;

        Persona vendedor = CasaMatriz.seleccionarPersona(vendedores);

        if (vendedor == null)
            return;

        if (!vendedor.getRol().equals(Rol.VENDEDOR)) {
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

    private void trasladarAutosAOficinaDeOrigen() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'trasladarAutosAOficinaDeOrigen'");
    }
}