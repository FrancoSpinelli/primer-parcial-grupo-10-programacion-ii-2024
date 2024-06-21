package Interfaces;

import java.time.LocalDate;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Personas.Vendedor;
import Personas.Persona;

public class CreadorVendedor implements CreadorStrategy{
    
    @Override
    public void crear(){
        int dni;
        String nombre, telefono, email, contrasenia;
        LocalDate fechaNacimiento;
    
        dni = EntradaSalida.leerEntero("¿Cuál es el DNI del usuario?");
        nombre = EntradaSalida.leerString("¿Cuál es el nombre y el apellido del usuario?");
        telefono = EntradaSalida.leerString("¿Cuál es el teléfono del usuario?");
        email = EntradaSalida.leerString("¿Cuál es el correo del usuario?");
        fechaNacimiento = EntradaSalida.leerFechaNacimiento("Ingrese la fecha de nacimiento del usuario.");
        contrasenia = Integer.toString(dni);
    
        Persona persona;
        persona = new Vendedor(dni, nombre, fechaNacimiento, telefono, email, contrasenia);
        CasaMatriz.agregarPersona(persona);
    }
}
