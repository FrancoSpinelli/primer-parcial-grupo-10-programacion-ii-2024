import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;

import Auto.Auto;
import CasaMatriz.CasaMatriz;
import Control.Control;
import Oficina.Oficina;
import Personas.Cliente;
import Personas.Persona;

public class Main {
  public static void main(String[] args) throws Exception {

    Control control = new Control();
     
    control.ejecutar();

  }
}

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