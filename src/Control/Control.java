package Control;

import java.io.*;
import java.time.LocalDate;
import CasaMatriz.CasaMatriz;
import EntradaSalida.EntradaSalida;
import Personas.Admin;

public class Control {

  public void ejecutar() {

    CasaMatriz casaMatriz = new CasaMatriz();

    try {
      casaMatriz = casaMatriz.deserializar("datos.bin");
    } catch (Exception e) {
      EntradaSalida.mostrarString("Primera ejecución del sistema");

      int dni = EntradaSalida.leerEnteroConLimites("IAdministrador, ngrese su número de DNI: ",
          100000, 99999999);

      String nombre = EntradaSalida.leerString("Ingrese su nombre y apellido: ");
      if (nombre.equals("")) {
        throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
      }

      String email = EntradaSalida.leerString("Ingrese su correo electrónico: ");
      if (email.equals("")) {
        throw new NullPointerException("ERROR: El usuario no puede ser nulo.");
      }

      LocalDate fechaNacimiento = EntradaSalida.leerFechaAnteriorAHoy("Ingrese su fecha de nacimiento: ");

      String contrasenia = EntradaSalida.leerPassword("Ingrese su password: ");
      if (contrasenia.equals("")) {
        throw new NullPointerException("ERROR: La password no puede ser nula.");
      }

      String telefono = EntradaSalida.leerString("Ingrese su número de teléfono: ");
      if (telefono.equals("")) {
        throw new NullPointerException("ERROR: El teléfono debe existir.");
      }

      CasaMatriz.getPersonas().add(new Admin(dni, nombre, fechaNacimiento, telefono, email, contrasenia));

      try {
        casaMatriz.serializar("datos.bin");
        EntradaSalida.mostrarString("Primer ingreso exitoso. Ahora se debe reiniciar el sistema...");
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }

    casaMatriz.login();

  }
}
