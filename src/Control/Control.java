package Control;

import java.io.IOException;
import java.time.LocalDate;

import CasaMatriz.CasaMatriz;
import CasaMatriz.Const;
import EntradaSalida.EntradaSalida;
import Personas.Admin;
import enums.Enviroment;

public class Control {

  public void ejecutar() {

    EntradaSalida.mostrarString("Seleccione un modo de ejecución: ");
    EntradaSalida.mostrarString("\n\t1. Modo desarrollo");
    EntradaSalida.mostrarString("\t2. Modo producción");

    int modo = EntradaSalida.leerEnteroConLimites("\nIngrese una opción: ", Const.LIMITE_INFERIOR_DEFAULT,
        Const.MAX_ENVIROMENT_OPTION);

    CasaMatriz casaMatriz = new CasaMatriz();

    if (modo == 1) {
      casaMatriz.setEnviroment(Enviroment.DEVELOPMENT);
    } else {
      casaMatriz.setEnviroment(Enviroment.PRODUCTION);

      try {
        casaMatriz = casaMatriz.deserializar("datos.bin");
      } catch (Exception e) {
        EntradaSalida.mostrarString("Primera ejecución del sistema");

        int dni = EntradaSalida.leerEnteroConLimites("Administrador, ingrese su número de DNI: ",
            Const.MIN_DNI, Const.MAX_DNI);

        String nombre = EntradaSalida.leerString("Ingrese su nombre: ");
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

        CasaMatriz.getPersonas().add(new Admin(dni, nombre, fechaNacimiento,
            telefono, email, contrasenia));

        try {
          casaMatriz.serializar("datos.bin");
          EntradaSalida.mostrarString("Primer ingreso exitoso. Ahora se debe reiniciar el sistema...");
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }

    casaMatriz.login();

  }
}
