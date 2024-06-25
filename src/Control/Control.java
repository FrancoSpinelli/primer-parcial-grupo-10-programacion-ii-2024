package Control;

import java.io.IOException;
import java.time.LocalDate;

import CasaMatriz.CasaMatriz;
import CasaMatriz.Const;
import EntradaSalida.EntradaSalida;
import Personas.Admin;
import Personas.Persona;
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
      casaMatriz.login();
    } else {
      casaMatriz.setEnviroment(Enviroment.PRODUCTION);

      try {
        CasaMatriz casaMatrizDesc = casaMatriz.deserializar("datos.bin");

        if (casaMatrizDesc == null) {
          throw new Exception("Primera ejecución del sistema");
        }

        casaMatrizDesc.login();
        
      } catch (Exception e) {
        
        EntradaSalida.mostrarString("Primera ejecución del sistema\n");
        
        EntradaSalida.mostrarString("Bienvenido al sistema de alquiler de autos\n");
        
        EntradaSalida.advertencia("Registro de administrador");
        
        CasaMatriz.crearAdmin(); 

        CasaMatriz.listarPersonas();

        try {
          casaMatriz.serializar("datos.bin", casaMatriz);
          EntradaSalida.mostrarString("Su clave generada es: " + Const.CONTRASNIA_DEFAULT);

          casaMatriz.login();
        } catch (IOException ex) {
          EntradaSalida.error("Error al serializar los datos" + ex.getMessage());
        }
      }
    }

  }
}