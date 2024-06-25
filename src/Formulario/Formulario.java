package Formulario;

import java.time.LocalDate;

import Auto.Auto;
import Auto.Gasolina;
import EntradaSalida.EntradaSalida;
import Oficina.Oficina;
import Personas.Cliente;
import Personas.Persona;
import Validador.Validador;
import enums.Color;
import enums.Marca;
import CasaMatriz.Const;

public class Formulario {

    public static Auto crearAuto() {
        Marca marca = seleccionMarca();
        String modelo = EntradaSalida.leerString("Ingrese el modelo ");
        Color color = seleccionColor();
        int precio = EntradaSalida.leerEnteroConLimites("Ingrese el precio ", Const.MIN_PRECIO_AUTO, Const.MAX_PRECIO_AUTO);
        int gasolina = EntradaSalida.leerEnteroConLimites("Capacidad de combustible en litros (20 - 200): ", Const.MIN_LITROS_AUTO, Const.MAX_LITROS_AUTO);
        String patente;
        do {
            patente = EntradaSalida.leerString("Ingrese la patante (6 digitos): ").toUpperCase();
        } while (!Validador.patenteValida(patente));

        return new Auto(patente, modelo, precio, color, marca, new Gasolina(gasolina));
    }

    public static Persona crearPersona() {
        String nombre, telefono, email, contrasenia;
        int dni;
        LocalDate fechaNacimiento;

        dni = EntradaSalida.leerEnteroConLimites("Ingrese su DNI: ", Const.MIN_DNI, Const.MAX_DNI);
        nombre = EntradaSalida.leerString("Ingrese su nombre: ");
        telefono = EntradaSalida.leerString("Ingrese su teléfono: ");
        email = EntradaSalida.leerString("Ingrese su email: ");
        fechaNacimiento = EntradaSalida.leerFechaAnteriorAHoy("Ingrese su fecha de nacimiento: ");
        contrasenia = Integer.toString(dni);

        if (!Validador.validoParaCrearPersona(dni, email)) {
            EntradaSalida.error("El DNI o el email ya están en uso.");
            return null;
        }

        return new Cliente(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
    }

    public static Oficina crearOficina() {
        String direccion = EntradaSalida.leerString("Ingrese la dirección: ");
        String telefono;
        do {
            telefono = EntradaSalida.leerString("Ingrese el teléfono: ");
        } while (!Validador.telefonoValido(telefono));
        return new Oficina(direccion, telefono);
    }

    private static Marca seleccionMarca() {
        EntradaSalida.mostrarString("Seleccione la marca del auto\n");
        EntradaSalida.mostrarString("\t1. Chevrolet");
        EntradaSalida.mostrarString("\t2. Ford");
        EntradaSalida.mostrarString("\t3. BMW");
        EntradaSalida.mostrarString("\t4. Fiat");
        EntradaSalida.mostrarString("\n\t0 Volver\n");

        int opcion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", Const.LIMITE_INFERIOR_DEFAULT, 4);
        switch (opcion) {
            case 1:
                return Marca.CHEVROLET;
            case 2:
                return Marca.FORD;
            case 3:
                return Marca.BMW;
            case 4:
                return Marca.FIAT;
            case 0:
                return null;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                return seleccionMarca();
        }
    }

    private static Color seleccionColor() {
        EntradaSalida.mostrarString("Seleccione el color del auto\n");
        EntradaSalida.mostrarString("\t1. Azul");
        EntradaSalida.mostrarString("\t2. Rojo");
        EntradaSalida.mostrarString("\t3. Blanco");
        EntradaSalida.mostrarString("\t4. Negro");
        EntradaSalida.mostrarString("\t5. Gris");
        EntradaSalida.mostrarString("\n\t0 Volver\n");

        int opcion = EntradaSalida.leerEnteroConLimites("Ingrese su elección: ", Const.LIMITE_INFERIOR_DEFAULT, 5);
        switch (opcion) {
            case 1:
                return Color.AZUL;
            case 2:
                return Color.ROJO;
            case 3:
                return Color.BLANCO;
            case 4:
                return Color.NEGRO;
            case 5:
                return Color.GRIS;
            case 0:
                return null;
            default:
                EntradaSalida.error("Opción inválida. Por favor, seleccione una opción válida.");
                return seleccionColor();
        }
    }

}
