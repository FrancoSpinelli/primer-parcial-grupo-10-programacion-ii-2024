import java.util.ArrayList;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import Oficina.Oficina;
import Personas.Persona;

public class Main {
  public static void main(String[] args) throws Exception {

    ArrayList<Persona> personas = new ArrayList<Persona>();
    ArrayList<Auto> autos = new ArrayList<Auto>();
    ArrayList<Oficina> oficinas = new ArrayList<Oficina>();
    

    CasaMatriz casaMatriz = new CasaMatriz(personas, autos, oficinas);
    casaMatriz.login();

/*     cliente1.crear();
    cliente1.verReservas();

    System.out.println("Soy vendedor 1");
    vendedor1.getOficina().verListadoReservasPendientes();
    System.out.println("Soy vendedor 2");
    vendedor2.getOficina().verListadoReservasPendientes();

    cliente1.pagarReserva(cliente1.getReserva(1, cliente1));
    cliente1.pagarReserva(cliente1.getReserva(1, cliente1));

    cliente1.verReservas();

    vendedor1.aceptarReserva(oficina1.getReserva(1));
    vendedor2.aceptarReserva(oficina2.getReserva(1));

    cliente1.verReservas();

    cliente1.pagarReserva(cliente1.getReserva(1, cliente1));
    cliente1.pagarReserva(cliente1.getReserva(1, cliente1)); */

  }
}
